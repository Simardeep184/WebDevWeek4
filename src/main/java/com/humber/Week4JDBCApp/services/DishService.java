package com.humber.Week4JDBCApp.services;

import com.humber.Week4JDBCApp.models.Dish;
import com.humber.Week4JDBCApp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public int saveDish(Dish dish){

        if(dish.getPrice()>8) {
            dishRepository.save(dish);
            return 1;
        }
        return 0;
    }

    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }
}
