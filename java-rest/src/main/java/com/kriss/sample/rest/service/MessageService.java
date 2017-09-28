package com.kriss.sample.rest.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.kriss.sample.rest.model.Message;

public class MessageService {
	
	private static Map<Integer, Message> messages;
	
	static {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1946);
		messages = new HashMap<Integer, Message>();
		messages.put(1, new Message(1, "Initial Message", "System", new Date()));
		messages.put(2, new Message(2, "Second Initial Message", "System", cal.getTime()));
	}
	
	private static int getMessageIndex() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.addAll(messages.keySet());
		return set.last()+1;
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message msg : messages.values()) {
			cal.setTime(msg.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(msg);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> pagedMessages = new ArrayList<Message>(messages.values());
		if(start+size > pagedMessages.size()) return new ArrayList<Message>();
		return pagedMessages.subList(start, size);
	}
	
	public Message getMessage(int id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message msg) {
		msg.setId(MessageService.getMessageIndex());
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message updateMessage(Message msg) {
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message deleteMessage(int id) {
		return messages.remove(id);
	}

}
