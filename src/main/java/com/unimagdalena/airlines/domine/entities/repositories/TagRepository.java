package com.unimagdalena.airlines.domine.entities.repositories;

import com.unimagdalena.airlines.domine.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {

    //- Busca una etiqueta por nombre (“promo”, “eco”, “red-eye”).
    Optional<Tag> findByName(String name);

    //- Retorna todas las Tag cuyos nombres estén en la lista dada.
    List<Tag> findByNameIn(List<String> names);
}
