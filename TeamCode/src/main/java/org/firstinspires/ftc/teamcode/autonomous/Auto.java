
package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.manual.HardwareBot;

/**
 * Created by wjackson on 2/20/2018.
 */

public abstract class Auto extends LinearOpMode {
    protected HardwareBot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        prep();
//        AutoUtil.resetAll(robot);
        waitForStart();

        run();

        AutoUtil.resetAll(robot);
    }

    public abstract void prep() throws InterruptedException;

    public abstract void run() throws InterruptedException;
}
