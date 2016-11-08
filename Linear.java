
    package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.eventloop.opmode.Disabled;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.util.ElapsedTime;

    @TeleOp(name="Linear", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
    public class Linear extends LinearOpMode {
        //Declare the motors being used and a timer called "time"
        private ElapsedTime runtime = new ElapsedTime();
        DcMotor motor1;//Declare Motors for Drive Train
        DcMotor motor2;
        int count = 0;
        double currentTime = 0;
        ElapsedTime time;





        //Gives the Enum State a name that we can use in the code to reference the 3 actions

        @Override
        public void runOpMode() throws InterruptedException {
            telemetry.addData("Status", "Initialized");
            telemetry.update();
            time = new ElapsedTime();
            motor1 = hardwareMap.dcMotor.get("motor_1");//rightwheels
            motor2 = hardwareMap.dcMotor.get("motor_2");

            motor2.setDirection(DcMotor.Direction.REVERSE);//reversed because the motors flipped




        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
                // leftMotor  = hardwareMap.dcMotor.get("left motor");
                // rightMotor = hardwareMap.dcMotor.get("right motor");

                // eg: Set the drive motor directions:
                // "Reverse" the motor that runs backwards when connected directly to the battery
                // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
                // rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

                // Wait for the game to start (driver presses PLAY)

                waitForStart();


                runtime.reset();
            time = new ElapsedTime();
                // run until the end of the match (driver presses STOP)
                while (opModeIsActive()) {
                    telemetry.addData("Status", "Run Time: " + runtime.toString());
                    telemetry.update();

                    double currentTime = time.time();
                    if (count == 0) {
                        time.reset();
                        count = 4;//time is zero
                    }

                    if (count == 1) {
                        motor1.setPower(1);
                        motor2.setPower(1);
                    }

                    if (count == 2) {
                        motor1.setPower(-1);
                        motor2.setPower(-1);

                    }
                    if (currentTime > 11.5 && currentTime < 12.4)//turn right after moving forward
                    {
                        count = 1;
                    }
                    if (currentTime > 12.4) {
                        count =4;
                    }

                    idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
                 }

            }
        }

