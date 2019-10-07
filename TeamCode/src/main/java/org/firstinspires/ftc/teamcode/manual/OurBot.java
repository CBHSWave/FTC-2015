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

    // 0.53 if you don't want whine
    public static final double LOCKED = 0.5;
    public static final double UNLOCKED = 0.65;

    // Given in milliseconds
    public static final long LOCK_DELAY = 150;

    public static final double Block_UP = 0;
    public static final double Block_DOWN = 0;

    public static final double UP_SPEED = 1.0;
    public static final double DOWN_SPEED = -0.5;

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
            robot.block();
            robot.block.ifPresent(block -> {
                block.setPosition(-1);
            });

            robot.arm();
            robot.lock();
            robot.lock.ifPresent(lock -> lock.setPosition(LOCKED));
            robot.intake();
        } catch (NullPointerException e) {
            telemetry.addData("NPE", e.toString());
        }
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

        robot.leftIn.ifPresent(leftIn -> robot.rightIn.ifPresent(rightIn -> {
            rightIn.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
            leftIn.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
        }));

        robot.block.ifPresent(block -> {
            if (gamepad1.a) {
                block.setPosition(1);
            } else if (gamepad1.b) {
                block.setPosition(-1);
            }
        });

        robot.arm.ifPresent(arm -> robot.lock.ifPresent(lock -> {
            if (gamepad1.dpad_up) {
                doLock(lock);

                arm.setPower(UP_SPEED);
            } else if (gamepad1.dpad_down) {
                doLock(lock);
                arm.setPower(DOWN_SPEED);
            } else {
                arm.setPower(0);
                if (gamepad1.right_bumper) {
                    lock.setPosition(UNLOCKED);
                } else {
                    lock.setPosition(LOCKED);
                }
            }
        }));

        robot.allTelemetry(telemetry);
        telemetry.update();
    }

    private void doLock(Servo lock) {
        try {
            if (!gamepad1.right_bumper && !gamepad1.left_bumper) {
                lock.setPosition(UNLOCKED);
            } else if (gamepad1.left_bumper) {
                lock.setPosition(LOCKED);
            }
            sleep(LOCK_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
            telemetry.addData("Lock Fail", e.getStackTrace());
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        for (DcMotor motor : robot.motors) {
            motor.setPower(0);
        }
        robot.lock.ifPresent(lock -> lock.setPosition(LOCKED));
    }

}
