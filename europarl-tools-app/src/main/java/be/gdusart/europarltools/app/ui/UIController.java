package be.gdusart.europarltools.app.ui;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;

import be.gdusart.europarltools.app.ui.exceptions.Error404Exception;
import be.gdusart.europarltools.app.ui.helpers.DisplayableList;
import be.gdusart.europarltools.app.ui.helpers.DisplayableObject;
import be.gdusart.europarltools.app.ui.helpers.DisplayableList.DisplayableRow;
import be.gdusart.europarltools.app.ui.helpers.DisplayableList.DisplayableRow.RowLevel;
import be.gdusart.europarltools.model.Batch;
import be.gdusart.europarltools.model.BatchTask;
import be.gdusart.europarltools.model.BatchTask.TaskStatus;
import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.services.BatchService;
import be.gdusart.europarltools.services.EnvironmentService;

@Controller
@RequestMapping("/ui")
public class UIController {

	private static final Map<TaskStatus, RowLevel> TASK_STATUS_TO_ROWLEVEL = ImmutableMap.of(TaskStatus.QUEUED,
			RowLevel.INFO, TaskStatus.FAILED, RowLevel.DANGER, TaskStatus.IN_PROGRESS, RowLevel.INFO,
			TaskStatus.SUCCESS, RowLevel.SUCCESS);

	@Autowired
	private BatchService batchService;

	@Autowired
	private EnvironmentService envService;
	
	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getDateTimeInstance(FastDateFormat.SHORT, FastDateFormat.LONG);

	@RequestMapping("batches")
	public ModelAndView listBatches() {
		Collection<Batch> batches = IterableUtils.toList(batchService.getAllBatches());

		Collection<DisplayableRow> rows = CollectionUtils.collect(batches, new Transformer<Batch, DisplayableRow>() {
			@Override
			public DisplayableRow transform(Batch input) {
				DisplayableRow row = new DisplayableRow("batches/" + input.getId(), input.getId(),
						formatDate(input.getStartTime()), formatDate(input.getEndTime()));
				return row;
			}
		});

		DisplayableList list = new DisplayableList("List of batches", new String[] { "ID", "START", "END" }, rows);

		return listView(list);
	}

	@RequestMapping("batches/{batchId}")
	public ModelAndView getBatchTasks(@PathVariable long batchId) {
		Batch batch = batchService.getBatch(batchId);
		
		if (batch == null) {
			throw new Error404Exception("Batch with ID " + batchId + " does not exist");
		}
		
		Iterable<BatchTask> tasks = batchService.getTasksForBatchId(batchId);

		Collection<DisplayableRow> rows = CollectionUtils.collect(tasks, new Transformer<BatchTask, DisplayableRow>() {
			@Override
			public DisplayableRow transform(BatchTask task) {
				DisplayableRow row = new DisplayableRow("batches/task/" + task.getId(), task.getId(), formatDate(task.getStartDate()),
						formatDate(task.getEndDate()), task.getStatus(), task.getResultInfo());
				
				if (task.getStatus() != null) {
					row.setLevel(TASK_STATUS_TO_ROWLEVEL.get(task.getStatus()));
				}
				
				return row;
			}
		});

		DisplayableList list = new DisplayableList("Tasks for batch " + batch.getId(),
				new String[] { "ID", "START DATE", "END DATE", "STATUS", "INFO" }, rows);

		return listView(list);
	}
	
	@RequestMapping("batches/task/{taskId}")
	public ModelAndView getBatchTaskDetail(@PathVariable long taskId) {
		BatchTask task = batchService.getTask(taskId);
		
		if (task == null) {
			throw new Error404Exception("Task with ID {} does not exist");
		}
		
//		JsonObject object = null;
//		try {
//			//TODO: probably really bad
//			
//			object = new JsonObject((new ObjectMapper().writeValueAsString(taskId));
//		} catch (Exception e) {
//			
//			//TODO
//		}
		
//		return showObject(new DisplayableObject("Details for task " + task.getId(), object));
		throw new IllegalArgumentException("Not implemented");
	}

	@RequestMapping("environments")
	public ModelAndView listEnvironments() {
		Collection<Environment> envs = IterableUtils.toList(envService.getEnvironments());

		Collection<DisplayableRow> rows = CollectionUtils.collect(envs, new Transformer<Environment, DisplayableRow>() {
			@Override
			public DisplayableRow transform(Environment env) {
				DisplayableRow row = new DisplayableRow("batches/" + env.getId(), env.getId(), env.getName(), env.getServerUrl());
				return row;
			}
		});

		DisplayableList list = new DisplayableList("List of environments", new String[] { "ID", "NAME", "SERVER URL" }, rows);

		return listView(list);
	}

	private static ModelAndView listView(DisplayableList list) {
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("list", list);
		return mav;
	}

	
	private static ModelAndView showObject(DisplayableObject displayableObject) {
		ModelAndView mav = new ModelAndView("displayobject");
		mav.addObject("object", displayableObject);
		return mav;
	}
	
	private static String formatDate(Date date) {
		return date != null ? DATE_FORMAT.format(date) : StringUtils.EMPTY;
	}
	


}
