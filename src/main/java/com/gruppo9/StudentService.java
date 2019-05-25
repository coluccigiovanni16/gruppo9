package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Path("/StudentService")
public class StudentService {

    TeacherDao teacherDao = new TeacherDao();
    EventDao eventDao = new EventDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";


    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    public List<Teacher> getTeachers() throws SQLException, ClassNotFoundException {
        return teacherDao.getAllDocenti();
    }

    @GET
    @Path("/teachers/{idT}/events")
    @Produces(MediaType.APPLICATION_XML)
    public LinkedList<Event> getEventsByTeacher(@PathParam("idT") int id) throws SQLException, ClassNotFoundException {
        return eventDao.getEventsByTeacher( id );
    }

    @PUT
    @Path("/teachers/{idT}/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String updateStudentPartecipation(@PathParam("idE") String name, @PathParam("idT") int idT) throws SQLException, ClassNotFoundException {
        Student s1 = new Student( "nome", "cognome", 1 );
//        definire nel form un booleano prenota/cancella prenotazione
        eventDao.updateEventPart( s1, name, idT, true );
        return SUCCESS_RESULT;

    }


    @OPTIONS
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsT() {
        return "<operations>GET</operations>";
    }

    @OPTIONS
    @Path("/teachers/{idT}/events")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsE() {
        return "<operations>GET,PUT</operations>";
    }
}