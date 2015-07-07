package DWP_ScoreCatcher;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;

import myTools.DBUtil;
import model.Score;

public class ScoreTest
{

	@Test
	public void testGetScore()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		try 
		{
			Score score = em.find(Score.class, (long)751);
			
			assertEquals (new BigDecimal(78), score.getScore());
		} 
		catch (Exception e)
		{
			fail("Method getCustEmail failed: " + e.getMessage());
		}	
		finally
		{
			em.close();
		}
	}
	
	@Test
	public void testGetAverage()
	{
		// first get the scores from the database
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT s FROM Score s WHERE s.scoreId < :score_Id";
		TypedQuery<Score> q = em.createQuery(qString, Score.class);
		q.setParameter("score_Id", new BigDecimal(803));
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
		
		for (Score s : scores)
		{
			total = total.add(s.getScore());
		}
		
		
		
		try
		{
			BigDecimal average = total.divide(new BigDecimal(new Integer(scores.size())));
			
			assertEquals (new BigDecimal("68.8"), average);
		}
		catch (Exception e)
		{
			fail("Method for calculating average failed: " + e.getMessage());
		}	
		finally
		{
			
		}
	}

}
