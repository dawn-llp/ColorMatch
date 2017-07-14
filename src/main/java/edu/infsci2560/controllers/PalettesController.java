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

import edu.infsci2560.models.MatchPalettes;
import edu.infsci2560.repositories.MatchPaletteRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PalettesController {
    @Autowired
    private MatchPalettesRepository repository;

       //method = RequestMethod.GET
    @RequestMapping(value = "palettes", method = RequestMethod.GET)
    public ModelAndView List() {
//    public Iterable<LipicPalettes> List() {
//        return repository.findAll();
        return new ModelAndView("palettes", "palettes", repository.findAll());
    }

    @RequestMapping(value = "/palettes/{id}", method = RequestMethod.GET)
    public ModelAndView Palette(@PathVariable("id") Long id) {
        return new ModelAndView("palettes","palettes",repository.findOne(id));
    }
//delete
    @RequestMapping(value = "/palettes/{id}", method = RequestMethod.DELETE)
    public ModelAndView DeleteById(@PathVariable("id") Long id) {
        repository.delete(id);
        return new ModelAndView("palettes","palettes",repository.findAll());
    }

    @RequestMapping(value = "/palettes/add", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded", produces = "application/json")
    public ModelAndView create(@ModelAttribute @Valid MatchPalettes palettte, BindingResult result) {
        repository.save(palettte);
        return new ModelAndView("palettes", "palettes", repository.findAll());
    }

/*delete
    @RequestMapping(value = "/palettes/delete/{id}")
    public ModelAndView DeleteById2(@PathVariable("id") Long id) {
        repository.delete(id);
        return new ModelAndView("palettes","palettes",repository.findAll());
    }
*/
//update
    @RequestMapping(value = "/palettes/vote/{id}", method = RequestMethod.PUT)
    public ModelAndView UpdateVoteById(@PathVariable("id") Long id) {
            MatchPalettes palette = repository.findOne(id);
            palette.updateNumVotes();
            repository.save(palette);
        return new ModelAndView("palettes","palettes",repository.findAll());
    }

    @RequestMapping(value = "/palettes/view/{id}", method = RequestMethod.PUT)
    public ModelAndView UpdateVoteById(@PathVariable("id") Long id) {
            MatchPalettes palette = repository.findOne(id);
            palette.updateNumViews();
            repository.save(palette);
        return new ModelAndView("palettes","palettes",repository.findAll());
    }

    @RequestMapping(value = "/palettes/view/{id}", method = RequestMethod.PUT)
    public ModelAndView UpdateVoteById(@PathVariable("id") Long id) {
            MatchPalettes palette = repository.findOne(id);
            palette.updateNumViews();
            repository.save(palette);
        return new ModelAndView("palettes","palettes",repository.findAll());
    }

    //在repository搜查sourceType and souceID
    @RequestMapping(value = "/palettes/{sourceType}/{sourceID}", method = RequestMethod.GET)
    public ModelAndView Palette(@PathVariable("sourceType") String sourceType,
    @PathVariable("souceID") String sourceID) {
        return new ModelAndView("palettes","palettes",repository.findOne(sourceType, sourceID));
    }

    @RequestMapping(value = "/palettes/{colors}", method = RequestMethod.GET)
    public ModelAndView Palette(@PathVariable("colors") String[] colors) {
        return new ModelAndView("palettes","palettes",repository.findOne(colors));
    }
/*
    @RequestMapping(value = "/palettes/dislike/{id}",method = RequestMethod.PUT)
    public ModelAndView UpdateDisLikeById(@PathVariable("id") Long id) {
            LipicPalettes palette = repository.findOne(id);
            palette.dislike();
            repository.save(palette);
        return new ModelAndView("palettes","palettes",repository.findAll());
    }
*/

   //try put method not successful
/*  @RequestMapping(value = "/palettes/updatelikes/{id}", method = RequestMethod.PUT)

    public @ResponseBody LipicPalettes updateLikes(@PathVariable("id") long id){
        LipicPalettes palette = repository.findOne(id);
        palette.like();
        repository.save(palette);
        return palette;
    }

    @RequestMapping(value = "/palettes/updatedislikes/{id}", method = RequestMethod.PUT)
        public @ResponseBody LipicPalettes updatedisLikes(@PathVariable("id") long id){
            LipicPalettes palette = repository.findOne(id);
            palette.dislike();
            repository.save(palette);
            return palette;
        }
 */

}
