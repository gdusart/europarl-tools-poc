package be.gdusart.europarltools.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.services.EnvironmentService;

@Service
public class MockEnvironmentService implements EnvironmentService {

	@Override
	public Collection<Environment> getEnvironments() {
		List<Environment> envs = new ArrayList<>();
		envs.add(new Environment("PROD", "http://www.europarl.europa.eu"));
		envs.add(new Environment("DEV", "http://www.europarldv.ep.ec"));
		envs.add(new Environment("DEV TOMCAT7", "http://www.erplfrondv.ep.parl.union.eu"));
		envs.add(new Environment("PP", "http://www.europarlpp.ep.ec"));
		
		for (Environment env : envs) {
			env.setReverseProxyRulesets(Collections.singletonList("TEST"));
		}
		
		return envs;
	}

	@Override
	public Environment getByName(String env) {
		return ((List<Environment>)getEnvironments()).get(0);
	}

}
