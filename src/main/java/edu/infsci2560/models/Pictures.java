
package edu.infsci2560.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;  // 增加日期
import java.text.SimpleDateFormat;

@Entity
public class Pictures {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String dateCreated;
	protected String picPath;
	protected Long picColorID;
  protected Long[] match_PaID;

	public Pictures(){
		this.id = Long.MAX_VALUE;
		this.dateCreated = null;
		this.picPath = null;
		this.picColorID = null;
		this.match_PaID = null;
	}
	                                                                           //MultipartFile image,
	public Pictures( Long id, String picPath, Long picColorID,
  Long[] match_PaID){
		this.id = id;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.dateCreated = formatter.format(new Date());
    this.picPath = picPath;
		this.picColorID = picColorID;
		this.match_PaID = match_PaID;
	}

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

	/**
	* Returns value of picPath
	* @return
	*/
	public String getPicPath() {
		return picPath;
	}

	/**
	* Sets new value of picPath
	* @param
	*/
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	/**
	* Returns value of picColorID
	* @return
	*/
	public Long getPicColorID() {
		return picColorID;
	}

	/**
	* Sets new value of picColorID
	* @param
	*/
	public void setPicColorID(Long picColorID) {
		this.picColorID = picColorID;
	}

	/**
	* Returns value of g
	* @return
	*/
	public Long[] getMatch_PaID() {
		return match_PaID;
	}

	/**
	* Sets new value of g
	* @param
	*/
	public void setMatch_PaID(Long match_PaID) {
		this.match_PaID = match_PaID;
	}

}
