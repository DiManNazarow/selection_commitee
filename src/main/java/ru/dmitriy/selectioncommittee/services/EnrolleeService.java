package ru.dmitriy.selectioncommittee.services;

import ru.dmitriy.selectioncommittee.models.Enrollee;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 12.03.17.
 */

public interface EnrolleeService {

    String addEnrollee(String enrolleeJson);

    String getAllEnrollee();

    String getEnrolleeById(String idJson);

    String findEnrolleeByInitials(String initials);

}
