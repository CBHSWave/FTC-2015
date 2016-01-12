package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Wes
 * To be extended by custom opmodes, provides telemetry logging
 */
public abstract class WaveTelemetry extends WaveHardware {
    @Override
    public void loop() {
        telemetry.addData("Text", "*** Robot Data***");
    }

    protected void setMotors(double power, DcMotor... motors) {
        for (DcMotor motor : motors) {
            String name = hardwareNames.get(motor);
            if (name == null)
                name = "Unknown motor";
            telemetry.addData(name + " tgt pwr", name + "  pwr: " + String.format("%.2f", power));
            motor.setPower(power);
        }
    }
}
