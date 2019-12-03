package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.function.Consumer;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

/**
 * This is NOT an opmode.
 * hello
 * This class can be used to define all the specific hardware for our robot
 * Each different section of the design requires a method for initialization
 * For example, the mecanum wheels can be initialized by calling the mecanum() method
 */
public class HardwareBot {
    // Main Robot Functionality
    // Optional Values are either empty or filled with the value of the type in <>

    /* Classic drive motors */
    public Optional<DcMotor>  leftMotor = Optional.empty();
    public Optional<DcMotor>  rightMotor = Optional.empty();

    // Motors (fr = front right)
    public Optional<DcMotor> fr = Optional.empty();
    public Optional<DcMotor> fl = Optional.empty();
    public Optional<DcMotor> br = Optional.empty();
    public Optional<DcMotor> bl = Optional.empty();

    // arm
    public Optional<DcMotor> arm = Optional.empty();

    // skystone
    public Optional<Servo> vaughn = Optional.empty();

    //block holder
    public Optional<Servo> block = Optional.empty();

    // Autonomous arm
    public Optional<Servo> armA = Optional.empty();

    // Transmission functionality
    public Optional<Servo> transGear = Optional.empty();
    public Optional<DcMotor> transDrive = Optional.empty();
    public Optional<Servo> transTurn = Optional.empty();

    // Some nice arrays
    public ArrayList<DcMotor> motors = new ArrayList<>();
    public ArrayList<Servo> servos = new ArrayList<>();

    private final HardwareMap hardwareMap;

    private Consumer<DcMotor> encoderConsumer = m -> setupMotor(m, true);
    private Consumer<DcMotor> noEncoderConsumer = m -> setupMotor(m, false);
    private Consumer<DcMotor> reverseConsumer = m -> m.setDirection(DcMotorSimple.Direction.REVERSE);

//    public AccelerationSensor accel;

    /* local OpMode members. */
    public ElapsedTime period  = new ElapsedTime();


    /* Constructor */
    public HardwareBot(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    public Optional<Servo> setupServo(Servo servo) {
        servo.setPosition(0);
        servos.add(servo);
        return Optional.ofNullable(servo);
    }

    public Optional<DcMotor> setupMotor(DcMotor motor, boolean encoder) {
        motor.setPower(0);
        if (encoder) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        motors.add(motor);
        return Optional.ofNullable(motor);
    }

    // defines motors
    public void mecanum() {
        fr = Optional.ofNullable(hardwareMap.dcMotor.get("fr"));
        fl = Optional.ofNullable(hardwareMap.dcMotor.get("fl"));
        br = Optional.ofNullable(hardwareMap.dcMotor.get("br"));
        bl = Optional.ofNullable(hardwareMap.dcMotor.get("bl"));

        fl.ifPresent(reverseConsumer);
        bl.ifPresent(reverseConsumer);

        fr.ifPresent(noEncoderConsumer);
        br.ifPresent(noEncoderConsumer);
        fl.ifPresent(noEncoderConsumer);
        bl.ifPresent(noEncoderConsumer);
    }

    public void transGear() {
        transGear = Optional.ofNullable(hardwareMap.servo.get("transGear"));
        transGear.ifPresent(this::setupServo);
    }

    public void transDrive() {
        transDrive = Optional.ofNullable(hardwareMap.dcMotor.get("transDrive"));
        transDrive.ifPresent(noEncoderConsumer);
    }

    public void transTurn() {
        transTurn = Optional.ofNullable(hardwareMap.servo.get("transTurn"));
        transTurn.ifPresent(this::setupServo);
    }

    public void transmission() {
        transTurn();
        transGear();
        transDrive();
    }

    public void arm() {
        arm = Optional.ofNullable(hardwareMap.dcMotor.get("arm"));
        arm.ifPresent(encoderConsumer);
   }

    public void normalDrive() {
        // Define and Initialize Motors
        leftMotor   = Optional.ofNullable(hardwareMap.dcMotor.get("left"));
        rightMotor  = Optional.ofNullable(hardwareMap.dcMotor.get("right"));
//        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.ifPresent(reverseConsumer) ;// Set to FORWARD if using AndyMark motors

        leftMotor.ifPresent(encoderConsumer);
        rightMotor.ifPresent(encoderConsumer);
    }

    public void armA() {
        armA = Optional.ofNullable(hardwareMap.servo.get("armA"));
        armA.ifPresent(armA -> {
            armA.setPosition(1);
            servos.add(armA);
        });
    }

    public void block() {
        block = Optional.ofNullable(hardwareMap.servo.get("block"));
        block.ifPresent(block -> {
            block.setPosition(0);
            servos.add(block);
        });
    }
    public void vaughn() {
        vaughn = Optional.ofNullable(hardwareMap.servo.get("vaughn"));
        vaughn.ifPresent(vaughn -> {
            vaughn.setPosition(0);
            servos.add(vaughn);
        });
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

