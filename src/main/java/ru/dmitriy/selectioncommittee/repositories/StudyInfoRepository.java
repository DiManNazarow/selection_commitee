package ru.dmitriy.selectioncommittee.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public interface StudyInfoRepository extends CrudRepository<StudyInfo, Long> {

    /**
     * Сведения об абитуриенте по буквам фамилии
     * @param name
     * @param surname
     * @param patronymic
     * @return
     */
    List<StudyInfo> findByEnrolleeNameOrEnrolleeSurnameOrEnrolleePatronymicContaining(String name, String surname, String patronymic);

    /**
     * Сведения об абитуриенте по названию специальности
     * @param speciality
     * @return
     */
    List<StudyInfo> findBySpecialityNameContaining(String speciality);

    /**
     * Сведения об абитуриенте по учебному заведению
     * @param institution
     * @return
     */
    List<StudyInfo> findByInstitutionNameContaining(String institution);

    /**
     * Сведения об абитуриенте по названию кафедры
     * @param pulpit
     * @return
     */
    List<StudyInfo> findByPulpitNameContaining(String pulpit);

    /**
     * Абитуриенты закончившие учебное заведение
     * @param institution
     * @param status
     * @return
     */
    List<StudyInfo> findByInstitutionNameContainingAndStatus(String institution, int status);

    /**
     * Абитуриенты поступающие на специальность (По названию)
     * @param speciality
     * @param status
     * @return
     */
    List<StudyInfo> findBySpecialityNameContainingAndStatus(String speciality, int status);

    /**
     * Абитуриенты поступающие на специальность (По номеру)
     * @param speciality
     * @param status
     * @return
     */
    List<StudyInfo> findBySpecialitySpecialNumberContainingAndStatus(String speciality, int status);

}
