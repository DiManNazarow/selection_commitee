package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.navigator.View;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by diman on 20.03.17.
 */
public abstract class Screen<Layout extends AbstractComponent, P extends Presenter> extends VerticalLayout implements View{

    protected P presenter;

    public Screen(Layout layout){
        mainLayout = layout;
        buildScreen();
        onScreenBuild();
    }

    protected void onScreenBuild(){

    }

    protected Layout mainLayout;

    public abstract void buildScreen();

    public Layout getMainLayout(){
        return mainLayout;
    }

    public void setPresenter(P presenter){
        this.presenter = presenter;
        onPresenterSet();
    }

    protected void onPresenterSet(){};

    public P getPresenter(){
        return presenter;
    }

    protected void scrollTo(Component component){
        UI.getCurrent().scrollIntoView(component);
    }
}
