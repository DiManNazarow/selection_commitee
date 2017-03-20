package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.services.PulpitService;

/**
 * Created by diman on 20.03.17.
 */
public class AddNewPulpitScreen extends Screen<VerticalLayout> {

    private TextField pulpitNameTextField;

    private Button saveButton;

    private PulpitService pulpitService;

    public AddNewPulpitScreen(PulpitService pulpitService){
        this.pulpitService = pulpitService;
    }

    @Override
    public VerticalLayout buildScreen() {
        VerticalLayout verticalLayout = new VerticalLayout();

        pulpitNameTextField = new TextField("Название кафедры");

        saveButton = new Button("Сохранить");
        saveButton.addClickListener(clickEvent -> {
            savePulpit();
        });

        verticalLayout.addComponent(pulpitNameTextField);
        verticalLayout.addComponent(saveButton);
        return verticalLayout;
    }

    public void savePulpit(){
        Pulpit pulpit = new Pulpit();
        pulpit.setName(pulpitNameTextField.getValue());
        pulpitService.savePulpit(pulpit);
    }
}
