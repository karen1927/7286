
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
package org.firstinspires.ftc.teamcode.firstinspires;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="redteam_2", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
public class redteam extends OpMode
{
    /* Declare OpMode members. */
    ElapsedTime runtime;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo arm;
    private ColorSensor colorSensor=null;

    int count = 0;
    double currentTime = 0;
    String jewel = "";


   /*
    * Code to run ONCE when the driver hits INIT
    */

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

       /* eg: Initialize the hardware variables. Note that the strings used here as parameters
        * to 'get' must correspond to the names assigned during the robot configuration
        * step (using the FTC Robot Controller app on the phone).
        */
        // leftMotor  = hardwareMap.dcMotor.get("left_drive");
        // rightMotor = hardwareMap.dcMotor.get("right_drive");

        colorSensor = hardwareMap.colorSensor.get("color");
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");
        arm = hardwareMap.servo.get("arm");
        runtime = new ElapsedTime();






        // eg: Set the drive motor directions:
        // Reverse the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        //  rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        // telemetry.addData("Status", "Initialized");
    }

   /*
    * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    */

    @Override
    public void init_loop() {
        arm.setPosition(0.9 ); //sets  arm under 18" once initialized? (test)



    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();



    }

   /*
    * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    */

    @Override
    public void loop() {
        currentTime = runtime.time();
        telemetry.addData("Status", "Running");

        // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
        // leftMotor.setPower(-gamepad1.left_stick_y);
        // rightMotor.setPower(-gamepad1.right_stick_y);

        colorSensor.enableLed(true);

        double red = colorSensor.red();
        double blue = colorSensor.blue();
        double green = colorSensor.green();


        telemetry.addData("Red", red);
        telemetry.addData("Green", green);
        telemetry.addData("Blue", blue);
        telemetry.addData("Current Time ", currentTime);
        telemetry.addData("jewel color:", jewel);

        if (count == 1)//under or equal to 3 secs
        {
            arm.setPosition(0.00);

        }

        if (count == 2)//between 5 and 5.25 secs
        {
            if (red > blue && red > green)
            {
                jewel = "red";//if red move right
                moveRight();

            }
            if (blue > red && blue > green)

            {
                jewel = "blue";//if blue move left
                moveLeft();
                //thicc
            }
        }

        if (count == 3)//between 3.25 and 6.0 secs
        {
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        if (count == 4)// between 6 and 9 secs
        {
            arm.setPosition(0.9); //lift arm up
        }

        if (count == 5)//between 9 and 9.25 secs

        {
            if (jewel == "blue") {
                returnFromLeft();
            }
            if (jewel == "red") {
                returnFromRight();
            }
        }


        if (count == 6) { //stop
            frontLeft.setPower(0.0);
            backLeft.setPower(0.0);
            frontRight.setPower(0.0);
            backRight.setPower(0.0);
        }

        if (count == 7) { //go to safe zone
            double speed = 0.6;
            frontLeft.setDirection(DcMotor.Direction.REVERSE);
            backLeft.setDirection(DcMotor.Direction.REVERSE);
            frontRight.setDirection(DcMotor.Direction.FORWARD);
            backRight.setDirection(DcMotor.Direction.FORWARD);

            frontLeft.setPower(speed);
            backLeft.setPower(speed);
            frontRight.setPower(speed);
            backRight.setPower(speed);
        }

        if (count == 8) { //stop for the rest of autonomous
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        if (currentTime <= 3.0)
        {
            count = 1;
        }

        if (currentTime > 3.0 && currentTime <= 3.25)
        {
            count = 2;

        }

        if (currentTime > 3.25 && currentTime <= 6.0) {
            count = 3;

        }

        if (currentTime > 6.0 && currentTime <= 9.0)
        {
            count = 4;
        }


        if (currentTime > 9.0 && currentTime <= 9.25)
        {
            count = 5;

        }


        if (currentTime > 9.25 && currentTime<=11.0)
        {
            count = 6;

        }

        if (currentTime > 11.0 && currentTime <=12.25)
        {
            count = 7;

        }

        if (currentTime > 12.25)
        {
            count = 8;

        }
    }



    public void moveLeft(){
// this is if we are on team blue

        double speed = 0.2;
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);

    }

    public void returnFromLeft(){
        double speed = 0.2;
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);

    }

    public void moveRight(){
// this is if we are on team blue

        double speed = 0.2;
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);
    }

    public void returnFromRight(){
// this is if we are on team blue

        double speed = 0.2;
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        frontRight.setPower(speed);
        backRight.setPower(speed);

    }


    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}