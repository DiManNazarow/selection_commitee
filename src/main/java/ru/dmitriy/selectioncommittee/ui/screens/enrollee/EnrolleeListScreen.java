package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import ru.dmitriy.selectioncommittee.models.Enrollee;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.screens.pulpits.PulpitInfoScreen;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class EnrolleeListScreen extends ListScreen<Enrollee, EnrolleeScreensPresenter> {

    public static final String ENROLLEE_LIST_SCREEN = "enrollee_list_screen";

    @Override
    protected void onScreenBuild(){
        super.onScreenBuild();
        grid.addItemClickListener(itemClick -> {
            getPresenter().showEnrollee(itemClick.getItem());
            ScreenManager.getInstance().navigateTo(EnrolleeInfoScreen.ENROLLEE_INFO_SCREEN);
        });
    }

    @Override
    public List<Enrollee> getContent() {
        return ServiceProvider.instance().getEnrolleeService().getAllEnrollee();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Enrollee::getName).setCaption("Имя");
        grid.addColumn(Enrollee::getSurname).setCaption("Фамилия");
        grid.addColumn(Enrollee::getPatronymic).setCaption("Отчество");
    }
}
