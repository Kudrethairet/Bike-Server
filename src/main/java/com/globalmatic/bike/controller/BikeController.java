package com.globalmatic.bike.controller;


import com.globalmatic.bike.models.Bike;
import com.globalmatic.bike.repositories.BikeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;


    @GetMapping
    public List<Bike> list(){
        return  bikeRepository.findAll();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Bike create(@RequestBody Bike bike, Long id){
            bikeRepository.save(bike);
            return bikeRepository.saveAndFlush(bike);
    }

    @GetMapping("/{id}")
    public Bike get(@PathVariable("id") long id){
        return bikeRepository.getOne(id);
    }


    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public Bike update(@PathVariable Long id, @RequestBody Bike bike ){
        Bike existBike = bikeRepository.getOne(id);
        BeanUtils.copyProperties(bike, existBike, "id");
        return bikeRepository.saveAndFlush(bike);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        bikeRepository.deleteById(id);
    }

}
