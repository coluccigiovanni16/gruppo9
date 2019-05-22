package com.gruppo9;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String cognome;
    private int id;
    private int conferma;
    private int richiesta;

    public User() {
    }

    public User(int id,String name, String cognome,  int conferma, int richiesta) {
        this.name = name;
        this.cognome = cognome;
        this.id = id;
        this.conferma = conferma;
        this.richiesta = richiesta;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getCognome() {
        return cognome;
    }

    @XmlElement
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getConferma() {
        return conferma;
    }

    @XmlElement
    public void setConferma(int conferma) {
        this.conferma = conferma;
    }

    public int getRichiesta() {
        return richiesta;
    }

    @XmlElement
    public void setRichiesta(int richiesta) {
        this.richiesta = richiesta;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (!(object instanceof User)) {
            return false;
        } else {
            User user = (User) object;
            if (id == user.getId()
                    && name.equals( user.getName() )
                    && cognome.equals( user.getCognome() )
                    && richiesta == (user.getRichiesta())
                    && conferma == (user.getConferma())
            ) {
                return true;
            }
        }
        return false;
    }
}