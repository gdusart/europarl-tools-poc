package be.gdusart.europarltools.rp.scheduling.tasks;

import static org.mockito.Matchers.matches;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.gdusart.europarltools.model.BatchTask.TaskStatus;
import be.gdusart.europarltools.rp.model.ReverseProxyRule;
import be.gdusart.europarltools.rp.model.ReverseProxyRuleValidationTask;
import be.gdusart.europarltools.services.BatchService;

public final class RPRuleValidationTask implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(RPRuleValidationTask.class);
	private static final Pattern TITLE_PATTERN = Pattern.compile("<title>(.*?)</title>",
			Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	private String host;
	private ReverseProxyRule rule;
	private CloseableHttpClient client;
	private ReverseProxyRuleValidationTask result;
	private BatchService batchService;

	public RPRuleValidationTask(String host, ReverseProxyRule rule, CloseableHttpClient client,
			ReverseProxyRuleValidationTask result, BatchService service) {
		super();
		this.host = host;
		this.rule = rule;
		this.client = client;
		this.result = result;
		this.batchService = service;
	}

	@Override
	public void run() {
		result.setBaseUrl(host + rule.getBaseUrl());

		if (!StringUtils.isEmpty(rule.getExpectedRedictionUrl())) {
			result.setDestinationUrl(host + rule.getExpectedRedictionUrl());
		}

		result.setStatus(TaskStatus.IN_PROGRESS);
		result = batchService.saveBatchTask(result);

		LOG.debug("Starting check for url {}", result.getBaseUrl());

		HttpGet get = new HttpGet(result.getBaseUrl());
		get.setHeader(new BasicHeader("Prama", "no-cache"));
		get.setHeader(new BasicHeader("Cache-Control", "no-cache"));

		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			result.setHttpStatus(response.getStatusLine().getStatusCode());
			result.setHttpMessage(response.getStatusLine().getReasonPhrase());

			computeStatus(rule, result, response);

		} catch (Exception e) {
			LOG.error("Error validating rule {}", rule, e);
			result.setException(ExceptionUtils.getStackTrace(e));
			result.setStatus(TaskStatus.FAILED);
		} finally {
			IOUtils.closeQuietly(response);
		}

		result.setEndDate(new Date());
		result = batchService.saveBatchTask(result);
		LOG.info("Result of URL {}: {}", result.getBaseUrl(), result.getStatus());
	}

	private void computeStatus(ReverseProxyRule rule, ReverseProxyRuleValidationTask result,
			CloseableHttpResponse response) {
		result.setStatus(TaskStatus.SUCCESS);
		
		try {
			if (result.getHttpStatus() != rule.getExpectedCode()) {
				result.setStatus(TaskStatus.FAILED);
				result.setException("HTTP Status " + result.getHttpStatus() + " did not match expected one (" + rule.getExpectedCode() +")");
			}

			if (result.getStatus() == TaskStatus.SUCCESS && !StringUtils.isEmpty(rule.getExpectedTitle())) {
				String pageContent = EntityUtils.toString(response.getEntity());
				Matcher matcher = TITLE_PATTERN.matcher(pageContent);
				if (matcher.find()) {
					String foundTitle = matcher.group(1);
					if (!StringUtils.equals(rule.getExpectedTitle(), foundTitle)) {
						result.setStatus(TaskStatus.FAILED);
						result.setException("Title did not match. Expected " + StringUtils.abbreviate(rule.getExpectedTitle(), 20) +  
								"... but was " + StringUtils.abbreviate(foundTitle, 20) + "...");
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error computing task status", e);
			result.setStatus(TaskStatus.FAILED);
			result.setException(e.getMessage());
		}
	}
}