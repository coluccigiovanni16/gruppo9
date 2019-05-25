package com.gruppo9;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TeacherDao {




    public List<Teacher> getAllDocenti() throws ClassNotFoundException, SQLException {
        LinkedList<Teacher> teachersList = new LinkedList<Teacher>(  );
        Connection conn = startConn();
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery( "SELECT * from teacher" );
        while (rs.next()) {
            System.out.println( rs.getString( "name" ));
            teachersList.add( new Teacher( rs.getString( "name" ),
                    rs.getString( "lastname" ),
                    rs.getInt( "id" ) ) );
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return teachersList;
    }



    public Teacher getDocente(int teacherid) throws ClassNotFoundException, SQLException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        ResultSet rs;
        Teacher teacher=null;
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
        Connection conn = startConn();
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
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "update teacher set (name =?,lastname=?) where (id=?)" );
        pstmt.setString( 1, pTeacher.getNome() );
        pstmt.setString( 2, pTeacher.getCognome() );
        pstmt.setInt( 3, pTeacher.getId() );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

    public void deleteDocente(int teacherid) throws ClassNotFoundException, SQLException {
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement( "delete from teacher where (id=?)" );
        pstmt.setInt( 1, teacherid );
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
    }

    private Connection startConn() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String databaseURL = "jdbc:postgresql://localhost:5432/gruppo9DB";
        return DriverManager.getConnection( databaseURL,"postgres","admin" );
    }

}


