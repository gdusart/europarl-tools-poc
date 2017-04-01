package be.gdusart.europarltools.scheduling.reverseproxy.scheduler;

import java.util.Collection;

import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.model.ReverseProxyRule;
import be.gdusart.europarltools.model.ReverseProxyRuleSet;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.model.RpRulesValidationBatch;
import be.gdusart.europarltools.scheduling.reverseproxy.RPSchedulerHttpClientConfiguration;
import be.gdusart.europarltools.scheduling.reverseproxy.workers.RPRuleValidationTask;
import be.gdusart.europarltools.services.BatchService;
import be.gdusart.europarltools.services.EnvironmentService;

@Component
public class RpRulesValidationScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(RPSchedulerHttpClientConfiguration.class);
	
	@Autowired
	@Qualifier(RPSchedulerHttpClientConfiguration.HTTP_CLIENT_QUALIFIER)
	private CloseableHttpClient client;

	@Autowired
	private EnvironmentService envService;
	
	@Autowired
	private BatchService batchService;

	@Autowired
	@Qualifier(RPSchedulerHttpClientConfiguration.EXECUTOR_QUALIFIER)
	private TaskExecutor taskExecutor;
	
	@Scheduled(fixedDelay = 60 * 1000 * 10, initialDelay=3000)
	public void validateRPules() {
		LOG.info("Starting RP rules validation task");
		Iterable<Environment> environments = envService.getEnvironmentsWithRules();
		for (Environment env : environments) {
			LOG.info("Starting RP rules validation for environment {}...", env.getName());
			RpRulesValidationBatch batch = batchService.createNewRpRulesValidationBatch();
			
			LOG.debug("Getting rulesets for environnement {}", env.getName());
			
			for (ReverseProxyRuleSet ruleSet : env.getReverseProxyRulesets()) {
				LOG.info("Launching tasks for ruleset {}, env {}", ruleSet.getRuleSetName(), env.getName());
				
				Collection<ReverseProxyRule> rules = ruleSet.getRules();
				LOG.debug("Loaded {} rule(s) for ruleset {}, env {}", rules.size(), ruleSet.getRuleSetName(), env.getName());
				
				for (ReverseProxyRule rule : rules) {
					LOG.info("Launching tasks for rule {}", rule.getBaseUrl());
					ReverseProxyRuleValidationResult result = new ReverseProxyRuleValidationResult(batch.getId(), ruleSet.getRuleSetName(), env.getName());
					result = batchService.saveRPValidationResult(result);
					
					taskExecutor.execute(new RPRuleValidationTask(env.getServerUrl(), rule, client, result, batchService));
				}
			}			
		}
	}

	
}
