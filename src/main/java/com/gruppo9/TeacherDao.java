package com.gruppo9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {

    String path = "C:\\Users\\jiovy\\Desktop\\gruppo9\\teachers.txt";
    //    String path = "C:\\Users\\valer\\Documents\\GitHub\\gruppo9\\teachers.txt";


    public List<Teacher> getAllDocenti() {
        List<Teacher> teachersList = null;
        try {
            File file = new File( path );
            if (!file.exists()) {
                teachersList = new ArrayList<Teacher>();
                saveDocentiList( teachersList );
            } else {
                FileInputStream fis = new FileInputStream( file );
                ObjectInputStream ois = new ObjectInputStream( fis );
                teachersList = (List<Teacher>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teachersList;
    }

    public Teacher getDocente(int id) {
        List<Teacher> teachers = getAllDocenti();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public int addDocente(Teacher pTeacher) {
        List<Teacher> teachers = getAllDocenti();
        boolean teacherExists = false;
        for (Teacher teacher : teachers) {
            if (teacher.equals( pTeacher )) {
                teacherExists = true;
                break;
            }
        }
        if (!teacherExists) {
            teachers.add( pTeacher );
            saveDocentiList( teachers );
            return 1;
        }
        return 0;
    }

    public int updateDocente(Teacher pTeacher) {
        List<Teacher> teachers = getAllDocenti();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == pTeacher.getId()) {
                int index = teachers.indexOf( teacher );
                teachers.set( index, pTeacher );
                saveDocentiList( teachers );
                return 1;
            }
        }
        return 0;
    }

    public int deleteDocente(int id) {
        List<Teacher> teachers = getAllDocenti();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                int index = teachers.indexOf( teacher );
                teachers.remove( index );
                saveDocentiList( teachers );
                return 1;
            }
        }
        return 0;
    }


    private void saveDocentiList(List<Teacher> teacherList) {
        try {
            File file = new File( path );
            FileOutputStream fos;
            fos = new FileOutputStream( file );
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.writeObject( teacherList );
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
