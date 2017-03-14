package ru.dmitriy.selectioncommittee.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriy.selectioncommittee.models.StudyInfo;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public interface StudyInfoRepository extends CrudRepository<StudyInfo, Long> {

}
