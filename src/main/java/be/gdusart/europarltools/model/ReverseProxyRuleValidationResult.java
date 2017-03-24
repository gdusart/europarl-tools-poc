package be.gdusart.europarltools.model;

import java.util.Date;

public class ReverseProxyRuleValidationResult {

	public enum TaskStatus {
		QUEUED, IN_PROGRESS, SUCCESS, FAILED
	}

	private long groupId;
	private Long id;
	private String rulesetName;
	private String environmentName;
	private TaskStatus taskStatus = TaskStatus.QUEUED;
	
	private String url;
	private Exception exception;
	private Date timestamp;
	private String httpMessage;
	private int httpStatus;
	

	public ReverseProxyRuleValidationResult(long groupId, String rulesetName, String environmentName) {
		super();
		this.groupId = groupId;
		this.rulesetName = rulesetName;
		this.environmentName = environmentName;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
