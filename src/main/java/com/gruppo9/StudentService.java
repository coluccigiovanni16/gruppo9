package com.gruppo9;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/StudentService")
public class StudentService {

    StudentDao studentDao = new StudentDao();
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";



    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_XML)
    public List<Student> getStudents() {
        return studentDao.getAllStudenti();
    }

    @GET
    @Path("/students/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Student getStudent(@PathParam("id") int id) {
        return studentDao.getStudente( id );
    }

    @POST
    @Path("/students")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createStudente()
//            (
//            @FormParam("id") int id,
//            @FormParam("name") String name,
//            @FormParam("profession") String profession,
//            @Context HttpServletResponse servletResponse)
//            throws IOException
    {
        int result = studentDao.addStudente( new Student( "nom", "prenom", 2, 0, 0 ) );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @PUT
    @Path("/students")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateStudent()
//            (@FormParam("id") int id,
//             @FormParam("name") String name,
//             @FormParam("profession") String profession,
//             @Context HttpServletResponse servletResponse) throws IOException
    {
        LinkedList<Student> participants = null;
        int result = studentDao.updateStudente( new Student( "nom", "prenom", 2, 0, 0 ) );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @DELETE
    @Path("/students/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteStudent(@PathParam("id") int id) {
        int result = studentDao.deleteStudente( id );
        if (result == 1) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }

    @OPTIONS
    @Path("/students")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}