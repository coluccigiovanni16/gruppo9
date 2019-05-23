package com.gruppo9;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EventDao {
    String path = "C:\\Users\\jiovy\\Desktop\\gruppo9\\events.txt";

    public LinkedList<Event> getAllEvents() {
        LinkedList<Event> eventList = null;
        try {
            File file = new File( path );
            if (!file.exists()) {
                eventList = new LinkedList<Event>();
                saveEventList( eventList );
            } else {
                FileInputStream fis = new FileInputStream( file );
                ObjectInputStream ois = new ObjectInputStream( fis );
                eventList = (LinkedList<Event>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public Event getEvent(String name) {
        List<Event> events = getAllEvents();
        for (Event event : events) {
            if (event.getNome().equalsIgnoreCase( name )) {
                return event;
            }
        }
        return null;
    }

    public LinkedList<Event> getEventsByTeacher(int id) {
        List<Event> events = getAllEvents();
        LinkedList<Event> result = new LinkedList<Event>();
        for (Event event : events) {
            if (event.getTeacher() == id) {
                result.add( event );
            }
        }
        return result;
    }

    public int addEvent(Event pEvent) {
        LinkedList<Event> eventList = getAllEvents();
        boolean userExists = false;
        for (Event event : eventList) {
            if (event.equals( pEvent )) {
                userExists = true;
                break;
            }
        }
        if (!userExists) {
            eventList.add( pEvent );
            saveEventList( eventList );
            return 1;
        }
        return 0;
    }

    public int updateEvent(Event pEvent) {
        List<Event> eventList = getAllEvents();
        for (Event event : eventList) {
            if (event.getNome().equalsIgnoreCase( pEvent.getNome() )) {
                int index = eventList.indexOf( event );
                eventList.set( index, pEvent );
                saveEventList( eventList );
                return 1;
            }
        }
        return 0;
    }

    public int deleteEvent(String idE, int idT) {
        List<Event> eventList = getAllEvents();

        for (Event event : eventList) {
            if (event.getType().equalsIgnoreCase( idE ) && event.getTeacher() == idT) {
                int index = eventList.indexOf( event );
                eventList.remove( index );
                saveEventList( eventList );
                return 1;
            }
        }
        return 0;
    }

    private void saveEventList(List<Event> eventList) {
        try {
            File file = new File( path );
            FileOutputStream fos;
            fos = new FileOutputStream( file );
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.writeObject( eventList );
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int updateEventPart(Student student, String nameEvent, int idT, boolean request) {
        LinkedList<Event> eventList = getAllEvents();
        for (Event event : eventList) {
            if (event.getNome().equals( nameEvent ) && event.getTeacher() == idT) {
                if (!event.getParticipants().containsKey( student ) && request) {
                    event.getParticipants().put( student, false );
                } else if (event.getParticipants().containsKey( student ) && !request) {
                    event.getParticipants().remove( student );
                }
                return 1;
            }
        }
        return 0;
    }

    public Map<Student, Boolean> getPartecipants(String idE, int idT) {
        LinkedList<Event> eventList = getAllEvents();
        for (Event event : eventList) {
            if (event.getType().equals( idE ) && event.getTeacher() == idT) {
                return event.getParticipants();
            }
            return null;
        }
        return null;
    }

    public void setPatecipants(String idE, int idT, Map<Student, Boolean> partecipants) {
        LinkedList<Event> eventList = getAllEvents();
        for (Event event : eventList) {
            if (event.getNome().equals( idE ) && event.getTeacher() == idT) {
                event.setParticipants( partecipants );
            }
        }
        saveEventList( eventList );
    }
}