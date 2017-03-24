package be.gdusart.europarltools.model;

import java.util.Collection;

public class ReverseProxyRuleSet {

	public String ruleSetName;
	public Collection<ReverseProxyRule> rules;

	public ReverseProxyRuleSet(String ruleSetName, Collection<ReverseProxyRule> rules) {
		super();
		this.ruleSetName = ruleSetName;
		this.rules = rules;
	}

	public String getRuleSetName() {
		return ruleSetName;
	}

	public void setRuleSetName(String ruleSetName) {
		this.ruleSetName = ruleSetName;
	}

	public Collection<ReverseProxyRule> getRules() {
		return rules;
	}

	public void setRules(Collection<ReverseProxyRule> rules) {
		this.rules = rules;
	}
}
