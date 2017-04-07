package be.gdusart.europarltools.services.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.gdusart.europarltools.dao.BatchRepository;
import be.gdusart.europarltools.dao.BatchTaskRepository;
import be.gdusart.europarltools.model.Batch;
import be.gdusart.europarltools.model.BatchTask;
import be.gdusart.europarltools.services.BatchService;

@Service
@Transactional
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepo;
	
	@Autowired
	private BatchTaskRepository taskRepo;

		
	@Override
	public Batch createNewBatch() {
		Batch batch = new Batch();
		batch.setStartTime(new Date());
		return batchRepo.save(batch);
	}

	@Override
	public Iterable<Batch> getAllBatches() {
		return batchRepo.findAll();
	}

	@Override
	public Batch saveBatch(Batch batch) {
		return batchRepo.save(batch);
	}

	@Override
	public <T extends BatchTask> T saveBatchTask(T task) {
		return taskRepo.save(task);
	}

	@Override
	public <T extends BatchTask> Iterable<T> getTasksForBatchId(long batchId) {
		return taskRepo.findByBatchId(batchId);
	}

	@Override
	public Batch getBatch(long batchId) {
		return batchRepo.findOne(batchId);
	}

	@Override
	public BatchTask getTask(long taskId) {
		return taskRepo.findOne(taskId);
	}

}
