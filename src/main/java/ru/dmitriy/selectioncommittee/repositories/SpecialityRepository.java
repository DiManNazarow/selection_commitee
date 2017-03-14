package ru.dmitriy.selectioncommittee.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriy.selectioncommittee.models.Speciality;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
