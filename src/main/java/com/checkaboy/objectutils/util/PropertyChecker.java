package com.checkaboy.objectutils.util;

import java.util.Comparator;
import java.util.List;

public class PropertyChecker {
    
    public static <T> boolean checkProperty(List<T> obj, Comparator<? super T> c) {
        if(obj == null || c == null) return false;
        if(obj.size() < 2) return true;

        try {
            for(int i = 1; i < obj.size(); i ++)
                if (c.compare(obj.get(i - 1), obj.get(i)) != 0) return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
