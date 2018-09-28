package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for our robot
 * Each different section of the design requires a method for initialization
 * For example, the mecanum wheels can be initialized by calling the mecanum() method
 */
public class HardwareBot {
    // Main Robot Functionality

    /* Classic drive motors */
    public DcMotor  leftMotor;
    public DcMotor  rightMotor;

    // Motors (fr = front right)
    public DcMotor fr;
    public DcMotor fl;
    public DcMotor br;
    public DcMotor bl;

    // Intake motors
    public DcMotor leftIn;
    public DcMotor rightIn;

    public Servo knock;

    // Transmission functionality
    public Servo transGear;
    public DcMotor transDrive;
    public Servo transTurn;

    // Some nice arrays
    public ArrayList<DcMotor> motors = new ArrayList<>();
    public ArrayList<Servo> servos = new ArrayList<>();

    private final HardwareMap hardwareMap;
    
//    public AccelerationSensor accel;

    /* local OpMode members. */
    public ElapsedTime period  = new ElapsedTime();


    /* Constructor */
    public HardwareBot(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    public Servo setupServo(Servo servo) {
        servo.setPosition(0);
        servos.add(servo);
        return servo;
    }

    public DcMotor setupMotor(DcMotor motor, boolean encoder) {
        motor.setPower(0);
        if (encoder) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        motors.add(motor);
        return motor;
    }

    public void intake() {
        leftIn = hardwareMap.dcMotor.get("leftIn");
        leftIn.setDirection(DcMotorSimple.Direction.REVERSE);
        rightIn = hardwareMap.dcMotor.get("rightIn");

        setupMotor(leftIn, true);
        setupMotor(rightIn, true);
    }

    public void mecanum() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        setupMotor(fr, false);
        setupMotor(br, false);
        setupMotor(fl, false);
        setupMotor(bl, false);
    }

    public void transGear() {
        transGear = hardwareMap.servo.get("transGear");
        setupServo(transGear);
    }

    public void transDrive() {
        transDrive = hardwareMap.dcMotor.get("transDrive");
        setupMotor(transDrive, false);
    }

    public void transTurn() {
        transTurn = hardwareMap.servo.get("transTurn");
        setupServo(transTurn);
    }

    public void transmission() {
        transTurn();
        transGear();
        transDrive();
    }

    public void normalDrive() {
        // Define and Initialize Motors
        leftMotor   = hardwareMap.dcMotor.get("left");
        rightMotor  = hardwareMap.dcMotor.get("right");
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        setupMotor(leftMotor, true);
        setupMotor(rightMotor, true);
    }

    public void knock() {
        knock = hardwareMap.servo.get("knock");

        setupServo(knock);
    }

    public void motorTelemetry(Telemetry telemetry) {
        for (DcMotor motor : hardwareMap.dcMotor) {
            if (motor != null) {
                Iterator<String> iterator = hardwareMap.getNamesOf(motor).iterator();
                if (iterator.hasNext()) {
                    telemetry.addData(iterator.next(), motor.getPower());
                }
            }
        }
    }

    public void servoTelemetry(Telemetry telemetry) {
        for (Servo servo : hardwareMap.servo) {
            if (servo != null) {
                Iterator<String> iterator = hardwareMap.getNamesOf(servo).iterator();
                if (iterator.hasNext()) {
                    telemetry.addData(iterator.next(), servo.getPosition());
                }
            }
        }
    }

    public void allTelemetry(Telemetry telemetry) {
        motorTelemetry(telemetry);
        servoTelemetry(telemetry);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

