package models;

import io.ebean.Model;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExpirationSettings extends Model {

    @Id
    private long id;
    private int days;
    private int minutes;
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    private DateTime cached;

    public ExpirationSettings(){

    }

    public ExpirationSettings(int days, int minutes){
        this.days = days;
        this.minutes = minutes;
    }


    public DateTime getExpirationDate(DateTime turnstarted){
        int i = 0;
        DateTime expirationDate = turnstarted;
        while(i != days){
            expirationDate.plusDays(1);
            switch (expirationDate.dayOfWeek().getAsText()) {
                case "Monday": if(!monday){ expirationDate.plusDays(1); i--; }
                case "Tuesday": if(!tuesday){ expirationDate.plusDays(1); i--; }
                case "Wednesday": if(!wednesday){ expirationDate.plusDays(1); i--; }
                case "Thursday": if(!thursday){ expirationDate.plusDays(1); i--; }
                case "Friday": if(!friday){ expirationDate.plusDays(1); i--; }
                case "Saturday": if(!saturday){ expirationDate.plusDays(1); i--; }
                case "Sunday": if(!sunday){ expirationDate.plusDays(1); i--; }
            }
            i++;
            if(i == days){
                expirationDate.plusMinutes(minutes);
            }
        }
        this.cached = expirationDate;
        return expirationDate;

    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public DateTime getCached() {
        return cached;
    }

    public void setCached(DateTime cached) {
        this.cached = cached;
    }
}
