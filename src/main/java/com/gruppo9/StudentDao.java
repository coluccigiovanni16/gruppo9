package com.gruppo9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    String path = "C:\\Users\\jiovy\\Desktop\\gg\\studenti.txt";

    public List<Student> getAllStudenti() {
        List<Student> studentiList = null;
        try {
            File file = new File( path );
            if (!file.exists()) {
                studentiList = new ArrayList<Student>();
                Student s1 = new Student( "name", "prename", 1, 0, 0 );
                studentiList.add( s1 );
                saveStudentList( studentiList );
            } else {
                FileInputStream fis = new FileInputStream( file );
                ObjectInputStream ois = new ObjectInputStream( fis );
                studentiList = (List<Student>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return studentiList;
    }

    public Student getStudente(int id) {
        List<Student> students = getAllStudenti();
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public int addStudente(Student pStudent) {
        List<Student> students = getAllStudenti();
        boolean studentExists = false;
        for (Student student : students) {
            if (student.getId() == (pStudent.getId())) {
                studentExists = true;
                break;
            }
        }
        if (!studentExists) {
            students.add( pStudent );
            saveStudentList( students );
            return 1;
        }
        return 0;
    }

    public int updateStudente(Student pStudent) {
        List<Student> students = getAllStudenti();
        for (Student student : students) {
            if (student.getId() == pStudent.getId()) {
                int index = students.indexOf( student );
                students.set( index, pStudent );
                saveStudentList( students );
                return 1;
            }
        }
        return 0;
    }

    public int deleteStudente(int id) {
        List<Student> students = getAllStudenti();
        for (Student student : students) {
            if (student.getId() == id) {
                int index = students.indexOf( student );
                students.remove( index );
                saveStudentList( students );
                return 1;
            }
        }
        return 0;
    }


    private void saveStudentList(List<Student> studentList) {
        try {
            File file = new File( path );
            FileOutputStream fos;
            fos = new FileOutputStream( file );
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.writeObject( studentList );
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
