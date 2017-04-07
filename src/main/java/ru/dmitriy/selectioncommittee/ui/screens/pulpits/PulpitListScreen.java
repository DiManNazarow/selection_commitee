package ru.dmitriy.selectioncommittee.ui.screens.pulpits;

import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.PulpitScreensPresenter;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class PulpitListScreen extends ListScreen<Pulpit, PulpitScreensPresenter> {

    public static final String PULPIT_SCREEN_LIST = "pulpit_screen_list";

    @Override
    protected void onScreenBuild(){
        super.onScreenBuild();
        grid.addItemClickListener(itemClick -> {
            getPresenter().showPulpit(itemClick.getItem());
            ScreenManager.getInstance().navigateTo(PulpitInfoScreen.PULPIT_INFO_SCREEN);
        });
    }

    @Override
    public List<Pulpit> getContent() {
        return ServiceProvider.instance().getPulpitService().getAllPulpit();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Pulpit::getName).setCaption("Кафедра");
    }
}
