package com.minimanagment.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository repo;

    public List<City> listAll(){
        return repo.findAll();
    }

    public void save(City city){
        repo.save(city);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public Optional<City> findById(Long id){
        return repo.findById(id);
    }

    public Page<City> findAll(Pageable page){
        return repo.findAll(page);
    }

}
