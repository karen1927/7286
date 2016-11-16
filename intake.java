
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.Range;

 @TeleOp(name="Intake", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
 @Disabled
 public  class intake extends OpMode {
     /* Public OpMode members. */
     public DcMotor  leftintake;
     public DcMotor rightintake;
        /* Constructor */
     /* Initialize standard Hardware interfaces */
     @Override
     public void init() {
         // save reference to HW Map


         leftintake = hardwareMap.dcMotor.get("intake_1");
         rightintake = hardwareMap.dcMotor.get("intake_2");

         rightintake.setPower(0);
         leftintake.setPower(0);

         rightintake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         leftintake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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


         if (gamepad1.a) {
             leftintake.setPower(1);
             rightintake.setPower(1);
         } else if (gamepad1.b) {
             leftintake.setPower(-1);
             rightintake.setPower(-1);
         } else {
             leftintake.setPower(0);
             rightintake.setPower(0);
         }
   /*  if (gamepad1.a)
     {
         intake2.setPower(1);
     }

     else if (gamepad1.b)
     {
         intake2.setPower(-1);

     }
     else
     {
         intake2.setPower(0);

     }

         */       /*
                 * Code to run ONCE after the driver hits STOP
                 */

     }
         @Override
         public void stop() {
         }


     }

