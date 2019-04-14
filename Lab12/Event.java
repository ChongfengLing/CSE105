package lab12.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Event {

    private LocalTime startTime;
    private LocalDate startDate;
    private LocalTime endTime;
    private LocalDate endDate;
    private String what;

    public Event(String what, LocalDateTime start, LocalDateTime end) {
        this.startDate = LocalDate.from(start);
        this.startTime = LocalTime.from(start);
        this.endDate = LocalDate.from(end);
        this.endTime = LocalTime.from(end);
        this.what = what;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getWhat() {
        return what;
    }

    public boolean periodOverlaps(LocalDate query) {
        if (query.isAfter(startDate) && query.isBefore(endDate)) {
            return true;
        } else {
            return false;
        }
    }

}
