package be.gdusart.europarltools.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.services.BatchService;

@RestController
@RequestMapping("/rprules")
public class ReverseProxyRulesController {

	@Autowired
	private BatchService batchService;

//	@RequestMapping("/list/{env}")
//	public Collection<ReverseProxyRule> list(@PathVariable String envName) {
//		Environment env = envService.getByName(envName);
//		
//		return rpRuleService.getRules(env.getReverseProxyRulesets().toArray(new String[0]));
//	}
	
	@RequestMapping("/results/{batchId}")
	public Iterable<ReverseProxyRuleValidationResult> results(@PathVariable long batchId) {
		return batchService.getAllResultsForBatch(batchId);
	}
	
	
}
