package dto;

import entity.Order;
import entity.Room;
import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        
        public static List<OrderDTO> convert(List<Order> orders) {
            List<OrderDTO> orderDTOs = new ArrayList<>();
            
            for (Order order : orders) {
                OrderDTO orderDTO = convert(order);
                orderDTOs.add(orderDTO);
            }
            return orderDTOs;
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
