package com.kriss.sample.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.kriss.sample.rest.model.Message;
import com.kriss.sample.rest.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MessageResource {
	
	MessageService service = new MessageService();

	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
					@QueryParam("startId") int startId, @QueryParam("size") int size) {
		if(year > 0) return service.getAllMessagesForYear(year);
		if(startId >= 0 && size > 0) return service.getAllMessagesPaginated(startId, size);
		return service.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") int id) {
		return service.getMessage(id);
	}
	
	@POST
	public Message addMessage(Message msg) {
		return service.addMessage(msg);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") int id, Message msg) {
		msg.setId(id);
		return service.updateMessage(msg);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") int id) {
		return service.deleteMessage(id);
	}
}
