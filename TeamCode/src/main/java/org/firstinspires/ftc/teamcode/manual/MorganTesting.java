package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareBot;

@TeleOp(name="MorganTest", group="Manual")
public class MorganTesting extends OpMode{

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
        robot.normalDrive(hardwareMap);
        robot.lift(hardwareMap);
        robot.grabber(hardwareMap);
        robot.lead(hardwareMap);
        telemetry.addData("*TEST*", "");


        if (robot.fl != null && robot.fr != null && robot.bl != null && robot.br != null) {
            ManualUtil.mecanumDrive(gamepad1, 0.1,
                    robot.fl, robot.fr,
                    robot.bl, robot.br);
            telemetry.addData("fl", robot.fl.getPower());
            telemetry.addData("fr", robot.fr.getPower());
            telemetry.addData("bl", robot.bl.getPower());
            telemetry.addData("br", robot.br.getPower());
        }

        if (robot.leftMotor != null && robot.rightMotor != null) {
            ManualUtil.normalDrive(gamepad1, robot.leftMotor, robot.rightMotor);

            telemetry.addData("leftmotor", robot.leftMotor.getPower());
            telemetry.addData("rightmotor", robot.rightMotor.getPower());
        }

        if (robot.testservo != null) {
            if (gamepad1.dpad_up) {
                robot.testservo.setPosition(robot.testservo.getPosition() + 0.2);
            } else if (gamepad1.dpad_down) {
                robot.testservo.setPosition(robot.testservo.getPosition() - 0.2);
            }
            telemetry.addData("testservo", robot.testservo.getPosition());
        }

        if (robot.lift != null) {
            if (gamepad1.dpad_up) {
                robot.lift.setPower(0.5);
            } else if (gamepad1.dpad_down){
                robot.lift.setPower(-0.5);
            } else {
                robot.lift.setPower(0);
            }
            telemetry.addData("liftmotor", robot.leftMotor.getPower());
        }

        if (robot.lead != null) {
            robot.lead.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
            telemetry.addData("lead", robot.lead.getPower());
        }

        if (robot.grabber != null) {
            if (gamepad1.dpad_left) {
                robot.grabber.setPower(1);
            } else if (gamepad1.dpad_right) {
                robot.grabber.setPower(-1);
            } else {
                robot.grabber.setPower(0);
            }
            telemetry.addData("grabber", robot.grabber.getPower());
        }

        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
