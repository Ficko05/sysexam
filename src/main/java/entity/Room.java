package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Kristian
 */
@Entity
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private int price;

    public Room(Integer id, int price, Hotel hotel) {
        this.id = id;
        this.price = price;
        this.hotel = hotel;
    }

    public Room(Integer id, int price) {
        this.id = id;
        this.price = price;
    }
    
    @ManyToOne
    private Hotel hotel;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    } 
    
    public Integer getId() {
        return id;
    }

    
}
