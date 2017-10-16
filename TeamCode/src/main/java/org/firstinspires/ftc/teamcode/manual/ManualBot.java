package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareBot;

@TeleOp(name="Manual", group="Manual")
public class ManualBot extends OpMode{

    private static final float TEST_MOTOR_POW = 1;
    /* Declare OpMode members. */
    HardwareBot robot       = new HardwareBot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
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
        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        double left = -gamepad1.left_stick_y;
        double right = -gamepad1.right_stick_y;

        // Set the power based on scaling to make it easier to control
        robot.leftMotor.setPower(ManualUtil.scale(left));
        robot.rightMotor.setPower(ManualUtil.scale(right));

        // Testmotor1 controls
        robot.testmotor1.setPower(ManualUtil.scale(gamepad1.right_trigger)- ManualUtil.scale(gamepad1.left_trigger));


        // Temporary testservo controls
        if (gamepad1.dpad_up) {
            robot.testservo.setPosition(robot.testservo.getPosition() + 0.2);
        } else if (gamepad1.dpad_down) {
            robot.testservo.setPosition(robot.testservo.getPosition() - 0.2);
        }

        // Testmotor2 controls

        if (gamepad1.right_bumper){
            robot.testmotor2.setPower(TEST_MOTOR_POW);
        } else if (gamepad1.left_bumper) {
            robot.testmotor2.setPower(-TEST_MOTOR_POW);
        } else {
            robot.testmotor2.setPower(0);
        }

        // Record the positions and powers for telemetry data for testing
        telemetry.addData("*TEST*", "");
        telemetry.addData("testservo", robot.testservo.getPosition());
        telemetry.addData("testmotor1", robot.testmotor1.getPower());
        telemetry.addData("testmotor2", robot.testmotor2.getPower());

        telemetry.addData("*DRIVE*", "");
        telemetry.addData("leftmotor", robot.leftMotor.getPower());
        telemetry.addData("rightmotor", robot.rightMotor.getPower());
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
