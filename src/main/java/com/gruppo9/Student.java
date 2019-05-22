package com.gruppo9;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String cognome;
    private int id;
    private int conferma;
    private int richiesta;

    public Student() {
    }

    public Student(String nome, String cognome, int id, int conferma, int richiesta) {
        this.nome = nome;
        this.cognome = cognome;
        this.id = id;
        this.conferma = conferma;
        this.richiesta = richiesta;
    }


    public String getNome() {
        return nome;
    }

    @XmlElement
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    @XmlElement
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public int getConferma() {
        return conferma;
    }

    public int getRichiesta() {
        return richiesta;
    }

    @XmlElement
    public void setConferma(int conferma) {
        this.conferma = conferma;
    }


    @XmlElement
    public void setRichiesta(int richiesta) {
        this.richiesta = richiesta;
    }


    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof Student)) {
            return false;
        } else {
            Student student = (Student) object;
            if (id == student.getId()
                    && nome.equals( student.getNome() )
                    && cognome.equals( student.getCognome() )
                    && richiesta == (student.getRichiesta())
                    && conferma == (student.getConferma())
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", id='" + id + '\'' +
                ", conferma=" + conferma +
                ", richiesta=" + richiesta +
                '}';
    }
}
