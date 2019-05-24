package com.gruppo9;

import java.sql.*;
import java.util.LinkedList;
import java.util.Map;

public class EventDao {


    public LinkedList<Event> getAllEvents() throws SQLException, ClassNotFoundException {
        LinkedList<Event> eventList = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\jiovy\\Desktop\\gruppo9\\SQLlite\\gruppo9.db" );
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
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT * from partecipant WHERE name=? AND teacherid=?" );
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
        LinkedList<Event> eventList = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
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
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\jiovy\\Desktop\\gruppo9\\SQLlite\\gruppo9.db" );
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
                "(date=?,type=?,description=?) where (name=?,teacherid=?)" );
        pstmt.setDate( 1, (Date) pEvent.getDate() );
        pstmt.setString( 2, pEvent.getDescription() );
        pstmt.setString( 3, pEvent.getType() );
        pstmt.setString( 4, pEvent.getNome() );
        pstmt.setInt( 5, pEvent.getTeacher() );


        pstmt.execute();
    }

    public void deleteEvent(String idEvent, int idTeacher) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "DELETE from event WHERE name=? AND teacherid=?" );
        pstmt.setString( 1, idEvent );
        pstmt.setInt( 2, idTeacher );
        rs = pstmt.executeQuery();
        Event e;
        while (rs.next()) {
            e = new Event( rs.getString( "name" ),
                    rs.getDate( "date" ),
                    rs.getString( "type" ),
                    rs.getString( "description" ),
                    rs.getInt( "teacherid" ) );
        }
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }


    public Map<Student, Boolean> getPartecipants(String idEvent, int idTeacher) throws ClassNotFoundException, SQLException {
        Map<Student, Boolean> partecipants = null;
        StudentDao studentDao = new StudentDao();
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT * from partecipant WHERE name=? AND teacherid=?" );
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
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "UPDATE partecipant SET" +
                "(confirmed=?)" );
        pstmt.setBoolean( 1, true );
        pstmt.execute();
    }

    public void setPatecipant(String idE, int idT, int idS) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "UPDATE partecipant SET" +
                "(confirmed=?) where (studentid=?,eventid=?,teacherid=?" );
        pstmt.setBoolean( 1, true );
        pstmt.setInt( 2, idS );
        pstmt.setString( 3, idE );
        pstmt.setInt( 4, idT );
        pstmt.execute();
    }

    public void updateEventPart(Student s1, String eventName, int idT, boolean b) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
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
            pstmt = conn.prepareStatement( "DELETE FROM partecipant WHERE (eventid=?,teacherid=?,studentid=?)" );
            pstmt.setString( 1, eventName );
            pstmt.setInt( 2, idT );
            pstmt.setInt( 3, s1.getId() );
            pstmt.execute();

        }


    }
}