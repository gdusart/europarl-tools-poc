package be.gdusart.europarltools.rp.dao;

import org.springframework.data.repository.CrudRepository;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.rp.model.ReverseProxyRuleSet;

public interface ReverseProxyRulesetRepository extends CrudRepository<ReverseProxyRuleSet, Long> {

	Iterable<ReverseProxyRuleSet> findByEnvironmentsIn(Environment... env);
}
