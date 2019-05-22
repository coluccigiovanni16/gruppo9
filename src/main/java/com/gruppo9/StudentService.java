package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("/StudentService")
public class StudentService {

    TeacherDao teacherDao = new TeacherDao();
    EventDao eventDao = new EventDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    public List<Teacher> getTeachers() {
        return teacherDao.getAllDocenti();
    }

    @GET
    @Path("/teachers/{idT}/events")
    @Produces(MediaType.APPLICATION_XML)
    public LinkedList<Event> getEventsByTeacher(@PathParam("idT") int id) {
        return eventDao.getEventsByTeacher( id );
    }

    @PUT
    @Path("/teachers/{idT}/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String updateStudentPartecipation(@PathParam("idE") String name,@PathParam("idT") int idT)
    {
        Student s1= new Student( "nome","cognome",1 );
        int result = eventDao.updateEventPart(s1,name,idT,true);
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
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