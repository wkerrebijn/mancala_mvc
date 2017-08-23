package nl.Servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import nl.sogyo.mancala.*;

public class MancalaServlet extends HttpServlet {
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		Game game = (Game) session.getAttribute("game");

		if(game == null) {
			game = new Game();
		}

		boolean restart = false;

		try {
			restart = Boolean.parseBoolean((request.getParameter("restart")));
		} catch(Exception e) {}

		if(restart) {
			game.restart();
		}

		try {
			int holeNumber = Integer.parseInt(request.getParameter("picked"));
			game.getFields().get(holeNumber).play();
		} catch(Exception e) {}

		session.setAttribute("game", game);

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");

		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}

