package myTools;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DWP_ScoreCatcher");
	
	public static EntityManagerFactory getEmFactory(){
		return emf;
	}
	
}
