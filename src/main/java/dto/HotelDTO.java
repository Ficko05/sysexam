package dto;

import entity.Hotel;

/**
 *
 * @author Kristian
 */
public class HotelDTO {
      
    private int id;
    private String name;
    private String description;
    private int rating;
    private int zipCode;
    private byte[] picture;

    public HotelDTO() {
    }

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.rating = hotel.getRating();
        this.zipCode = hotel.getZipCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    

    @Override
    public String toString() {
        return "HotelDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", rating=" + rating + ", zipCode=" + zipCode + ", picture=" + picture + '}';
    }
    
    
    


}
