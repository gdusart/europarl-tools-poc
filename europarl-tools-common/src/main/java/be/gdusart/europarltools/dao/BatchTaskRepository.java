package be.gdusart.europarltools.dao;

import org.springframework.data.repository.CrudRepository;

import be.gdusart.europarltools.model.BatchTask;

public interface BatchTaskRepository extends CrudRepository<BatchTask, Long> {

	
	<T extends BatchTask> Iterable<T> findByBatchId(long batchId);

}
