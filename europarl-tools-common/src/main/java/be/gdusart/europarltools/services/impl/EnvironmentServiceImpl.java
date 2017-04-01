package be.gdusart.europarltools.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gdusart.europarltools.dao.EnvironmentRepository;
import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.services.EnvironmentService;

@Service
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {
	
	@Autowired
	private EnvironmentRepository repository;

	@Override
	public Iterable<Environment> getEnvironments() {
		return repository.findAll();
	}

	@Override
	public Environment getByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Environment save(Environment prodEnv) {
		return repository.save(prodEnv);		
	}

	@Override
	public Environment getEnvironment(long environmentId) {
		return repository.findOne(environmentId);
	}
}
