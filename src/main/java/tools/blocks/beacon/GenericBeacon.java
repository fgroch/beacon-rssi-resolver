package tools.blocks.beacon;

import tools.blocks.constants.MeasurementDeviceConstants;
import tools.blocks.filter.RssiFilter;

/**
 * Created by fgroch on 29.08.16.
 */
public class GenericBeacon implements Beacon {

    private double distance;
    private String macAddress;
    private double rssi;
    private int txPower;
    RssiFilter rssiFilter;
    MeasurementDeviceConstants measurementDeviceConstants;
    private boolean isFilterApplied = false;
    private boolean isDistanceCalculated = false;

    public GenericBeacon(String macAddress, int txPower) {
        this.macAddress = macAddress;
        this.txPower = txPower;
    }

    public GenericBeacon(String macAddress, double rssi, int txPower) {
        this.macAddress = macAddress;
        this.rssi = rssi;
        this.txPower = txPower;
    }

    public GenericBeacon(String macAddress, double rssi, int txPower, RssiFilter rssiFilter) {
        this(macAddress,rssi, txPower);
        this.rssiFilter = rssiFilter;
    }

    public GenericBeacon(String macAddress, double rssi, int txPower, MeasurementDeviceConstants measurementDeviceConstants) {
        this(macAddress,rssi, txPower);
        this.measurementDeviceConstants = measurementDeviceConstants;
    }

    public GenericBeacon(String macAddress, double rssi, int txPower, RssiFilter rssiFilter, MeasurementDeviceConstants measurementDeviceConstants) {
        this(macAddress,rssi, txPower);
        this.rssiFilter = rssiFilter;
        this.measurementDeviceConstants = measurementDeviceConstants;
    }

    @Override
    public double getDistance() {
        if (!isFilterApplied) {
            applyFilter();
        }
        if (!isDistanceCalculated) {
            calculateDistanceFromRssi();
        }
        return distance;
    }

    @Override
    public String getMacAddress() {
        return macAddress;
    }

    @Override
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public double getRssi() {
        return rssi;
    }

    @Override
    public void setRssi(double rssi) {
        isFilterApplied = false;
        isDistanceCalculated = false;
        this.rssi = rssi;
        applyFilter();
    }

    @Override
    public int getTxPower() {
        return txPower;
    }

    @Override
    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    @Override
    public RssiFilter getRssiFilter() {
        return rssiFilter;
    }

    @Override
    public void setRssiFilter(RssiFilter rssiFilter) {
        this.rssiFilter = rssiFilter;
    }

    @Override
    public MeasurementDeviceConstants getMeasurementDeviceConstants() {
        return measurementDeviceConstants;
    }

    @Override
    public void setMeasurementDeviceConstants(MeasurementDeviceConstants measurementDeviceConstants) {
        this.measurementDeviceConstants = measurementDeviceConstants;
    }

    @Override
    public void calculateDistanceFromRssi() {
        if (measurementDeviceConstants == null) {
            throw new IllegalStateException("Measurement device constants must be set before calculateDistanceFromRssi operation call!");
        }
        if (rssi == 0) {
            distance = -1.0;
        }

        double ratio = rssi*1.0/txPower;
        if (ratio < 1.0) {
            distance = Math.pow(ratio,10);
        }
        else {
            distance =  (measurementDeviceConstants.getCoefficientA())*Math.pow(ratio,measurementDeviceConstants.getCoefficientB()) + measurementDeviceConstants.getCoefficientC();
        }
        isDistanceCalculated = true;
    }

    @Override
    public void applyFilter() {
        if (rssiFilter == null) {
            throw new IllegalStateException("RSSI filter must be set before applyFilter operation call!");
        }
        rssi = rssiFilter.applyFilter(rssi);
        isFilterApplied = true;
    }
}
