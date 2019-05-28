package com.gruppo9;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class WebServiceTester {

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:8081";
    private static final String SUCCESS_RESULT = "<result>success</result>";

    private void init() {
        this.client = ClientBuilder.newClient();
    }

    public static void main(String[] args) {
        WebServiceTester tester = new WebServiceTester();
        tester.init();
        tester.addTeacher();
        tester.addStudent();
        tester.addEvent();
        tester.updateEvent();
        tester.getEvent();
        tester.getTeachersEvents();
        tester.requestPartecipation();
        tester.getPartecipants();
        tester.confirmAllPartecipants();
        tester.getTeachers();
        tester.getDescription();
        tester.deletePartecipation();
        tester.requestPartecipation();
        tester.confirmPartecipant();
        tester.deleteEvent();
    }

    private void deleteEvent() {
        String callResult =  client.target( REST_SERVICE_URL )
                .path( "/TeacherService/1/events/mobile" ).request( MediaType.APPLICATION_XML )
                .delete( String.class  );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void confirmPartecipant() {
        Form form = new Form();
        String callResult =  client.target( REST_SERVICE_URL )
                .path( "/TeacherService/1/events/mobile/partecipants/1" ).request( MediaType.APPLICATION_XML )
                .put( Entity.entity( form, MediaType.APPLICATION_FORM_URLENCODED ), String.class  );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void deletePartecipation() {
        String callResult = client.target( REST_SERVICE_URL )
                .path( "/StudentService/1/teachers/1/events/mobile" ).request( MediaType.APPLICATION_XML )
                .delete( String.class  );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void getDescription() {
        String callResult =  client.target( REST_SERVICE_URL )
                .path( "/StudentService/1/teachers/1/events/mobile" ).request( MediaType.APPLICATION_XML )
                .get( String.class ) ;
        if (callResult.equals("<desciption>programmazione</desciption>")) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void getTeachers() {
        GenericType<List<Teacher>> list = new GenericType<List<Teacher>>() {
        };
        List<Teacher> teachers = client
                .target( REST_SERVICE_URL )
                .path( "StudentService/1/teachers" )
                .request( MediaType.APPLICATION_XML )
                .get(list );
        if (!teachers.isEmpty()) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void confirmAllPartecipants() {
        Form form = new Form();
        String callResult =  client.target( REST_SERVICE_URL )
                .path( "/TeacherService/1/events/mobile/partecipants" ).request( MediaType.APPLICATION_XML )
                .put( Entity.entity( form, MediaType.APPLICATION_FORM_URLENCODED ), String.class  );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void getPartecipants() {
        GenericType<List<Student>> list = new GenericType<List<Student>>() {
        };
        List<Student> students = client
                .target( REST_SERVICE_URL )
                .path( "/TeacherService/1/events/mobile/partecipants" )
                .request( MediaType.APPLICATION_XML )
                .get( list );
        if (!students.isEmpty()) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void getTeachersEvents() {
        GenericType<List<Event>> list = new GenericType<List<Event>>() {
        };
        List<Event> events = client
                .target( REST_SERVICE_URL )
                .path( "/StudentService/1/teachers/1/events" )
                .request( MediaType.APPLICATION_XML )
                .get( list );
        if (!events.isEmpty()) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );

    }

    private void requestPartecipation() {
        String callResult = String.valueOf( client.target( REST_SERVICE_URL )
                .path( "/StudentService/1/teachers/1/events/mobile" ).request( MediaType.APPLICATION_XML )
                .put( Entity.entity( new Form(), MediaType.APPLICATION_FORM_URLENCODED ), String.class ) );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void getEvent() {
        GenericType<List<Event>> list = new GenericType<List<Event>>() {
        };
        List<Event> events = client
                .target( REST_SERVICE_URL )
                .path( "/TeacherService/1/events" )
                .request( MediaType.APPLICATION_XML )
                .get( list );
        if (!events.isEmpty()) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void updateEvent() {
        Form form = new Form();
        form.param( "day", "22" );
        form.param( "month", "3" );
        form.param( "years", "2019" );
        form.param( "type", "programmazione" );
        form.param( "description", "app" );
        String callResult = client.target( REST_SERVICE_URL )
                .path( "/TeacherService/1/events/mobile" ).request( MediaType.APPLICATION_XML )
                .put( Entity.entity( form, MediaType.APPLICATION_FORM_URLENCODED ), String.class );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void addEvent() {
        Form form = new Form();
        form.param( "name", "mobile" );
        form.param( "day", "22" );
        form.param( "month", "3" );
        form.param( "year", "2019" );
        form.param( "type", "prog" );
        form.param( "description", "app" );
        String callResult = client.target( REST_SERVICE_URL )
                .path( "/TeacherService/1/events" ).request( MediaType.APPLICATION_XML )
                .post( Entity.entity( form, MediaType.APPLICATION_FORM_URLENCODED ), String.class );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void addStudent() {
        Form form = new Form();
        form.param( "name", "Ajeje" );
        form.param( "lastname", "Brazof" );
        form.param( "identifier", "1" );
        form.param( "categories", "StudentService" );
        String callResult = client.target( REST_SERVICE_URL )
                .path( "/Initialize" ).request( MediaType.APPLICATION_XML )
                .post( Entity.entity( form, MediaType.APPLICATION_FORM_URLENCODED ), String.class );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

    private void addTeacher() {
        Form form = new Form();
        form.param( "firstname", "Alessia" );
        form.param( "lastname", "Saggesse" );
        form.param( "identifier", "1" );
        form.param( "categories", "TeacherService" );
        String callResult = client.target( REST_SERVICE_URL )
                .path( "/Initialize" ).request( MediaType.APPLICATION_XML )
                .post( Entity.entity( form, MediaType.APPLICATION_FORM_URLENCODED ), String.class );
        if (callResult.equals( SUCCESS_RESULT )) {
            System.out.println( "pass" );
        } else System.out.println( "fail" );
    }

}
