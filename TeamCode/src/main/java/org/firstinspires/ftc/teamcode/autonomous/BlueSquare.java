package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.general.GeneralUtil;
import org.firstinspires.ftc.teamcode.manual.HardwareBot;

    // Declare that the OpMode is Autonomous and is named AutoBot
    @Autonomous(name="BlueSquare", group="Auto")
   public class BlueSquare extends Auto {

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
            robot.armA();
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
                            double[] east = new double[]{-0.5, 0.5, 0.5, -0.5};
                            double[] west = new double[]{0.5, -0.5, -0.5, 0.5};
                            double[] south = new double[]{-1,-1, -1, -1};
                            double[] north = new double[]{ 0.5, 0.5, 0.5, 0.5};
                            double[] rotate = new double[] {0.5, -0.5, 0.5, -0.5};
                            double[] rotate2 = new double[] {-1, 1, -1, 1};
                            robot.armA.ifPresent(armA -> armA.setPosition(1));



                            // The actual process
                            AutoUtil.setMotors(east, motors);
                            sleep(2450);
                            AutoUtil.stopMotors(motors);
                            sleep(500);
                            robot.armA.ifPresent(armA -> armA.setPosition(.25));
                            sleep(125);
                            robot.arm.ifPresent(arm ->{
                                arm.setPower(.5);
                                sleep(600);
                                arm.setPower(0);
                                sleep(100);
                            });
                            AutoUtil.setMotors( west , motors );
                            sleep(900);
                            AutoUtil.stopMotors(motors);
                            sleep(100);
                            AutoUtil.setMotors(rotate, motors);
                            sleep(80);
                            AutoUtil.stopMotors(motors);
                            sleep(100);
                            AutoUtil.setMotors(north , motors);
                            sleep(2250);
                            AutoUtil.stopMotors(motors);
                            sleep(100);
                            AutoUtil.setMotors(rotate , motors);
                            sleep(10);
                            AutoUtil.stopMotors(motors);
                            sleep(100);
                            robot.armA.ifPresent(armA -> armA.setPosition(1));
                            sleep(500);
                            robot.arm.ifPresent(arm ->{
                                arm.setPower(-.5);
                                sleep(1000);
                                arm.setPower(0);
                                sleep(100);
                            });
                            AutoUtil.stopMotors(motors);
                            sleep(100);
                            AutoUtil.setMotors(west,motors);
                            sleep(1400);
                        });
                    });
                });
            });
        }
    }


