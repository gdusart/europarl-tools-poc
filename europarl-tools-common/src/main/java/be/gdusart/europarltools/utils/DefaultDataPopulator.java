package be.gdusart.europarltools.utils;

import java.util.Collection;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.model.ReverseProxyRule;
import be.gdusart.europarltools.model.ReverseProxyRuleSet;
import be.gdusart.europarltools.services.EnvironmentService;

@Component
public class DefaultDataPopulator implements CommandLineRunner {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(DefaultDataPopulator.class);
	
	@Autowired
	private EnvironmentService envService;

	@Override
	public void run(String... args) throws Exception {

		LOG.info("initializing data...");
		
		Environment prodEnv = new Environment("PROD", "http://www.europarl.europa.eu");
		

		ReverseProxyRuleSet tomcat6 = new ReverseProxyRuleSet();
		tomcat6.setRuleSetName("TOMCAT6");
		Collection<ReverseProxyRule> rules = tomcat6.getRules();
		rules.add(new ReverseProxyRule("/common/css/box.css"));
		rules.add(new ReverseProxyRule("/common_dit/css/common_dit.css"));
		rules.add(new ReverseProxyRule("/ep_framework/css/notices.css"));
		rules.add(new ReverseProxyRule("/forms/css/forms_main.css"));
		rules.add(new ReverseProxyRule("/rwd_site/common/css/atomicdesign.css"));
		

		ReverseProxyRuleSet planets = new ReverseProxyRuleSet();
		planets.setRuleSetName("planets");
		rules = planets.getRules();
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
		
		
		ReverseProxyRuleSet duplicate = new ReverseProxyRuleSet();
		duplicate.setRuleSetName("Duplicate entry");
		rules = duplicate.getRules();
		rules.add(new ReverseProxyRule("/common/css/box.css"));
		rules.add(new ReverseProxyRule("/common/css/box.css"));
		
		prodEnv.getReverseProxyRulesets().add(tomcat6);
		prodEnv.getReverseProxyRulesets().add(planets);
		prodEnv.getReverseProxyRulesets().add(duplicate);
				
		Environment env = envService.save(prodEnv);
		LOG.info("Create env : {}", new ObjectMapper().writeValueAsString(env));

	}

}
