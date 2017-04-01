package be.gdusart.europarltools.rp.dao;

import org.springframework.data.repository.CrudRepository;

import be.gdusart.europarltools.rp.model.ReverseProxyRuleValidationTask;

public interface ReverseProxyRuleValidationResultRepository
		extends CrudRepository<ReverseProxyRuleValidationTask, Long> {

	Iterable<ReverseProxyRuleValidationTask> findByBatchId(long batchId);

}
