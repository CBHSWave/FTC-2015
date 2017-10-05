package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareBot;

// Declare that the OpMode is Autonomous and is named AutoBot
@Autonomous(name="Square", group="Auto")
public class Square extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareBot robot   = new HardwareBot();   // Use our hardware
    private AutoUtil util = AutoUtil.testBot(this); // Create a testBot utility class with this OpMode

    @Override
    public void runOpMode() {
        // Setup hardware and then wait for start of the OpMode
        robot.init(hardwareMap);
        waitForStart();


        while (opModeIsActive()) {
            // Go forward on the motors for 100 cm
            util.goForward(50, robot.leftMotor, robot.rightMotor);

            // Zero Turn on the established motors for 90 degrees counterclockwise
            util.zeroTurn(robot.leftMotor, robot.rightMotor, 90);

            // Go forward on the motors for 50 cm
            util.goForward(50, robot.leftMotor, robot.rightMotor);

            // Zero Turn on the established motors for 90 degrees counterclockwise
            util.zeroTurn(robot.leftMotor, robot.rightMotor, 90);

            // Go forward on the motors for 50 cm
            util.goForward(50, robot.leftMotor, robot.rightMotor);

            // Zero Turn on the established motors for 90 degrees counterclockwise
            util.zeroTurn(robot.leftMotor, robot.rightMotor, 90);

            // Go forward on the motors for 50 cm
            util.goForward(50, robot.leftMotor, robot.rightMotor);

            // Zero Turn on the established motors for 90 degrees counterclockwise
            util.zeroTurn(robot.leftMotor, robot.rightMotor, 90);
        }
    }
}

