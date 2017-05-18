package ru.dmitriy.selectioncommittee.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitriy.selectioncommittee.resources.R;
import ru.dmitriy.selectioncommittee.utils.TextUtils;

import java.util.Locale;

/**
 * Created by diman on 06.04.17.
 */
public class InputTextLayout extends VerticalLayout implements View {

    public static final String DEFAULT_ERROR_TEXT = "Заполните поле";

    private String text;

    private String errorText = DEFAULT_ERROR_TEXT;

    private String caption;

    private TextField textField;

    private Label errorTextLabel;

    public InputTextLayout(String caption){
        this.caption = caption;
        init();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public void init(){

        textField = new TextField();
        textField.setCaption(caption);
        textField.addValueChangeListener(valueChangeEvent -> {
            text = valueChangeEvent.getValue();
            hideError();
        });

        errorTextLabel = new Label();

        setMargin(false);
        addComponents(textField, errorTextLabel);
    }

    public void setTextField(String text){
        textField.setValue(text);
    }

    public void setErrorField(String text){
        errorTextLabel.setValue(text);
    }

    public void showError(){
        String error = String.format(Locale.getDefault(), "<font color=\"%s\"> %s", R.Color.RED, errorText);
        errorTextLabel.setContentMode(ContentMode.HTML);
        errorTextLabel.setValue(error);
    }

    public void hideError(){
        errorTextLabel.setValue("");
    }

    public void refresh(){
        textField.setValue(text);
        textField.setCaption(caption);
        errorTextLabel.setValue(errorText);
    }

    public void clear(){
        textField.clear();
        errorTextLabel.setValue("");
        text = null;
    }

    public boolean isTextEmpty(){
        return TextUtils.isEmpty(text);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
