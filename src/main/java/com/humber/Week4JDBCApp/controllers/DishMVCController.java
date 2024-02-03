package com.humber.Week4JDBCApp.controllers;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurant")
public class DishMVCController {

    @Autowired
    private DishService dishService;

    //getting value from app properties and storing it to variable name
    @Value("${restaurant.name}")
    private String name;

    //get mapping - returns home page (/restaurant/home)
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("restaurantName", name);
        return "home";
    }

    //get mapping - returns menu page (/restaurant/menu)
    @GetMapping("/menu")
    public String menu(Model model){
        model.addAttribute("dishes",dishService.getAllDishes());
        return "menu";
    }

    @GetMapping("/add-dish")
    public String addDish(Model model){
        model.addAttribute("item", new Dish());
        return "add-dish";
    }

    @PostMapping("/post-dish")
    public String postDish(@ModelAttribute Dish dish, Model model){
       int saveCode = dishService.saveDish(dish);
        model.addAttribute("dishes",dishService.getAllDishes());
       if(saveCode==0){
           model.addAttribute("msg", "Dish too cheap. Failed to add dish!");
            return "menu";
       }
        model.addAttribute("msg", "Dish added successfully!");
        return "menu";
    }
}
