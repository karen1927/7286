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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
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

@TeleOp(name="TeleOp7286", group="Iterative Opmode")  // @Autonomous(...) is the other common choice

public class TeleOp7286 extends OpMode
{
    /* Declare OpMode members. */
    WW7286_SY1718_Bot robot;

    /*  Adjustment values for the encoded lift  */
    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: ANDYMARK Motor Encoder
    // static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 1.0 ;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (3.1415);
    static final double DRIVE_SPEED = 0.2;
    /*  END - Adjustment values for the encoded lift  */


    private ElapsedTime runtime = new ElapsedTime();

    //Initializing bot object

    int counterUp=0;
    double totalHeight = 0;


    @Override
    public void init()
    {
        robot = new WW7286_SY1718_Bot(
                hardwareMap.dcMotor.get("frontLeft"),
                hardwareMap.dcMotor.get("frontRight"),
                hardwareMap.dcMotor.get("backLeft"),
                hardwareMap.dcMotor.get("backRight"),
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

        /*    BEGIN -- Game Pad 1 -- BEGIN    */
        double leftX = gamepad1.left_stick_x;
        double leftY = -gamepad1.left_stick_y;

        double rightX = gamepad1.right_stick_x;
        double rightY = gamepad1.right_stick_y;

        double throttle = gamepad1.right_trigger;

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        throttle = Range.clip(throttle, 0, 1);//clips values into section
        throttle = (float) scaleInput(throttle);
        throttle = (float) (throttle * .9);

        //If the right joystick is pushed more than half right then rotate clockwise
        if (rightX >= .5)
        {
            robot.clockWise(throttle);
            telemetry.addData("Direction", "Clock-wise");
        }

        //If the right joystick is pushed more than half left then rotate counter clockwise
        else if (rightX <= -0.5)
        {
            robot.counterClockWise(throttle);
            telemetry.addData("Direction", "Counter Clock-wise");
        }

        //If the left joystick is pushed more than half left and less than half up or down then go left
        else if (leftX <= -.5 && (leftY < .5 && leftY > -.5))
        {
            robot.left(throttle);
            telemetry.addData("Direction", "Left");
        }

        //If the left joystick is pushed more than half right and less than half up or down then go right
        else if (leftX >= .5 && (leftY < .5 && leftY > -.5))
        {
            robot.right(throttle);
            telemetry.addData("Direction", "Right");
        }

        //If the left joystick is at least half up
        else if (leftY >= .5)
        {
            //Check to see if it is also half right, if so go Forward and Right
            if (leftX > .5) {
                robot.forRight(throttle);
                telemetry.addData("Direction", "Forward-Right");
            }

            //If not, check if it is also half left, if so go Forward and Left
            else if (leftX < -.5) {
                robot.forLeft(throttle);

                telemetry.addData("Direction", "Forward-Left");
            }

            //If they didn't move it left or right, just go forward
            else if (leftX >= -.5 && leftX <= .5) {
                robot.forward(throttle);

                telemetry.addData("Direction", "Forward");
            }
        }

        //Opposite of the previous chain
        //Check to see if the left joystick is at least half down
        else if (leftY <= -.5) {

            //If it is also half right then go back and right
            if (leftX > .5) {
                robot.backwRight(throttle);

                telemetry.addData("Direction", "Back-Right");
            }

            //If it is also half left then go back and left
            else if (leftX < -.5) {
                robot.backwLeft(throttle);

                telemetry.addData("Direction", "Back-Left");
            }

            //If it was not moved left or right just go back
            else if (leftX >= -.5 && leftX <= .5) {
                robot.back(throttle);

                telemetry.addData("Direction", "Back");
            }
        }
        //If the joystick was not moved at least halfway in any direction then stay still
        else {
            robot.forward(0);
        }

        /*    END  -- Game Pad 1  -- END   */



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


        if (gamepad2.y)
        {
            //telemetry.update();

            if (counterUp < 2)
            {
                encoderDrive(DRIVE_SPEED, 4.7, 10.0);
                counterUp++;
                totalHeight += 4.7;
            }

            telemetry.addData("Status", "right stick button");    //
            telemetry.update();
            ;

        }

        if (gamepad2.a)
        {
            if (counterUp > 0) {
                encoderDrive(DRIVE_SPEED, -4.7, 3.0);
                counterUp--;
                totalHeight -= 4.7;
            }
            telemetry.addData("Status", "left stick button");    //
            telemetry.update();
        }

        if (gamepad2.dpad_down)
        {
            robot.lift.setPower(-.4);

            if (gamepad2.dpad_up)
            {
                robot.lift.setPower(.4);
            }
            else
            {
                robot.lift.setPower(0);
            }

        /*
        if (gamepad2.left_stick_button && gamepad2.right_stick_button)
        {
            encoderDrive(DRIVE_SPEED, -totalHeight, 3.0);
            counterUp=0;
            totalHeight = 0;
        }
        */

            if (gamepad2.right_bumper)
            {
                encoderDrive(DRIVE_SPEED, 1.0, 3.0);
                totalHeight = 1.0;
            }

            if (gamepad2.left_bumper)
            {
                encoderDrive(DRIVE_SPEED, -totalHeight, 3.0);
                totalHeight = 0;
            }





        }
    }



    public void encoderDrive(double speed,
                             double liftInches,
                             double timeoutS) {


        int newLiftTarget;

        // Determine new target position, and pass to motor controller
        newLiftTarget = robot.lift.getCurrentPosition() + (int) (liftInches * COUNTS_PER_INCH);

        robot.lift.setTargetPosition(newLiftTarget);

        // Turn On RUN_TO_POSITION
        robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        robot.lift.setPower(Math.abs(speed));


        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.

        while ( (runtime.seconds() < timeoutS) && (robot.lift.isBusy()) )
        {
            //do nothing??
        }

        // Stop all motion;
        robot.lift.setPower(0);


        // Turn off RUN_TO_POSITION
        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //  sleep(250);   // optional pause after each move


        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.


        // Stop all motion;


        //  sleep(250);   // optional pause after each move


        robot.shutdown();
    }



    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};
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
