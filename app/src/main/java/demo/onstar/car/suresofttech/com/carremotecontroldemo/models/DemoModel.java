package demo.onstar.car.suresofttech.com.carremotecontroldemo.models;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

public class DemoModel {

    private Map<String, TargetConnectionInfo> map; // <VIN Number, TargetConnectionInfo>

    public DemoModel(){}

    public Map<String, TargetConnectionInfo> getMap() {
        if(map == null || map.size() == 0){
            map = new HashMap<String, TargetConnectionInfo>();
        }
        return map;
    }
}
