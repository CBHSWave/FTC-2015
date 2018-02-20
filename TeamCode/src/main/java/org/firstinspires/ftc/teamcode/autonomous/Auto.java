package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by wjackson on 2/20/2018.
 */

public abstract class Auto extends OpMode {
    private boolean done = false;

    @Override
    public void loop() {
        if (done) {
            return;
        }
        main();
        done = true;
    }

    public abstract void main();
}
