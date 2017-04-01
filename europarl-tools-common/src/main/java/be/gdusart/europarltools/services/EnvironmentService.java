package be.gdusart.europarltools.services;

import be.gdusart.europarltools.model.Environment;

public interface EnvironmentService {

	Iterable<Environment> getEnvironments();

	Environment getByName(String env);

	Environment save(Environment prodEnv);

	Environment getEnvironment(long environmentId);


}
