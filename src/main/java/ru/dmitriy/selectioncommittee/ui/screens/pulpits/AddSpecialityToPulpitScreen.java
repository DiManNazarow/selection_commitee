package ru.dmitriy.selectioncommittee.ui.screens.pulpits;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import ru.dmitriy.selectioncommittee.models.Speciality;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.PulpitScreensPresenter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by diman on 06.04.17.
 */
public class AddSpecialityToPulpitScreen extends ListScreen<Speciality, PulpitScreensPresenter> {

    public static final String ADD_SPECIALITY_TO_PULPIT_SCREEN = "add_speciality_to_pulpit_screen";

    private Button addButton;

    private List<Speciality> selectedPulpits;

    @Override
    public void buildScreen() {

        grid = new Grid<>();
        setGridColumn();

        addButton  = new Button("Добавить");
        addButton.addClickListener(clickEvent -> {
            getPresenter().addSpeciality(selectedPulpits);
            ScreenManager.getInstance().navigateTo(AddNewPulpitScreen.ADD_NEW_PULPIT_SCREEN);
        });
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(selectionEvent -> {
            Set<Speciality> pulpits = selectionEvent.getAllSelectedItems();
            selectedPulpits = pulpits.stream().collect(Collectors.toList());
        });

        mainLayout.addComponent(grid);
        mainLayout.addComponent(addButton);
        addComponent(mainLayout);
    }

    @Override
    public List<Speciality> getContent() {
        return ServiceProvider.instance().getSpecialityService().getAllSpecialities();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(Speciality::getName).setCaption("Специальность");
    }

}
