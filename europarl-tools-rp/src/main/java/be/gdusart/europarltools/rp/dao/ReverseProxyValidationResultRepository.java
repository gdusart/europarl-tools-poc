package be.gdusart.europarltools.rp.dao;

import org.springframework.data.repository.CrudRepository;

import be.gdusart.europarltools.rp.model.ReverseProxyRuleValidationTask;

public interface ReverseProxyValidationResultRepository extends CrudRepository<ReverseProxyRuleValidationTask, Long> {

}
