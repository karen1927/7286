
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

 @TeleOp(name="Intake", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
 @Disabled
 public  class intake extends OpMode {
     /* Public OpMode members. */
     private ElapsedTime runtime = new ElapsedTime();
     public DcMotor  leftintake;
     public DcMotor rightintake;
     public   Servo serveleft;//Declares servo hooks on front for bars
     public DcMotor rollOne;
     public DcMotor rollTwo;

     Servo serveright;
     int count = 0;
     double currentTime = 0;
     ElapsedTime time;
     ServoController screwController;//Declares servo controller
   //screwPosition2 = .6;
   //screwPosition1 = .4;
     /* Constructor */
     /* Initialize standard Hardware interfaces */
     @Override
     public void init() {
         // save reference to HW Map


         leftintake = hardwareMap.dcMotor.get("intake_1");
         rightintake = hardwareMap.dcMotor.get("intake_2");
         serveleft = hardwareMap.servo.get("servo_1");
         serveright = hardwareMap.servo.get("servo_2");
         rollOne= hardwareMap.dcMotor.get("roll_1");
         rollTwo= hardwareMap.dcMotor.get("roll_2");

         rollOne.setPower(0);
         rollTwo.setPower(0);
         rightintake.setPower(0);
         leftintake.setPower(0);

         serveleft.setPosition(.45);//sets to initial position
         serveright.setPosition(.45);

         rightintake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         leftintake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         rollOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         rollTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

         screwController = hardwareMap.servoController.get("servoController");//creats servoControler
         screwController.pwmEnable();
         double  serveleft;//Declares values for initial screw position
         double  serveright;
         // amount to change the claw servo position by

     }



     /*
      * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
      */
     @Override
     public void init_loop() {
     }


// write the values to the motors


     // leadScrew.setPower(powerScrew);
                /*
                 * Code to run ONCE when the driver hits PLAY
                 */
     @Override
     public void start() {

     }

     /*
      * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
      */
     @Override
     public void loop() {
         float rollLeft = -gamepad2.left_stick_y;
         rollLeft = Range.clip(rollLeft, -1, 1);//clips values into section


         // scale the joystick value to make it easier to control
         // the robot more precisely at slower speeds.


         rollLeft = (float)scaleInput(rollLeft);

         rollLeft = (float) (rollLeft*.9);

         rollTwo.setPower(rollLeft);
         rollOne.setPower(rollLeft);
         // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
         // leftMotor.setPower(-gamepad1.left_stick_y);
         // rightMotor.setPower(-gamepad1.right_stick_y);


         time = new ElapsedTime();

         if (gamepad1.right_bumper) {
             runtime.reset();//time is 0
             while (currentTime < .01) //while current time < set period of time
             {
                 leftintake.setPower(1);
                 rightintake.setPower(1);
             }//will do this action for set period of time
             leftintake.setPower(0);
             rightintake.setPower(0);
         } else if (gamepad1.left_bumper) {
             runtime.reset();//time is 0
             while (currentTime < .01) //while current time < set period of time
             {

                 leftintake.setPower(-1);
                 rightintake.setPower(-1);


             }
             leftintake.setPower(0);
             rightintake.setPower(0);
         } else {
             leftintake.setPower(0);
             rightintake.setPower(0);
         }
     /*
                 * Code to run ONCE after the driver hits STOP
                 */
         if (gamepad2.a) {
             serveleft.setPosition(-.45);
             serveright.setPosition(.45);
         }

         if (gamepad2.b) {
             serveleft.setPosition(.45);
             serveright.setPosition(-.45);
         }
     }
//

         // if (gamepad1.a)
         //     { serveleft.setPower(1);
         //       serveleft.setPower(1);
//           }//will do this action for set period of time
         //  leftintake.setPower(0);
         //  rightintake.setPower(0);
         //}
         // else if (gamepad1.b)
         //  {
         //runtime.reset();//time is 0
         //     while(currentTime< .01) //while current time < set period of time
         //    {

         //      leftintake.setPower(-1);
         //    rightintake.setPower(-1);


         //
         //leftintake.setPower(0);
         // rightintake.setPower(0);
         // }
         //else {
         //  leftintake.setPower(0);
         //    rightintake.setPower(0);
         // }//
         //}

         @Override
         public void stop () {
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
