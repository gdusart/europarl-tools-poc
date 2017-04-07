package be.gdusart.europarltools.rp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.http.HttpStatus;

@Entity
public class ReverseProxyRule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@ManyToOne
	public ReverseProxyRuleSet ruleset;

	public String baseUrl;
	public int expectedCode = HttpStatus.SC_OK;
	public String expectedRedictionUrl;
	public String expectedTitle;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReverseProxyRuleSet getRuleset() {
		return ruleset;
	}

	public void setRuleset(ReverseProxyRuleSet ruleset) {
		this.ruleset = ruleset;
	}

	public String getExpectedTitle() {
		return expectedTitle;
	}

	public void setExpectedTitle(String expectedTitle) {
		this.expectedTitle = expectedTitle;
	}

}
