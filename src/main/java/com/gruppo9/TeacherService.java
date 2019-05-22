package com.gruppo9;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/TeacherService")
public class TeacherService {

    TeacherDao teacherDao = new TeacherDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    public List<Teacher> getDocente() {
        return teacherDao.getAllDocenti();
    }

    @GET
    @Path("/teachers/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Teacher getDocente(@PathParam("id") int id) {
        return teacherDao.getDocente( id );
    }

    @POST
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createDocente()
//            (
//            @FormParam("id") int id,
//            @FormParam("name") String name,
//            @FormParam("profession") String profession,
//            @Context HttpServletResponse servletResponse)
//            throws IOException
    {
        int result = teacherDao.addDocente( new Teacher( "nome","cognome",1 ) );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @PUT
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateDocente()
//            (@FormParam("id") int id,
//             @FormParam("name") String name,
//             @FormParam("profession") String profession,
//             @Context HttpServletResponse servletResponse) throws IOException
    {
        int result = teacherDao.updateDocente( new Teacher( "nome","cognome",1 ) );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @DELETE
    @Path("/teachers/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteDocente(@PathParam("id") int id) {
        int result = teacherDao.deleteDocente( id );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @OPTIONS
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}