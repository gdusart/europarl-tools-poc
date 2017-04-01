package be.gdusart.europarltools.scheduling.reverseproxy.workers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.gdusart.europarltools.model.ReverseProxyRule;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult.TaskStatus;
import be.gdusart.europarltools.services.BatchService;

public final class RPRuleValidationTask implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(RPRuleValidationTask.class);
	
	private String host;
	private ReverseProxyRule rule;
	private CloseableHttpClient client;
	private ReverseProxyRuleValidationResult result;
	private BatchService service;

	public RPRuleValidationTask(String host, ReverseProxyRule rule, CloseableHttpClient client, ReverseProxyRuleValidationResult result, BatchService service) {
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
		result = service.saveRPValidationResult(result);
		
		LOG.debug("Starting check for url {}", result.getUrl());
		
		HttpGet get = new HttpGet(result.getUrl());
		get.setHeader(new BasicHeader("Prama", "no-cache"));
		get.setHeader(new BasicHeader("Cache-Control", "no-cache"));
		
		try (CloseableHttpResponse response = client.execute(get)) {
			result.setHttpStatus(response.getStatusLine().getStatusCode());
			result.setHttpMessage(response.getStatusLine().getReasonPhrase());		
			
			result.setTaskStatus(result.getHttpStatus() == rule.getExpectedCode() ? TaskStatus.SUCCESS : TaskStatus.FAILED);
		} catch (Exception e) {
			LOG.error("Error validating rule {}", rule, e);
			result.setException(e);
			result.setTaskStatus(TaskStatus.FAILED);
		}
		
		service.saveRPValidationResult(result);
		LOG.debug("Result of URL {}: {}", result.getUrl(), result.getTaskStatus());
	}
}