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

    public static void showNotification(String title, String value){
        Notification notification = new Notification(title, value, Notification.Type.HUMANIZED_MESSAGE);
        notification.setDelayMsec(3000);
        notification.show(Page.getCurrent());
    }

    public static void showNotification(String title, int value){
        Notification notification = new Notification(title, String.valueOf(value), Notification.Type.HUMANIZED_MESSAGE);
        notification.setDelayMsec(3000);
        notification.show(Page.getCurrent());
    }

    public static void showNotification(String title, long value){
        Notification notification = new Notification(title, String.valueOf(value), Notification.Type.HUMANIZED_MESSAGE);
        notification.setDelayMsec(3000);
        notification.show(Page.getCurrent());
    }

}
