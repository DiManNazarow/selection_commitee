package ru.dmitriy.selectioncommittee.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Nazarow on 11.03.17.
 */
public class JsonUtils {

    /**
     * классы, которые возможно форматировать в {@link JsonObject}, следут наследовать от этого интерфейса
     */
    public interface JSONPresentable {
        JsonObject asJSON();
    }

    /**
     * Не сериализуется
     * не нужно унаследоваться от этого интерфейса<br/>
     * достаточно объявить в теле класса public static final JSONParcel <class> PARCEL<br/>
     * чтобы передвать его в качестве аргумента методу {@link JsonUtils#asList(JsonArray, JSONParcel)}
     *
     * @param <T> тип класса, восстановленного из {@link JsonObject}
     */
    public interface JSONParcel<T> {
        T fromJSONObject(JsonObject o);
    }

    public static JsonArray asJSONArray(List<? extends JSONPresentable> list) {
        JsonArray a = new JsonArray();
        if (list != null) {
            for (JSONPresentable p : list) {
                a.add(p.asJSON());
            }
        }
        return a;
    }

    public static <T> ArrayList<T> asList(JsonArray array, JSONParcel<T> parcel) {
        ArrayList<T> result = new ArrayList<>();
        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                JsonObject o = (JsonObject) array.get(i);
                result.add(parcel.fromJSONObject(o));
            }
        }
        return result;
    }

}
