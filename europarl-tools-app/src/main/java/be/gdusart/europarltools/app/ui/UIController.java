package be.gdusart.europarltools.app.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import be.gdusart.europarltools.model.BatchTask;
import be.gdusart.europarltools.services.BatchService;

@Controller
@RequestMapping("/ui")
public class UIController {

	@Autowired
	private BatchService batchService;
	
	@RequestMapping("rprules/results/{batchId}")
	public String getRpRulesResults(Model model, @PathVariable long batchId) {
		Iterable<BatchTask> results = batchService.getTasksForBatchId(batchId);
		model.addAttribute("results", results);
		
		return "listresults";
	}
	
}
