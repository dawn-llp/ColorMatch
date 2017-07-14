package edu.infsci2560.coordinator;
// 临时的类 没有java persistence api

public class SortedPalette {

	private Long id;
	private String sourceType;
  private String sourceID;
  private String author;
  private String[] colors;
	private float rating;
	private int numViews;
	private int numVotes;
//  private float colorDistance;

	/**
	* Default empty MatchPalettes constructor
	*/
	public SortedPalette() {
    this.id = Long.MAX_VALUE;
    this.sourceType = null;
    this.sourceID = null;
    this.author = null;
    this.colors = null;
    this.rating = null; // from souce info
    this.numViews = null; // this site users' actions
    this.numVotes = null;
		//this.colorDistance = null;
	}

	/**
	* Default MatchPalettes constructor
	*/
	public SortedPalette(Long id, String sourceType, String sourceID, String author,
  String[] colors, float rating, int numViews, int numVotes) {
		this.id = id;
		this.sourceType = sourceType;
		this.sourceID = sourceID;
		this.author = author;
		this.colors = colors;
		this.rating = rating; // from souce info
		this.numViews = numViews; // this site users' actions
		this.numVotes = numVotes;
	//	this.colorDistance = colorDistance;
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
	* Returns value of sourceType
	* @return
	*/
	public String getSourceType() {
		return sourceType;
	}

	/**
	* Sets new value of sourceType
	* @param
	*/
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	* Returns value of sourceID
	* @return
	*/
	public String getSourceID() {
		return sourceID;
	}

	/**
	* Sets new value of sourceID
	* @param
	*/
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}

	/**
	* Returns value of author
	* @return
	*/
	public String getAuthor() {
		return author;
	}

	/**
	* Sets new value of author
	* @param
	*/
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	* Returns value of g
	* @return
	*/
	public String getColors() {
		return colors;
	}

	/**
	* Sets new value of g
	* @param
	*/
	public void setColors(String[] colors) {
		this.colors = colors;
	}

	/**
	* Returns value of rating
	* @return
	*/
	public float getRating() {
		return rating;
	}

	/**
	* Sets new value of rating
	* @param
	*/
	public void setRating(float rating) {
		this.rating = rating;
	}

	/**
	* Returns value of numViews
	* @return
	*/
	public int getNumViews() {
		return numViews;
	}

	/**
	* Sets new value of numViews
	* @param
	*/
	public void setNumViews(int numViews) {
		this.numViews = numViews;
	}

	/**
	* Returns value of numVotes
	* @return
	*/
	public int getNumVotes() {
		return numVotes;
	}

	/**
	* Sets new value of numVotes
	* @param
	*/
	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}

}
