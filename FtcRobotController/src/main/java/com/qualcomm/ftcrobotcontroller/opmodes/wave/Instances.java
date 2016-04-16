package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.auto.Move;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.auto.Spin;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Bucket;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Drive;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Lift;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Spinner;

public class Instances {
    public static Wave manual = new Wave(
            Drive.class,
            Bucket.class,
            Lift.class,
            Spinner.class);

    public static Wave auto = new Wave(Move.class, Spin.class);

    public static Wave tele = new Wave(Drive.class);
}
