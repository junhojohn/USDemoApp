package demo.onstar.car.suresofttech.com.carremotecontroldemo.models;

import demo.onstar.car.suresofttech.com.carremotecontroldemo.consts.Const;

public class TargetConnectionInfo {

    private String ipAddress = null;
    private String ipPort = null;

    public TargetConnectionInfo(){

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    @Override
    public String toString() {
        return "[IP ADDRESS]:" + getIpAddress() + Const.NEXT_LINE +
                "[IP PORT]:" + getIpPort();
    }
}
