package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;

@TeleOp(name="OurBot", group="Manual")
public class OurBot extends OpMode {

    private static final float TEST_MOTOR_POW = 1;
    /* Declare OpMode members. */
    HardwareBot robot       = new HardwareBot(hardwareMap); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.mecanum();
        robot.knock();
//        robot.intake();
//        robot.lift();
//        robot.flippy();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        ManualUtil.drive(robot, gamepad1);

        if (robot.lift != null) {
            if (gamepad1.b) {
                robot.lift.setPower(0.25);
            } else if (gamepad1.a){
                robot.lift.setPower(-0.25);
            } else {
                robot.lift.setPower(0);
            }
        }

        if (robot.leftIn != null && robot.rightIn != null) {
            if (gamepad1.right_bumper || gamepad1.left_bumper) {
                robot.rightIn.setPower(-gamepad1.right_trigger);
                robot.leftIn.setPower(-gamepad1.left_trigger);
            } else {
                robot.rightIn.setPower(gamepad1.right_trigger);
                robot.leftIn.setPower(gamepad1.left_trigger);
            }
        }

        if (robot.flippy != null) {
            if (gamepad1.y) {
                robot.flippy.setPower(0.2);
            } else if (gamepad1.x) {
                robot.flippy.setPower(-0.1);
            } else {
                robot.flippy.setPower(0);
            }
        }

        if (robot.knock != null) {
            if (gamepad1.a) {
                robot.knock.setPosition(0.55);
            } else if (gamepad1.b) {
                robot.knock.setPosition(0.45);
            } else {
                robot.knock.setPosition(0.5);
            }
        }

        robot.allTelemetry(telemetry);
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        for (DcMotor motor : robot.motors) {
            motor.setPower(0);
        }
    }

}
