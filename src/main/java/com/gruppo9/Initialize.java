package com.gruppo9;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.Date;

@Path("/Initialize")
public class Initialize {

    EventDao eventDao = new EventDao();
    StudentDao studentDao = new StudentDao();
    TeacherDao teacherDao = new TeacherDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";


    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String create(@FormParam("identifier") int id,
                           @FormParam("firstname") String name,
                           @FormParam("lastname") String lastname,
                           @FormParam("categories") String categories,
                           @Context HttpServletResponse response) throws IOException {
        System.out.println("gkf");
        if (categories.equals("TeacherService")) {
            TeacherDao td = new TeacherDao();
            Teacher t = new Teacher(name, lastname, id);
            if (td.getDocente(id) == null) {
                td.addDocente(t);
            } else {
                td.updateDocente(t);
            }
            //response.encodeRedirectURL("\"/TeacherService\"+id+\"/events\"");
            response.sendRedirect("TeacherService/"+id+"/events");
            return "<result>success</result>";
        } else if (categories.equals("StudentService")) {
            StudentDao sd = new StudentDao();
            Student s = new Student(name, lastname, id);
            if (sd.getStudente(id) == null) {
                sd.addStudente(s);
            } else {
                sd.updateStudente(s);
            }
            response.sendRedirect("StudentService./"+id+"/teachers");
            return "<result>success</result>";
        } else{
            response.sendError(400);
            return "fail";
        }

    }
    @GET
    @Path("/create")
    public String createAll(){
        Student s = new Student( "name", "prename", 1 );
        Student s1 = new Student( "name1", "prename1", 2 );
        studentDao.addStudente( s );
        studentDao.addStudente( s1 );
        Teacher t = new Teacher( "nom", "cogn", 100 );
        Teacher t1 = new Teacher( "nom1", "cogn1", 101 );
        teacherDao.addDocente( t );
        teacherDao.addDocente( t1 );
        Event event = new Event( "maker", new Date( 2019,05,24  ), "tech1", "good1", 100 );
        Event event1 = new Event( "bigdata", new Date( 2019,05,23 ), "tech2", "good2", 101 );
        eventDao.addEvent( event );
        eventDao.addEvent( event1 );
        return SUCCESS_RESULT;
    }


    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_XML)
    public void deleteEvent() {

    }

    @OPTIONS
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}