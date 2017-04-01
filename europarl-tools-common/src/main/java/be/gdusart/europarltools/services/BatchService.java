package be.gdusart.europarltools.services;

import be.gdusart.europarltools.model.Batch;
import be.gdusart.europarltools.model.BatchTask;

public interface BatchService {

	Iterable<Batch> getAllBatches();

	Batch saveBatch(Batch batch);

	Batch createNewBatch();

	<T extends BatchTask> T saveBatchTask(T task);
	
	<T extends BatchTask> Iterable<T> getTasksForBatchId(long batchId);

}
