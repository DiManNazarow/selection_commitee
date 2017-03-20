package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.services.PulpitService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by diman on 20.03.17.
 */
public class AddSavedPulpitScreen extends Screen<VerticalLayout>{

    private Grid<Pulpit> pulpitGrid;

    private Button addNewPulpitButton;

    private Button addButton;

    private Institution institutionForAdd;

    private final PulpitService pulpitService;

    public AddSavedPulpitScreen(PulpitService pulpitService, Institution institution) {
        this.pulpitService = pulpitService;
        institutionForAdd = institution;
    }

    public VerticalLayout buildScreen(){
        VerticalLayout verticalLayout = new VerticalLayout();

        pulpitGrid = new Grid<>();
        pulpitGrid.setItems(pulpitService.getAllPulpit());
        pulpitGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        pulpitGrid.addSelectionListener(selectionEvent -> {

        });

        addNewPulpitButton = new Button("Добавить новою кафедру");
        addNewPulpitButton.addClickListener(clickEvent -> {

        });

        addButton = new Button("Добавить кафедры");
        addButton.addClickListener(clickEvent -> {
            addPulpitToInstitute();
        });

        verticalLayout.addComponent(pulpitGrid);
        verticalLayout.addComponent(addNewPulpitButton);
        verticalLayout.addComponent(addButton);
        return verticalLayout;
    }

    private void addPulpitToInstitute(){
        List<Pulpit> pulpits = pulpitGrid.getSelectedItems().stream().collect(Collectors.toList());
        institutionForAdd.setPulpits(pulpits);
    }

}
