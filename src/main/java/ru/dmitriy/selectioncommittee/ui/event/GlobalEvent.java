package ru.dmitriy.selectioncommittee.ui.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Nazarow on 07.04.17.
 */
public class GlobalEvent {

    public static GlobalEvent instance;

    private List<Subscriber> subscribers;

    public interface Subscriber {
        void event(int code, EventData eventData);
    }

    public static GlobalEvent instance(){
        if (instance == null){
            instance = new GlobalEvent();
        }
        return instance;
    }

    private GlobalEvent(){
        subscribers = new ArrayList<>();
    }

    public void subscribe(Subscriber subscriber){
        if (subscribers != null){
            subscribers.add(subscriber);
        }
    }

    public void unSubscribe(Subscriber subscriber){
        if (subscribers != null){
            subscribers.remove(subscriber);
        }
    }

    public void event(int code, EventData eventData){
        if (subscribers != null){
            for (Subscriber s : subscribers){
                s.event(code, eventData);
            }
        }
    }

    public static class EventData<Data>{

        private Data data;

        public EventData(Data data){
            this.data = data;
        }

        public Data getData(){
            return data;
        }

    }

}
