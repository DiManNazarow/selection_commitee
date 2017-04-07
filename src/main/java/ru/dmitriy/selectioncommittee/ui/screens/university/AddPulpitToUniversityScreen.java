package ru.dmitriy.selectioncommittee.ui.screens.university;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.UniversityScreensPresenter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by diman on 28.03.17.
 */
public class AddPulpitToUniversityScreen extends ListScreen<Pulpit, UniversityScreensPresenter>{

    public static final String ADD_PULPIT_TO_UNIVERSITY_SCREEN = "add_pulpit_to_university_screen";

    private Button addButton;

    private List<Pulpit> selectedPulpits;

    @Override
    public void buildScreen() {

        grid = new Grid<>();
        setGridColumn();

        addButton  = new Button("Добавить");
        addButton.addClickListener(clickEvent -> {
            getPresenter().addPulpits(selectedPulpits);
        });
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(selectionEvent -> {
            Set<Pulpit> pulpits = selectionEvent.getAllSelectedItems();
            selectedPulpits = pulpits.stream().collect(Collectors.toList());
        });

        mainLayout.addComponent(grid);
        mainLayout.addComponent(addButton);
        addComponent(mainLayout);
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
