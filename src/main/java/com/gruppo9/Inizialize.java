package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/Initialize")
public class Inizialize {

    EventDao eventDao = new EventDao();
    StudentDao studentDao = new StudentDao();
    TeacherDao teacherDao = new TeacherDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_XML)
    public String createAll() {
        Student s = new Student( "name", "prename", 1 );
        Student s1 = new Student( "name1", "prename1", 2 );
        studentDao.addStudente( s );
        studentDao.addStudente( s1 );
        Map<Student, Boolean> p = new HashMap<Student, Boolean>();
        p.put( s, true );
        p.put( s1, true );
        Event event = new Event( "maker", 1, "tech1", "good1", 100,p );
        Event event1 = new Event( "bigdata", 2, "tech2", "good2", 101,p );
        eventDao.addEvent( event );
        eventDao.addEvent( event1 );
        Teacher t = new Teacher( "nom", "cogn", 100 );
        Teacher t1 = new Teacher( "nom1", "cogn1", 101 );
        teacherDao.addDocente( t );
        teacherDao.addDocente( t1 );
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