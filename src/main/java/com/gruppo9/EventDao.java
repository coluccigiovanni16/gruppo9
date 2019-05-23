package com.gruppo9;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventDao {


    public LinkedList<Event> getAllEvents() throws SQLException, ClassNotFoundException {
        LinkedList<Event> eventList = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "SELECT * from event" );
        while (rs.next()) {
            eventList.add( new Event( rs.getString( "name" ),
                    rs.getDate( "date" ),
                    rs.getString( "type" ),
                    rs.getString( "description" ),
                    rs.getInt( "teacherid" ) ) );
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return eventList;
    }

    public Event getEvent(String idEvent, int idTeacher) throws SQLException, ClassNotFoundException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "SELECT * from event WHERE name='" + idEvent + "' AND teacherid=" + idTeacher );
        Event e = null;
        while (rs.next()) {
            e = new Event( rs.getString( "name" ),
                    rs.getDate( "date" ),
                    rs.getString( "type" ),
                    rs.getString( "description" ),
                    rs.getInt( "teacherid" ) );
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return e;
    }

    public LinkedList<Event> getEventsByTeacher(int idTeacher) throws ClassNotFoundException, SQLException {
        LinkedList<Event> eventList = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "SELECT * from event WHERE  teacherid=" + idTeacher );
        while (rs.next()) {
            eventList.add( new Event( rs.getString( "name" ),
                    rs.getDate( "date" ),
                    rs.getString( "type" ),
                    rs.getString( "description" ),
                    rs.getInt( "teacherid" ) ) );
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return eventList;
    }

    public void addEvent(Event pEvent) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        Statement stmt;
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "INSERT INTO event " +
                "(name,teacherid,date,type,description) values (?,?,?,?,?)" );
        pstmt.setString( 1, pEvent.getNome() );
        pstmt.setInt( 2, pEvent.getTeacher() );
        pstmt.setDate( 3, (Date) pEvent.getDate() );
        pstmt.setString( 4, pEvent.getDescription() );
        pstmt.setString( 5, pEvent.getType() );
        pstmt.execute();
    }

    public void updateEvent(Event pEvent) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        Statement stmt;
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "UPDATE event SET" +
                "(name=?,teacherid=?,date=?,type=?,description=?)" );
        pstmt.setString( 1, pEvent.getNome() );
        pstmt.setInt( 2, pEvent.getTeacher() );
        pstmt.setDate( 3, (Date) pEvent.getDate() );
        pstmt.setString( 4, pEvent.getDescription() );
        pstmt.setString( 5, pEvent.getType() );
        pstmt.execute();
    }

    public void deleteEvent(String idEvent, int idTeacher) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "DELETE from event WHERE name='" + idEvent + "' AND teacherid=" + idTeacher );
        Event e = null;
        while (rs.next()) {
            e = new Event( rs.getString( "name" ),
                    rs.getDate( "date" ),
                    rs.getString( "type" ),
                    rs.getString( "description" ),
                    rs.getInt( "teacherid" ) );
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

    

    public int updateEventPart(Student student, String nameEvent, int idT, boolean request) {
        LinkedList<Event> eventList = getAllEvents();
        for (Event event : eventList) {
            if (event.getNome().equals( nameEvent ) && event.getTeacher() == idT) {
                if (!event.getParticipants().containsKey( student ) && request) {
                    event.getParticipants().put( student, false );
                } else if (event.getParticipants().containsKey( student ) && !request) {
                    event.getParticipants().remove( student );
                }
                return 1;
            }
        }
        return 0;
    }

    public Map<Student, Boolean> getPartecipants(String idE, int idT) {
        LinkedList<Event> eventList = getAllEvents();
        for (Event event : eventList) {
            if (event.getType().equals( idE ) && event.getTeacher() == idT) {
                return event.getParticipants();
            }
        }
        return null;
    }

    public void setPatecipants(String idE, int idT, Map<Student, Boolean> partecipants) {
        LinkedList<Event> eventList = getAllEvents();
        for (Event event : eventList) {
            if (event.getNome().equals( idE ) && event.getTeacher() == idT) {
                event.setParticipants( partecipants );
            }
        }
        saveEventList( eventList );
    }
}