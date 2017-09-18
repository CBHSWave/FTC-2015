/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

import java.security.acl.AclNotFoundException;
import java.util.HashMap;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="AutoBot", group="Auto")
public class AutoBot extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareBot robot   = new HardwareBot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();
    private double TICKS_PER_CM = (25.33233 + 25.24073)/2;
    private double RADIUS = 23;
    private double CIRC = Math.PI * RADIUS * 2;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        DcMotor[] motors = {robot.leftMotor, robot.rightMotor};
        goForward(motors, 100);
        zeroTurn(robot.leftMotor, robot.rightMotor, 90);
        goForward(motors, 50);
        while(opModeIsActive()) {}
    }

    public void goForward(DcMotor[] motors, double cm) {
        HashMap<DcMotor, Float> powers = new HashMap<>();
        HashMap<DcMotor, Double> cms = new HashMap<>();
        for (DcMotor motor : motors) {
            powers.put(motor, Float.valueOf(1));
            cms.put(motor, cm);
        }
        goDistanceMap(powers, cms);
    }

    public void zeroTurn(DcMotor left, DcMotor right, double angle) {
        HashMap<DcMotor, Float> powers = new HashMap<>();
        powers.put(left, Float.valueOf(angle < 0 ? 1 : -1));
        powers.put(right, Float.valueOf(angle < 0 ? -1 : 1));
        HashMap<DcMotor, Double> cms = new HashMap<>();
        cms.put(left, angleToDistance(Math.abs(angle)));
        cms.put(right, angleToDistance(Math.abs(angle)));
        goDistanceMap(powers, cms);
    }

    public void goDistanceMap(HashMap<DcMotor, Float> motors, HashMap<DcMotor, Double> cm) {
        HashMap<DcMotor, Double> hashMap = new HashMap<>();
        for (DcMotor motor : motors.keySet()) {
            motor.setPower(motors.get(motor));
            hashMap.put(motor, traveled(motor));
        }
        while (opModeIsActive() && !hashMap.isEmpty()) {
            for (DcMotor motor : motors.keySet()) {
                if (traveled(motor) - hashMap.get(motor) > cm.get(motor)) {
                    motor.setPower(0);
                    hashMap.remove(motor);
                }
            }
        }
    }

    // Return centimeters traveled since start
    public double traveled(DcMotor motor) {
        return convertEncoder(motor.getCurrentPosition());
    }

    //TODO
    public double convertEncoder(float encoderTicks) {
        return encoderTicks / TICKS_PER_CM;
    }

    public double angleToDistance(double angle) {
        return angle / 360 * CIRC;
    }
}

