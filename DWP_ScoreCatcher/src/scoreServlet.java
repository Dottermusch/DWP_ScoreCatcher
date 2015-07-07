

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Score;
import myTools.DBUtil;

/**
 * Servlet implementation class scoreServlet
 */
@WebServlet("/scoreServlet")
public class scoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public scoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String url = null;
		String action = request.getParameter("action");
		String scoreString = request.getParameter("score");
		
		
		
		if (action.equals("addScore"))
		{
			if (scoreString == null || scoreString.equals(""))
				scoreString = "0";
			BigDecimal scoreValue = new BigDecimal(scoreString);
			url = "/getScore.jsp";
			Score score = new Score();
			score.setScore(scoreValue);
			
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			
			try
			{
				trans.begin();
				em.persist(score);
				trans.commit();
			}
			catch (Exception e)
			{
				trans.rollback();
				System.out.println("A problem occurred while inserting score: " + e);
			}
			finally 
			{
				em.close();
			}
		}
		else if (action.endsWith("calcAverage"))
		{
			NumberFormat number = NumberFormat.getInstance();
			number.setMaximumIntegerDigits(2);
			number.setMinimumFractionDigits(2);
			url = "/displayScores.jsp";
			
			// set up query to get list of scores from database
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "SELECT s FROM Score s";
			TypedQuery<Score> q = em.createQuery(qString, Score.class);
			BigDecimal total = new BigDecimal(0);
			
			
			List<Score> scores = null;
			
			try	// actually get the scores from the database
			{
				scores = q.getResultList();
				if (scores == null || scores.isEmpty())
					scores = null;
			}
			catch (Exception e)
			{
				System.out.println("A problem occurred while retrieving scores: " + e);
			}
			finally 
			{
				em.close();
			}
			
			if (scores == null)	// allow for case where no scores already entered
			{
				request.setAttribute("message", "No scores retrieve to calculate average.");
			}
			else
			{
				for (Score s : scores)	// generate the total of the scores entered so far
				{
					total = total.add(s.getScore());
				}
				
				int numScores = scores.size();	// get the number of scores
				double totalDouble = Double.parseDouble(total.toString());
				double average = totalDouble/numScores;

				request.setAttribute("scores", scores);	// feed the scores to the request variable for display on next page
				
				String averageString = number.format(average);
				request.setAttribute("average", averageString);
			}
						
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}

}
