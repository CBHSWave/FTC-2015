package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.google.inject.Injector;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Bucket;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Drive;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Lift;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.manual.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wes
 * Manual period code
 */
public class WaveManual extends WaveTele {
    public WaveManual(Injector injector) {
        super(injector);
    }

    @Override
    public List<Class<? extends Module>> getModules() {
        ArrayList<Class<? extends Module>> mods = new ArrayList<Class<? extends Module>>();

        mods.add(Bucket.class);
        mods.add(Drive.class);
        mods.add(Lift.class);
        mods.add(Spinner.class);

        return mods;
    }
}
