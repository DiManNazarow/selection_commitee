package ru.dmitriy.selectioncommittee.ui.screens.university;

import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.UniversityScreensPresenter;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 23.03.17.
 */
public class UniversityListScreen extends ListScreen<Institution, UniversityScreensPresenter> {

    public static final String UNIVERSITY_SCREEN_LIST = "university_screen_list";

    @Override
    protected void onScreenBuild(){
        super.onScreenBuild();
        grid.addItemClickListener(itemClick -> {
           getPresenter().showUniversity(itemClick.getItem());
           ScreenManager.getInstance().navigateTo(UniversityInfoScreen.UNIVERSITY_INFO_SCREEN);
        });
    }

    @Override
    public List<Institution> getContent() {
        return ServiceProvider.instance().getInstituteService().getAllInstitute();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Institution::getName).setCaption("Уиверситет");
    }
}
