package edu.infsci2560.coordinator;

import edu.infsci2560.models.MatchPalettes;
import edu.infsci2560.repositories.MatchPalettesRepository;
import edu.infsci2560.models.Pictures;
import edu.infsci2560.repositories.PicturesRepository;
import edu.infsci2560.models.PicColors;
import edu.infsci2560.repositories.PicColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import edu.infsci2560.coordinator.PictaReqResult;
import edu.infsci2560.coordinator.SortedPalettes;
import edu.infsci2560.coordinator.SortedPalette;
import java.util.List;

public class RankModule {
    private PicColors pic;
    private List<MatchPalettes> unsorted;
    private List<MatchPalettes> sorted;
    private float[] rankValue;

    public RankModule() {
        super();
    }

    public RankModule(PicColors pic, List<MatchPalettes> unsorted){
      this.pic = pic;
      this.unsorted = unsorted;
      this.sorted = null;
      this.rankValue = null;
    }

    public void rankFunction(){
      //get rankValue
      int size = unsorted.size();
      for (int i = 0; i < size; i++) {
        rankValue[i] = (float) (getPaletteDistance(pic.getColors(), unsorted.get(i).getColors())
        //  + unsorted.get(i).getRating() rate scale not sure
          + unsorted.get(i).getNumVotes/(unsorted.get(i).getNumViews+1));
		      }
      //get rank order
      for(int j = 0; j < size; j++){
        int jmax =0;
        for (int k = 0; k < size; k++){
          if (rankValue[j] < rankValue[k]) jmax++;
        }
        sorted.set(jmax, unsorted.get(j)); //没有考虑 rankValue相等的情况
      }
    }

    public float getColorDistance(String m, String n){
      //hex to rgb
      int  r1=  Integer.valueOf( m.substring( 0, 2 ), 16 );
      int  g1=  Integer.valueOf( m.substring( 2, 4 ), 16 );
      int b1=  Integer.valueOf( m.substring( 4, 6 ), 16 );
      int  r2=  Integer.valueOf( n.substring( 0, 2 ), 16 );
      int  g2=  Integer.valueOf( n.substring( 2, 4 ), 16 );
      int b2=  Integer.valueOf( n.substring( 4, 6 ), 16 );
      //adjust euclidean https://en.wikipedia.org/wiki/Color_difference

      return (float)(Math.sqrt( (Math.pow(r1-r2,2) + Math.pow(g1-g2,2)
      + Math.pow(b1-b2,2)))/255);
    }

    //1
    public float getPaletteDistance(String[] p1, String[] p2){
      float cd = 0.0;
      int l1 = p1.length;
      int l2 = p2.length;
      if (l1 <= l2){
        for (int i=0; i<l1; i++){
          cd = cd + getColorDistance(p1[i], p2[i]);
        }
      }
      else {
        for (int i=0; i<l2; i++){
          cd = cd + getColorDistance(p1[i], p2[i]);
        }
        cd = cd + (float)(l1-l2);
      }
      return cd;
    }

	/**
	* Returns value of pic
	* @return
	*/
	public PicColors getPic() {
		return pic;
	}

	/**
	* Sets new value of pic
	* @param
	*/
	public void setPic(PicColors pic) {
		this.pic = pic;
	}

	/**
	* Returns value of unsorted
	* @return
	*/
	public List<MatchPalettes> getUnsorted() {
		return unsorted;
	}

	/**
	* Sets new value of unsorted
	* @param
	*/
	public void setUnsorted(List<MatchPalettes> unsorted) {
		this.unsorted = unsorted;
	}

	/**
	* Returns value of sorted
	* @return
	*/
	public List<MatchPalettes> getSorted() {
		return sorted;
	}

	/**
	* Sets new value of sorted
	* @param
	*/
	public void setSorted(List<MatchPalettes> sorted) {
		this.sorted = sorted;
	}

  /**
	* Returns value of rankValue
	* @return
	*/
	public float[] getRankValue() {
		return rankValue;
	}

	/**
	* Sets new value of rankValue
	* @param
	*/
	public void setRankValue(float[] rankValue) {
		this.rankValue = rankValue;
	}

}
