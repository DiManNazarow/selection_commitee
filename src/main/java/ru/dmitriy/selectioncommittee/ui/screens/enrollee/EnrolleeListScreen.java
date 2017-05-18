package ru.dmitriy.selectioncommittee.ui.screens.enrollee;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.ButtonRenderer;
import ru.dmitriy.selectioncommittee.models.StudyInfo;
import ru.dmitriy.selectioncommittee.ui.ListScreen;
import ru.dmitriy.selectioncommittee.ui.manager.ScreenManager;
import ru.dmitriy.selectioncommittee.ui.manager.ServiceProvider;
import ru.dmitriy.selectioncommittee.ui.presenter.EnrolleeScreensPresenter;
import ru.dmitriy.selectioncommittee.ui.views.SearchView;
import ru.dmitriy.selectioncommittee.utils.GuiUtils;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public class EnrolleeListScreen extends ListScreen<StudyInfo, EnrolleeScreensPresenter> {

    public static final String ENROLLEE_LIST_SCREEN = "enrollee_list_screen";

    private HorizontalLayout searchComponentContainer;

    private HorizontalLayout chooseComponentContainer;

    private SearchView searchByInitials;

    private SearchView searchByPulpit;

    private SearchView searchBySpeciality;

    private SearchView chooseEndedEnrollee;

    private SearchView chooseEnterEnrollee;

    private SearchView countEnded;

    private SearchView averageAgeSpeciality;

    @Override
    public void buildScreen() {
        searchComponentContainer = new HorizontalLayout();
        chooseComponentContainer = new HorizontalLayout();
        searchByInitials = new SearchView("Введите ФИО");
        searchByPulpit = new SearchView("Введите кафедру");
        searchBySpeciality = new SearchView("Введите специальность");

        chooseEndedEnrollee = new SearchView("Техикум который закочили");
        chooseEnterEnrollee = new SearchView("Специальость на которую поступают");
        countEnded = new SearchView("Количество окончивших\n универсистет");
        averageAgeSpeciality = new SearchView("Средний возраст\n специальности");
        chooseComponentContainer.addComponents(chooseEnterEnrollee, chooseEndedEnrollee, averageAgeSpeciality);

        searchComponentContainer.addComponents(searchByInitials, searchBySpeciality, searchByPulpit, countEnded);
        searchComponentContainer.setSizeFull();
        mainLayout.addComponents(searchComponentContainer, chooseComponentContainer);
        super.buildScreen();
    }

    @Override
    protected void onScreenBuild(){
        super.onScreenBuild();

        grid.setSizeFull();

        searchByInitials.setSearchButtonClickListener(() -> grid.setItems(ServiceProvider.instance().getStudyInfoService().searchByInitials(searchByInitials.getSearchText())));

        searchByPulpit.setSearchButtonClickListener(() -> grid.setItems(ServiceProvider.instance().getStudyInfoService().searchByPulpit(searchByPulpit.getSearchText())));

        searchBySpeciality.setSearchButtonClickListener(() -> grid.setItems(ServiceProvider.instance().getStudyInfoService().findBySpeciality(searchBySpeciality.getSearchText())));

        chooseEnterEnrollee.setSearchButtonClickListener(() -> grid.setItems(ServiceProvider.instance().getStudyInfoService().chooseBySpecialityNameAndStudyState(chooseEnterEnrollee.getSearchText(), StudyInfo.Status.ENTER)));

        chooseEndedEnrollee.setSearchButtonClickListener(() -> grid.setItems(ServiceProvider.instance().getStudyInfoService().chooseByUniversityAndStudyState(chooseEndedEnrollee.getSearchText(), StudyInfo.Status.ENDED)));

        countEnded.setSearchButtonClickListener(() -> GuiUtils.showNotification("Количество абитуриентов", ServiceProvider.instance().getStudyInfoService().enrollCountOfEndedUniversity(countEnded.getSearchText())));

        averageAgeSpeciality.setSearchButtonClickListener(() -> GuiUtils.showNotification("Средий возраст", ServiceProvider.instance().getStudyInfoService().averageAgeOfSpeciality(averageAgeSpeciality.getSearchText())));

        grid.addItemClickListener(itemClick -> {
            getPresenter().showEnrollee(itemClick.getItem().getEnrollee());
            ScreenManager.getInstance().navigateTo(EnrolleeInfoScreen.ENROLLEE_INFO_SCREEN);
        });
    }

    @Override
    public List<StudyInfo> getContent() {
        return ServiceProvider.instance().getStudyInfoService().getAll();
    }

    @Override
    protected void setGridColumn(){
        grid.addColumn(studyInfo -> studyInfo.getEnrollee().getName()).setCaption("Имя");
        grid.addColumn(studyInfo -> studyInfo.getEnrollee().getSurname()).setCaption("Фамилия");
        grid.addColumn(studyInfo -> studyInfo.getEnrollee().getPatronymic()).setCaption("Отчество");
        grid.addColumn(studyInfo -> studyInfo.getInstitution().getName()).setCaption("Учебное заведение");
        grid.addColumn(studyInfo -> studyInfo.getPulpit().getName()).setCaption("Кафедра");
        grid.addColumn(studyInfo -> studyInfo.getSpeciality().getName()).setCaption("Специальность");
        grid.addColumn(studyInfo -> studyInfo.getStudyState().getName()).setCaption("Статус");
        grid.addColumn(studyInfo -> "Удалить", new ButtonRenderer<>(rendererClickEvent -> {
            delete(rendererClickEvent.getItem());
        }));
    }

    private void delete(StudyInfo studyInfo){
        ServiceProvider.instance().getStudyInfoService().delete(studyInfo);
        grid.setItems(getContent());
    }
}
