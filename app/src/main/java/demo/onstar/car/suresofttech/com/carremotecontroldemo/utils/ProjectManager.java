package demo.onstar.car.suresofttech.com.carremotecontroldemo.utils;

import demo.onstar.car.suresofttech.com.carremotecontroldemo.consts.Const;
import demo.onstar.car.suresofttech.com.carremotecontroldemo.models.DemoModel;
import demo.onstar.car.suresofttech.com.carremotecontroldemo.models.TargetConnectionInfo;

public class ProjectManager {

    private volatile static ProjectManager instance;
    private DemoModel model;

    private ProjectManager(){
        model = new DemoModel();
    }

    public static ProjectManager getDefault() {
        if (instance == null) {
            synchronized (ProjectManager.class) {
                if (instance == null) {
                    instance = new ProjectManager();
                }
            }
        }
        return instance;
    }

    public void createModel(String vinNumber){

        model.getMap().put(vinNumber, new TargetConnectionInfo());
    }

    public TargetConnectionInfo findModelBy(String vinNumber){
        if(model.getMap().containsKey(vinNumber)){
            return model.getMap().get(vinNumber);
        }else{
            return null;
        }
    }

    public void deleteModelBy(String vinNumber){
        if(model.getMap().containsKey(vinNumber)){
            model.getMap().remove(vinNumber);
        }
    }

    public void updateModelBy(String vinNumber, String ipAddress, String ipPort){
        if(model.getMap().containsKey(vinNumber)){
            model.getMap().get(vinNumber).setIpAddress(ipAddress);
            model.getMap().get(vinNumber).setIpPort(ipPort);
        }
    }

    public void deleteModelAll(){
        model.getMap().clear();
    }

    public String toString(String vinNumber) {
        StringBuffer sb = new StringBuffer();
        TargetConnectionInfo info = findModelBy(vinNumber);
        if(info != null){
            sb.append("[VIN]:");
            sb.append(vinNumber);
            sb.append(Const.NEXT_LINE);
            sb.append(info.toString());
        }
        return sb.toString();
    }
}
