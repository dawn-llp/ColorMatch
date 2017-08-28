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
    private PicturesRepository picRepository;
    @Autowired
    private MatchPalettesRepository palettesRepository;
    @Autowired
    private HistoryRepository historyRepository;

    @RequestMapping(value = "history", method = RequestMethod.GET)
    public ModelAndView List() {

        return new ModelAndView("history", "history", historyRepository.findAll());
    }

    @RequestMapping(value = "/history/id/{id}", method = RequestMethod.GET)
    public ModelAndView HistoryById(@PathVariable("id") Long id) {
      return new ModelAndView("history", "history", historyRepository.findOne(id));
    }

    @RequestMapping(value = "/history/pictureID/{pictureID}", method = RequestMethod.GET)
    public ModelAndView HistoryByPic(@PathVariable("pictureID") Long pictureID) {
      return new ModelAndView("history", "history", historyRepository.findOne(pictureID));
    }

    @RequestMapping(value = "/history/match_PaID/{match_PaID}", method = RequestMethod.GET)
    public ModelAndView HistoryByPa(@PathVariable("match_PaID") Long id) {
      return new ModelAndView("history", "history", historyRepository.findByMatch_PaID(id));
    }

    @RequestMapping(value = "/history/vote_match_PaID/{vote_match_PaID}", method = RequestMethod.GET)
    public ModelAndView HistoryByVote(@PathVariable("vote_match_PaID") Long id) {
      return new ModelAndView("history", "history", historyRepository.findByVote_match_PaID(id));
    }

    @RequestMapping(value = "/history/add", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded", produces = "application/json")
    public ModelAndView create(@ModelAttribute @Valid History history, BindingResult result) {
        historyRepository.save(history);
        return new ModelAndView("history", "histroy", historyRepository.findAll());
    }

    @RequestMapping(value = "/history/vote/id/{vote_match_PaID}",method = RequestMethod.PUT)
    public ModelAndView UpdateVoteById(@PathVariable("id") Long id) {
            History history = historyRepository.findOne(id);
            history.setVote_match_PaID(vote_match_PaID);
            history.updateDateCreated();
            historyRepository.save(history); //投票记录时间
        return new ModelAndView("history","history",historyRepository.findAll());
    }

}
