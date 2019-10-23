package com.minimanagment.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private CityService service;
    @Autowired
    private CountryService cservice;

    @RequestMapping("/")
    public String viewHomePage(Model model, @SortDefault("name") Pageable pageable){
        List<City> cityList = service.listAll();
        model.addAttribute("page", service.findAll(pageable));
        return "index";
    }

    @RequestMapping("/countries")
    public String viewCountryPage(Model model){
        List<Country> countrieList = cservice.listAll();
        model.addAttribute("countrieList", countrieList);
        return "country_list";
    }

    @RequestMapping("/new")
    public String showNewProductForm(Model model){
        City city = new City();
        List<Country> countryList=cservice.listAll();
        model.addAttribute("city", city);
        model.addAttribute("countryList", countryList);
        return "new_city";
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public String saveEditCity(@ModelAttribute("city") City city, @PathVariable(name="id") Long id){
        city.setId(id);
        city.setCountry(city.getCountry());
        service.save(city);
        return "redirect:/";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCity(@ModelAttribute("city") City city){
        city.setCountry(city.getCountry());
        service.save(city);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteCity(@PathVariable(name="id") Long id){
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editCity(@PathVariable(name="id") Long id, Model model){
        City city = service.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid id:"+ id));
        List<Country> countryList=cservice.listAll();
        model.addAttribute("countryList", countryList);
        model.addAttribute("city", city);
        return "edit";
    }

}
