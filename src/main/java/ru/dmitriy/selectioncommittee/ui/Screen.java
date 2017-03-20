package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.ui.UI;

/**
 * Created by diman on 20.03.17.
 */
public abstract class Screen<Layout>{

    private Layout mainLayout;

    public abstract Layout buildScreen();

    public Layout getMainLayout(){
        return mainLayout;
    }
}
