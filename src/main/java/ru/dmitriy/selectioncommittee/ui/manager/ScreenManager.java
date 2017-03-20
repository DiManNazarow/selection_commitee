package ru.dmitriy.selectioncommittee.ui.manager;

import com.vaadin.navigator.Navigator;
import ru.dmitriy.selectioncommittee.ui.Screen;

/**
 * Created by diman on 20.03.17.
 */
public class ScreenManager {

    private static ScreenManager instance;

    private Navigator navigator;

    private ScreenManager(Navigator navigator){
        this.navigator = navigator;
    }

    public static ScreenManager init(Navigator navigator){
        if (instance == null){
            instance = new ScreenManager(navigator);
        }
        return instance;
    }

    public static ScreenManager getInstance() throws ScreenNotInitException {
        if (instance == null){
            throw new ScreenNotInitException();
        }
        return instance;
    }



    public static class ScreenNotInitException extends Exception{
        public ScreenNotInitException(){
            super("Screen Manger not initialised. Call init() before getInstance()");
        }
    }

}
