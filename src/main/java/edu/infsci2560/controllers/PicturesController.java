package edu.infsci2560.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.infsci2560.models.Pictures;
import edu.infsci2560.repositories.PicturesRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PicturesController {
    @Autowired
    private PicturesRepository repository;

       //method = RequestMethod.GET
    @RequestMapping(value = "pictures", method = RequestMethod.GET)
    public ModelAndView List() {
//    public Iterable<LipicPalettes> List() {
//        return repository.findAll();
        return new ModelAndView("pictures", "pictures", repository.findAll());
    }

    @RequestMapping(value = "/pictures/{id}", method = RequestMethod.GET)
    public ModelAndView Picture(@PathVariable("id") Long id) {
        return new ModelAndView("pictures","pictures",repository.findOne(id));
    }
//delete
    @RequestMapping(value = "/pictures/{id}", method = RequestMethod.DELETE)
    public ModelAndView DeleteById(@PathVariable("id") Long id) {
        repository.delete(id);
        return new ModelAndView("pictures","pictures",repository.findAll());
    }

    @RequestMapping(value = "/pictures/add", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded", produces = "application/json")
    public ModelAndView create(@ModelAttribute @Valid Pictures pic, BindingResult result) {
        repository.save(pic);
        return new ModelAndView("pictures", "pictures", repository.findAll());
    }

}
