package be.gdusart.europarltools.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Environment {

	public Environment() {
		super();
	}
	
	public Environment(String name, String serverUrl) {
		super();
		this.name = name;
		this.serverUrl = serverUrl;
	}

	@Id
	private String name;
	private String serverUrl;

	@OneToMany(cascade={CascadeType.ALL})
	private List<ReverseProxyRuleSet> reverseProxyRulesets = new ArrayList<>();

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

	public List<ReverseProxyRuleSet> getReverseProxyRulesets() {
		return reverseProxyRulesets;
	}

	public void setReverseProxyRulesets(List<ReverseProxyRuleSet> reverseProxyRulesets) {
		this.reverseProxyRulesets = reverseProxyRulesets;
	}

}
