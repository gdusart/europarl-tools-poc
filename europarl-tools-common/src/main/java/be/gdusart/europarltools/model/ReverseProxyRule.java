package be.gdusart.europarltools.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;

@Entity
public class ReverseProxyRule {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
		
	@Column(name="RULESET_ID")
	public Long ruleSetId;
	
	public Long getRuleSetId() {
		return ruleSetId;
	}

	public void setRuleSetId(Long ruleSetId) {
		this.ruleSetId = ruleSetId;
	}

	public String baseUrl;
	public int expectedCode = HttpStatus.OK.value();
	public String expectedRedictionUrl;

	public ReverseProxyRule() {
		super();
	}
	
	public ReverseProxyRule(String baseUrl) {
		super();
		this.baseUrl = baseUrl;
	}

	public ReverseProxyRule(String baseUrl, int expectedCode, String expectedRedictionUrl) {
		super();
		this.baseUrl = baseUrl;
		this.expectedCode = expectedCode;
		this.expectedRedictionUrl = expectedRedictionUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public int getExpectedCode() {
		return expectedCode;
	}

	public void setExpectedCode(int expectedCode) {
		this.expectedCode = expectedCode;
	}

	public String getExpectedRedictionUrl() {
		return expectedRedictionUrl;
	}

	public void setExpectedRedictionUrl(String expectedRedictionUrl) {
		this.expectedRedictionUrl = expectedRedictionUrl;
	}
}
