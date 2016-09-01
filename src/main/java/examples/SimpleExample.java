package examples;

import tools.blocks.beacon.GenericBeacon;
import tools.blocks.constants.RPiEmbeddedAntennaConstants;
import tools.blocks.filter.KalmanFilter;

import java.time.Clock;

/**
 * Created by floyd on 29.08.16.
 */
public class SimpleExample {

    private static int[] DATA = new int[]{-76, -76, -78, -81, -74, -80, -81, -78, -88, -76, -88, -90};

    public static void main(String[] args){
        SimpleExample filter = new SimpleExample();
        KalmanFilter kalmanFilter = new KalmanFilter();
        for(int d : DATA){
            //filter.addMeasurement(d);
            //filter.update(d);
            //System.out.println("rssi:" +d + ", measurement: " + (int)kalmanFilter.applyFilter(d));
            System.out.println((int)d * -1 + "," + (int)kalmanFilter.applyFilter(d) * -1);
            //System.out.println("------------------------------------");
        }

        GenericBeacon genericBeacon = new GenericBeacon("macHere",-59);
        genericBeacon.setMeasurementDeviceConstants(new RPiEmbeddedAntennaConstants());
        genericBeacon.setRssiFilter(new KalmanFilter());
        for(int d : DATA){
            genericBeacon.setRssi(d);
            System.out.println((int)d * -1 + "," + (int)genericBeacon.getDistance());
        }
    }
}
