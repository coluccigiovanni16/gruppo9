package com.gruppo9;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    private int date;
    private String type;
    private String description;
    private String teacher;
    private List<Student> participants;

    public Event() {
    }

    public Event(int date, String type, String description, String teacher, List<Student> participants) {
        this.date = date;
        this.type = type;
        this.description = description;
        this.teacher = teacher;
        this.participants = participants;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getDate() {
        return date;
    }

    @XmlElement
    public void setDate(int date) {
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

    public String getTeacher() {
        return teacher;
    }

    @XmlElement
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<Student> getParticipants() {
        return participants;
    }

    @XmlElement
    public void setParticipants(List<Student> participants) {
        this.participants = participants;
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
                    && teacher.equals( event.getTeacher() )
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", teacher='" + teacher + '\'' +
                ", participants=" + participants +
                '}';
    }
}