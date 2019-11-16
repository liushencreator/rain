package util.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2018/9/9.
 */
public class Paramap extends HashMap<String, Object> implements Map<String, Object> {

    private static final long serialVersionUID = 1L;

    private Paramap() {
    }

    public static Paramap create() {
        return new Paramap();
    }

    @Override
    public Paramap put(String name, Object value) {
        super.put(name, value);
        return this;
    }


}
