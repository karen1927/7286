
    package org.firstinspires.ftc.teamcode;

    import android.app.Activity;
    import android.graphics.Color;
    import android.view.View;

    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.ColorSensor;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.Servo;
    import com.qualcomm.robotcore.hardware.ServoController;
    import com.qualcomm.robotcore.util.ElapsedTime;


    @TeleOp(name="Red", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
    public class LinearRed extends LinearOpMode {
        //Declare the motors being used and a timer called "time"




        DcMotor motor1;//Declare Motors for Drive Train
        DcMotor motor2;

        public Servo pushButton;//Declares servo hooks on front for bars
        ServoController servoController;//Declares servo controller

        ColorSensor color;  //*need  // Hardware Device Object


        int count = 0;
        boolean red = true;


        ElapsedTime timer=  new ElapsedTime();
        ElapsedTime colorTimer  =new ElapsedTime();;

        double currentTime = 0;
        double currentColorTime=0;

        //Gives the Enum State a name that we can use in the code to reference the 3 actions

        @Override
        public void runOpMode() throws InterruptedException {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            motor1 = hardwareMap.dcMotor.get("motor_1");//rightwheels
            motor2 = hardwareMap.dcMotor.get("motor_2");
            color = hardwareMap.colorSensor.get("color sensor"); //*need
            color.enableLed(true); //*need    (TRUE)

            motor2.setDirection(DcMotor.Direction.REVERSE);//reversed because the motors flipped

            color = hardwareMap.colorSensor.get("color sensor");
            color.enableLed(true); //*need    (TRUE)

            servoController = hardwareMap.servoController.get("servoController");//creats servoControler
            servoController.pwmEnable();

            pushButton = hardwareMap.servo.get("stick");

            pushButton.setPosition(0);//sets initial hook position


            float hsvValues[] = {0F, 0F, 0F};

            final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
            final float values[] = hsvValues;


            waitForStart();//this starts when you hit play

            timer.reset();
            colorTimer.reset();


            // while the op mode is active, loop and read the RGB data.
            // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
            while (opModeIsActive()&& currentTime<29) {

                currentTime = timer.time();

                Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, hsvValues); //*need
                if (count == 0)
                {
                    count = 4;//time is zero
                }

                if (count == 1) //backward
                {
                    motor1.setPower(.75);
                    motor2.setPower(.75);
                }

                if (count == 2)//forward
                {
                    motor1.setPower(-.75);
                    motor2.setPower(-.75);

                }
                if (count == 4) //turn right
                {
                    motor1.setPower(.5);
                    motor2.setPower(.1);
                }
                if (count == 5)
                {
                    motor1.setPower(.1);
                    motor2.setPower(.3);
                }
                if (count==6)//turn left
                {
                    motor1.setPower(.1);
                    motor2.setPower(.5);
                }
                if (count == 7)
                {
                    motor1.setPower(0);
                    motor2.setPower(0);//time is zero
                }
                if (count == 8)//sharp right turn
                {
                    motor1.setPower(-1);
                    motor2.setPower(1);
                }
                if (count == 9)//curvy left turn
                {
                    motor1.setPower(.75);
                    motor2.setPower(.1);
                }





                if (count == 3)//color sensorruns// cant use counts in this section
                {

                    if (color.red() > 33)
                    {
                        //code
                    }
                }
                if (0 < currentTime && currentTime < .5)//stop for .5 seconds
                {
                    count = 7;

                }

                if (.5 < currentTime && currentTime < 1)//going forward for .5 seconds
                {
                    count = 2;

                }
                if (1 < currentTime && currentTime < 1.1)//stops robot for .1 seconds
                {
                    count = 7;

                }
                if (1.1 < currentTime && currentTime < 1.6)//turns left for .5 seoonds
                {
                    count = 6;

                }
                if (1.6 < currentTime && currentTime < 1.7)//stops for .1 seconds
                {
                    count = 7;
                    pushButton.setPosition(1);//swings stick out
                }


                if (1.7 < currentTime && currentTime < 1.9)//going forward for .2 seconds-in front of beacon
                {
                    count = 2;

                }
                if (1.9 < currentTime && currentTime < 2)//stops robot for .1 seconds//in front of beacon
                {
                    count = 7;

                }
                if (2 < currentTime && currentTime < 3)//checks color for 1 second
                {
                    if (color.red()>9)
                    {
                        red=true;
                    }
                    else
                    {
                        red=false;
                    }

                }



                if (3< currentTime && currentTime < 3.1 && red)
                {

                count = 2;//goes forward for .1 seconds
                }
                if (3.1< currentTime && currentTime < 3.2 && red)
                {

                    count = 7;//stops for .1 seconds
                }
                if (3.2< currentTime && currentTime < 3.3 && red)
                {

                    count = 1;//goes backward for .1 seconds


                }
                if (3.3< currentTime && currentTime < 8 && red)
                {

                    count = 7; //stops for 5 seconds

                }


                if (3< currentTime && currentTime < 3.5 && !red) {
                    count = 1;//goes backwards for .5
                }

                if (3.5< currentTime && currentTime < 3.8 && !red)
                {

                    count =8 ; //sharp right turn

                }
                if (3.8< currentTime && currentTime < 3.9 && !red)
                {

                    count =7 ; //stops for  .1 seconds

                }
                if (3.9< currentTime && currentTime < 4 && !red)
                {

                    count =9 ;// curvy left turn

                }
                if (4< currentTime && currentTime < 4.1 && !red)// stops
                {

                    count =7 ;

                }
                if (4.1< currentTime && currentTime < 4.7 && !red)// forward
                {

                    count =2 ;

                }
                if (4.7< currentTime && currentTime < 4.8 && !red)// stops for .1 seconds
                {

                    count =7 ;

                }
                if (4.8< currentTime && currentTime < 4.9 && !red)// 1 backwards
                {

                    count =1 ;

                }

                if (4.9< currentTime && currentTime < 5 && !red)// 1 backwarrds
                {

                    count =1 ;

                }













            }
                telemetry.update();
                idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
            }

        }

        }


