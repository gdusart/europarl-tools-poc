package be.gdusart.europarltools.ui;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.services.ReverseProxyRulesService;

@Controller
@RequestMapping("/ui")
public class UIController {

	@Autowired
	private ReverseProxyRulesService ruleService;
	
	@RequestMapping("rprules/results/{groupId}")
	public String getRpRulesResults(Model model, @PathVariable long groupId) {
		Collection<ReverseProxyRuleValidationResult> results = ruleService.getAllResultsForGroup(groupId);
		model.addAttribute("results", results);
		
		return "listresults";
	}
	
}
