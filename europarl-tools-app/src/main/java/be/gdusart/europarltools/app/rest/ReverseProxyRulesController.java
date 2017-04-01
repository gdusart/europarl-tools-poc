package be.gdusart.europarltools.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.gdusart.europarltools.rp.model.ReverseProxyRuleSet;
import be.gdusart.europarltools.rp.services.ReverseProxyRulesService;
import be.gdusart.europarltools.services.EnvironmentService;

@RestController
@RequestMapping("/rprules")
public class ReverseProxyRulesController {

	@Autowired
	private ReverseProxyRulesService rulesService;
	
	@Autowired
	private EnvironmentService envService;
	
	
	@RequestMapping("/{environmentId}")
	public Iterable<ReverseProxyRuleSet> list(@PathVariable long environmentId) {
		return rulesService.getRulesetsForEnvironement(envService.getEnvironment(environmentId));
	}
		
	
}
