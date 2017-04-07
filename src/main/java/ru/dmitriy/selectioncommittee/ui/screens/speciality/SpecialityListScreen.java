package ru.dmitriy.selectioncommittee.ui.screens.speciality;

import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.SpecialityScreensPresenter;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class SpecialityListScreen extends ListScreen<Speciality, SpecialityScreensPresenter> {

    public static final String SPECIALITY_LIST_SCREEN = "speciality_list_screen";

    @Override
    protected void onScreenBuild(){
        super.onScreenBuild();
        grid.addItemClickListener(itemClick -> {
            getPresenter().showSpeciality(itemClick.getItem());
            ScreenManager.getInstance().navigateTo(SpecialityInfoScreen.SPECIALITY_INFO_SCREEN);
        });
    }

    @Override
    public List<Speciality> getContent() {
        return ServiceProvider.instance().getSpecialityService().getAllSpecialities();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Speciality::getName).setCaption("Специальость");
    }
}
