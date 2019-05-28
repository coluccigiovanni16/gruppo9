package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Path("/TeacherService/{idT}")
public class TeacherService {

    @PathParam("idT")
    int idT;

    EventDao eventDao = new EventDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    //ADD AN EVENT
    @POST
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addEvent(@FormParam("name") String name,
                           @FormParam("day") int day,
                           @FormParam("month") int month,
                           @FormParam("year") int year,
                           @FormParam("type") String type,
                           @FormParam("description") String desc) {
//        to do : FORM
        Event event = new Event(name, new Date(year, month, day), type, desc, idT);
        eventDao.addEvent(event);
        return SUCCESS_RESULT;
    }


    //  SHOW ALL EVENT
    @GET
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public List<Event> getEvents() {
        return eventDao.getAllEvents(idT);
    }

    @GET
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public Event getEvent(@PathParam("idE") String idE) {
        return eventDao.getEvent(idE, idT);
    }

    //    SHOW ALL PARTECIPANTS OF AN EVENT(ONLY IF IS OF THE SPECIFIC TEACHER)
//    THANKS TO THE ID OF THE EVENTS AND THE ID OF THE SPECIFIC TEACHER
    @GET
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    public Set<Student> getPartecipants(@PathParam("idE") String idE) {
        return eventDao.getPartecipants(idE, idT).keySet();
    }

    //    SHOW ALL PARTECIPANTS OF AN EVENT(ONLY IF IS OF THE SPECIFIC TEACHER)
//    THANKS TO THE ID OF THE EVENTS AND THE ID OF THE SPECIFIC TEACHER
    @GET
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    public Student getPartecipant(@PathParam("idE") String idE,
                                  @PathParam("idS") int idS) {
        return new StudentDao().getStudente(idS);
    }

    // THE TEACHER CAN CONFIRM ALL THE PARTECIPANTS OF HIS EVENT
    @PUT
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String confimAllPartecipants(@PathParam("idE") String idE) {
        eventDao.setAllPatecipants(idE, idT);

        return SUCCESS_RESULT;
    }

    // THE TEACHER CAN CONFIRM ONE OF THE PARTECIPANTS OF HIS EVENT
    @PUT
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String confimPartecipant(@PathParam("idE") String idE,
                                    @PathParam("idS") int idS) {
        eventDao.setPatecipant(idE, idT, idS);
        return SUCCESS_RESULT;
    }

    @PUT
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String modifyEvent(@PathParam("idE") String idE,
                              @FormParam("day") int day,
                              @FormParam("month") int month,
                              @FormParam("year") int year,
                              @FormParam("type") String type,
                              @FormParam("description") String desc) {
        // to do : FORM
        Event e = new Event(idE, new Date(year, month  , day), type, desc, idT);
        eventDao.updateEvent(e);
        return SUCCESS_RESULT;
    }

    //    THE TEACHER CAN DELETE HIS EVENT
    @DELETE
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteEvent(@PathParam("idE") String idE) {
        eventDao.deleteEvent(idE, idT);
        return SUCCESS_RESULT;
    }


    @OPTIONS
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, POST,</operations>";
    }

    @OPTIONS
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsE() {
        return "<operations>GET, PUT, DELETE</operations>";
    }

    @OPTIONS
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsP() {
        return "<operations>GET, PUT</operations>";
    }

    @OPTIONS
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsSingleP() {
        return "<operations>GET, PUT</operations>";
    }


}
