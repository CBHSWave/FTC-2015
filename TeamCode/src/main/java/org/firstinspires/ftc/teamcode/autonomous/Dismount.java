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
        robot.knock.ifPresent(knock -> knock.setPosition(OurBot.UNLOCKED));
        sleep(OurBot.LOCK_DELAY);
        robot.winch.ifPresent(winch -> winch.setPower(OurBot.DOWN_SPEED));
        sleep(100);
        robot.winch.ifPresent(winch -> winch.setPower(0));
        sleep(5000);
        AutoUtil.stopMotors((DcMotor[]) robot.motors.toArray());
    }
}
