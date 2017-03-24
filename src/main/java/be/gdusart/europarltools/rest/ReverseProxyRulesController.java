package be.gdusart.europarltools.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.model.ReverseProxyRule;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.services.EnvironmentService;
import be.gdusart.europarltools.services.ReverseProxyRulesService;

@RestController
@RequestMapping("/rprules")
public class ReverseProxyRulesController {

	@Autowired
	private ReverseProxyRulesService rpRuleService;
	
	@Autowired
	private EnvironmentService envService;
	
	@RequestMapping("/list/{env}")
	public Collection<ReverseProxyRule> list(@PathVariable String envName) {
		Environment env = envService.getByName(envName);
		
		return rpRuleService.getRules(env.getReverseProxyRulesets().toArray(new String[0]));
	}
	
	@RequestMapping("/results")
	public Collection<ReverseProxyRuleValidationResult> results() {
		return rpRuleService.getAllResults();
	}
	
	@RequestMapping("/results/{groupId}")
	public Collection<ReverseProxyRuleValidationResult> results(@PathVariable long groupId) {
		return rpRuleService.getAllResultsForGroup(groupId);
	}
	
	
}
