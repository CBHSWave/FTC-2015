package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;
import org.firstinspires.ftc.teamcode.manual.OurBot;

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
        opmode.robot.lock.ifPresent(lock -> lock.setPosition(OurBot.UNLOCKED));
        opmode.sleep(OurBot.LOCK_DELAY);
        opmode.robot.winch.ifPresent(winch -> winch.setPower(0.1));
        opmode.sleep(100);
        opmode.robot.winch.ifPresent(winch -> winch.setPower(0));
        opmode.sleep(5000);

        AutoUtil.stopMotors((DcMotor[]) opmode.robot.motors.toArray());
    }
}
