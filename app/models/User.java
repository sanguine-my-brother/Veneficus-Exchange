package models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mashape.unirest.http.Unirest;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Model  {

    @Id
    private long id;
    private long steamid;
    private String personaname;
    private String avatar;
    private String avatarfull;
    private String clientCode;
    private String email;
    private String profileurl;

    private String loccountrycode;
    @OneToMany(mappedBy="user")
    private List<Session> sessionList;

    public User(){

    }

    public void newUser(){
        this.clientCode = getPlayercodeString();
        this.save();
    }

    protected String getPlayercodeString() {
        String CODECHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        while (code.length() < 10) {
            int index = (int) (rnd.nextFloat() * CODECHARS.length());
            code.append(CODECHARS.charAt(index));
        }
        String codeStr = code.toString();
        return codeStr;

    }

    public long getSteamid() {
        return steamid;
    }

    public void setSteamid(long steamid) {
        this.steamid = steamid;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getLoccountrycode() {
        return loccountrycode;
    }

    public void setLoccountrycode(String loccountrycode) {
        this.loccountrycode = loccountrycode;
    }

    public String getAvatarfull() {
        return avatarfull;
    }

    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public static final Finder<Long, User> find = new Finder<>(User.class);


}
