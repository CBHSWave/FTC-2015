package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.manual.HardwareBot;
import org.firstinspires.ftc.teamcode.general.GeneralUtil;
import org.firstinspires.ftc.teamcode.manual.OurBot;

// Declare that the OpMode is Autonomous and is named AutoBot
@Autonomous(name="CraterAuto", group="Auto")
public class CraterAuto extends Auto {

    static final long RTime = 1700;
    static final long ETime = 1120;
    static final long FTime = 1100;

    @Override
    public void prep() throws InterruptedException {
        robot = new HardwareBot(hardwareMap);

        robot.mecanum();
        robot.block();
        robot.arm();
        robot.vaughn();
    }

    @Override
    public void run() throws InterruptedException {
        robot.fl.ifPresent(fl ->{
            robot.fr.ifPresent(fr -> {
                robot.bl.ifPresent(bl ->{
                    robot.br.ifPresent(br -> {
                        DcMotor[] motors = new DcMotor[]{fl, fr, bl, br};

                        double[] northeast = GeneralUtil.polarMecanum(45, 1);
                        double[] southeast = GeneralUtil.polarMecanum(-45, 1);
                        double[] southwest = GeneralUtil.polarMecanum(180 + 45, 1);
                        double[] northwest = GeneralUtil.polarMecanum(180 - 45, 1);
//                        double[] north = GeneralUtil.polarMecanum(0, 1);/
                        double[] east = new double[]{-1, 1, 1, -1};
                        double[] north = new double[]{-1, -1, -1, -1};
                        double[] getItOut = new double[]{0.3, 0.5, -0.5, -0.3};
                        double[] rotate = new double[] {1, -1, 1, -1};
                        double[] rotate2 = new double[] {-1, 1, -1, 1};

                        double[] west = new double[]{0.5, -0.5, -0.5, 0.5};
                        double[] south = new double[]{1, 1, 1, 1};

                        // The actual dismount process of going down
                        robot.block.ifPresent(lock -> lock.setPosition(OurBot.UNLOCKED));
                        sleep(OurBot.LOCK_DELAY);
                        robot.arm.ifPresent(winch -> winch.setPower(1));
                        sleep(200);
                        robot.arm.ifPresent(winch -> winch.setPower(0));
                        sleep(3000);

                        AutoUtil.setMotors(west, motors);
                        sleep(500);
                        robot.arm.ifPresent(winch -> winch.setPower(0.5));
                        sleep(350);
                        robot.arm.ifPresent(winch -> winch.setPower(0));
                        AutoUtil.setMotors(west, motors);
                        sleep(150);
                        AutoUtil.stopMotors(motors);
                        sleep(100);
                        AutoUtil.setMotors(south, motors);
                        sleep(540);
                        AutoUtil.stopMotors(motors);
                        sleep(100);

                        AutoUtil.setMotors(rotate2, motors);
                        sleep(100);
                        AutoUtil.stopMotors(motors);
                        sleep(100);

                        // Go code
                        AutoUtil.setMotors(east, motors);
                        sleep(1375);
                        AutoUtil.stopMotors(motors);
                        sleep(100);

                        AutoUtil.setMotors(rotate2, motors);
                        sleep(RTime/4-275);

                        AutoUtil.stopMotors(motors);
                        sleep(100);
                        AutoUtil.setMotors(north, motors);
                        sleep(ETime + 275
                        );
                        AutoUtil.stopMotors(motors);
                        sleep(100);
//                        AutoUtil.setMotors(north, motors);
//                        sleep(FTime);
                        AutoUtil.stopMotors(motors);


                        robot.vaughn.ifPresent(vaughn -> {
                            vaughn.setPosition(1.0);
                            sleep(600);
                            vaughn.setPosition(-1);
                            sleep(600);
                            vaughn.setPosition(1.0);
                            sleep(600);
                            vaughn.setPosition(-1);
                            sleep(600);
                            vaughn.setPosition(1.0);
                            sleep(600);
                            vaughn.setPosition(-1);
                            sleep(600);
                            vaughn.setPosition(1.0);
                            sleep(600);
                            vaughn.setPosition(-1);
                            sleep(600);
                            vaughn.setPosition(1.0);
                            sleep(600);
                            vaughn.setPosition(-1);
                            sleep(600);
                        });
                        AutoUtil.setMotors(south, motors);
                        sleep(250);
                        AutoUtil.stopMotors(motors);
                    });
                });
            });
        });
    }
}

