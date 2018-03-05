package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;

@TeleOp(name="OurBot", group="Manual")
public class OurBot extends OpMode {
    /* Declare OpMode members. */
    HardwareBot robot       = new HardwareBot(); // use the class created to define a Pushbot's hardware

    @Override
    public void init() {
        robot.drive(hardwareMap);

    }

    @Override
    public void loop()
    {
        if(robot.lm != null && robot.rm != null)
        {
            robot.lm.setPower(gamepad1.left_stick_y);
            robot.rm.setPower(gamepad1.right_stick_y);
        }
    }
}
