package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SCORES database table.
 * 
 */
@Entity
@Table(name="SCORES", schema="TESTUSER")
@NamedQuery(name="Score.findAll", query="SELECT s FROM Score s")
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SCORE_ID")
	private long scoreId;

	private BigDecimal score;

	@Temporal(TemporalType.DATE)
	@Column(name="SCORE_DATE")
	private Date scoreDate;

	public Score() {
	}

	public long getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
	}

	public BigDecimal getScore() {
		return this.score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Date getScoreDate() {
		return this.scoreDate;
	}

	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}

}