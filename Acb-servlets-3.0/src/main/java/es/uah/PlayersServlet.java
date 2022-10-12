package es.uah;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uah.domain.Player;
import es.uah.repository.PlayerRepository;
import es.uah.repository.impl.PostgresPlayerRepository;

@WebServlet(name = "PlayersServlet", urlPatterns = "/players")
public class PlayersServlet extends HttpServlet{

    private final PlayerRepository playerRepository;

    public PlayersServlet() throws SQLException, ClassNotFoundException{
        super();
        playerRepository = new PostgresPlayerRepository();
    }

    @Override
    public void init(){
        System.out.println("--> Initializing Acb Servlet");
    }

    @Override
    public void destroy(){
        System.out.println("--> Destroying ACBController. Bye Bye!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        final List<Player> allPlayers = playerRepository.findAll();
        req.setAttribute("players", allPlayers);
        req.getRequestDispatcher("WEB-INF/views/players.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        final Integer selectedPLayerId = Integer.valueOf(req.getParameter("player"));
        playerRepository.votePlayer(selectedPLayerId);

        resp.sendRedirect(req.getContextPath() + "/players");
    }
}