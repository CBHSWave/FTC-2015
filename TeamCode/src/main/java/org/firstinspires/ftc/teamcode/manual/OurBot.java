package org.firstinspires.ftc.teamcode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwareBot;

@TeleOp(name="OurBot", group="Manual")
public class OurBot extends OpMode {
    /* Declare OpMode members. */
    HardwareBot robot       = new HardwareBot(); // use the class created to define a Pushbot's hardware
}
