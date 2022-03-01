package com.example.statnett.demo.controller;

import com.example.statnett.demo.exception.CatFactNotFoundException;
import com.example.statnett.demo.model.CatFact;
import com.example.statnett.demo.service.CatFactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CatFactController {

    private final CatFactService service;

    CatFactController(CatFactService service) {
        this.service = service;
    }

    @GetMapping("/cat-facts")
    public List<CatFact> all() {
        return service.allFacts();
    }

    @PostMapping("/cat-facts")
    public CatFact newCatFact(@RequestBody CatFact newCatFact) {
        return service.newCatFact(newCatFact);
    }

    @GetMapping("/cat-facts/{id}")
    public CatFact oneFactGivenID(@PathVariable Long id) {
        try {
            return service.findFactGivenID(id);
        } catch (CatFactNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cat Fact Not Found", exc);
        }
    }


}
