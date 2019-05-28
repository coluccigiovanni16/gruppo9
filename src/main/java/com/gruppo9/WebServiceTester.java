package com.gruppo9;

import java.util.List;
import java.util.Scanner;

import javax.ws.rs.FormParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

public class WebServiceTester {

    private Client client;
    private String REST_SERVICE_URL = "http://localhost:8080";
    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";

    private void init() {
        this.client = ClientBuilder.newClient();
    }

    public static void main(String[] args) {
        WebServiceTester tester = new WebServiceTester();
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
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/event/mobile").request(MediaType.APPLICATION_XML)
                .delete(String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void confirmPartecipant() {
        Form form = new Form();
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/events/mobile/partecipants/1").request(MediaType.APPLICATION_XML)
                .put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void deletePartecipation() {
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/event/mobile").request(MediaType.APPLICATION_XML)
                .delete(String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void getDescription() {
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/event/mobile").request(MediaType.APPLICATION_XML)
                .get(String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void getTeachers() {
        Teacher teacher = client
                .target(REST_SERVICE_URL)
                .path("/1/teachers")
                .request(MediaType.APPLICATION_XML)
                .get(Teacher.class);
        if (teacher != null) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void confirmAllPartecipants() {
        Form form = new Form();
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/events/mobile/partecipants").request(MediaType.APPLICATION_XML)
                .put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void getPartecipants() {
        Student student = client
                .target(REST_SERVICE_URL)
                .path("/1/events/mobile/partecipants")
                .request(MediaType.APPLICATION_XML)
                .get(Student.class);
        if (student != null) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void getTeachersEvents() {
        Event event = client
                .target(REST_SERVICE_URL)
                .path("/1/teachers/1/events")
                .request(MediaType.APPLICATION_XML)
                .get(Event.class);
        if (event != null) {
            System.out.println("pass");
        } else System.out.println("fail");

    }

    private void requestPartecipation() {
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/event/mobile").request(MediaType.APPLICATION_XML)
                .put(null, String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void getEvent() {
        Event event = client
                .target(REST_SERVICE_URL)
                .path("/1/events")
                .request(MediaType.APPLICATION_XML)
                .get(Event.class);
        if (event != null) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void updateEvent() {
        Form form = new Form();
        form.param("day", "22");
        form.param("month", "3");
        form.param("years", "2019");
        form.param("type", "programmazione");
        form.param("description", "app");
        form.param("teacher", "1");
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/event/mobile").request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void addEvent() {
        Form form = new Form();
        form.param("name", "mobile");
        form.param("day", "22");
        form.param("month", "3");
        form.param("years", "2019");
        form.param("type", "prog");
        form.param("description", "app");
        form.param("teacher", "1");
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/1/event").request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void addStudent() {
        Form form = new Form();
        form.param("name", "Ajeje");
        form.param("lastname", "Brazof");
        form.param("identifier", "1");
        form.param("categories", "StudentService");
        String callResult = String.valueOf(client.target(REST_SERVICE_URL)
                .path("/Inizialize").request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class));
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

    private void addTeacher() {
        Form form = new Form();
        form.param("firstname", "Alessia");
        form.param("lastname", "Saggesse");
        form.param("identifier", "1");
        form.param("categories", "TeacherService");
        String callResult = client.target(REST_SERVICE_URL)
                .path("/Initialize").request(MediaType.APPLICATION_XML)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
        if (callResult.equals(SUCCESS_RESULT)) {
            System.out.println("pass");
        } else System.out.println("fail");
    }

}