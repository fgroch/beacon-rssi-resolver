package tools.blocks.constants;

/**
 * Created by floyd on 29.08.16.
 */
public class RPiEmbeddedAntennaConstants implements MeasurementDeviceConstants {

    private double coefficientA;
    private double coefficientB;
    private double coefficientC;

    public RPiEmbeddedAntennaConstants() {
        coefficientA = 0.42093;
        coefficientB = 6.9476;
        coefficientC = 0.54992;
    }

    @Override
    public double getCoefficientA() {
        return coefficientA;
    }

    @Override
    public double getCoefficientB() {
        return coefficientB;
    }

    @Override
    public double getCoefficientC() {
        return coefficientC;
    }
}
