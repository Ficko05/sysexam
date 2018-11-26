package dto;

import entity.Hotel;

/**
 *
 * @author Kristian
 */
public class RoomDTO {
    
 private Hotel hotel;
 private int price;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
 
 

}
