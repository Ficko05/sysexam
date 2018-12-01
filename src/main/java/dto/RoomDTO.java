package dto;

import entity.Hotel;
import entity.Room;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kristian
 */
public class RoomDTO {
    
 private int id;
 private int price;

    private RoomDTO(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public static RoomDTO convert(Room room){
        return new RoomDTO(room.getId(), room.getPrice());
    }
    
        public static List<RoomDTO> convert(List<Room> rooms){
            List<RoomDTO> results = new ArrayList<>();
            for(Room room : rooms)
                results.add(convert(room));
            
            return results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
 
 

}
