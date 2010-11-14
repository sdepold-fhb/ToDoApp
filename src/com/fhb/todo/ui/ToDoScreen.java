package com.fhb.todo.ui;

import com.fhb.todo.model.Task;
import com.fhb.todo.model.TaskManager;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.MainScreen;

public class ToDoScreen extends MainScreen {
	TaskManager taskManager;
	
	public ToDoScreen(TaskManager tm) {
		super();
		
		this.taskManager = tm;
		
		this.renderNewBlock();
//		
//		
//		
//			add(new TextField());
//        LabelField title = new LabelField("ToDo", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH); 
//        setTitle(title); 
//        add(new RichTextField("yo!"));
//        add(new CheckboxField());
//        
//        
        this.renderTasks();
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
	
	private void renderTasks() {
		for(Task t : this.taskManager.getTasks()) {
			add(new CheckboxField(t.getTitle(), false));
		}
	}
}
