package ru.dmitriy.selectioncommittee.ui;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.ui.Screen;

import java.util.List;

/**
 * Created by Dmitriy Nazarow on 24.03.17.
 */
public abstract class ListScreen<Content> extends Screen<VerticalLayout> {

    protected Grid<Content> grid;

    public ListScreen() {
        super(new VerticalLayout());
    }

    @Override
    public void buildScreen() {
        grid = new Grid<Content>();
        setGridColumn();
        mainLayout.addComponent(grid);
        addComponent(grid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        grid.setItems(getContent());
    }

    public abstract List<Content> getContent();

    /**
     * Override to set column of grid
     */
    protected void setGridColumn(){}
}
