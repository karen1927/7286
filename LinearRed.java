
    package org.firstinspires.ftc.teamcode;

    import android.app.Activity;
    import android.graphics.Color;
    import android.view.View;

    import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.ColorSensor;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.hardware.ServoController;
    import com.qualcomm.robotcore.util.ElapsedTime;

    @Autonomous(name="Red", group="autonomous")  // @Autonomous(...) is the other common choice
    public class LinearRed extends LinearOpMode {
        //Declare the motors being used and a timer called "time"

        private Bot turingBot;



        public Servo pushButton;//Declares servo hooks on front for bars
        ServoController servoController;//Declares servo controller

       // ColorSensor color;  //*need  // Hardware Device Object


        int count = 0;
        boolean red = true;
        double throttle = .3;

        ElapsedTime timer=  new ElapsedTime();


        double currentTime = 0;


        //Gives the Enum State a name that we can use in the code to reference the 3 actions

        @Override
        public void runOpMode() throws InterruptedException {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            turingBot = new Bot(
                    hardwareMap.dcMotor.get("frontLeft"),
                    hardwareMap.dcMotor.get("frontRight"),
                    hardwareMap.dcMotor.get("backLeft"),
                    hardwareMap.dcMotor.get("backRight")

            );


            /*
            float hsvValues[] = {0F, 0F, 0F};

            final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
            final float values[] = hsvValues;
            */

            waitForStart();//this starts when you hit play

            timer.reset();



            // while the op mode is active, loop and read the RGB data.
            // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
            while (opModeIsActive()&& currentTime<1) {

                currentTime = timer.time();



                /*
                if (count == 1) //backward
                {
                     turingBot.forward(throttle);
                }
                */

                if (0 < currentTime && currentTime <= .5)//stop for .5 seconds
                {
                    turingBot.forward(throttle);
                }

            }
                telemetry.update();
                idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }

    }




