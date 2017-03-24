package be.gdusart.europarltools.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.stereotype.Service;

import be.gdusart.europarltools.model.ReverseProxyRule;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.services.ReverseProxyRulesService;

@Service
public class MockReverseProxyRuleService implements ReverseProxyRulesService {
	
	private static final AtomicLong RESULT_GROUP_SEQ_GENERATOR = new AtomicLong();
	private static final AtomicLong RESULT_SEQ_GENERATOR = new AtomicLong();
	private static final Map<Long, ReverseProxyRuleValidationResult> RESULTS_REPO = Collections.synchronizedMap(new HashMap<>());
	
	public Collection<ReverseProxyRule> getRules(String... rulesetNames) {
		List<ReverseProxyRule> rules = new ArrayList<>();
		
		rules.add(new ReverseProxyRule("/meps/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/meps/css/mep_main.css"));
		rules.add(new ReverseProxyRule("/meps/css/notexist.css"));
		
		return rules;
	}

	@Override
	public void saveResult(ReverseProxyRuleValidationResult result) {
		if (result.getId() == null) {
			result.setId(RESULT_SEQ_GENERATOR.incrementAndGet());
		}
		
		result.setTimestamp(new Date());
		RESULTS_REPO.put(result.getId(), result);		
	}

	@Override
	public Collection<ReverseProxyRuleValidationResult> getAllResults() {
		return RESULTS_REPO.values();
	}

	@Override
	public long createNewRPRuleGroupValidationId() {
		return RESULT_GROUP_SEQ_GENERATOR.incrementAndGet();
	}

	@Override
	public Collection<ReverseProxyRuleValidationResult> getAllResultsForGroup(final long groupId) {
		List<ReverseProxyRuleValidationResult> filtered = new ArrayList<>(getAllResults());
		CollectionUtils.filter(filtered, new Predicate<ReverseProxyRuleValidationResult>() {
			@Override
			public boolean evaluate(ReverseProxyRuleValidationResult result) {
				return groupId == result.getGroupId();
			}			
		});
		
		return filtered;
	}
 
}
