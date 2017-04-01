package be.gdusart.europarltools.rp.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.rp.dao.ReverseProxyRulesetRepository;
import be.gdusart.europarltools.rp.model.ReverseProxyRuleSet;
import be.gdusart.europarltools.rp.services.ReverseProxyRulesService;

@Service
@Transactional
public class ReverseProxyRuleServiceImpl implements ReverseProxyRulesService {

	@Autowired
	private ReverseProxyRulesetRepository rulesetRepo;
	
	@Override
	public Iterable<ReverseProxyRuleSet> getRulesetsForEnvironement(Environment env) {
		return rulesetRepo.findByEnvironmentsIn(env);
	}

	@Override
	public ReverseProxyRuleSet saveRuleset(ReverseProxyRuleSet ruleset) {
		return rulesetRepo.save(ruleset);
	}
}
