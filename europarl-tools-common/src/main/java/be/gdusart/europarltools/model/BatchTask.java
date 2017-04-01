package be.gdusart.europarltools.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class BatchTask {

	public enum TaskStatus {
		QUEUED, IN_PROGRESS, SUCCESS, FAILED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Batch batch;

	private TaskStatus status = TaskStatus.QUEUED;
	private Date startDate;
	private Date endDate;

	
	public BatchTask() {
		super();
	}

	public BatchTask(Batch batch) {
		super();
		this.batch = batch;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus taskStatus) {
		this.status = taskStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
