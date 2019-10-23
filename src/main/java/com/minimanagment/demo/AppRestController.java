package com.minimanagment.demo;

import com.minimanagment.demo.City;
import com.minimanagment.demo.CityService;
import com.minimanagment.demo.CountryService;
import com.minimanagment.demo.Exceptions.AppExceptionsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppRestController {

    @Autowired
    private CityService service;

    @Autowired
    private CountryService cservice;

    @RequestMapping(value = "/rest/getAll", method = RequestMethod.GET)
    public List<City> getAll(){
        throw new AppExceptionsHandler("lol");
    //    return service.listAll();
    }

    @RequestMapping(value="/rest/get/{id}", method = RequestMethod.GET)
    public City getCity(@PathVariable Long id){
        return service.getCity(id);
    }

    @RequestMapping(value = "rest/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name="id") Long id){
        service.delete(id);
    }

    @RequestMapping(value = "rest/add", method = RequestMethod.POST)
    public void add(@RequestParam(name = "name") String name,
                    @RequestParam(name = "population") int population,
                    @RequestParam(name = "country_name") String countryName){
        City newCity= new City();
        newCity.setName(name);
        newCity.setPopulation(population);
        newCity.setCountry(cservice.getByCode(countryName).orElseThrow(()->new IllegalArgumentException("Invalid id:"+ countryName)));
        service.save(newCity);
    }



}
