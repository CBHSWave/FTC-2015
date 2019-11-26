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
                            double[] west = new double[]{0.5, -0.5, -0.5, 0.5};
                            double[] south = new double[]{-1,-1, -1, -1};
                            double[] north = new double[]{1, 1, 1, 1};
                            double[] rotate = new double[] {1, -1, 1, -1};
                            double[] rotate2 = new double[] {-1, 1, -1, 1};



                            // The actual dismount process of going down

                            AutoUtil.setMotors(east, motors);
                            sleep(1250);
                            AutoUtil.setMotors(south , motors);
                            sleep(20);
                            AutoUtil.stopMotors(motors);
                            sleep(10);
                            AutoUtil.setMotors(rotate, motors);
                            sleep(500);
                            AutoUtil.setMotors(east, motors);
                        });
                    });
                });
            });
        }
    }


