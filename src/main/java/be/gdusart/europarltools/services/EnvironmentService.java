package be.gdusart.europarltools.services;

import java.util.Collection;

import be.gdusart.europarltools.model.Environment;

public interface EnvironmentService {

	Collection<Environment> getEnvironments();

	Environment getByName(String env);

}
