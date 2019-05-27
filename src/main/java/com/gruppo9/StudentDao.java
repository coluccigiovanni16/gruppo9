package com.gruppo9;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDao {

    public List<Student> getAllStudenti() {
        try{
        LinkedList<Student> studentList = new LinkedList<Student>();
        Connection conn = startConn();
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * from student");
        while (rs.next()) {
            studentList.add(new Student(rs.getString("name"),
                    rs.getString("lastname"),
                    rs.getInt("id")));
        }
        stmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        return studentList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Student getStudente(int studentid){
        try {
            Student student = null;
            Connection conn = startConn();
            PreparedStatement pstmt;
            ResultSet rs;
            pstmt = conn.prepareStatement("SELECT * from student where id=?");
            pstmt.setInt(1, studentid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getInt("id"));
            }
            pstmt.close(); // rilascio le risorse
            conn.close(); // termino la connessione
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addStudente(Student pStudent) {
        try {
            Connection conn = startConn();
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("insert  into student (name, lastname, id) values (?,?,?)");
            pstmt.setString(1, pStudent.getNome());
            pstmt.setString(2, pStudent.getCognome());
            pstmt.setInt(3, pStudent.getId());
            pstmt.execute();
            pstmt.close(); // rilascio le risorse
            conn.close(); // termino la connessione
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudente(Student pStudent) {
        try{
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement("update student SET name =?,lastname=? where id=?");
        pstmt.setString(1, pStudent.getNome());
        pstmt.setString(2, pStudent.getCognome());
        pstmt.setInt(3, pStudent.getId());
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudente(int studentid) {
        try{
        Connection conn = startConn();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement("delete from student where id=?");
        pstmt.setInt(1, studentid);
        pstmt.execute();
        pstmt.close(); // rilascio le risorse
        conn.close(); // termino la connessione
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection startConn() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String databaseURL = "jdbc:postgresql://localhost:5432/gruppo9DB";
        return DriverManager.getConnection(databaseURL, "postgres", "admin");
    }

}
