package be.gdusart.europarltools.rp.services;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.rp.model.ReverseProxyRuleSet;

public interface ReverseProxyRulesService {

	Iterable<ReverseProxyRuleSet> getRulesetsForEnvironement(Environment env);

	ReverseProxyRuleSet saveRuleset(ReverseProxyRuleSet tomcat6);

}
