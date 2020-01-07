package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.general.GeneralUtil;
import org.firstinspires.ftc.teamcode.manual.HardwareBot;

// Declare that the OpMode is Autonomous and is named AutoBot
@Autonomous(name="RedTriangle", group="Auto")
public class RedTriangle extends Auto {


    @Override
    public void prep() throws InterruptedException {
        robot = new HardwareBot(hardwareMap);

        robot.mecanum();
        robot.block();
        robot.arm();
        robot.vaughn();
        robot.armA();
    }

    @Override
    public void run() throws InterruptedException {
        robot.fl.ifPresent(fl -> {
            robot.fr.ifPresent(fr -> {
                robot.bl.ifPresent(bl -> {
                    robot.br.ifPresent(br -> {
                        DcMotor[] motors = new DcMotor[]{fl, fr, bl, br};

                        double[] northeast = GeneralUtil.polarMecanum(45, 1);
                        double[] southeast = GeneralUtil.polarMecanum(-45, 1);
                        double[] southwest = GeneralUtil.polarMecanum(180 + 45, 1);
                        double[] northwest = GeneralUtil.polarMecanum(180 - 45, 1);
                        double[] east = new double[]{-0.5, 0.5, 0.5, -0.5};
                        double[] west = new double[]{0.5, -0.5, -0.5, 0.5};
                        double[] south = new double[]{-0.5, -0.5, -0.5, -0.5};
                        double[] north = new double[]{0.5, 0.5, 0.5, 0.5};
                        double[] rotate = new double[]{0.5, -0.5, 0.5, -0.5};
                        double[] rotate2 = new double[]{-0.5, 0.5, -0.5, 0.5};
                        robot.armA.ifPresent(armA -> armA.setPosition(1));

                        //actual process

                        AutoUtil.setMotors(east, motors);
                        sleep(2350);
                        AutoUtil.stopMotors(motors);
                        sleep(100);
                        AutoUtil.setMotors(rotate2, motors);
                        sleep(10);
                        AutoUtil.stopMotors(motors);
                        sleep(100);
                        robot.armA.ifPresent(armA -> armA.setPosition(0));
                        sleep(750);
                        AutoUtil.stopMotors(motors);
                        sleep(100);
                        AutoUtil.setMotors(south, motors);
                        sleep(500);
                        AutoUtil.setMotors(west, motors);
                        sleep(2400);
                        AutoUtil.stopMotors(motors);
                        sleep(100);
                        robot.armA.ifPresent(armA -> armA.setPosition(1));
                        AutoUtil.setMotors(north, motors);
                        sleep(2000);
                        AutoUtil.stopMotors(motors);
                        sleep(1000);
                    });
                });
            });
        });
    }
}
