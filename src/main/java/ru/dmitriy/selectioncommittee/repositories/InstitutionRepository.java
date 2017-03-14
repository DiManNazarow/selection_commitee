package ru.dmitriy.selectioncommittee.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriy.selectioncommittee.models.Institution;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public interface InstitutionRepository extends CrudRepository<Institution, Long> {

    List<Institution> findByNameIgnoreCaseContains(String name);

}
