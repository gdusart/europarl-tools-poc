package be.gdusart.europarltools.rp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;

import be.gdusart.europarltools.model.Batch;
import be.gdusart.europarltools.model.BatchTask;

@Entity
public class ReverseProxyRuleValidationTask extends BatchTask {

	private String rulesetName;
	private String environmentName;

	private String baseUrl;
	private String destinationUrl;
	
	@Column(length=1000)
	private String exception;
	private String httpMessage;
	private int httpStatus;

	public ReverseProxyRuleValidationTask() {
		super();
	}

	public ReverseProxyRuleValidationTask(Batch batch, String rulesetName, String environmentName) {
		super(batch);
		this.rulesetName = rulesetName;
		this.environmentName = environmentName;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception != null ? StringUtils.abbreviate(exception, 1000) : null;
	}

	public String getHttpMessage() {
		return httpMessage;
	}

	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}

	public String getRulesetName() {
		return rulesetName;
	}

	public void setRulesetName(String rulesetName) {
		this.rulesetName = rulesetName;
	}

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}

	public String getDestinationUrl() {
		return destinationUrl;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setDestinationUrl(String url) {
		this.destinationUrl = url;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public String getResultInfo() {
		
		List<Object> fields = new ArrayList<>();
		fields.add(baseUrl != null ? baseUrl  + (destinationUrl != null ? (" -> " + destinationUrl) : StringUtils.EMPTY) : StringUtils.EMPTY);
		fields.add(httpMessage);
		fields.add(httpStatus);
		fields.add(exception);		
		fields.removeAll(Collections.singletonList(null));
		
		return StringUtils.join(fields, " - ");
	}

}
