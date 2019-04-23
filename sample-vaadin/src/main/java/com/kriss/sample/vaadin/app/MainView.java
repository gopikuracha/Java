package com.kriss.sample.vaadin.app;

import java.util.UUID;

import com.kriss.sample.vaadin.customer.Customer;
import com.kriss.sample.vaadin.customer.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class MainView extends VerticalLayout {
	
	private int i = 10;
	
	private CustomerService service = CustomerService.getInstance();
	private Grid<Customer> grid = new Grid<>(Customer.class);

    public MainView() {
    	
    	TextField uid = new TextField("Random UID");
    	uid.setValue(UUID.randomUUID().toString());
    	uid.setWidth("60%");
        
        Button uidChange = new Button("Generate New",
       			event -> uid.setValue(UUID.randomUUID().toString()));
        
        HorizontalLayout hLayout1 = new HorizontalLayout();
        hLayout1.setWidth("80%");
        hLayout1.add(uid);
        hLayout1.add(uidChange);
        add(hLayout1);
        
        
        TextField curTime = new TextField("Current Time in Milli Sec");
        curTime.setValue(Long.toString(System.currentTimeMillis()));
        curTime.setWidth("60%");
        
        Button miSecChange = new Button("Generate New",
       			event -> curTime.setValue(Long.toString(System.currentTimeMillis())));
        
        HorizontalLayout hLayout2 = new HorizontalLayout();
        hLayout2.setWidth("80%");
        hLayout2.add(curTime);
        hLayout2.add(miSecChange);
        add(hLayout2);
        
        grid.setColumns("firstName", "lastName", "status");
        grid.setItems(service.findAll());
        add(grid);
        
        Button button = new Button("Click me",
                event -> Notification.show("Clicked!"));
        //add(button);
        
        setSizeFull();
        
    }
    
}
