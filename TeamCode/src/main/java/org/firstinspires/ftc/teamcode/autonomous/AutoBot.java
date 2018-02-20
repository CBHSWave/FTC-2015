package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;
import org.firstinspires.ftc.teamcode.general.Call;

import java.util.concurrent.TimeUnit;

// Declare that the OpMode is Autonomous and is named AutoBot
@Autonomous(name="AutoBot", group="Auto")
public class AutoBot extends Auto {

    /* Declare OpMode members. */

    HardwareBot robot;

    @Override
    public void init(){
        robot = new HardwareBot();   // Use our hardware
        robot.mecanum(hardwareMap);
    }

    @Override
    public void main() {
        DcMotor[] motors = new DcMotor[]{robot.fl, robot.br, robot.fr, robot.bl};
        AutoUtil.beforeAfter(AutoUtil.setMotorsCall(0.5, motors), AutoUtil.setMotorsCall(0, motors), robot.period, 1000);
    }
}

