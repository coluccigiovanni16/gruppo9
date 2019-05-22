package com.gruppo9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    String path = "C:\\Users\\jiovy\\Desktop\\gg\\events.txt";

    public List<Event> getAllEvents() {
        List<Event> eventList = null;
        try {
            File file = new File( path );
            if (!file.exists()) {
//                LinkedList<Participant> participants =null;
//                participants.add( new Participant( "gggi" ));
//                participants.add(   new Participant( "fdfi" ));
                Event event = new Event( 1, "tech1", "good1", "gg1", null );
                Event event1 = new Event( 2, "tech2", "good2", "gg2", null );
                eventList = new ArrayList<Event>();
                eventList.add( event );
                eventList.add( event1 );
                saveEventList( eventList );
            } else {
                FileInputStream fis = new FileInputStream( file );
                ObjectInputStream ois = new ObjectInputStream( fis );
                eventList = (List<Event>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public Event getEvent(String type) {
        List<Event> events = getAllEvents();
        for (Event event : events) {
            if (event.getType().equalsIgnoreCase( type )) {
                return event;
            }
        }
        return null;
    }

    public int addEvent(Event pEvent) {
        List<Event> eventList = getAllEvents();
        boolean userExists = false;
        for (Event event : eventList) {
            if (event.getType().equalsIgnoreCase( pEvent.getType() )) {
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
            if (event.getType().equalsIgnoreCase( pEvent.getType() )) {
                int index = eventList.indexOf( event );
                eventList.set( index, pEvent );
                saveEventList( eventList );
                return 1;
            }
        }
        return 0;
    }

    public int deleteEvent(String type) {
        List<Event> eventList = getAllEvents();

        for (Event event : eventList) {
            if (event.getType().equalsIgnoreCase( type )) {
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
}