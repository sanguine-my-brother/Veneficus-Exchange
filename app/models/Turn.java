package models;

import io.ebean.Model;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Turn extends Model {

    @Id
    private long id;
    private int turnNumber;
    private Player player;
    private DateTime started;
    private DateTime expires;
    private boolean previousPlayerSkipped;
    private boolean firstTurn;

    private String saveFilePath;
    private Game game;

    public Turn(){

    }

    public Turn(int turnNumber, Player player, boolean previousPlayerSkipped, boolean firstTurn, String saveFilePath, ExpirationSettings expirationSettings){
        this.turnNumber = turnNumber;
        this.player = player;
        this.expires = expires;
        this.previousPlayerSkipped = previousPlayerSkipped;
        this.firstTurn = firstTurn;
        this.saveFilePath = saveFilePath;

        this.started = DateTime.now();
        if(expirationSettings != null){
            this.expires = expirationSettings.getExpirationDate(this.started);
        }

        this.save();


    }


}
