package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;
import org.firstinspires.ftc.teamcode.general.GeneralUtil;
import org.firstinspires.ftc.teamcode.manual.OurBot;

import java.util.Optional;

@Autonomous(name="Dismount", group="Auto")
public class Dismount extends Auto {
    @Override
    public void prep() throws InterruptedException {
        robot = new HardwareBot(hardwareMap);
        robot.mecanum();
        robot.winch();
        robot.lock();
        robot.knock();
    }

    @Override
    public void run() throws InterruptedException {
        detach(this);
    }

    public static void detach(Auto opmode) {
        opmode.robot.fl.ifPresent(fl ->{
            opmode.robot.fr.ifPresent(fr -> {
                opmode.robot.bl.ifPresent(bl ->{
                    opmode.robot.br.ifPresent(br -> {
                        DcMotor[] motors = new DcMotor[]{fl, fr, bl, fr};

                        double[] northeast = GeneralUtil.polarMecanum(45, 1);
                        double[] southeast = GeneralUtil.polarMecanum(-45, 1);
                        double[] southwest = GeneralUtil.polarMecanum(180 + 45, 1);
                        double[] northwest = GeneralUtil.polarMecanum(180 - 45, 1);
                        double[] north = GeneralUtil.polarMecanum(0, 1);
                        double[] south = GeneralUtil.polarMecanum(180, 1);
                        double[] east = GeneralUtil.polarMecanum(90, 1);
                        double[] west = GeneralUtil.polarMecanum(270, 0.5);

                        // The actual dismount process of going down
                        opmode.robot.lock.ifPresent(lock -> lock.setPosition(OurBot.UNLOCKED));
                        opmode.sleep(OurBot.LOCK_DELAY);
                        opmode.robot.winch.ifPresent(winch -> winch.setPower(1));
                        opmode.sleep(200);
                        opmode.robot.winch.ifPresent(winch -> winch.setPower(0));
                        opmode.sleep(2000);

                        AutoUtil.setMotors(west, motors);
                        opmode.sleep(300);
                        opmode.robot.winch.ifPresent(winch -> winch.setPower(0.4));
                        opmode.sleep(300);
                        opmode.robot.winch.ifPresent(winch -> winch.setPower(0));
                        AutoUtil.setMotors(west, motors);
                        opmode.sleep(700);
                        AutoUtil.stopMotors(motors);
                    });
                });
            });
        });

    }
}
