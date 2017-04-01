package be.gdusart.europarltools.rp.model;

import javax.persistence.Entity;

import be.gdusart.europarltools.model.Batch;
import be.gdusart.europarltools.model.BatchTask;

@Entity
public class ReverseProxyRuleValidationTask extends BatchTask {

	private String rulesetName;
	private String environmentName;

	private String url;
	private Exception exception;
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

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
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

	public String getUrl() {
		return url;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
