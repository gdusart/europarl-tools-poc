package be.gdusart.europarltools.model;

import java.util.List;

public class Environment {

	public Environment(String name, String serverUrl) {
		super();
		this.name = name;
		this.serverUrl = serverUrl;
	}

	private String name;
	private String serverUrl;
	private List<String> reverseProxyRulesets;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}	

	public List<String> getReverseProxyRulesets() {
		return reverseProxyRulesets;
	}

	public void setReverseProxyRulesets(List<String> reverseProxyRulesets) {
		this.reverseProxyRulesets = reverseProxyRulesets;
	}
}
