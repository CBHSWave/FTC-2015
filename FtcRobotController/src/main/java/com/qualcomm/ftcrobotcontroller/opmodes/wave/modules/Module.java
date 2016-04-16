package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public interface Module {
    void setup(Wave mode);

    void loop(Wave mode);

    void stop(Wave mode);
}
