package com.qualcomm.ftcrobotcontroller.opmodes.wave;

/**
 * Created by Wes
 * To be extended by custom opmodes, provides telemetry logging
 */
public abstract class WaveTelemetry extends WaveHardware {
    @Override
    public void loop() {
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", motorLeft.getPower()));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", motorRight.getPower()));
    }
}
