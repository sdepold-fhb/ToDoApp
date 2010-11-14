package com.fhb.todo.model;

import java.util.Date;
import java.util.Enumeration;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.util.ContentProtectedVector;

public class TaskManager {
	private PersistentObject storedTaskManager; 
	private ContentProtectedVector tasks;
	private static long storeId = 0xf6b2360f363bfe36L;
	
	public static int LATE = 0;
	public static int IN_TIME = 1;
	
    public TaskManager() {
    	this.storedTaskManager = PersistentStore.getPersistentObject(this.storeId); 
    	this.tasks = (ContentProtectedVector) this.storedTaskManager.getContents();

    	if(this.tasks == null)
    		this.tasks = new ContentProtectedVector();
    	
    	this.saveTasks();
    }
    
    private void saveTasks() {
    	this.storedTaskManager.setContents(this.tasks);
    	this.storedTaskManager.commit();
    }
    
    public Task[] getTasks() {
    	Task[] result = new Task[this.tasks.size()];
    	
    	for (Enumeration e = this.tasks.elements() ; e.hasMoreElements() ;) {
    		Task t = (Task) e.nextElement();
    		result[this.tasks.indexOf(t)] = t;
        }
    	
    	return result;
    }
    
    // this method is total crap... how to refactor ??? arraylist???
    public Task[] getTasks(int filter) {
    	Task[] unfilteredTasks = this.getTasks();
    	int filteredTaskCount = 0;
    	
    	// first count the relevant tasks
    	for(Task t : unfilteredTasks) {
    		if (
    			((filter == TaskManager.IN_TIME) && (t.getDeadline().getTime() >= System.currentTimeMillis())) // for in-time-tasks
    			|| ((filter == TaskManager.LATE) && (t.getDeadline().getTime() < System.currentTimeMillis())) // for late tasks
    		) filteredTaskCount++;
    	}
    	
    	// now use the count for creating an array
    	Task[] filteredTasks = new Task[filteredTaskCount];
    	int taskIndex = 0;
    	
    	// and add the tasks to the array
    	for(Task t : unfilteredTasks) {
    		if (
				((filter == TaskManager.IN_TIME) && (t.getDeadline().getTime() >= System.currentTimeMillis())) // for in-time-tasks
    			|| ((filter == TaskManager.LATE) && (t.getDeadline().getTime() < System.currentTimeMillis())) // for late tasks
    		) {
    			filteredTasks[taskIndex] = t;
    			taskIndex++;
    		}
    	}
    	
    	return filteredTasks;
    }
    
    public void addTask(Task t) {
    	this.tasks.addElement(t);
    	this.saveTasks();
    }
    
    public void removeTask(Task t) {
    	this.tasks.removeElement(t);
    	this.saveTasks();
    }
    
    public void saveTask(Task t) {
    	this.tasks.setElementAt(t, this.tasks.indexOf(t));
    	this.saveTasks();
    }
}