/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Room;
import entity.User;

/**
 *
 * @author Dradrach
 */
public class ReceivedBookingDTO {
    private String startDate;
    private Integer days;
    private Integer roomID;

    public ReceivedBookingDTO() {
    }

    public ReceivedBookingDTO(String startDate, Integer days, Integer roomID) {
        this.startDate = startDate;
        this.days = days;
        this.roomID = roomID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }
    
    
}
