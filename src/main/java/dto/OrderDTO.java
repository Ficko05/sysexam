package dto;

import entity.Order;
import entity.Room;
import entity.User;
import java.util.Date;
/**
 *
 * @author Kristian
 */
public class OrderDTO {

    private Date startDate;
    private int days;
    private RoomDTO roomDTO;

    public OrderDTO() {
    }

    public OrderDTO(Date startDate, int days, RoomDTO roomDTO) {
        this.startDate = startDate;
        this.days = days;
        this.roomDTO = roomDTO;
    }
    
        public OrderDTO(Date startDate, int days, Room room) {
        this.startDate = startDate;
        this.days = days;
     this.roomDTO = RoomDTO.convert(room);
    }
        
        public static OrderDTO convert(Order order){
           return new OrderDTO(order.getStartDate(), order.getDays(), order.getRoom()); 
        }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
