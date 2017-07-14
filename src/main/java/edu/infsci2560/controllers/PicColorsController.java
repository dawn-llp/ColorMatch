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

import edu.infsci2560.models.PicColors;
import edu.infsci2560.repositories.PicColorsRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PicColorsController {
    @Autowired
    private PicColorsRepository repository;

       //method = RequestMethod.GET
    @RequestMapping(value = "picColors", method = RequestMethod.GET)
    public ModelAndView List() {
//    public Iterable<LipicPalettes> List() {
//        return repository.findAll();
        return new ModelAndView("picColors", "picColors", repository.findAll());
    }

    @RequestMapping(value = "/picColors/{id}", method = RequestMethod.GET)
    public ModelAndView Palette(@PathVariable("id") Long id) {
        return new ModelAndView("picColors","picColors",repository.findOne(id));
    }
//delete
    @RequestMapping(value = "/picColors/{id}", method = RequestMethod.DELETE)
    public ModelAndView DeleteById(@PathVariable("id") Long id) {
        repository.delete(id);
        return new ModelAndView("picColors","picColors",repository.findAll());
    }

    @RequestMapping(value = "/picColors/add", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded", produces = "application/json")
    public ModelAndView create(@ModelAttribute @Valid PicColors picColors, BindingResult result) {
        repository.save(picColors);
        return new ModelAndView("picColors", "picColors", repository.findAll());
    }


}
