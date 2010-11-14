package com.fhb.todo.model;

import java.util.Enumeration;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.util.ContentProtectedVector;

public class TaskManager {
	private PersistentObject storedTaskManager; 
	private ContentProtectedVector tasks;
	private static long storeId = 0xf6b2360f363bfe36L;
	
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