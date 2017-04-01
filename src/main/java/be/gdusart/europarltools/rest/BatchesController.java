package be.gdusart.europarltools.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.gdusart.europarltools.model.RpRulesValidationBatch;
import be.gdusart.europarltools.services.BatchService;

@RestController
@RequestMapping("/batches")
public class BatchesController {

	@Autowired
	private BatchService batchService;
	
	@RequestMapping({"","/"})
	public Iterable<RpRulesValidationBatch> list() {
		return batchService.getAllBatches();
	}
}
