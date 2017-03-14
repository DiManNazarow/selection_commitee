package ru.dmitriy.selectioncommittee.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriy.selectioncommittee.models.Pulpit;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public interface PulpitRepository extends CrudRepository<Pulpit, Long> {
}
