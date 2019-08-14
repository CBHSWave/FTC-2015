package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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
        robot.transGear.ifPresent(transGear -> {
            if (gamepad1.right_bumper) {
                transGear.setPosition(0.55);
            } else if (gamepad1.left_bumper) {
                transGear.setPosition(0.45);
            } else {
                transGear.setPosition(0.5);
            }
        });

        robot.transDrive.ifPresent(transDrive -> {
            double magStick = ManualUtil.pythag(gamepad1.right_stick_x, gamepad1.right_stick_y);
            transDrive.setPower(ManualUtil.scale(magStick));
        });

        robot.transTurn.ifPresent(transDrive -> {
            double turnValue = (gamepad1.left_stick_x + 1) / 2;
            transDrive.setPosition(ManualUtil.scale(turnValue));

        });
    }
}