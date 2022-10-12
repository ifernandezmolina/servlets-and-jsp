package es.uah.domain;

public class Player{

    private final Integer id;
    private final String name;
    private final Integer votes;
    private final String jpgImage;

    public Player(Integer id, String name, Integer votes, String jpsImage){
        this.id = id;
        this.name = name;
        this.votes = votes;
        this.jpgImage = jpsImage;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Integer getVotes(){
        return votes;
    }

    public String getJpgImage(){
        return jpgImage;
    }
}
