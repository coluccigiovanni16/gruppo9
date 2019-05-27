package com.gruppo9;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EventDao {


    public LinkedList<Event> getAllEvents() throws SQLException, ClassNotFoundException {
        LinkedList<Event> eventList = new LinkedList<Event>(  );
        Connection conn = startConn();
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
        Connection conn = startConn();
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT * from event WHERE name=? AND teacherid=?" );
        pstmt.setString( 1, idEvent );
        pstmt.setInt( 2, idTeacher );
        rs = pstmt.executeQuery();
        Event e = null;
        while (rs.next()) {
            e = new Event( rs.getString( "name" ),
                    rs.getDate( "date" ),
                    rs.getString( "type" ),
                    rs.getString( "description" ),
                    rs.getInt( "teacherid" ) );
        }
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return e;
    }

    public LinkedList<Event> getEventsByTeacher(int idTeacher) throws ClassNotFoundException, SQLException {
        LinkedList<Event> eventList = new LinkedList<Event>(  );
        Connection conn = startConn();
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT * from event WHERE  teacherid=?" );
        pstmt.setInt( 1, idTeacher );
        rs = pstmt.executeQuery();
        while (rs.next()) {
            eventList.add( new Event( rs.getString( "name" ),
                    rs.getDate( "date" ),
                    rs.getString( "type" ),
                    rs.getString( "description" ),
                    rs.getInt( "teacherid" ) ) );
        }
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return eventList;
    }

    public void addEvent(Event pEvent) throws ClassNotFoundException, SQLException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "INSERT INTO event " +
                "(name,teacherid,date,type,description) values (?,?,?,?,?)" );
        pstmt.setString( 1, pEvent.getNome() );
        pstmt.setInt( 2, pEvent.getTeacher() );
        pstmt.setDate( 3, (Date) pEvent.getDate() );
        pstmt.setString( 4, pEvent.getDescription() );
        pstmt.setString( 5, pEvent.getType() );
        pstmt.execute();
        pstmt.close();
        conn.close();
    }

    public void updateEvent(Event pEvent) throws ClassNotFoundException, SQLException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "UPDATE event SET date=?,type=?,description=? where name=? and teacherid=?" );
        pstmt.setDate( 1, (Date) pEvent.getDate() );
        pstmt.setString( 2, pEvent.getDescription() );
        pstmt.setString( 3, pEvent.getType() );
        pstmt.setString( 4, pEvent.getNome() );
        pstmt.setInt( 5, pEvent.getTeacher() );
        pstmt.execute();
        pstmt.close();
        conn.close();
    }

    public void deleteEvent(String idEvent, int idTeacher) throws ClassNotFoundException, SQLException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "DELETE from event WHERE name=? AND teacherid=?" );
        pstmt.setString( 1, idEvent );
        pstmt.setInt( 2, idTeacher );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }


    public Map<Student, Boolean> getPartecipants(String idEvent, int idTeacher) throws ClassNotFoundException, SQLException {
        Map<Student, Boolean> partecipants = new HashMap<Student, Boolean>(  );
        StudentDao studentDao = new StudentDao();
        Connection conn = startConn();
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT * from partecipant WHERE eventid=? AND teacherid=?" );
        pstmt.setString( 1, idEvent );
        pstmt.setInt( 2, idTeacher );
        rs = pstmt.executeQuery();
        while (rs.next()) {
            partecipants.put( studentDao.getStudente( rs.getInt( "studentid" ) ),
                    rs.getBoolean( "confirmed" ) );
        }
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return partecipants;
    }

    public void setAllPatecipants(String idE, int idT) throws ClassNotFoundException, SQLException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "UPDATE partecipant SET confirmed=? where eventid=? and teacherid=?" );
        pstmt.setBoolean( 1, true );
        pstmt.setString( 2, idE );
        pstmt.setInt( 3, idT );
        pstmt.execute();
    }

    public void setPatecipant(String idE, int idT, int idS) throws ClassNotFoundException, SQLException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "UPDATE partecipant SET confirmed=? where studentid=? and eventid=? and teacherid=?" );
        pstmt.setBoolean( 1, true );
        pstmt.setInt( 2, idS );
        pstmt.setString( 3, idE );
        pstmt.setInt( 4, idT );
        pstmt.execute();
        pstmt.close();
        conn.close();
    }

    public void updateEventPart(Student s1, String eventName, int idT, boolean b) throws SQLException, ClassNotFoundException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        if (b) {
            pstmt = conn.prepareStatement( "INSERT INTO partecipant " +
                    "(eventid,teacherid,studentid,confirmed) values (?,?,?,?)" );
            pstmt.setString( 1, eventName );
            pstmt.setInt( 2, idT );
            pstmt.setInt( 3, s1.getId() );
            pstmt.setBoolean( 4, false );
            pstmt.execute();
        } else {
            pstmt = conn.prepareStatement( "DELETE FROM partecipant WHERE eventid=? and teacherid=? and studentid=?" );
            pstmt.setString( 1, eventName );
            pstmt.setInt( 2, idT );
            pstmt.setInt( 3, s1.getId() );
            pstmt.execute();
        }
        pstmt.close();
        conn.close();
    }


    private Connection startConn() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String databaseURL = "jdbc:postgresql://localhost:5432/gruppo9DB";
        return DriverManager.getConnection( databaseURL,"postgres","admin" );
    }

    public String getEventDescription(String name, int idT) throws SQLException, ClassNotFoundException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT description from event WHERE  name=? and teacherid=?" );
        pstmt.setString(1,name);
        pstmt.setInt( 2,idT );
        rs = pstmt.executeQuery();
        String desc=null;
        while (rs.next()) {
            desc=rs.getString( "description" );
        }
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return desc;
    }
}