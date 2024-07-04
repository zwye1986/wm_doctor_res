package com.pinde.res.ctrl.osca;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/10.
 */
public class OscaCompartor  implements Comparator
{
    @Override
    public int compare(Object o1, Object o2)
    {

        Map m1= (Map )o1;
        String name1= (String) m1.get("order");
        Map m2= (Map )o2;
        String name2= (String) m2.get("order");

        return name1.compareTo(name2);

    }
}
