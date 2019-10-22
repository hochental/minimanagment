package com.minimanagment.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryService {

    @Autowired
    private CountryRepository repo;

    public List<Country> listAll(){
        return repo.findAll();
    }

    public Optional<Country> getByCode(String id){
        return repo.findById(id);
    }

}
