package be.gdusart.europarltools.dao;

import org.springframework.data.repository.CrudRepository;

import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;

public interface ReverseProxyRuleValidationResultRepository
		extends CrudRepository<ReverseProxyRuleValidationResult, Long> {

	Iterable<ReverseProxyRuleValidationResult> findByBatchId(long batchId);

}
