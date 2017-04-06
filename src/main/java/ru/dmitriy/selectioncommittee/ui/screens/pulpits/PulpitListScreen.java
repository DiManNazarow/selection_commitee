package ru.dmitriy.selectioncommittee.ui.screens.pulpits;

import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.AddNewPulpitScreenPresenter;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class PulpitListScreen extends ListScreen<Pulpit, AddNewPulpitScreenPresenter> {

    public static final String PULPIT_SCREEN_LIST = "pulpit_screen_list";

    @Override
    public List<Pulpit> getContent() {
        return ServiceProvider.instance().getPulpitService().getAllPulpit();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Pulpit::getName).setCaption("Кафедра");
    }
}
