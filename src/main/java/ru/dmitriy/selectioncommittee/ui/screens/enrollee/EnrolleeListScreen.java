package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitInfoScreen;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class EnrolleeListScreen extends ListScreen<StudyInfo, EnrolleeScreensPresenter> {

    public static final String ENROLLEE_LIST_SCREEN = "enrollee_list_screen";

    @Override
    protected void onScreenBuild(){
        super.onScreenBuild();
        grid.addItemClickListener(itemClick -> {
            getPresenter().showEnrollee(itemClick.getItem().getEnrollee());
            ScreenManager.getInstance().navigateTo(EnrolleeInfoScreen.ENROLLEE_INFO_SCREEN);
        });
    }

    @Override
    public List<StudyInfo> getContent() {
        return ServiceProvider.instance().getStudyInfoService().getAll();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(studyInfo -> studyInfo.getEnrollee().getName()).setCaption("Имя");
        grid.addColumn(studyInfo -> studyInfo.getEnrollee().getSurname()).setCaption("Фамилия");
        grid.addColumn(studyInfo -> studyInfo.getEnrollee().getPatronymic()).setCaption("Отчество");
        grid.addColumn(studyInfo -> studyInfo.getInstitution().getName()).setCaption("Учебное заведение");
        grid.addColumn(studyInfo -> studyInfo.getPulpit().getName()).setCaption("Кафедра");
        grid.addColumn(studyInfo -> studyInfo.getSpeciality().getName()).setCaption("Специальность");
    }
}
