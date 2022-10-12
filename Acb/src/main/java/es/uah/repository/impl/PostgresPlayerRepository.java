package es.uah.repository.impl;

import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.uah.domain.Player;
import es.uah.repository.PlayerRepository;

public class PostgresPlayerRepository implements PlayerRepository{

    private static final String FIND_ALL_PLAYERS = "SELECT id, name, votes, jpgName FROM PLAYER ORDER BY votes DESC";
    private static final String FIND_PLAYER_BY_ID = "SELECT id, name, votes, jpgName FROM PLAYER WHERE id = ?";
    private static final String UPDATE_VOTE_OF_PLAYERS = "UPDATE PLAYER SET votes = ? WHERE id = ?";

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "postgres";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_VOTES = "votes";
    private static final String ATTRIBUTE_JPG_NAME = "jpgName";

    private final Connection con;

    public PostgresPlayerRepository() throws ClassNotFoundException, SQLException{
        //load Postgres driver
        Class.forName("org.postgresql.Driver");
        con = getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        System.out.println("--> Connection with database stablished");
    }

    @Override
    public List<Player> findAll(){

        PreparedStatement statement = null;

        try{
            statement = con.prepareStatement(FIND_ALL_PLAYERS);
            final ResultSet resultSet = statement.executeQuery();
            return getPlayersFromResultSet(resultSet);
        }catch(SQLException e){
            System.out.println("---> Error finding players");
            e.printStackTrace();
            return Collections.emptyList();
        }finally{
            closeStatement(statement);
        }
    }

    @Override
    public Player findById(Integer id){

        PreparedStatement statement = null;

        try{
            statement = con.prepareStatement(FIND_PLAYER_BY_ID);
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? getPlayerFromResultSet(resultSet) : null;
        }catch(SQLException e){
            System.out.println("---> Error finding player");
            e.printStackTrace();
            return null;
        }finally{
            closeStatement(statement);
        }
    }

    @Override
    public void votePlayer(Integer id){

        final Player player = findById(id);

        PreparedStatement statement = null;

        try{
            statement = con.prepareStatement(UPDATE_VOTE_OF_PLAYERS);
            statement.setInt(1, player.getVotes() + 1);
            statement.setInt(2, id);
            final int numberOfAfectedRows = statement.executeUpdate();
            System.out.println("---> Número de jugadores votados: " + numberOfAfectedRows);
        }catch(SQLException e){
            System.out.println("---> Error voting player");
            e.printStackTrace();
        }finally{
            closeStatement(statement);
        }

    }

    private void closeStatement(Statement statement){

        if(statement == null){
            return;
        }

        try{
            statement.close();
        }catch(SQLException e){
            System.out.println("---> Error closing statement");
        }
    }

    private List<Player> getPlayersFromResultSet(ResultSet resultSet) throws SQLException{
        final List<Player> players = new ArrayList<>();
        while(resultSet.next()){
            players.add(getPlayerFromResultSet(resultSet));
        }
        return players;
    }

    private Player getPlayerFromResultSet(ResultSet resultSet) throws SQLException{
        final int id = resultSet.getInt(ATTRIBUTE_ID);
        final String name = resultSet.getString(ATTRIBUTE_NAME);
        final int votes = resultSet.getInt(ATTRIBUTE_VOTES);
        final String jpgName = resultSet.getString(ATTRIBUTE_JPG_NAME);
        return new Player(id, name, votes, jpgName);
    }

}
