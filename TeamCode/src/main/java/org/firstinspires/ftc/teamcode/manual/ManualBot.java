package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;

@TeleOp(name="Manual", group="Manual")
public class ManualBot extends OpMode {

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
        robot.mecanum(hardwareMap);
        robot.intake(hardwareMap);
        robot.lift(hardwareMap);
        robot.flippy(hardwareMap);

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
        if (robot.fl != null && robot.fr != null && robot.bl != null && robot.br != null) {
            ManualUtil.mecanumDrive(gamepad1, 0.1,
                    robot.fl, robot.fr,
                    robot.bl, robot.br);
//            telemetry.addData("fl", robot.fl.getPower());
//            telemetry.addData("fr", robot.fr.getPower());
//            telemetry.addData("bl", robot.bl.getPower());
//            telemetry.addData("br", robot.br.getPower());
        }

        if (robot.leftMotor != null && robot.rightMotor != null) {
            ManualUtil.normalDrive(gamepad1, robot.leftMotor, robot.rightMotor);

//            telemetry.addData("leftmotor", robot.leftMotor.getPower());
//            telemetry.addData("rightmotor", robot.rightMotor.getPower());
        }

        if (robot.lift != null) {
            if (gamepad1.b) {
                robot.lift.setPower(0.25);
            } else if (gamepad1.a){
                robot.lift.setPower(-0.25);
            } else {
                robot.lift.setPower(0);
            }
//            telemetry.addData("liftmotor", robot.leftMotor.getPower());
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

        if (gamepad1.y) {
            robot.flippy.setPower(0.2);
        } else if (gamepad1.x) {
            robot.flippy.setPower(-0.1);
        } else {
            robot.flippy.setPower(0);
        }

        robot.allTelemetry(telemetry, hardwareMap);
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
