package be.gdusart.europarltools.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ReverseProxyRuleSet {

	@Id
	@Column(name="RULESET_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	@Column(unique=true)
	public String ruleSetName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="RULESET_ID", referencedColumnName="RULESET_ID")
	public Collection<ReverseProxyRule> rules = new ArrayList<>();

	public ReverseProxyRuleSet() {
		super();
	}
	
	public ReverseProxyRuleSet(String ruleSetName, Collection<ReverseProxyRule> rules) {
		super();
		this.ruleSetName = ruleSetName;
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
