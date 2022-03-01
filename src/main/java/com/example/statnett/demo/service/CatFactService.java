package com.example.statnett.demo.service;

import com.example.statnett.demo.exception.CatFactNotFoundException;
import com.example.statnett.demo.model.CatFact;
import com.example.statnett.demo.repository.CatFactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: Valgt å unngå DTOer gitt logikken er veldig enkel. I en produksjonssetting skal DTOer være inkludert.
@Service
public class CatFactService {

    private final CatFactRepository repository;

    public CatFactService(CatFactRepository repository) {
        this.repository = repository;
    }

    public List<CatFact> allFacts() {
        return repository.findAll();
    }

    public CatFact newCatFact(CatFact newCatFact) {
        return repository.save(newCatFact);
    }

    public CatFact findFactGivenID(Long id) throws CatFactNotFoundException {
        return repository.findById(id).orElseThrow(() -> new CatFactNotFoundException(id));
    }

}
