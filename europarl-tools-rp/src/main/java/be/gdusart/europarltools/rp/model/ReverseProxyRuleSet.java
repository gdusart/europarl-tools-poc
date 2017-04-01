package be.gdusart.europarltools.rp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import be.gdusart.europarltools.model.Environment;

@Entity
public class ReverseProxyRuleSet {

	@Id
	@Column(name = "RULESET_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String ruleSetName;

	@ManyToMany
	private List<Environment> environments = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "RULESET_ID", referencedColumnName = "RULESET_ID")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<Environment> environments) {
		this.environments = environments;
	}
}
