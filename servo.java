
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

@TeleOp(name="Servo", group="Iterative Opmode")  // @Autonomous(...) is the other common choice

public  class servo extends OpMode {
    /* Public OpMode members. */

    public Servo serveleft;//Declares servo hooks on front for bars


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
        screwController = hardwareMap.servoController.get("servoController");//creats servoControler
        screwController.pwmEnable();

        serveleft = hardwareMap.servo.get("servo_1");
        serveright = hardwareMap.servo.get("servo_2");


        serveleft.setPosition(.45);//sets to initial position
        serveright.setPosition(.45);



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
    public void stop() {
    }
}