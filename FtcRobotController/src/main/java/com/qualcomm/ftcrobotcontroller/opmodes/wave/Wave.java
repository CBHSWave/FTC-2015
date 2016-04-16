package com.qualcomm.ftcrobotcontroller.opmodes.wave;

import com.google.inject.Injector;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.ftcrobotcontroller.opmodes.wave.modules.Module;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Telemetry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wes
 * Top Level class for all bot stuff, automatically loops
 */
public abstract class Wave extends OpMode {
    public HashMap<HardwareDevice, String> hardwareNames;
    private Injector injector;
    protected double startTime;
    protected List<Module> modules = new ArrayList<Module>();

    public Wave(Injector injector) {
        this.injector = injector;
    }


    public abstract List<Class<? extends Module>> getModules();

    private void makeModules(List<Class<? extends Module>> moduleClasses) {
        for (Class<? extends Module> clazz : moduleClasses) {
            modules.add(injector.getInstance(clazz));
        }
    }

    @Override
    public void start()
    {
        makeModules(getModules());
        startTime = time;
    }

    @Override
    public void loop() {
        for (Module module : modules) {
            try {
                module.loop(this);
            } catch (ClassCastException e) {
                notSupported(module, e);
            }
        }
    }

    @Override
    public void init() {
        for (Module module : modules) {
            try {
                module.setup(this);
            } catch (ClassCastException e) {
                notSupported(module, e);
            }
        }
    }

    private void notSupported(Module module, ClassCastException e) {
        String name = module.getClass().getSimpleName();
        String info = "module " + name + " not supported by " + this.getClass().getSimpleName();
        telemetry.
                addData("module " + name + " failure", info);
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
