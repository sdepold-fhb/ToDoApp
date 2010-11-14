package com.fhb.todo.ui;

import com.fhb.todo.model.Task;
import com.fhb.todo.model.TaskManager;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.MainScreen;

public class ToDoScreen extends MainScreen {
	TaskManager taskManager;
	
	public ToDoScreen(TaskManager tm) {
		super();
		
		this.taskManager = tm;
		
//		this.renderNewBlock(); 
		
		// render late tasks
		add(new LabelField("Late tasks:", Field.USE_ALL_WIDTH));
		renderTasks(taskManager.getTasks(TaskManager.LATE));
		
		// render in-time-tasks
		add(new LabelField("", Field.USE_ALL_WIDTH));
		add(new LabelField("In-time tasks:", Field.USE_ALL_WIDTH));
		renderTasks(taskManager.getTasks(TaskManager.IN_TIME));
	}
	
	private void renderNewBlock() {
		add(new LabelField("New Task:", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH));
		add(new LabelField("Title:", LabelField.LEFT));
		add(new TextField());
		
		ButtonField buttonField = new ButtonField("Save new task.");
		buttonField.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				System.out.println("schnubbsi");
			}
		});
		add(buttonField);
	}
	
	public boolean onClose() { 
		Dialog.alert("Goodbye!"); 
		System.exit(0);
		return true; 
    }
	
	private void renderTasks(Task[] tasks) {
		final TaskManager tm = this.taskManager;
		
		for(final Task t : tasks) {
			CheckboxField cbf = new CheckboxField(t.getTitle(), t.isFinished(), Field.FIELD_RIGHT | Field.USE_ALL_WIDTH);

			cbf.setChangeListener(new FieldChangeListener() {
				public void fieldChanged(Field field, int context) {
					CheckboxField cbf = (CheckboxField) field;
					t.setFinished(cbf.getChecked());
					tm.saveTask(t);
				}
			});

			add(cbf);
		}
	}
}