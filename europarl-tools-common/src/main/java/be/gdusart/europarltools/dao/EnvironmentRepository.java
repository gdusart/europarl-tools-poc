package be.gdusart.europarltools.dao;

import org.springframework.data.repository.CrudRepository;

import be.gdusart.europarltools.model.Environment;

public interface EnvironmentRepository extends CrudRepository<Environment, String> {

}
