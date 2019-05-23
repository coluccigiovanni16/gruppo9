package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/EventService")
public class EventService {

    EventDao eventDao = new EventDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    @GET
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public List<Event> getEvents() {
        return eventDao.getAllEvents();
    }

    @GET
    @Path("/events/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public Event getEvent(@PathParam("name") String name) {
        return eventDao.getEvent( name );
    }

    @POST
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createEvent()
    {
        Event event = new Event( "AI",1, "tech", "good event", 100 );
        Event event1 = new Event( "AI1",12, "tech1", "good event", 101 );
        eventDao.addEvent( event1 );
        int result = eventDao.addEvent( event );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @PUT
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateEvent(@PathParam("idE") String name)
    {
        Event event = new Event("AI", 1, "tech", "good event", 100 );
        int result = eventDao.updateEvent( event );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @DELETE
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteEvent(@PathParam("idE") String name) {
        int result = eventDao.deleteEvent( name,1 );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @OPTIONS
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}
