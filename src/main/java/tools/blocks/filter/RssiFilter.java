package tools.blocks.filter;

/**
 * Created by fgroch on 29.08.16.
 */
public interface RssiFilter {
    double applyFilter(double rssi);
}
