package tools.blocks.beacon;

import tools.blocks.constants.MeasurementDeviceConstants;
import tools.blocks.filter.RssiFilter;

/**
 * Created by fgroch on 29.08.16.
 */
public interface Beacon {
    double getDistance();
    String getMacAddress();
    void setMacAddress(String macAddress);
    double getRssi();
    void setRssi(double rssi);
    int getTxPower();
    void setTxPower(int txPower);
    RssiFilter getRssiFilter();
    void setRssiFilter(RssiFilter rssiFilter);
    MeasurementDeviceConstants getMeasurementDeviceConstants();
    void setMeasurementDeviceConstants(MeasurementDeviceConstants measurementDeviceConstants);
    void calculateDistanceFromRssi();
    void applyFilter();
}
