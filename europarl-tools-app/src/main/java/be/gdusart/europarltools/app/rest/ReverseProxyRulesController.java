package be.gdusart.europarltools.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.gdusart.europarltools.model.BatchTask;
import be.gdusart.europarltools.rp.model.ReverseProxyRuleSet;
import be.gdusart.europarltools.rp.services.ReverseProxyRulesService;
import be.gdusart.europarltools.services.BatchService;
import be.gdusart.europarltools.services.EnvironmentService;

@RestController
@RequestMapping("/rprules")
public class ReverseProxyRulesController {

	@Autowired
	private BatchService batchService;

	@Autowired
	private ReverseProxyRulesService rulesService;
	
	@Autowired
	private EnvironmentService envService;
	
	
	@RequestMapping("/list/{environmentId}")
	public Iterable<ReverseProxyRuleSet> list(@PathVariable long environmentId) {
		return rulesService.getRulesetsForEnvironement(envService.getEnvironment(environmentId));
	}
	
	@RequestMapping("/results/{batchId}")
	public Iterable<BatchTask> results(@PathVariable long batchId) {
		return batchService.getTasksForBatchId(batchId);
	}
	
	
}
