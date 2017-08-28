package edu.infsci2560.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.infsci2560.coordinator.PictaCoordinator;
import edu.infsci2560.coordinator.TpictaResp;
import edu.infsci2560.coordinator.PictaReqResult;
import edu.infsci2560.repositories.PicColorsRepository;
import edu.infsci2560.repositories.PicturesRepository;
import edu.infsci2560.repositories.HistoryRepository;
import edu.infsci2560.models.Pictures;
//import edu.infsci2560.models.LiColors;

import edu.infsci2560.models.History;
import edu.infsci2560.models.MatchPalettes;
import edu.infsci2560.repositories.MatchPalettesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

import java.util.Date;
import java.text.SimpleDateFormat;

import edu.infsci2560.storage.StorageFileNotFoundException;
import edu.infsci2560.storage.StorageService;

//import edu.infsci2560.models.LipicUsersPictures;
//import edu.infsci2560.repositories.UsersPicturesRepository;

//import edu.infsci2560.models.LipicReqActions;
//import edu.infsci2560.repositories.ReqActionsRepository;

import javax.servlet.http.HttpServletRequest;
import edu.infsci2560.models.LipicUsers;
import edu.infsci2560.repositories.UsersRepository;

import java.util.ArrayList;

@Controller
public class MatchServiceController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private MatchPalettesRepository palettesrepository;
	@Autowired
	private PicColorsRepository piccolorsrepository;
	@Autowired
	private PicturesRepository picturesepository;
	@Autowired
	private HistoryRepository historyrespository;


	private final StorageService storageService;

    @Autowired //自动完成装配，没有get set
    public MatchServiceController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value="/match", method=RequestMethod.POST)   //post match with image param return Page Result
    public ModelAndView Pictaupload(@RequestParam("image") MultipartFile image) throws Exception {
        if (!image.isEmpty()){

			ModelAndView mv = new ModelAndView();
            mv.setViewName("match");

			Long remoteUserId = new Long(0L);

/*
			if (request.getRemoteUser() == null){ //guest mode
	//		    remoteUserId = new Long(-1);   //guest will be marked -1
			    System.out.println("Guest Action");
			} else {                         //someone logon
			    remoteUserId = usersrepository.findByName(request.getRemoteUser()).get(0).getId();
			    System.out.println("User Action, NAME="+ request.getRemoteUser()+ " ID=" + remoteUserId.toString());
			}
*/

      PictaCoordinator pc = new PictaCoordinator(palettesrepository, piccolorsrepository);                 //match picture via Picata
			PictaReqResult reqResult = pc.PostBinaryImage(image);

			mv.addObject("palettes",palettesrepository.findOne(reqResult.getPalettesId()));  //return palette info
			mv.addObject("pictureColors",piccolorsrepository.findOne(reqResult.getPicColorId()));							//return colors detected from user's picture

			//generate an unique image file name
			//UUID uuid  =  UUID.randomUUID();
			String uuidFile = UUID.randomUUID().toString() +"-" + image.getOriginalFilename();
			storageService.store(image, uuidFile);                       //save image

			mv.addObject("imageUrl",uuidFile);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Long pictureId = picturesrepository.save( new Pictures( null, // save picture info  - id
														formatter.format(new Date()), //dateCreated

														uuidFile, 			//image file name // imageName in LipicUsersPictures
														reqResult.getPicColorId(),
														reqResult.getPalettesId()
														)).getId();

		    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				//生成history
			Long historyId = historyrespository.save( new History( null,  // save action history -id
			                                 0,   //type
			                                 pictureId,                          //pictureId optional
			                                 reqResult.getPalettesId(),
																			 null,          //paletteId optional
			                                 formatter2.format(new Date())      //timestamp
			                                 )).getId();


			return mv;
        } else {
            //if no file submitted
            ModelAndView mv = new ModelAndView();
            mv.setViewName("/error");  //temp, not created yet
            return mv;
        }

    }



}
