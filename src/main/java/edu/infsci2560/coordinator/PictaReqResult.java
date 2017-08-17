package edu.infsci2560.coordinator;
// 临时的类 没有java persistence api
public class PictaReqResult{
	private Long picColorId;
	private Long[] palettesId;


	/**
	* Default empty PictaReqResult constructor
	*/
	public PictaReqResult() {
		super();
	}

	/**
	* Default PictaReqResult constructor
	*/
	public PictaReqResult(Long picColorId, Long[] palettesId) {
		super();
		this.picColorId = picColorId;
		this.palettesId = palettesId;
	}


	/**
	* Returns value of picColorId
	* @return
	*/
	public Long getPicColorId() {
		return picColorId;
	}

	/**
	* Sets new value of picColorId
	* @param
	*/
	public void setPicColorId(Long picColorId) {
		this.picColorId = picColorId;
	}

	/**
	* Returns value of palettesId
	* @return
	*/
	public Long[] getPalettesId() {
		return palettesId;
	}

	/**
	* Sets new value of palettesId
	* @param
	*/
	public void setPalettesId(Long[] palettesId) {
		this.palettesId = palettesId;
	}
}
