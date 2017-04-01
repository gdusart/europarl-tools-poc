package be.gdusart.europarltools.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.services.BatchService;

@Controller
@RequestMapping("/ui")
public class UIController {

	@Autowired
	private BatchService ruleService;
	
	@RequestMapping("rprules/results/{batchId}")
	public String getRpRulesResults(Model model, @PathVariable String batchId) {
		Iterable<ReverseProxyRuleValidationResult> results = ruleService.getAllResultsForBatch(Long.valueOf(batchId));
		model.addAttribute("results", results);
		
		return "listresults";
	}
	
}
