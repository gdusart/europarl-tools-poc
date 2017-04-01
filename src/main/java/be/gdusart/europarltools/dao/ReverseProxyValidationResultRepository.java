package be.gdusart.europarltools.dao;

import org.springframework.data.repository.CrudRepository;

import be.gdusart.europarltools.model.ReverseProxyRuleValidationResult;

public interface ReverseProxyValidationResultRepository extends CrudRepository<ReverseProxyRuleValidationResult, Long> {

}
