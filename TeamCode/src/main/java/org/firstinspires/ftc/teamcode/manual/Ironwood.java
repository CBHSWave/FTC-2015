package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareBot;
import org.firstinspires.ftc.teamcode.autonomous.AutoUtil;

@TeleOp(name="Ironwood", group="Manual")
public class Ironwood extends OpMode{

    private static final float TEST_MOTOR_POW = 1;
    private static final float POSITION_A = 0;
    private static final float POSITION_B = 1000;
    private static final float POSITION_Y = 2000;
    private static final float POSITION_X = 3000;


    /* Declare OpMode members. */
    HardwareBot robot       = new HardwareBot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.
    AutoUtil autoUtil = new AutoUtil(1, 1, this);

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.lift(hardwareMap);

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

        if (robot.lift != null) {
            if (gamepad1.a) {
                autoUtil.runUntilEncoder(robot.lift, POSITION_A);
            } else if (gamepad1.b) {
                autoUtil.runUntilEncoder(robot.lift, POSITION_B);
            } else if (gamepad1.y) {
                autoUtil.runUntilEncoder(robot.lift, POSITION_Y);
            } else if (gamepad1.x) {
                autoUtil.runUntilEncoder(robot.lift, POSITION_X);
            }
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
