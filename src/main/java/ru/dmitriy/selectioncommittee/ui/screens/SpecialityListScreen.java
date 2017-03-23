package ru.dmitriy.selectioncommittee.ui.screens;

import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class SpecialityListScreen extends ListScreen<Speciality> {

    public static final String SPECIALITY_LIST_SCREEN = "speciality_list_screen";

    @Override
    public List<Speciality> getContent() {
        return ServiceProvider.instance().getSpecialityService().getAllSpecialities();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Speciality::getName).setCaption("Специальость");
    }
}
