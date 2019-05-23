package com.gruppo9;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private Date date;
    private String type;
    private String description;
    private int teacher;
    private Map<Student, Boolean> participants;

    public Event() {
    }

    public Event(String nome, Date date, String type, String description, int teacher) {
        this.nome = nome;
        this.date = date;
        this.type = type;
        this.description = description;
        this.teacher = teacher;
        this.participants = new HashMap<Student, Boolean>(  );
    }

    public Event(String nome, Date date, String type, String description, int teacher, Map<Student, Boolean> participants) {
        this.nome = nome;
        this.date = date;
        this.type = type;
        this.description = description;
        this.teacher = teacher;
        this.participants = participants;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getDate() {
        return date;
    }

    @XmlElement
    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeacher() {
        return teacher;
    }

    @XmlElement
    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public Map<Student, Boolean> getParticipants() {
        return participants;
    }

    @XmlElement
    public void setParticipants(Map<Student, Boolean> participants) {
        this.participants = participants;
    }

    public String getNome() {
        return nome;
    }

    @XmlElement
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof Event)) {
            return false;
        } else {
            Event event = (Event) object;
            if (date == event.getDate()
                    && type.equals( event.getType() )
                    && description.equals( event.getDescription() )
                    && teacher==( event.getTeacher() )
                    && participants == event.getParticipants()
                    && nome.equals( event.getNome() )
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Event{" +
                "nome='" + nome + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", teacher='" + teacher + '\'' +
                ", participants=" + participants +
                '}';
    }
}