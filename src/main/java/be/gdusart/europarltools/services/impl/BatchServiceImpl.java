package be.gdusart.europarltools.services.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gdusart.europarltools.dao.ReverseProxyRuleValidationResultRepository;
import be.gdusart.europarltools.dao.RpRulesValidationBatchRepository;
import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.model.RpRulesValidationBatch;
import be.gdusart.europarltools.services.BatchService;

@Service
@Transactional
public class BatchServiceImpl implements BatchService {

	@Autowired
	private RpRulesValidationBatchRepository batchRepo;
	
	@Autowired
	private ReverseProxyRuleValidationResultRepository resultRepo;
		
	@Override
	public RpRulesValidationBatch createNewRpRulesValidationBatch() {
		RpRulesValidationBatch batch = new RpRulesValidationBatch();
		batch.setStartTime(new Date());
		return batchRepo.save(batch);
	}

	@Override
	public ReverseProxyRuleValidationResult saveRPValidationResult(ReverseProxyRuleValidationResult result) {
		return resultRepo.save(result);
	}

	@Override
	public Iterable<ReverseProxyRuleValidationResult> getAllResultsForBatch(Long batchId) {
		return resultRepo.findByBatchId(batchId);
	}

	@Override
	public Iterable<RpRulesValidationBatch> getAllBatches() {
		return batchRepo.findAll();
	}

}
