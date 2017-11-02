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
            //This is the standard run time that is displayed on the driver station
            private ElapsedTime runtime = new ElapsedTime();

            //Initializing bot object
            private Bot turingBot;

            //Variable declarations for team selection
            private boolean teamBlue = false;
            private boolean teamRed = false;

            //Initializing servos
            private Servo poker;
            private Servo fBsktServo;
            private Servo bBsktServo;
            private ServoController servoController;
            private DcMotor lift;
            //Color sensor init
            //private ColorSensor cSensor;

            @Override
            public void init() {
                servoController = hardwareMap.servoController.get("ServoController");
                //creates servoController
                servoController.pwmEnable();

                telemetry.addData("Status", "Initialized");

                //Assigning each piece of hardware names set up on the phones to initialized variables.
           /*poker = hardwareMap.servo.get("poker");
                fBsktServo = hardwareMap.servo.get("fBsktServo");
                bBsktServo = hardwareMap.servo.get("bBsktServo");
                */
                //cSensor = hardwareMap.colorSensor.get("cSensor");

                //Creating a bot object containing movement methods to avoid the clutter of setting directions constantly.
                turingBot = new Bot(
                        hardwareMap.dcMotor.get("frontLeft"),
                        hardwareMap.dcMotor.get("frontRight"),
                        hardwareMap.dcMotor.get("backLeft"),
                        hardwareMap.dcMotor.get("backRight"),
                        hardwareMap.dcMotor.get("lifting")
                );


            }

            //Team selection mechanism, initially intended for use with an assisted button pusher using the color sensor
            @Override
            public void init_loop()
            {
                if(gamepad1.x) {
                    telemetry.addData("You are team", "Blue");
                    if(teamRed) {
                        teamBlue = true;
                        teamRed = false;
                    }
                }
                else if(gamepad1.b) {
                    telemetry.addData("You are team", "Red");
                    if(teamBlue) {
                        teamRed = true;
                        teamBlue = false;
                    }
                }

            }

            //Sets time to 0 just as the match starts
            @Override
            public void start() {
                runtime.reset();
            }

            //This function is called elsewhere as the body of a while loop, therefore this is an iterative OP mode.
            @Override
            public void loop() {

                //Retrieves color info from the sensor
                /*int red = cSensor.red();
                int green = cSensor.green();
                int blue = cSensor.blue();

                //Prints data from above the the driver station
                telemetry.addData("Status", "Running: " + runtime.toString());
                telemetry.addData("Red","" + red);
                telemetry.addData("Green","" + green);
                telemetry.addData("Blue","" + blue); */

                //Assigns gamepad controls to shorter variables.
                //There is some trial and error here as sometimes the joysticks have inverted values
                double leftX = gamepad1.left_stick_x;
                double leftY = -gamepad1.left_stick_y;

                double rightX = gamepad1.right_stick_x;
                double rightY = gamepad1.right_stick_y;

                double throttle = gamepad1.right_trigger;

//                boolean rightColor = false;
//
//                //Also part of the unused assisted button pushing, checks for the highest color value
//                // Alternatively set to number based off of calibration
//                if(teamBlue)
//                    rightColor = blue > red && blue > green;
//                if(teamRed)
//                    rightColor = red > blue && red > green;

                //If the right joystick is pushed more than half right then rotate clockwise
                if (rightX >= .5) {
                    turingBot.cWise(throttle);

                    telemetry.addData("Direction", "Clock-wise");
                }

                //If the right joystick is pushed more than half left then rotate counter clockwise
                else if (rightX <= -0.5) {
                    turingBot.cCWise(throttle);

                    telemetry.addData("Direction", "Counter Clock-wise");
                }

                //If the left joystick is pushed more than half left and less than half up or down then go left
                else if (leftX <= -.5 && (leftY < .5 && leftY > -.5)) {
                    turingBot.left(throttle);

                    telemetry.addData("Direction", "Left");
                }

                //If the left joystick is pushed more than half right and less than half up or down then go right
                else if (leftX >= .5 && (leftY < .5 && leftY > -.5)) {
                    turingBot.right(throttle);

                    telemetry.addData("Direction", "Right");
                }

                //If the left joystick is at least half up
                else if (leftY >= .5) {
                    //Check to see if it is also half right, if so go Forward and Right
                    if (leftX > .5) {
                        turingBot.forRight(throttle);

                        telemetry.addData("Direction", "Forward-Right");
                    }

                    //If not, check if it is also half left, if so go Forward and Left
                    else if (leftX < -.5) {
                        turingBot.forLeft(throttle);

                        telemetry.addData("Direction", "Forward-Left");
                    }

                    //If they didn't move it left or right, just go forward
                    else if (leftX >= -.5 && leftX <= .5) {
                        turingBot.forward(throttle);

                        telemetry.addData("Direction", "Forward");
                    }
                }

                //Opposite of the previous chain
                //Check to see if the left joystick is at least half down
                else if (leftY <= -.5) {

                    //If it is also half right then go back and right
                    if (leftX > .5) {
                        turingBot.backwRight(throttle);

                        telemetry.addData("Direction", "Back-Right");
                    }

                    //If it is also half left then go back and left
                    else if (leftX < -.5) {
                        turingBot.backwLeft(throttle);

                        telemetry.addData("Direction", "Back-Left");
                    }

                    //If it was not moved left or right just go back
                    else if (leftX >= -.5 && leftX <= .5) {
                        turingBot.back(throttle);

                        telemetry.addData("Direction", "Back");
                    }
                }
                //If the joystick was not moved at least halfway in any direction then stay still
                else {
                    turingBot.forward(0);
                }
                if (gamepad2.a) {

                    turingBot.lifting(1);

                }
                if (gamepad2.b) {

                    turingBot.lifting(1);

                }

                if (gamepad2.y) {

                    turingBot.lifting(1);
                }

            }

                public void ServoController() {


                }


                //While dpad up is held down raise the lift
               /*f(gamepad1.dpad_up){
                    turingBot.elevate(1);

                    telemetry.addData("Elevator", "Active");
                }*/

                //Stop when let go
               /*lse{
                    turingBot.elevate(0);
                }*/

                /*Some of the joystick inversion problems come in here and flipping it when
                assigning the variable never seemed to work. Possibly fixed in the present*/
                //If the right joystick is at least half down: lower the claws at 1/10 the magnitude of joystick movement
