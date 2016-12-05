
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

         rightintake.setPower(0);
         leftintake.setPower(0);


         rightintake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         leftintake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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

         // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
         // leftMotor.setPower(-gamepad1.left_stick_y);
         // rightMotor.setPower(-gamepad1.right_stick_y);



         time = new ElapsedTime();

         if (gamepad1.right_bumper)
         {
             runtime.reset();//time is 0
             while(currentTime<.01) //while current time < set period of time
             {
                 leftintake.setPower(1);
                 rightintake.setPower(1);
             }//will do this action for set period of time
             leftintake.setPower(0);
             rightintake.setPower(0);
         }
         else if (gamepad1.left_bumper)
         {
             runtime.reset();//time is 0
             while(currentTime< .01) //while current time < set period of time
             {

                 leftintake.setPower(-1);
                 rightintake.setPower(-1);


         }
             leftintake.setPower(0);
             rightintake.setPower(0);
         }
             else {
                 leftintake.setPower(0);
                 rightintake.setPower(0);
         }
     /*
                 * Code to run ONCE after the driver hits STOP
                 */
         if (gamepad1.a)
         {
             serveleftt.setPosition(.45);
             serveright.setPosition(.45);
         }
         serveleftt.setPosition(0);
         serveright.setPosition(0);
         else if(gamepad1.b)
{
    serveleftt.setPosition(-.45);
    serveright.setPosition(-.45);
}
         serveleftt.setPosition(0);
         serveright.setPosition(0);

         else if (gamepad1.left_bumper)
         {
             runtime.reset();//time is 0
             while(currentTime< .01) //while current time < set period of time
             {

                 leftintake.setPower(-1);
                 rightintake.setPower(-1);


             }
             leftintake.setPower(0);
             rightintake.setPower(0);
         }
         else {
             leftintake.setPower(0);
             rightintake.setPower(0);
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
         public void stop() {
         }


     }