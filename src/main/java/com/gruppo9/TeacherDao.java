package com.gruppo9;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TeacherDao {

    String path = "C:\\Users\\jiovy\\Desktop\\gruppo9\\teachers.txt";
    //    String path = "C:\\Users\\valer\\Documents\\GitHub\\gruppo9\\teachers.txt";


    public List<Teacher> getAllDocenti() throws ClassNotFoundException, SQLException {
        LinkedList<Teacher> teachersList = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\jiovy\\Desktop\\gruppo9\\SQLlite\\gruppo9.db" );
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "SELECT * from teacher" );
        while (rs.next()) {
            teachersList.add( new Teacher( rs.getString( "name" ),
                    rs.getString( "lastname" ),
                    rs.getInt( "id" ) ) );
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return teachersList;
    }


    public Teacher getDocente(int teacherid) throws ClassNotFoundException, SQLException {
        Teacher teacher = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT * from Teacher where id=?" );
        pstmt.setInt( 1, teacherid );
        rs = pstmt.executeQuery();
        while (rs.next()) {
            teacher = new Teacher( rs.getString( "name" ),
                    rs.getString( "lastname" ),
                    rs.getInt( "id" ) );
        }
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return teacher;
    }

    public void addDocente(Teacher pTeacher) throws SQLException, ClassNotFoundException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\jiovy\\Desktop\\gruppo9\\SQLlite\\gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "insert  into teacher (name, lastname, id) values (?,?,?)" );
        pstmt.setString( 1, pTeacher.getNome() );
        pstmt.setString( 2, pTeacher.getCognome() );
        pstmt.setInt( 3, pTeacher.getId() );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

    public void updateDocente(Teacher pTeacher) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "update teacher SET (name =?,lastname=?) where (id=?)" );
        pstmt.setString( 1, pTeacher.getNome() );
        pstmt.setString( 2, pTeacher.getCognome() );
        pstmt.setInt( 3, pTeacher.getId() );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

    public void deleteDocente(int teacherid) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "delete from teacher where (id=?)" );
        pstmt.setInt( 1, teacherid );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

}


