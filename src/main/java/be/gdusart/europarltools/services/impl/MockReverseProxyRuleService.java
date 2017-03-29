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
	
	@Override
	public Collection<ReverseProxyRule> getRules(String... rulesetNames) {
		List<ReverseProxyRule> rules = new ArrayList<>();
						
		rules.add(new ReverseProxyRule("/common/css/box.css"));
		rules.add(new ReverseProxyRule("/common_dit/css/common_dit.css"));
		rules.add(new ReverseProxyRule("/ep_framework/css/notices.css"));
		rules.add(new ReverseProxyRule("/forms/css/forms_main.css"));
		rules.add(new ReverseProxyRule("/rwd_site/common/css/atomicdesign.css"));
		
		rules.add(new ReverseProxyRule("/100books/css/100_books.css"));
		rules.add(new ReverseProxyRule("/100books/img/photo/ckw_or.jpg"));
		rules.add(new ReverseProxyRule("/100books/js/test.txt"));
		rules.add(new ReverseProxyRule("/100books/ttf/gill-sans-mt-1361533556.ttf"));
		rules.add(new ReverseProxyRule("/committees/css/committees_revamped.css"));
		rules.add(new ReverseProxyRule("/committees/img/background/header_plenary.png"));
		rules.add(new ReverseProxyRule("/committees/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/delegations/css/delegations_revamped.css"));
		rules.add(new ReverseProxyRule("/delegations/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/delegations/img/icon/icon_rss.png"));
		rules.add(new ReverseProxyRule("/meps/css/mep_main.css"));
		rules.add(new ReverseProxyRule("/meps/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/meps/img/map/allemagne.png"));
		rules.add(new ReverseProxyRule("/plenary/css/plenary_sed_main.css"));
		rules.add(new ReverseProxyRule("/plenary/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/plenary/img/banner/banner_next_sitting_Strasbourg.png"));
		rules.add(new ReverseProxyRule("/rss/css/common_rss.css"));
		rules.add(new ReverseProxyRule("/rss/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/rss/img/ep_syndicationrss_screen2.jpg"));
		rules.add(new ReverseProxyRule("/rss/css/jquery.nimble.loader.css"));
		rules.add(new ReverseProxyRule("/thinktank/css/thinktank_main.css"));
		rules.add(new ReverseProxyRule("/thinktank/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/thinktank/img/background/gradient_white.png"));
		rules.add(new ReverseProxyRule("/thinktank/font/MyriadPro-Regular.otf"));	
		
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
