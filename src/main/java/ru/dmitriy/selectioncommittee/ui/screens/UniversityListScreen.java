package ru.dmitriy.selectioncommittee.ui.screens;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.services.InstituteService;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 23.03.17.
 */
public class UniversityListScreen extends ListScreen<Institution> {

    public static final String UNIVERSITY_SCREEN_LIST = "university_screen_list";

    @Override
    public List<Institution> getContent() {
        return ServiceProvider.instance().getInstituteService().getAllInstitute();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Institution::getName).setCaption("Уиверситет");
    }
}
