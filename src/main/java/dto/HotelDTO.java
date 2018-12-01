package dto;

import entity.Hotel;
import entity.Room;
import java.util.ArrayList;
import java.util.List;

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
    private int lowestPrice;
    private List<RoomDTO> rooms = new ArrayList<>(); // Remember to init lists in DTO

    public HotelDTO(int id, String name, String description, int rating, int zipCode, byte[] picture, List<RoomDTO> rooms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.zipCode = zipCode;
        this.picture = picture;
        this.lowestPrice = -1;
        this.rooms = rooms;
    }

    

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.rating = hotel.getRating();
        this.zipCode = hotel.getZipCode();
        this.picture = hotel.getPicture();

        for (Room room : hotel.getRooms()) {
            rooms.add(RoomDTO.convert(room));
        }
    }
    
    
    public static HotelDTO withoutPicture(Hotel hotel) {
        return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getDescription(), hotel.getRating(), hotel.getZipCode(), null, 
        RoomDTO.convert(hotel.getRooms()));
    }
     
    public static List<HotelDTO> withoutPicture(List<Hotel> hotels) {
        List<HotelDTO> results = new ArrayList<>();
        for(Hotel hotel : hotels)
            results.add(HotelDTO.withoutPicture(hotel));
        
        return results;
    }
    
    public static HotelDTO getHotelDTO(Hotel hotel) {
        return new HotelDTO(hotel);
    }
    
    public static List<HotelDTO> getHotelDTOs(List<Hotel> hotels) {
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDTOs.add(new HotelDTO(hotel));
        }
        return hotelDTOs;
    }

    public HotelDTO(int id, String name, String description, int rating, int zipCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.zipCode = zipCode;
    }

    public HotelDTO(int id, String name, String description, int rating, int zipCode, List<RoomDTO> rooms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.zipCode = zipCode;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
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

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "HotelDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", rating=" + rating + ", zipCode=" + zipCode + ", picture=" + picture + '}';
    }

}
