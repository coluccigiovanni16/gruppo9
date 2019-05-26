package com.gruppo9;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

@Path("/StudentService/{idS}")
public class StudentService extends HttpServlet {

    @PathParam("idS") int idS;

    TeacherDao teacherDao = new TeacherDao();
    EventDao eventDao = new EventDao();
    StudentDao studentDao=new StudentDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";


    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    public List<Teacher> getTeachers() {
        return teacherDao.getAllDocenti();
    }

    @GET
    @Path("/teachers/{idT}/events")
    @Produces(MediaType.APPLICATION_XML)
    public LinkedList<Event> getEventsByTeacher(@PathParam("idT") int id)  {
        return eventDao.getEventsByTeacher( id );
    }

    @PUT
    @Path("/teachers/{idT}/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String updateStudentPartecipation(@PathParam("idE") String name, @PathParam("idT") int idT)  {
        Student s1 = studentDao.getStudente(idS);
//        definire nel form un booleano prenota/cancella prenotazione
        eventDao.updateEventPart( s1, name, idT, true );
        return SUCCESS_RESULT;

    }

    @GET
    @Path("/teachers/{idT}/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String getEventDescription(@PathParam("idE") String name, @PathParam("idT") int idT)  {
//        definire nel form un booleano prenota/cancella prenotazione
         return "<desciption>"+eventDao.getEventDescription( name, idT )+"</desciption>";
    }

    @DELETE
    @Path("/teachers/{idT}/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteStudentPartecipation(@PathParam("idE") String name, @PathParam("idT") int idT)  {
        Student s1 = studentDao.getStudente(idS);
//        definire nel form un booleano prenota/cancella prenotazione
        eventDao.updateEventPart( s1, name, idT, false );
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
        return "<operations>GET</operations>";
    }

    @OPTIONS
    @Path("/teachers/{idT}/events/{idE}")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperationsSingleE() {
        return "<operations>GET,PUT,DELETE</operations>";
    }

}