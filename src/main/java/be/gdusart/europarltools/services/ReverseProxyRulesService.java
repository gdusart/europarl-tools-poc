package be.gdusart.europarltools.services;

import java.util.Collection;

import be.gdusart.europarltools.model.ReverseProxyRule;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;

public interface ReverseProxyRulesService {

	Collection<ReverseProxyRule> getRules(String... rulesetNames);

	void saveResult(ReverseProxyRuleValidationResult result);

	Collection<ReverseProxyRuleValidationResult> getAllResults();

	long createNewRPRuleGroupValidationId();

	Collection<ReverseProxyRuleValidationResult> getAllResultsForGroup(long groupId);

}
