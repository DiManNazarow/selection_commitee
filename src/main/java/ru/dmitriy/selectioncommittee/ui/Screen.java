package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.navigator.View;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by diman on 20.03.17.
 */
public abstract class Screen<Layout extends AbstractComponent> extends VerticalLayout implements View{

    public Screen(Layout layout){
        mainLayout = layout;
        buildScreen();
    }

    protected Layout mainLayout;

    public abstract void buildScreen();

    public Layout getMainLayout(){
        return mainLayout;
    }
}
