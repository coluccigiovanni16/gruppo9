package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Path("/TeacherService")
public class TeacherService {

    EventDao eventDao = new EventDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    //ADD AN EVENT
    @POST
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public String addEvent() {
//        to do : FORM
        Event event = new Event( "maker", 1, "tech1", "good1", 102 );
        int result = eventDao.addEvent( event );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }


    //  SHOW ALL EVENT
    @GET
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public List<Event> getEvents() {
        return eventDao.getAllEvents();
    }

    @GET
    @Path("/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public Event getEvent(@PathParam("idE") String idE) {
        return eventDao.getEvent( idE );
    }

    //    SHOW ALL PARTECIPANTS OF AN EVENT(ONLY IF IS OF THE SPECIFIC TEACHER)
//    THANKS TO THE ID OF THE EVENTS AND THE ID OF THE SPECIFIC TEACHER
    @GET
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    public Set<Student> getPartecipants(@PathParam("idE") String idE) {
        int idT = 100;
        return eventDao.getEvent( idE ).getParticipants().keySet();
    }

    //    SHOW ALL PARTECIPANTS OF AN EVENT(ONLY IF IS OF THE SPECIFIC TEACHER)
//    THANKS TO THE ID OF THE EVENTS AND THE ID OF THE SPECIFIC TEACHER
    @GET
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    public Student getPartecipant(@PathParam("idE") String idE) {
        int idT = 100;
        int idS = 1;
        return new StudentDao().getStudente( idS );
    }

    // THE TEACHER CAN CONFIRM ALL THE PARTECIPANTS OF HIS EVENT
    @PUT
    @Path("/events/{idE}/partecipants")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String confimAllPartecipants(@PathParam("idE") String idE) {
        int idT = 100;
        Map<Student, Boolean> partecipants = eventDao.getPartecipants( idE, idT );
        for (Student partecipant : partecipants.keySet()) {
            partecipants.put( partecipant, true );
//            to do : FORM
        }
        eventDao.setPatecipants( idE, idT, partecipants );
        return SUCCESS_RESULT;
    }

    // THE TEACHER CAN CONFIRM ALL THE PARTECIPANTS OF HIS EVENT
    @PUT
    @Path("/events/{idE}/partecipants/{idS}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String confimPartecipant(@PathParam("idE") String idE) {
        int idT = 100;
        int idS = 1;
        Map<Student, Boolean> partecipants = eventDao.getPartecipants( idE, idT );
        for (Student partecipant : partecipants.keySet()) {
            if (partecipant.getId() == idS) {
                partecipants.put( partecipant, true );
            }
        }
//            to do : FORM
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

    //    THE TEACHER CAN DELETE HIS EVENT
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


    @OPTIONS
    @Path("/events")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}