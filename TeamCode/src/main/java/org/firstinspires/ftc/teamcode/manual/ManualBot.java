package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareBot;

@TeleOp(name="Manual", group="Manual")
public class ManualBot extends OpMode{

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

        // Temporary testservo controls
        if (gamepad1.dpad_up) {
            robot.testservo.setPosition(robot.testservo.getPosition() + 1);
        } else if (gamepad1.dpad_down) {
            robot.testservo.setPosition(robot.testservo.getPosition() - 1);
        }

        // Record the position for the testservo
        telemetry.addData("testservo", robot.testservo.getPosition());
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
