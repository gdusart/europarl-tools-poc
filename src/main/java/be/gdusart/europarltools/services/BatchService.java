package be.gdusart.europarltools.services;

import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;
import be.gdusart.europarltools.model.RpRulesValidationBatch;

public interface BatchService {

	RpRulesValidationBatch createNewRpRulesValidationBatch();

	ReverseProxyRuleValidationResult saveRPValidationResult(ReverseProxyRuleValidationResult result);

	Iterable<ReverseProxyRuleValidationResult> getAllResultsForBatch(Long valueOf);

	Iterable<RpRulesValidationBatch> getAllBatches();

	RpRulesValidationBatch saveBatch(RpRulesValidationBatch batch);

}
