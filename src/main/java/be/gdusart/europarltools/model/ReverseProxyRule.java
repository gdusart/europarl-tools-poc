package be.gdusart.europarltools.model;

import org.springframework.http.HttpStatus;

public class ReverseProxyRule {

	public String baseUrl;
	public int expectedCode = HttpStatus.OK.value();
	public String expectedRedictionUrl;

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
