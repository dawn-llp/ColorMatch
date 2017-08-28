/*

package edu.infsci2560.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.web.multipart.MultipartFile;



@Entity
public class LipicUsersPictures {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String dateCreated;
	protected Long paletteIdMatched;
	protected String imageName;
	protected String[] colors;

	public LipicUsersPictures(){
		this.id = Long.MAX_VALUE;
		this.dateCreated = null;
		this.paletteIdMatched = null;
		this.image = null;
		this.colors = null;
	}
	                                                                           //MultipartFile image,
	public LipicUsersPictures( Long id, String dateCreated, Long paletteIdMatched, String imageName,  String[] colors){
		this.id = id;
		this.dateCreated = dateCreated;
		this.paletteIdMatched = paletteIdMatched;
		this.imageName = imageName;
		this.colors = colors;
	}

	public Long getId() {return id;}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDateCreated() {return dateCreated;}
	public void setDateCreated( String dateCreated){
		this.dateCreated = dateCreated;
	}

	public Long getPaletteIdMatched() {return paletteIdMatched;}
	public void setPaletteIdMacthed( Long paletteIdMatched) {
		this.paletteIdMatched = paletteIdMatched;
	}

	public String getImageName() {return imageName;}
	public void setImageName( String imageName){
		this.imageName = imageName;
	}

	public String[] getColors() {return colors;}
	public void setColors( String[] colors) {
		this.colors = colors;
	}
}
*/
