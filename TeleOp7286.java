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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

@TeleOp(name="TeleOp7286", group="Iterative OpMode")  // @Autonomous(...) is the other common choice

public class TeleOp7286 extends OpMode
{
    /* Declare OpMode members. */
    WW7286_SY1718_Bot robot;

    /*  Adjustment values for the encoded lift  */
    // final double COUNTS_PER_MOTOR_REV = 1120;    // eg: ANDYMARK Motor Encoder
    // static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    //static final double     WHEEL_DIAMETER_INCHES   = 1.0 ;     // For figuring circumference
    static final double COUNTS_PER_INCH = 535/2; // num of ticks to move one inch
    //static final double DRIVE_SPEED = 0.2;
    /*  END - Adjustment values for the encoded lift  */


    private ElapsedTime runtime = new ElapsedTime();

    //Initializing bot object

    //private int counterUp = 0;
    //double totalHeight = 0;


    @Override
    public void init()
    {
        robot = new WW7286_SY1718_Bot(
                hardwareMap.dcMotor.get("FL"),
                hardwareMap.dcMotor.get("FR"),
                hardwareMap.dcMotor.get("BL"),
                hardwareMap.dcMotor.get("BR"),
                hardwareMap.dcMotor.get("intakefront"),
                hardwareMap.dcMotor.get("lift")
        );

        //Assigning each piece of hardware names set up on the phones to initialized variables.
        /*poker = hardwareMap.servo.get("poker");
        fBsktServo = hardwareMap.servo.get("fBsktServo");
        bBsktServo = hardwareMap.servo.get("bBsktServo");
        */
        //cSensor = hardwareMap.colorSensor.get("cSensor");

        //Creating a bot object containing movement methods to avoid the clutter of setting directions constantly.

        robot.init();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();
    }

    @Override
    public void init_loop()
    {
        // Does nothing
    }

    //Sets time to 0 just as the match starts
    @Override
    public void start()
    {
        runtime.reset();            }


    @Override
    public void loop() {

        // left stick controls direction
        // right stick X controls rotation

        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        // holonomic formulas

        float FrontLeft = -gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float FrontRight = gamepad1LeftY - gamepad1LeftX - gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX - gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX - gamepad1RightX;

        // clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

        // write the values to the motors
        robot.frontRight.setPower(FrontRight);
        robot.frontLeft.setPower(FrontLeft);
        robot.backLeft.setPower(BackLeft);
        robot.backRight.setPower(BackRight);


      /*
       * Telemetry for debugging
       */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", gamepad1LeftX) + " " +
                String.format("%.2f", gamepad1LeftY) + " " +  String.format("%.2f", gamepad1RightX));
        telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
        telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
        telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));


        /*    END  -- Game Pad 1  -- END   */


//for grippers:

        if (gamepad2.dpad_left) {
            //rolls in
            //telemetry.addData("Status", "button a");
            //telemetry.update();
            robot.frontIntake.setPower(.5);//Motor one goes counter clockwise//

        } else if (gamepad2.dpad_right) {
            //telemetry.addData("Status", "button b");
            //telemetry.update();//roll balls out
            robot.frontIntake.setPower(-.5);//otor one goes counter clockwise//

        } else {//stops
            robot.frontIntake.setPower(0); //Motor one goes counter clockwise//

        }

//for lift:

        if (gamepad2.y) //move up
        {
            liftArms(.5, 7.0);

            // totalHeight +=2.0;


            //telemetry.addData("counterUp: ",counterUp);
        }


        if (gamepad2.a)//move down
        {
            liftArms(.5, -7.0);

            // totalHeight -= 2.0;


            //telemetry.addData("counterUp: ",counterUp);
            //telemetry.addData("Status", "left stick button");    //
        }

        telemetry.addData("Current encoder value: ", robot.lift.getCurrentPosition()) ;
    }


    public void liftArms(double speed, double inches)
    {
        telemetry.addData("BEFORE encoder value: ", robot.lift.getCurrentPosition());

        // Determine new target position, and pass to motor controller
        int newLiftTarget = robot.lift.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
        //int newLiftTarget = robot.lift.getCurrentPosition() + 10;
        //int newLiftTarget = 1600;
        telemetry.addData("New lift target: ", newLiftTarget);

        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //if (newLiftTarget >= 0 && newLiftTarget <= 2407 )//checks if the next position will be between 0 and 9 inches
        //{
        robot.lift.setTargetPosition(newLiftTarget);
        robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lift.setPower(speed);
        //}

        if (!robot.lift.isBusy())
            robot.lift.setPower(0);

        /*if (newLiftTarget >= 0 && newLiftTarget <= 1337 )//checks if the next position will be between 0 and 5 inches
        {*/
        // Turn On RUN_TO_POSITION


        // Start motion


        // Stop all motion - Is this line even necessary?
        //robot.lift.setPower(0);

        // Turn off RUN_TO_POSITION
    }






    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}