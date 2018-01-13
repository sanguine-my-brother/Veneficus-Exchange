package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Game extends Model {

    @Id
    private long id;

    private String name;
    private List<Player> players;
    private ExpirationSettings expirationSettings;
    private GameType gameType;
    private Turn currentTurn;
    private List<Turn> turns;


}

