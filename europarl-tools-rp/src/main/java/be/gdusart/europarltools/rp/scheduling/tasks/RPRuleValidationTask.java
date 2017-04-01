package be.gdusart.europarltools.rp.scheduling.tasks;

import java.util.Date;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.gdusart.europarltools.model.BatchTask.TaskStatus;
import be.gdusart.europarltools.rp.model.ReverseProxyRule;
import be.gdusart.europarltools.rp.model.ReverseProxyRuleValidationTask;
import be.gdusart.europarltools.services.BatchService;

public final class RPRuleValidationTask implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(RPRuleValidationTask.class);
	
	private String host;
	private ReverseProxyRule rule;
	private CloseableHttpClient client;
	private ReverseProxyRuleValidationTask result;
	private BatchService batchService;

	public RPRuleValidationTask(String host, ReverseProxyRule rule, CloseableHttpClient client, ReverseProxyRuleValidationTask result, BatchService service) {
		super();
		this.host = host;
		this.rule = rule;
		this.client = client;
		this.result = result;
		this.batchService = service;
	}

	@Override
	public void run() {
		result.setUrl(host + rule.getBaseUrl());
		result.setStatus(TaskStatus.IN_PROGRESS);
		result = batchService.saveBatchTask(result);
		
		LOG.debug("Starting check for url {}", result.getUrl());
		
		HttpGet get = new HttpGet(result.getUrl());
		get.setHeader(new BasicHeader("Prama", "no-cache"));
		get.setHeader(new BasicHeader("Cache-Control", "no-cache"));
		
		try (CloseableHttpResponse response = client.execute(get)) {
			result.setHttpStatus(response.getStatusLine().getStatusCode());
			result.setHttpMessage(response.getStatusLine().getReasonPhrase());		
			
			result.setStatus(result.getHttpStatus() == rule.getExpectedCode() ? TaskStatus.SUCCESS : TaskStatus.FAILED);
		} catch (Exception e) {
			LOG.error("Error validating rule {}", rule, e);
			result.setException(e);
			result.setStatus(TaskStatus.FAILED);
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		
		result.setEndDate(new Date());
		batchService.saveBatchTask(result);
		LOG.debug("Result of URL {}: {}", result.getUrl(), result.getStatus());
	}
}