package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("/EventService")
public class EventService {

    EventDAO eventDao = new EventDAO();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    @GET
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public List<Event> getEvents() {
        return eventDao.getAllEvents();
    }

    @GET
    @Path("/events/{type}")
    @Produces(MediaType.APPLICATION_XML)
    public Event getEvent(@PathParam("type") String type) {
        return eventDao.getEvent( type );
    }

    @POST
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createEvent()
//            (
//            @FormParam("id") int id,
//            @FormParam("name") String name,
//            @FormParam("profession") String profession,
//            @Context HttpServletResponse servletResponse)
//            throws IOException
    {
        LinkedList<Student> participants =null;
        Event event = new Event( 1, "tech", "good event", "saggese", participants );
        int result = eventDao.addEvent( event );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @PUT
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateEvent()
//            (@FormParam("id") int id,
//             @FormParam("name") String name,
//             @FormParam("profession") String profession,
//             @Context HttpServletResponse servletResponse) throws IOException
    {
        LinkedList<Student> participants =null;
        Event event = new Event( 1, "tech", "good event", "saggese", participants );
        int result = eventDao.updateEvent( event );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @DELETE
    @Path("/events/{type}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteEvent(@PathParam("type") String type) {
        int result = eventDao.deleteEvent( type );
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