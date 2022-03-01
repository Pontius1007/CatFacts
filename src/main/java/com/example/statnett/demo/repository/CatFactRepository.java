package com.example.statnett.demo.repository;
import com.example.statnett.demo.model.CatFact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatFactRepository extends JpaRepository<CatFact, Long> {
}
