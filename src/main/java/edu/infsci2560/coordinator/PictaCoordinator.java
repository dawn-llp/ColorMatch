package edu.infsci2560.coordinator;

import com.google.gson.Gson; // json -> obj
import net.coobird.thumbnailator.Thumbnails; // image compress component
import java.io.ByteArrayInputStream; // used in image compress process
import java.io.ByteArrayOutputStream; // used in image compress process
import edu.infsci2560.models.TpictaResp; // 引用
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;

import edu.infsci2560.models.MatchPalettes;
import edu.infsci2560.repositories.MatchPalettesRepository;
import edu.infsci2560.models.Pictures;
import edu.infsci2560.repositories.PicturesRepository;
import edu.infsci2560.models.PicColors;
import edu.infsci2560.repositories.PicColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import edu.infsci2560.coordinator.PictaReqResult;

import java.util.Date;
import java.text.SimpleDateFormat;
import edu.infsci2560.coordinator.RankModule;

public class PictaCoordinator {

@Autowired
private MatchPalettesRepository palettesRepository;
@Autowired
private PicColorsRepository picColorsRepository;

private String api = "http://pictaculous.com/api/1.0/";
private int sizeMax = 200*1024;

public String getApi() {
        return api;
}
public void setApi(String api){
        this.api = api;
}
public int getSizeMax() {
        return sizeMax;
}
public void setSizeMax(int sizeMax){
        this.sizeMax = sizeMax;
}

/*
   public PictaCoordinator(){
      this.api = "http://pictaculous.com/api/1.0/";
      this.sizeMax = 200*1024;
   }*/

public PictaCoordinator( MatchPalettesRepository palettesRepository,
                         PicColorsRepository picColorsRepository){
        this.api = "http://pictaculous.com/api/1.0/";
        this.sizeMax = 200*1024;
        this.palettesRepository = palettesRepository;
        this.picColorsRepository = picColorsRepository;
}

public PictaCoordinator(String api, int sizeMax, PalettesRepository repository ){      //full constructor
        this.api = api;
        this.sizeMax = sizeMax;
        this.palettesRepository = palettesRepository;
        this.picColorsRepository = picColorsRepository;
}

// palette convertor
public MatchPalettes Tkuler_themesConvertor(Tkuler_themes a){
  MatchPalettes m = new MatchPalettes(null, "kuler", a.getId(), a.getAuthor(),
  a.getColors(), a.getRating(), 0, 0);
  return( palettesRepository.check(m) );
}

public MatchPalettes Tcl_themesConvertor(Tcl_themes b){
  MatchPalettes n = new MatchPalettes(null, "colourlover", b.getId(), b.getAuthor(),
  b.getColors(), b.getRating(), 0, 0);
  return( palettesRepository.check(n) );
}

public PictaReqResult PostBinaryImage(MultipartFile image) throws Exception {        //return recent LipicPalettes.id

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();     //创建多媒体对象
        HttpPost post = new HttpPost(api);     //create post

        if (image.getSize() > sizeMax) {       // if image is too large
                //------------------image compress ---------------------------
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                //.outputFormat("jpeg").outputQuality(0.8)
                Thumbnails.of(image.getInputStream()).size(1200, 1200).outputFormat("jpg").outputQuality(0.8).toOutputStream(os); //1st: compress to 1200*1200 max
                // 0.8 ? springboot网站文件上传限制不详
                while (os.size()>sizeMax) {     // 2nd: loop to reduce size to meet the limit
                        ByteArrayInputStream in = new ByteArrayInputStream(os.toByteArray());
                        os.flush();
                        Thumbnails.of(in).scale(0.9).toOutputStream(os);
                        in.close();
                }
                // 循环缩小？
                builder.addBinaryBody("image", os.toByteArray());// compressed image stream

                os.close();

        } else {       // image src is less than 200k and okay to picta api
                builder.addBinaryBody("image", image.getInputStream());// image Stream
        }


        CloseableHttpClient client = HttpClients.createDefault();

        HttpEntity entity = builder.build();
        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        String PictaResult = EntityUtils.toString(response.getEntity(), "UTF-8");

        client.close();     //close httpclient

        //      if(response.getStatusLine().getStatusCode()==200){
        Gson gson = new Gson();
        TpictaResp pictaResp = gson.fromJson(PictaResult, TpictaResp.class);     //json -> obj

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        PicColors picColors = new PicColors(null, pictaResp.getInfo.getColors());

        //palettes结果转存一个临时Tpalettes
        List<Tkuler_themes> m = pictaResp.getKuler_themes();
        List<Tcl_themes> n = pictaResp.getCl_themes();
        List<MatchPalettes> Tpalettes = new List<MatchPalettes>;
        for(int i=0; i<m.size(); i++){
          Tpalettes.add(Tkuler_themesConvertor(m.get(i)));
        }
        for(int i=0; i<n.size(); i++){
          Tpalettes.add(Tcl_themesConvertor(n.get(i)));
        }

        //Tpalettes排序
        RankModule comparison = new RankModule(picColors, Tpalettes);
        comparison.rankFunction();

        return new PictaReqResult(picColorsRepository.save(picColors).getId(),
        palettesRepository.save(comparison.getSorted()).getId());
        /*		LipicPalettes ResultPalette = new LipicPalettes(null,  //id
                              pictaResp.getKuler_themes().get(0).getId(), //kuler_id
                              "n/a",          //temp omit cl
                              pictaResp.getKuler_themes().get(0).getColors(), //colors
                              0,		//likes
                              0,		//dislikes
                              pictaResp.getKuler_themes().get(0).getRating(), //kuler_rating
                              "n/a",		//temp omit cl
                              pictaResp.getKuler_themes().get(0).getAuthor(), //
                              formatter.format(new Date()), //date
                              currentUserId);
           // 这里没有save？

            return new PictaReqResult(repository.save(ResultPalette).getId(), pictaResp.getInfo().getColors());
            //      return pictaResp;  // need to deal with error
         */
}
}
