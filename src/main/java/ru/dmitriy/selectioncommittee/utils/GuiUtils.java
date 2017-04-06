package ru.dmitriy.selectioncommittee.utils;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

/**
 * Created by diman on 06.04.17.
 */
public class GuiUtils {

    public static void showErrorMessage(String text){
        Notification notification = new Notification("Ошибка", text, Notification.Type.ERROR_MESSAGE);
        notification.setDelayMsec(1000);
        notification.show(Page.getCurrent());
    }

}
