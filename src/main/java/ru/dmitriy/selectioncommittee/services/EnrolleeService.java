package ru.dmitriy.selectioncommittee.services;

import ru.dmitriy.selectioncommittee.models.Enrollee;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */

public interface EnrolleeService {

    String saveEnrollee(Enrollee enrollee);

    List<Enrollee> getAllEnrollee();

    Enrollee getEnrolleeById(Long id);

    List<Enrollee> findEnrolleeByInitials(String initials);

}