//                if(rightY >= .5){
//                    turingBot.moveClaws(rightY * .1);
//
//                    telemetry.addData("Claw", "Lowering");
//                }
//                //If the right joystick is at least half up: raise the claws at 1/2 the magnitude of joystick movement
//                else if(rightY <= -.5){
//                    turingBot.moveClaws(rightY * .5);
//
//                    telemetry.addData("Claw", "Raising");
//                }
//                //If the joystick is not moved half in either direction then don't move
//                else{
//                    turingBot.moveClaws(0);
//                }

                //Assisted button pushing, I ended up disabling this because I was too good for that
                /*if(gamepad1.left_bumper) {
                    if(rightColor)
                        poker.setPosition(.5);
                    else
                        telemetry.addData("Nope.","Wrong Color");
                }*/

                //Extend button pusher
             /* if(gamepad1.left_bumper) {

                    poker.setPosition(.5);
                }

                //Retract button pusher
                else (gamepad1.left_trigger > .49){
                    poker.setPosition(.9); // 1= inwards; .5 = outwards
                }
                */
//
//                //Raises the servo wall to catch the ball
//                if(gamepad1.dpad_right){
//                    bBsktServo.setPosition(1);
//                    fBsktServo.setPosition(0);
//                }
//
//                //Puts the servo wall in resting position
//                else if(gamepad1.dpad_down){
//                    bBsktServo.setPosition(.25); //1 = in; 0 = out
//                    fBsktServo.setPosition(.75); //0 = in;1 = out
//                }
//
//                //Pushes the ball out
//                else if(gamepad1.dpad_left){
//                    bBsktServo.setPosition(0);
//                    fBsktServo.setPosition(1);
//                }



            @Override
            public void stop() {
                turingBot.shutdown();
            }

        }