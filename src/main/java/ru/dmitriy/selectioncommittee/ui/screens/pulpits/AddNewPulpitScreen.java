package ru.dmitriy.selectioncommittee.ui.screens.pulpits;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitriy.selectioncommittee.models.Institution;
import ru.dmitriy.selectioncommittee.models.Pulpit;
import ru.dmitriy.selectioncommittee.services.PulpitService;
import ru.dmitriy.selectioncommittee.ui.Screen;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.PulpitPresenter;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by diman on 20.03.17.
 */
public class AddNewPulpitScreen extends Screen<VerticalLayout, PulpitPresenter> {

    public static final String ADD_NEW_PULPIT_SCREEN = "add_new_pulpit_screen";

    private Pulpit pulpit;

    private TextField pulpitName;

    private Button pulpitSaveButton;

    public AddNewPulpitScreen() {
        super(new VerticalLayout());
        pulpit = new Pulpit();
    }

    @Override
    public void buildScreen(){
        pulpitName = new TextField("Название кафедры");

        pulpitSaveButton = new Button("Сохранить");

        pulpitSaveButton.addClickListener(clickEvent -> {
            save();
        });
        mainLayout.addComponents(pulpitName, pulpitSaveButton);
        addComponent(mainLayout);
    }

    public void save(){
        if (pulpit.getId() != null){
            pulpit = new Pulpit();
        }
        pulpit.setName(pulpitName.getValue());
        String id = ServiceProvider.instance().getPulpitService().savePulpit(pulpit);
        if (!TextUtils.isEmpty(id)){
            pulpit.setId(Long.parseLong(id));
            Notification.show("Сохранено");
        } else {
            Notification.show("Ошибка");
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
