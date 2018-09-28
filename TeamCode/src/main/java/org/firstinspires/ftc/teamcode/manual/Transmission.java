package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.HardwareBot;

/**
 * Created by wjackson on 9/28/2018.
 */

public class Transmission extends OpMode {
    HardwareBot robot = new HardwareBot(hardwareMap);

    @Override
    public void init() {
        robot.transmission();
    }

    @Override
    public void loop() {
        if (robot.transGear != null) {
            if (gamepad1.a) {
                robot.transGear.setPosition(0.55);
            } else if (gamepad1.b) {
                robot.transGear.setPosition(0.45);
            } else {
                robot.transGear.setPosition(0.5);
            }
        }

        if (robot.transDrive != null) {
            double magStick = ManualUtil.pythag(gamepad1.left_stick_x, gamepad1.left_stick_y);
            robot.transDrive.setPower(ManualUtil.scale(magStick));
        }
    }
}
