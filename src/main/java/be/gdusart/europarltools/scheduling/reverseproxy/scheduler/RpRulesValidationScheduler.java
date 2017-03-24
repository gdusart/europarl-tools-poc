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
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.scheduling.reverseproxy.RPSchedulerHttpClientConfiguration;
import be.gdusart.europarltools.scheduling.reverseproxy.workers.RPRuleValidationTask;
import be.gdusart.europarltools.services.EnvironmentService;
import be.gdusart.europarltools.services.ReverseProxyRulesService;

@Component
public class RpRulesValidationScheduler {

	private static final Logger LOG = LoggerFactory.getLogger(RPSchedulerHttpClientConfiguration.class);
	
	@Autowired
	@Qualifier(RPSchedulerHttpClientConfiguration.HTTP_CLIENT_QUALIFIER)
	private CloseableHttpClient client;

	@Autowired
	private EnvironmentService envService;

	@Autowired
	private ReverseProxyRulesService rulesService;

	@Autowired
	@Qualifier(RPSchedulerHttpClientConfiguration.EXECUTOR_QUALIFIER)
	private TaskExecutor taskExecutor;
	
	@Scheduled(fixedDelay = 60 * 1000 * 10)
	public void validateRPules() {
		for (Environment env : envService.getEnvironments()) {
			long groupId = rulesService.createNewRPRuleGroupValidationId();
			
			LOG.debug("Getting rulesets for environnement {}", env.getName());
			
			for (String rulesetName : env.getReverseProxyRulesets()) {				
				Collection<ReverseProxyRule> rules = rulesService.getRules(rulesetName);
				LOG.debug("Loaded {} rule(s) for ruleset {}, env {}", rules.size(), rulesetName, env.getName());
				
				for (ReverseProxyRule rule : rules) {
					ReverseProxyRuleValidationResult result = new ReverseProxyRuleValidationResult(groupId, rulesetName, env.getName());
					rulesService.saveResult(result);
					
					taskExecutor.execute(new RPRuleValidationTask(env.getServerUrl(), rule, client, result, rulesService));
				}
			}			
		}
	}

	
}
