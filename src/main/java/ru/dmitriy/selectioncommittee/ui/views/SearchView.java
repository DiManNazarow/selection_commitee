package ru.dmitriy.selectioncommittee.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 * Created by diman on 19.04.17.
 */
public class SearchView extends HorizontalLayout implements View {

    private TextField searchField;

    private Button searchButton;

    private String caption;

    public interface OnTextChangeListener{
        void onTextChanged(String text);
    }

    public interface OnSearchButtonClickListener{
        void onSearchButtonClick();
    }

    private OnTextChangeListener textChangeListener;

    private OnSearchButtonClickListener searchButtonClickListener;

    public SearchView(String caption){
        this.caption = caption;
        init();
    }

    private void init(){

        searchField = new TextField(caption);
        searchButton = new Button("Поиск");

        searchField.addValueChangeListener(valueChangeEvent -> {
           if (textChangeListener != null){
               textChangeListener.onTextChanged(valueChangeEvent.getValue());
           }
        });

        searchButton.addClickListener(clickEvent -> {
            if (searchButtonClickListener != null){
                searchButtonClickListener.onSearchButtonClick();
            }
        });

        addComponents(searchField, searchButton);

    }

    public String getSearchText(){
        return searchField.getValue();
    }

    public void setSearchButtonClickListener(OnSearchButtonClickListener searchButtonClickListener) {
        this.searchButtonClickListener = searchButtonClickListener;
    }

    public void setTextChangeListener(OnTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
