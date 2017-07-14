package edu.infsci2560.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PicColors {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String[] colors;


	public PicColors() {
		this.id = Long.MAX_VALUE;
		this.colors = null;
	}

	/**
	* Default PicColors constructor
	*/
	public PicColors(Long id, String[] colors) {
		this.id = id;
		this.colors = colors;
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
	* Returns value of g
	* @return
	*/
	public String[] getColors() {
		return colors;
	}

	/**
	* Sets new value of g
	* @param
	*/
	public void setColors(String[] colors) {
		this.colors = colors;
	}

}
