package com.gruppo9;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDao {
    String path = "C:\\Users\\jiovy\\Desktop\\gruppo9\\students.txt";
//    String path = "C:\\Users\\valer\\Documents\\GitHub\\gruppo9\\students.txt";


    public List<Student> getAllStudenti() throws ClassNotFoundException, SQLException {
        LinkedList<Student> studentList = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\jiovy\\Desktop\\gruppo9\\SQLlite\\gruppo9.db" );
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "SELECT * from student" );
        while (rs.next()) {
            studentList.add( new Student( rs.getString( "name" ),
                    rs.getString( "lastname" ),
                    rs.getInt( "id" ) ) );
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return studentList;
    }

    public Student getStudente(int studentid) throws ClassNotFoundException, SQLException {
        Student student = null;
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = conn.prepareStatement( "SELECT * from student where id=?" );
        pstmt.setInt( 1, studentid );
        rs = pstmt.executeQuery();
        while (rs.next()) {
            student = new Student( rs.getString( "name" ),
                    rs.getString( "lastname" ),
                    rs.getInt( "id" ) );
        }
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return student;
    }

    public void addStudente(Student pStudent) throws SQLException, ClassNotFoundException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:C:\\Users\\jiovy\\Desktop\\gruppo9\\SQLlite\\gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "insert  into student (name, lastname, id) values (?,?,?)" );
        pstmt.setString( 1, pStudent.getNome() );
        pstmt.setString( 2, pStudent.getCognome() );
        pstmt.setInt( 3, pStudent.getId() );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

    public void updateStudente(Student pStudent) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "update student SET (name =?,lastname=?) where (id=?)" );
        pstmt.setString( 1, pStudent.getNome() );
        pstmt.setString( 2, pStudent.getCognome() );
        pstmt.setInt( 3, pStudent.getId() );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

    public void deleteStudente(int studentid) throws ClassNotFoundException, SQLException {
        Class.forName( "org.sqlite.JDBC" );
        Connection conn = DriverManager.getConnection( "jdbc:sqlite:SQLlite/gruppo9.db" );
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "delete from student where (id=?)" );
        pstmt.setInt( 1, studentid );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }


}
