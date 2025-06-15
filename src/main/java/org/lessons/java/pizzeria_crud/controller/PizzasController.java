package org.lessons.java.pizzeria_crud.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.lessons.java.pizzeria_crud.repository.PizzasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.lessons.java.pizzeria_crud.model.Pizza;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.server.ResponseStatusException;
// import java.util.Optional;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/pizzas")
public class PizzasController {

    @Autowired
    private PizzasRepository repository;

    //INDEX
    @GetMapping
    public String index (Model model, @RequestParam(name="pizzaName", required=false) String searchPizzaName) {
        List<Pizza>pizzas;
            if (searchPizzaName != null && !searchPizzaName.isBlank()) {
                pizzas = repository.findByNameContainingIgnoreCase(searchPizzaName);
                model.addAttribute("searchPizzaName", searchPizzaName);
            } else {
                pizzas = repository.findAll();
            }
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    // @GetMapping("/{id}")
    //     public String pizzaDetail(Model model, @PathVariable("id") int id) {
    //     Optional<Pizza> result = repository.findById(id);
    //        if (result.isPresent()) {
    //         Pizza pizzaFound = result.get();
    //         model.addAttribute("pizza", pizzaFound);
    //         return "pizzaDetail";
    //     } else {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con ID " + id + " non trovata.");
    //     }
    // }    
    
    //SHOW
    @GetMapping("/{id}")
        public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizzas/pizzaDetail";
        }

    //CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());  
        return "/pizzas/create";
    }

    // store del CREATE
    @PostMapping("/create")
    public String store (@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzas/create";
        }
        repository.save(formPizza);
        return "redirect:/pizzas";
    }

    //UPDATE
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update (@PathVariable("id") Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzas/edit";
        }
        repository.save(formPizza);
        return "redirect:/pizzas";
    }

    //DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return "redirect:/pizzas";
    }
    
}
