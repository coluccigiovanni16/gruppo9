package com.gruppo9;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/Initialize")
public class Initialize {

    EventDao eventDao = new EventDao();
    StudentDao studentDao = new StudentDao();
    TeacherDao teacherDao = new TeacherDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";


    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String create(
            @FormParam("firstname") String name,
            @FormParam("lastname") String lastname,
            @FormParam("identifier") int id,
            @FormParam("categories") String categories,
            @Context HttpServletResponse response) throws IOException {
        if (categories.equalsIgnoreCase( "TeacherService" )) {
            System.out.println(categories);
            TeacherDao td = new TeacherDao();
            Teacher t = new Teacher( name, lastname, id );
            System.out.println(id);
            if (td.getDocente( id ) == null) {
                td.addDocente( t );
            } else {
                td.updateDocente( t );
            }
//            response.sendRedirect( "TeacherService/" + id + "/events" );
            return "<result>success</result>";
        } else if (categories.equalsIgnoreCase( "StudentService" )) {
            StudentDao sd = new StudentDao();
            Student s = new Student( name, lastname, id );
            if (sd.getStudente( id ) == null) {
                sd.addStudente( s );
            } else {
                sd.updateStudente( s );
            }
//            response.sendRedirect( "StudentService./" + id + "/teachers" );
            return "<result>success</result>";
        } else {
            response.sendError( 400 );
            return "fail";
        }

    }


    @OPTIONS
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}
