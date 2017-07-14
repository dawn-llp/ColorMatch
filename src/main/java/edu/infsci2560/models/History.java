package edu.infsci2560.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;  // 增加日期
import java.text.SimpleDateFormat;

@Entity
public class History {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected Long userID;
	protected Long pictureID;
  protected Long[] match_PaID;
  protected Long vote_match_PaID;
  protected String dateCreated;

	/**
	* Default empty MatchPalettes constructor
	*/
	public History() {
    this.id = Long.MAX_VALUE;
		this.userID = 0L;
    this.pictureID = null;
    this.match_PaID = null;
    this.vote_match_PaID = null;
    this.dateCreated = null;
	}

	/**
	* Default MatchPalettes constructor
	*/
	public History(Long id, Long pictureID, Long[] match_PaID, Long vote_match_PaID) {
		this.id = id;
		this.userID = 0L;
		this.pictureID = pictureID;
		this.match_PaID = match_PaID;
		this.vote_match_PaID = vote_match_PaID;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.dateCreated = formatter.format(new Date());


	/**
	* Returns value of id
	* @return
	*/
	public Long getId() {
		return id;
	}

	/**
	* Sets new value of id
	* @param
	*/
	public void setId(Long id) {
		this.id = id;
	}

	/**
	* Returns value of userid
	* @return
	*/
	public Long getUserID() {
		return userID;
	}

	/**
	* Sets new value of userID
	* @param
	*/
	public void setUserID(Long userID) {
		this.userID = userID;
	}

	/**
	* Returns value of pictureID
	* @return
	*/
	public Long getPictureID() {
		return pictureID;
	}

	/**
	* Sets new value of pictureID
	* @param
	*/
	public void setPictureID(Long pictureID) {
		this.pictureID = pictureID;
	}

	/**
	* Returns value of g
	* @return
	*/
	public Long getMatch_PaID() {
		return match_PaID;
	}

	/**
	* Sets new value of g
	* @param
	*/
	public void setMatch_PaID(Long[] match_PaID) {
		this.match_PaID = match_PaID;
	}

	/**
	* Returns value of vote_match_PaID
	* @return
	*/
	public Long getVote_match_PaID() {
		return vote_match_PaID;
	}

	/**
	* Sets new value of vote_match_PaID
	* @param
	*/
	public void setVote_match_PaID(Long vote_match_PaID) {
		this.vote_match_PaID = vote_match_PaID;
	}

	/**
	* Returns value of dateCreated
	* @return
	*/
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	* Sets new value of dateCreated
	* @param
	*/
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void updateDateCreated() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.dateCreated = formatter.format(new Date());
	}
}
