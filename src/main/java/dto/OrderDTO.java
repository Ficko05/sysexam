package dto;

import entity.Room;
import entity.User;
import java.util.Date;
/**
 *
 * @author Kristian
 */
public class OrderDTO {

    private User user;
    private Date startDate;
    private int days;
    private Room room;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    
    
    
}
