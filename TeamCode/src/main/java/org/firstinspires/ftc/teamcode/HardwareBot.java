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
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareBot {
    /* PublicOpMode members. */
    public DcMotor  leftMotor;
    public DcMotor  rightMotor;

    // Motors (fr = front right)
    public DcMotor fr;
    public DcMotor fl;
    public DcMotor br;
    public DcMotor bl;

    public DcMotor lift;
    public DcMotor lead;
    public DcMotor grabber;
    public DcMotor flippy;

    public ArrayList<DcMotor> motors = new ArrayList<>();
    private ArrayList<Servo> servos = new ArrayList<>();
    public DcMotor leftIn;
    public DcMotor rightIn;

//    public AccelerationSensor accel;

    /* local OpMode members. */
    public ElapsedTime period  = new ElapsedTime();


    /* Constructor */
    public HardwareBot(){}

    public void setupServo(Servo servo) {
        servo.setPosition(0);
        servos.add(servo);
    }

    public void setupMotor(DcMotor motor, boolean encoder) {
        motor.setPower(0);
        if (encoder) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        motors.add(motor);
    }

    public void lift(HardwareMap map) {
        lift = map.dcMotor.get("lift");
        setupMotor(lift, true);
    }

    public void flippy(HardwareMap map) {
        flippy = map.dcMotor.get("flippy");
        setupMotor(flippy, true);
    }

    public void lead(HardwareMap map) {
        lead = map.dcMotor.get("lift");
        setupMotor(lead, true);
    }

    public void grabber(HardwareMap map) {
        grabber = map.dcMotor.get("grabber");
        setupMotor(grabber, true);
    }

    public void intake(HardwareMap map) {
        leftIn = map.dcMotor.get("leftIn");
        leftIn.setDirection(DcMotorSimple.Direction.REVERSE);
        rightIn = map.dcMotor.get("rightIn");

        setupMotor(leftIn, true);
        setupMotor(rightIn, true);
    }

    public void mecanum(HardwareMap map) {
        fr = map.dcMotor.get("fr");
        fl = map.dcMotor.get("fl");
        br = map.dcMotor.get("br");
        bl = map.dcMotor.get("bl");
//
//        fr.setDirection(DcMotorSimple.Direction.REVERSE);
//        fl.setDirection(DcMotorSimple.Direction.REVERSE);
//        br.setDirection(DcMotorSimple.Direction.FORWARD);
//        bl.setDirection(DcMotorSimple.Direction.FORWARD);

        setupMotor(fr, false);
        setupMotor(br, false);
        setupMotor(fl, false);
        setupMotor(bl, false);
    }

    public void normalDrive(HardwareMap map) {
        // Define and Initialize Motors
//        accel = map.accelerationSensor.get("accel");
        leftMotor   = map.dcMotor.get("left");
        rightMotor  = map.dcMotor.get("right");
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        setupMotor(leftMotor, true);
        setupMotor(rightMotor, true);
    }

    public void motorTelemetry(Telemetry telemetry, HardwareMap map) {
        for (DcMotor motor : motors) {
            if (motor != null) {
                Iterator<String> iterator = map.getNamesOf(motor).iterator();
                if (iterator.hasNext()) {
                    telemetry.addData(iterator.next(), motor.getPower());
                }
            }
        }
    }

    public void servoTelemetry(Telemetry telemetry, HardwareMap map) {
        for (Servo servo : map.servo) {
            if (servo != null) {
                Iterator<String> iterator = map.getNamesOf(servo).iterator();
                if (iterator.hasNext()) {
                    telemetry.addData(iterator.next(), servo.getPosition());
                }
            }
        }
    }

    public void allTelemetry(Telemetry telemetry, HardwareMap map) {
        motorTelemetry(telemetry, map);
        servoTelemetry(telemetry, map);
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

