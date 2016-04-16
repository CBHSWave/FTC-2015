package com.qualcomm.ftcrobotcontroller.opmodes.wave.modules;

import com.google.inject.AbstractModule;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.Wave;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Scaling;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.util.Setters;

public class ModuleInjection extends AbstractModule {
    private Wave wave;

    public ModuleInjection(Wave wave) {
        this.wave = wave;
    }

    @Override
    protected void configure() {
        Setters setters = new Setters(wave);
        bind(Scaling.class).toInstance(new Scaling(setters));
        bind(Setters.class).toInstance(setters);
    }
}
