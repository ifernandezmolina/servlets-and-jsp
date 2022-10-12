package es.uah.repository;

import java.sql.SQLException;
import java.util.List;

import es.uah.domain.Player;

public interface PlayerRepository{

    List<Player> findAll();

    Player findById(Integer id);

    void votePlayer(Integer playeridId);

}
