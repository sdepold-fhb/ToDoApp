package com.fhb.todo;

import com.fhb.todo.model.Task;
import com.fhb.todo.model.TaskManager;
import com.fhb.todo.ui.ToDoScreen;

import net.rim.device.api.synchronization.UIDGenerator;
import net.rim.device.api.ui.UiApplication;

public class ToDo extends UiApplication {
	TaskManager taskManager;
	
	
	public static void main(String[] args) { 
		ToDo theApp = new ToDo();
        theApp.enterEventDispatcher(); 
    }
	
	public ToDo() {
		this.taskManager = new TaskManager();
		Task t = new Task( "schnubbsi" );
		this.taskManager.addTask(t);
        pushScreen(new ToDoScreen(this.taskManager));
	}
}