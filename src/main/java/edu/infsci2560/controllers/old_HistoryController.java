/*
package edu.infsci2560.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.infsci2560.models.Pictures;
import edu.infsci2560.repositories.PicturesRepository;
import edu.infsci2560.models.MatchPalettes;
import edu.infsci2560.repositories.MatchPalettesRepository;
import edu.infsci2560.models.History;
import edu.infsci2560.repositories.HistoryRepository;

//import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.LinkedList;

@Controller
public class HistoryController {
    @Autowired
    PicturesRepository picRepository;
    @Autowired
    MatchPalettesRepository palettesRepository;
    @Autowired
    HistoryRepository historyRepository;

    @RequestMapping(value = "/history/id/{id}", method = RequestMethod.GET)
    public ModelAndView Palette(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("history");

        if (usersRepository.findOne(id) == null) {return mv;} else {mv.addObject("userName",usersRepository.findOne(id).getName());}
        if (actionsRepository.findByOwnerId(id) == null) { return mv;}
        List<LipicReqActions> actionList = actionsRepository.findByOwnerId(id);
        List<LipicPalettes> palettes = new LinkedList<LipicPalettes>();
        for (int i = 0; i< actionList.size();i++){
            palettes.add(palettesRepository.findOne(actionList.get(i).getPalettesId()));
        }
        mv.addObject("palettes",palettes);
        return mv;
    }

    @RequestMapping(value = "/history/pictureId/{pictureId}", method = RequestMethod.GET)
    public ModelAndView Palette(@PathVariable("pictureId") Long pictureId) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("history");


        if (usersRepository.findByName(name) == null) { return mv;}
        Long userId = usersRepository.findByName(name).get(0).getId();

        mv.addObject("userName",usersRepository.findOne(userId).getName());

        if (actionsRepository.findByOwnerId(userId) == null) { return mv;}

        List<LipicReqActions> actionList = actionsRepository.findByOwnerId(userId);
        List<LipicPalettes> palettes = new LinkedList<LipicPalettes>();
        for (int i = 0; i< actionList.size();i++){
            palettes.add(palettesRepository.findOne(actionList.get(i).getPalettesId()));
        }

        mv.addObject("palettes",palettes);
        return mv;
    }
}
*/
