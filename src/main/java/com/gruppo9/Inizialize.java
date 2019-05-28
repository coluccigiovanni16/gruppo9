package com.gruppo9;

import javax.ws.rs.Path;
import java.util.Scanner;

@Path("/")
public class Inizialize {

    private static final String SUCCESS_RESULT = "<result>success</result>";

    public static void main(String[] args) {
        EventDao eventDao = new EventDao();
        StudentDao studentDao = new StudentDao();
        TeacherDao teacherDao = new TeacherDao();
        //Istanzia scanner con standard input
        Scanner scan = new Scanner( System.in );
        //Chiede di introdurre il tipo
        int category = -1;
        do {
            System.out.println( "Introduci categoria:\n 1)Teacher\n 2)Student" );
            category = scan.nextInt();
        }
        while (category == 1 || category == 2);
        //Chiede di introdurre nome e cognome
        System.out.println( "Introduci nome :" );
        String nome = scan.nextLine(); //qui il programma attende l'immissione dei dati
        System.out.println( "Introduci  cognome:" );
        String cognome = scan.nextLine(); //qui il programma attende l'immissione dei dati
        //Chiede di introdurre la matricola
        System.out.println( "Introduci il tuo identificativo:" );
        int id = scan.nextInt();
        if (category == 1) {
            Teacher t = new Teacher( nome, cognome, id );
            teacherDao.addDocente( t );
            MenuTeacher menuTeacher = new MenuTeacher( t );
        } else if (category == 1) {
            Student s = new Student( nome, cognome, id );
            studentDao.addStudente( s );
            MenuStudent menuStudent = new MenuStudent( s );
        }


    }


}
