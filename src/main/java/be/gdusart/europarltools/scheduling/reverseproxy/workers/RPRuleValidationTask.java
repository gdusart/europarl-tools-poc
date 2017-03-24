package be.gdusart.europarltools.scheduling.reverseproxy.workers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.gdusart.europarltools.model.ReverseProxyRule;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult.TaskStatus;
import be.gdusart.europarltools.services.ReverseProxyRulesService;

public final class RPRuleValidationTask implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(RPRuleValidationTask.class);
	
	private String host;
	private ReverseProxyRule rule;
	private CloseableHttpClient client;
	private ReverseProxyRuleValidationResult result;
	private ReverseProxyRulesService service;

	public RPRuleValidationTask(String host, ReverseProxyRule rule, CloseableHttpClient client, ReverseProxyRuleValidationResult result, ReverseProxyRulesService service) {
		super();
		this.host = host;
		this.rule = rule;
		this.client = client;
		this.result = result;
		this.service = service;
	}

	@Override
	public void run() {
		result.setUrl(host + rule.getBaseUrl());
		result.setTaskStatus(TaskStatus.IN_PROGRESS);
		service.saveResult(result);

		try (CloseableHttpResponse response = client.execute(new HttpGet(result.getUrl()))) {
			result.setHttpStatus(response.getStatusLine().getStatusCode());
			result.setHttpMessage(response.getStatusLine().getReasonPhrase());
			result.setUrl(rule.getBaseUrl());		
			
			result.setTaskStatus(result.getHttpStatus() == rule.getExpectedCode() ? TaskStatus.SUCCESS : TaskStatus.FAILED);
		} catch (Exception e) {
			LOG.error("Error validating rule {}", rule, e);
			result.setException(e);
			result.setTaskStatus(TaskStatus.FAILED);
		}
		
		service.saveResult(result);
	}
}