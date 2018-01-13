package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player extends Model {

    @Id
    long id;
    User user;
    int turnOrder;
    Game game;



}
