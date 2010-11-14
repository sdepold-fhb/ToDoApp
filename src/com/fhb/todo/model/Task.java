package com.fhb.todo.model;

import java.util.Date;
import net.rim.device.api.util.Persistable;

public class Task implements Persistable {
	private String title;
	private long deadline;
	private boolean finished;
		
	public Task()  {
		this.title = null;
		this.deadline = 0;
		this.finished = false;
	}
	
	public Task(String title) {
		this();
		this.title = title;
	}
	
	public Task(String title, Date deadline) {
		this(title);
		this.deadline = deadline.getTime();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDeadline() {
		return new Date(deadline);
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline.getTime();
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isFinished() {
		return finished;
	}
}