package com.gruppo9;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName( "org.sqlite.JDBC" );
            Connection conn = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\jiovy\\Desktop\\gruppo9\\SQLlite\\gruppo9.db" );
            Statement stmt;
            PreparedStatement pstmt;
            ResultSet rs;

            // creo la tabella
            stmt = conn.createStatement();

//            stmt.executeUpdate("DROP TABLE IF EXISTS students");
//            stmt.executeUpdate("CREATE TABLE students " +
//                    "(id INTEGER primary key autoincrement, " +
//                    "firstname TEXT NOT NULL, " +
//                    "lastname TEXT NOT NULL)");

            // inserisco due record
            pstmt = conn.prepareStatement( "INSERT INTO student " +
                    "(name, lastname,id) values (?,?,?)" );

            pstmt.setString( 1, "Mario" );
            pstmt.setString( 2, "Rossi" );
            pstmt.setInt( 3, 1 );
            pstmt.execute();

            pstmt.setString( 1, "Marco" );
            pstmt.setString( 2, "Bianchi" );
            pstmt.setInt( 3, 2 );
            pstmt.execute();

            pstmt = conn.prepareStatement( "INSERT INTO teacher " +
                    "(name, lastname,id) values (?,?,?)" );
            pstmt.setString( 1, "Alessia" );
            pstmt.setString( 2, "Saggese" );
            pstmt.setInt( 3, 100 );
            pstmt.execute();

            pstmt.setString( 1, "Marco" );
            pstmt.setString( 2, "Nero" );
            pstmt.setInt( 3, 200 );
            pstmt.execute();

            pstmt = conn.prepareStatement( "INSERT INTO event " +
                    "(name,teacherid,date,type,description) values (?,?,?,?,?)" );
            pstmt.setString( 1, "MOBILE" );
            pstmt.setInt( 2, 100 );
            pstmt.setString( 5, "PROG" );
            pstmt.setString( 4, "APP-WEB" );
            pstmt.setString( 3, "10/10/10" );
            pstmt.execute();

            pstmt.setString( 1, "ROBOTICA" );
            pstmt.setInt( 2, 200 );
            pstmt.setString( 5, "ING" );
            pstmt.setString( 4, "ROB" );
            pstmt.setString( 3, "10/10/12" );
            pstmt.execute();

            pstmt = conn.prepareStatement( "INSERT INTO partecipant " +
                    "(studentid,eventid,teacherid,confirmed) values (?,?,?,?)" );
            pstmt.setInt( 1, 1 );
            pstmt.setString( 2, "ROBOTICA" );
            pstmt.setInt( 3, 100 );
            pstmt.setBoolean( 4, false );
            pstmt.execute();

            pstmt.setInt( 1, 2 );
            pstmt.setString( 2, "MOBILE" );
            pstmt.setInt( 3, 100 );
            pstmt.setBoolean( 4, false );
            pstmt.execute();

            // recupero i dati
            rs = stmt.executeQuery( "SELECT * from partecipant" );

            while (rs.next()) {
                System.out.println( "studentid: " + rs.getInt( "studentid" ) );
                System.out.println( "eventid: " + rs.getString( "eventid" ) );
                System.out.println( "teacherid: " + rs.getInt( "teacherid" ) );
                System.out.println( "confirmed: " + rs.getBoolean( "confirmed" ) );
                System.out.println( "--------------------------------------" );
            }

            stmt.close(); // rilascio le risorse
            pstmt.close(); // rilascio le risorse
            conn.close(); // termino la connessione
        } catch (ClassNotFoundException e) {
            System.out.println( e );
        } catch (SQLException e) {
            System.out.println( e );
        }
    }
}