package com.unimagdalena.airlines.domain.repositories;

import com.unimagdalena.airlines.domine.entities.Tag;
import com.unimagdalena.airlines.domine.entities.repositories.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TagReposirotyTest extends AbstractRepository {

    @Autowired TagRepository tagRepository;

    @Test @DisplayName("Tag: find by name and names in a List")
    void shouldFindByNameAndNamesInAList() {
        //Given
        tagRepository.save(Tag.builder().name("tagsito1").build());
        tagRepository.save(Tag.builder().name("tagsito2").build());

        // When
        Optional<Tag> loaded = tagRepository.findByName("tagsito1");
        List<Tag> loaded2 = tagRepository.findByNameIn(List.of("tagsito1","tagsito2"));

        // Then
        assertThat(loaded).isPresent();
        assertThat(loaded.get().getName()).isEqualTo("tagsito1");

        assertThat(loaded2).isNotEmpty();
    }


}
