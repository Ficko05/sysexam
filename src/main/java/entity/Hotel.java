package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Kristian
 */
@Entity
@Table(name = "HOTEL")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(nullable = false, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50, name = "NAME")
    private String name;

    @Column(nullable = false, length = 2500, name = "DESCRIPTION")
    private String description;

    @Column(nullable = false, length = 2, name = "RATING")
    private int rating;

    @Column(nullable = false, length = 5, name = "ZIPCODE")
    private int zipCode;

    @Column(length = 30_000, name = "PICTURE")
    private byte[] picture;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="hotel")
    private List<Room> rooms = new ArrayList<>();

    public Hotel() {
    }

    public Hotel(Integer id, String name, String description, int rating, int zipCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.zipCode = zipCode;
    }
    
    public Hotel(Integer id, String name, String description, int rating, int zipCode, byte[] picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.zipCode = zipCode;
        this.picture = picture;
    }

    public Hotel(Integer id, String name, String description, int rating, int zipCode, byte[] picture, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.zipCode = zipCode;
        this.picture = picture;
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    public void removePicture(){
        this.picture = null;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + this.rating;
        hash = 97 * hash + this.zipCode;
        hash = 97 * hash + Arrays.hashCode(this.picture);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hotel other = (Hotel) obj;
        if (this.rating != other.rating) {
            return false;
        }
        if (this.zipCode != other.zipCode) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Arrays.equals(this.picture, other.picture)) {
            return false;
        }
        return true;
    }
    
    

}
