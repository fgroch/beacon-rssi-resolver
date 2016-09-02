# beacon-rssi-resolver

Java library to simplify RSSI (Received Signal Strength Indication) and distance calculations for BLE beacons. Originally design to use with Raspberry Pi but can be used on every java enabled device. Includes Kalman filter implementation and Raspberry Pi embedded antenna constants.

##Usage
Example of use can be find in examples/SimpleExample class. Library contains GenericBeacon class which implements Beacon interface. 
```
GenericBeacon genericBeacon = new GenericBeacon("macHere",-59); //mac address and txPower constructor
genericBeacon.setMeasurementDeviceConstants(new RPiEmbeddedAntennaConstants());//constants implementation setter
genericBeacon.setRssiFilter(new KalmanFilter());//filter implementation setter
for(int d : DATA){
    genericBeacon.setRssi(d);//in setter filter is applied
    System.out.println((int)d * -1 + "," + (int)genericBeacon.getDistance());//getDistance method calculates distance from filtered rssi
}
```
##Additional devices / filters / beacon types support
Additional devices could be supported by implementing MeasurementDeviceConstants interface, filters by implementing RssiFilter interface and beacons by implementing Beacon interface.



