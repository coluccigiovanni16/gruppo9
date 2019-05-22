package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/TeacherService")
public class TeacherService {

    TeacherDao teacherDao = new TeacherDao();
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
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public Map<Student, Boolean> getPartecipants(@PathParam("idE") String idE) {
        int idT = 100;
        return eventDao.getPartecipants( idE, idT );
    }

    @PUT
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String confimPartecipant(@PathParam("idE") String idE) {
        int idS = 1;
        int idT = 100;
        Map<Student, Boolean> partecipants = eventDao.getPartecipants( idE, idT );
        for (Student partecipant : partecipants.keySet()) {
            partecipants.put( partecipant, true );
//            to do : FORM
        }
        eventDao.setPatecipants( idE, idT, partecipants );
//        int result = teacherDao.addDocente( new Teacher( "nome", "cognome", 100 ) );
//        if (result == 1) {
//            return SUCCESS_RESULT;
//        }
//        return FAILURE_RESULT;
        return SUCCESS_RESULT;
    }

//    @PUT
//    @Path("/events/{idE}")
//    @Produces(MediaType.APPLICATION_XML)
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public String modifyEvent(@PathParam("idE") String idE) {
//        int idT = 100;
//        Map<Student, Boolean> partecipants = eventDao.getPartecipants( idE, idT );
//        for (Student partecipant : partecipants.keySet()) {
//            partecipants.put( partecipant, true );
////            to do : FORM
//        }
//        eventDao.setPatecipants( idE, idT, partecipants );
////        int result = teacherDao.addDocente( new Teacher( "nome", "cognome", 100 ) );
////        if (result == 1) {
////            return SUCCESS_RESULT;
////        }
////        return FAILURE_RESULT;
//        return SUCCESS_RESULT;
//    }

    @DELETE
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteEvent(@PathParam("idE") String idE) {
        int idT = 100;
        int result = eventDao.deleteEvent( idE, idT );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @POST
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public String addEvent() {
//        to do : FORM
        Event event = new Event( "maker", 1, "tech1", "good1", 102 );
        int result = eventDao.addEvent( event );
        if( result == 1 ) {
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