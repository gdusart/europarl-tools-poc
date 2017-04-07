package be.gdusart.europarltools.rp.utils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.gdusart.europarltools.rp.model.ReverseProxyRule;

public class RuleTitleAppender {
	
	private static final Logger LOG = LoggerFactory.getLogger(RuleTitleAppender.class);

	private static final Pattern TITLE_PATTERN = Pattern.compile("<title>(.*?)</title>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

	public void addExpectedPageTitleToRules(Collection<ReverseProxyRule> rules, String hostUrl) {
		CloseableHttpClient client = HttpClientBuilder.create().setMaxConnPerRoute(5).setMaxConnTotal(10)
				.useSystemProperties().build();

		try {
			for (ReverseProxyRule rule : rules) {
				rule.setExpectedTitle(loadTitleForUrl(client, hostUrl + rule.getBaseUrl()));
				
				if (StringUtils.isEmpty(rule.getExpectedTitle())) {
					LOG.error("could not load tile for page {}", rule.getBaseUrl());
				}
			}
		} finally {
			IOUtils.closeQuietly(client);
		}
	}

	private String loadTitleForUrl(CloseableHttpClient client, String url) {
		CloseableHttpResponse response = null;

		try {
			response = client.execute(new HttpGet(url));
			String html = EntityUtils.toString(response.getEntity());
			return getTitleFromHtml(html);
		} catch (Exception e) {
			return null;
		} finally {
			IOUtils.closeQuietly(response);
		}

	}

	private String getTitleFromHtml(String html) {
		//TODO: accept new lines, accept not case sensitive
		Matcher matcher = TITLE_PATTERN.matcher(html);
		if (matcher.find()) {
			return matcher.group(1);
		}
		
		return null;
	}
}
