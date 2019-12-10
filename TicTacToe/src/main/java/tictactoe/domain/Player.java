package tictactoe.domain;

/** Player class that defines "X" and "O" player names and win records
 *
 * @author salojuur
 */
public class Player {
    
    private Integer id;
    private String name;
    private Integer wins;
    private Integer ties;
    private Integer losses;

    public Player(String name) {
        this.name = name;
    }
    
    public Player(Integer id, String name, Integer wins, Integer ties, Integer losses) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.ties = ties;
        this.losses = losses;
    }
    
    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getTies() {
        return ties;
    }

    public void setTies(Integer ties) {
        this.ties = ties;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
