package models;


import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model  {

    @Id
    private long id;
    private long steamid;
    private String playerName;
    private String avatarUrl;
    private String clientCode;
    private String email;




}
