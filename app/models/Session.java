package models;

import io.ebean.Finder;
import io.ebean.Model;
import org.joda.time.DateTime;
import util.Hyranasoftware.SHA512Generator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Session extends Model{

    @Id
    private long id;
    @ManyToOne
    private User user;
    private String sessioncode;
    private String useragent;
    private boolean valid;
    private DateTime expires;

    public Session(){

    }
    public Session(User user, String useragent){
        this.user = user;
        this.useragent = useragent;
        this.valid = true;
        this.sessioncode = SHA512Generator.SHA512(user.getPersonaname() + String.valueOf(System.currentTimeMillis()));
        this.expires = DateTime.now().plusDays(30);
        this.save();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessioncode() {
        return sessioncode;
    }

    public void setSessioncode(String sessioncode) {
        this.sessioncode = sessioncode;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public DateTime getExpires() {
        return expires;
    }

    public void setExpires(DateTime expires) {
        this.expires = expires;
    }

    public static final Finder<Long, Session> find = new Finder<>(Session.class);
}
