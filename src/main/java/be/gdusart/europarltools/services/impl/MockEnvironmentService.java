package be.gdusart.europarltools.services.impl;

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
		Environment env = new Environment("PROD", "http://www.europarl.europa.eu");
		env.setReverseProxyRulesets(Collections.singletonList("TEST"));
		return Collections.singletonList(env);
	}

	@Override
	public Environment getByName(String env) {
		return ((List<Environment>)getEnvironments()).get(0);
	}

}
