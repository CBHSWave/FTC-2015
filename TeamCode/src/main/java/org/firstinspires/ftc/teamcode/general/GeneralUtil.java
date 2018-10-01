package org.firstinspires.ftc.teamcode.general;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by wjackson on 9/18/2018.
 */

public class GeneralUtil {

    public static double[] polarMecanum(double theta, double magnitude) {
        return cartesianMecanum(magnitude * Math.cos(theta / 180 * Math.PI), magnitude * Math.sin(theta / 180 * Math.PI));
    }

    /**
     *
     * @param x x direction
     * @param y y direction
     * @return {flPow, frPow, blPow, brPow}
     */
    public static double[] cartesianMecanum(double x, double y) {
        // Assign the values for the different powers
        // You can find these values online (check the FTC subreddit)
        double flPow = y - x;
        double frPow = y + x;
        double blPow = y + x;
        double brPow = y - x;
        return new double[]{flPow, frPow, blPow, brPow};
    }

    @SafeVarargs
    public static <T> T[] optArray(Optional<T>... opts) {
        return (T[]) Stream.of(opts).filter(Optional::isPresent).map(o -> o.get()).toArray();
    }
}
