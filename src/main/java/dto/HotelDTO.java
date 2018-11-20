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
   
    @Override
    public String toString() {
        return "HotelDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", rating=" + rating + ", zipCode=" + zipCode + ", picture=" + picture + '}';
    }
    
    
    


}
