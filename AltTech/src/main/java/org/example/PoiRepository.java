package org.example;


import org.example.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoiRepository extends JpaRepository<Poi, Long> {
    // métodos custom podem ser adicionados aqui
}