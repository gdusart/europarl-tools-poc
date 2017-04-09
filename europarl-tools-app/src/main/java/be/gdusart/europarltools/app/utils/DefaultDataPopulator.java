package be.gdusart.europarltools.app.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.digester.RuleSet;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.spi.Function;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.rp.model.ReverseProxyRule;
import be.gdusart.europarltools.rp.model.ReverseProxyRuleSet;
import be.gdusart.europarltools.rp.services.ReverseProxyRulesService;
import be.gdusart.europarltools.services.EnvironmentService;

@Component
public class DefaultDataPopulator implements CommandLineRunner {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(DefaultDataPopulator.class);

	@Autowired
	private EnvironmentService envService;

	@Autowired
	private ReverseProxyRulesService rulesService;

	@Override
	public void run(String... args) throws Exception {

		LOG.info("initializing data...");

		Environment prodEnv = new Environment("PROD", "http://www.europarl.europa.eu");
		envService.save(prodEnv);

		ReverseProxyRuleSet tomcat6 = new ReverseProxyRuleSet();
		tomcat6.setRuleSetName("TOMCAT6");
		Collection<ReverseProxyRule> rules = tomcat6.getRules();
		rules.add(new ReverseProxyRule("/common/css/box.css"));
		rules.add(new ReverseProxyRule("/common_dit/css/common_dit.css"));
		rules.add(new ReverseProxyRule("/ep_framework/css/notices.css"));
		rules.add(new ReverseProxyRule("/forms/css/forms_main.css"));
//		rules.add(new ReverseProxyRule("/rwd_site/common/css/atomicdesign.css"));
		tomcat6 = rulesService.saveRuleset(tomcat6);

		ReverseProxyRuleSet planets = new ReverseProxyRuleSet();
		planets.setRuleSetName("planets");
		rules = planets.getRules();
//		rules.add(new ReverseProxyRule("/100books/css/100_books.css")); // Responsive
//		rules.add(new ReverseProxyRule("/100books/img/photo/ckw_or.jpg"));
//		rules.add(new ReverseProxyRule("/100books/js/test.txt"));
//		rules.add(new ReverseProxyRule("/100books/ttf/gill-sans-mt-1361533556.ttf"));
		rules.add(new ReverseProxyRule("/100_books/css/100_books_main.css"));
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
//		rules.add(new ReverseProxyRule("/rss/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/rss/img/ep_syndicationrss_screen2.jpg"));
		rules.add(new ReverseProxyRule("/rss/css/jquery.nimble.loader.css"));
		rules.add(new ReverseProxyRule("/thinktank/css/thinktank_main.css"));
		rules.add(new ReverseProxyRule("/thinktank/js/hotfix.js"));
		rules.add(new ReverseProxyRule("/thinktank/img/background/gradient_white.png"));
//		rules.add(new ReverseProxyRule("/thinktank/font/MyriadPro-Regular.otf"));
		planets = rulesService.saveRuleset(planets);

		planets.getEnvironments().add(prodEnv);
		tomcat6.getEnvironments().add(prodEnv);

		ReverseProxyRuleSet rpRulesRuleset = loadRpRules("Reverse proxy");
		rpRulesRuleset.getEnvironments().add(prodEnv);

		rulesService.saveRuleset(rpRulesRuleset);
		rulesService.saveRuleset(planets);
		rulesService.saveRuleset(tomcat6);

	}

	private ReverseProxyRuleSet loadRpRules(String rulesetName) {
		try(InputStream stream = new ClassPathResource("pe_dzm_rules_withtitle.json").getInputStream()) {
			List<ReverseProxyRule> rules = new ObjectMapper().readValue(stream,
					new TypeReference<List<ReverseProxyRule>>() {
					});			
		
			return new ReverseProxyRuleSet(rulesetName, rules);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
