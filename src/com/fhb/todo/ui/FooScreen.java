package com.fhb.todo.ui;

import com.fhb.todo.ToDo;

import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;


public class FooScreen extends MainScreen {
	public FooScreen() {
		super();
		
        LabelField title = new LabelField("ToDo", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH); 
        setTitle(title); 
        add(new RichTextField("yo!"));
        add(new CheckboxField());
	}
	
	public boolean onClose() { 
		Dialog.alert("Goodbye!1"); 
		System.exit(0); 
		return true; 
    }
}
