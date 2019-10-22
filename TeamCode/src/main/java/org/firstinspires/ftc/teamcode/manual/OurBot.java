package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

@TeleOp(name="OurBot", group="Manual")
public class OurBot extends OpMode {

    private static final float TEST_MOTOR_POW = 1;
    /* Declare OpMode members. */
    HardwareBot robot; // use the class created to define a Pushbot's hardware
    // could also use HardwarePushbotMatrix class.

    public final double BLOCK_IN=0.50;
    public final double BLOCK_OUT=-0.50;
    public final double BLOCK_STOP=0.00;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot = new HardwareBot(hardwareMap);
        try {
            robot.mecanum();
            robot.vaughn();
            robot.vaughn.ifPresent(vaughn -> {
                vaughn.setPosition(-1);
            });
            robot.arm();
        } catch (NullPointerException e) {
            telemetry.addData("NPE", e.toString());
        }

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
    public void start() {};

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        ManualUtil.drive(robot, gamepad1);

        robot.vaughn.ifPresent(vaughn -> {
            if (gamepad1.a) {
                vaughn.setPosition(.5);
            } else if (gamepad1.b) {
                vaughn.setPosition(0);
            }
        });

        robot.block.ifPresent(block -> {
            if (gamepad1.x) {
                block.setDirection(Servo.Direction.FORWARD);
            } else if (gamepad1.y) {
                block.setDirection(Servo.Direction.REVERSE);
            } else {
                block.setPosition(BLOCK_STOP);
            }
        });
        robot.arm.ifPresent(arm -> {
            arm.setPower(gamepad1.right_trigger / 2 - gamepad1.left_trigger / 2);
        });
        ;

        robot.allTelemetry(telemetry);
        telemetry.update();

    }

    ;

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


