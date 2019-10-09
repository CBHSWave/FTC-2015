package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.manual.HardwareBot;
import org.firstinspires.ftc.teamcode.manual.OurBot;

@Autonomous(name="Dismount", group="Auto")
public class Dismount extends Auto {
    @Override
    public void prep() throws InterruptedException {
        robot = new HardwareBot(hardwareMap);
        robot.mecanum();
        robot.arm();
        robot.block();
        robot.vaughn();
    }

    @Override
    public void run() throws InterruptedException {
        detachUp(this);
    }

    public static void detachGeneral(Auto opmode, double powDuring, double powAfter) {
        opmode.robot.fl.ifPresent(fl ->{
            opmode.robot.fr.ifPresent(fr -> {
                opmode.robot.bl.ifPresent(bl ->{
                    opmode.robot.br.ifPresent(br -> {
                        DcMotor[] motors = new DcMotor[]{fl, fr, bl, br};

                        double[] west = new double[]{0.5, -0.5, -0.5, 0.5};
                        double[] south = new double[]{1, 1, 1, 1};
                        double[] east = new double[]{-1, 1, 1, -1};

                        // The actual dismount process of going down
                        opmode.sleep(OurBot.LOCK_DELAY);
                        opmode.robot.arm.ifPresent(arm -> arm.setPower(1));
                        opmode.sleep(200);
                        opmode.robot.arm.ifPresent(arm -> arm.setPower(powDuring));
                        opmode.sleep(3000);

                        AutoUtil.setMotors(east, motors);
                        opmode.sleep(200);
                        opmode.robot.arm.ifPresent(arm -> arm.setPower(powAfter));
                        AutoUtil.stopMotors(motors);
                        opmode.sleep(350);
                        opmode.robot.arm.ifPresent(arm -> arm.setPower(0));
                        AutoUtil.setMotors(south, motors);
                        opmode.sleep(700);
                        AutoUtil.stopMotors(motors);
                    });
                });
            });
        });
    }

    public static void detachUp(Auto opmode) {
        detachGeneral(opmode, 0, 0.5);
    }

    public static void detachDown(Auto opmode) {
        detachGeneral(opmode, 0, -0.6);
    }

    public static void detachPower(Auto opmode) {
        detachGeneral(opmode, -0.5, 0);
    }
}
