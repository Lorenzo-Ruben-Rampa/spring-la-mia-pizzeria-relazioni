package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;            
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping;    
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;         
import org.springframework.validation.BindingResult; 
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffer; 
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.SpecialOffersRepository; 

@Controller
@RequestMapping("/special-offers")
public class SpecialOffersController {

    @Autowired
	private SpecialOffersRepository repository;        

	@PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
        // model.addAttribute("pizza", formSpecialOffer.getPizza());
        return "special-offers/create";
    }
    repository.save(formSpecialOffer);              
        return "redirect:/pizzas";
    }
}
