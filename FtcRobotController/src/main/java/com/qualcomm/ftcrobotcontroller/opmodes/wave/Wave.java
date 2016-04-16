package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.ModuleInjection;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Telemetry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wes
 * Top Level class for all bot stuff, automatically loops
 */
public class Wave extends OpMode {
    public HashMap<HardwareDevice, String> hardwareNames;
    private Injector injector;
    public double startTime;
    protected List<Module> modules = new ArrayList<Module>();

    public Wave(Class<? extends Module>... modules) {
        this.injector = Guice.createInjector(new ModuleInjection(this));
        makeModules(modules);
    }

    @SafeVarargs
    public Wave(Injector injector, Class<? extends Module>... modules) {
        this.injector = injector;
        makeModules(modules);
    }

    private void makeModules(Class<? extends Module>... modules) {
        makeModules(Arrays.asList(modules));
    }

    private void makeModules(List<Class<? extends Module>> moduleClasses) {
        for (Class<? extends Module> clazz : moduleClasses) {
            modules.add(injector.getInstance(clazz));
        }
    }

    @Override
    public void loop() {
        for (Module module : modules) {
            module.loop(this);
        }
    }

    @Override
    public void init() {
        for (Module module : modules) {
            module.setup(this);
        }
    }

    @Override
    public void stop() {
        for (Module module : modules) {
            module.stop(this);
        }
    }

    public <A> A getPart(String name, HardwareMap.DeviceMapping<A> mapping, Telemetry telemetry) {
        try {
            return mapping.get(name);
        } catch (IllegalArgumentException a) {
            telemetry.addData("Bad part name:", a.getLocalizedMessage());
            DbgLog.error(a.getLocalizedMessage());
        }
        return null;
    }
}
