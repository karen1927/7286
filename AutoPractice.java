

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="run", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
public class AutoPractice extends OpMode {
   /* Public OpMode members. */



    public DcMotor  frontintake;


    //  public Servo pushButton;//Declares servo hooks on front for bars

    //public ServoController servoController;//Declares servo controller



    @Override
    public void init(){
        // save reference to HW Map

        //  motor1=hardwareMap.dcMotor.get("motor_1");
        // motor2=hardwareMap.dcMotor.get("motor_2");

        frontintake = hardwareMap.dcMotor.get("intakefront");

        frontintake.setPower(0);

        // servoController = hardwareMap.servoController.get("servoController");//creats servoControler
        // servoController.pwmEnable();

        //  pushButton = hardwareMap.servo.get("stick");


        // motor1.setPower(0);
        // motor2.setPower(0);
        //pushButton.setPosition(0);//sets initial hook position


        // motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontintake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);





        //  motor1.setDirection(DcMotor.Direction.REVERSE);


    }





    @Override
    public void init_loop() {}



    @Override
    public void start() {}

    @Override
    public void loop() {

        // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
        // leftMotor.setPower(-gamepad1.left_stick_y);
        // rightMotor.setPower(-gamepad1.right_stick_y);
        float left = -gamepad1.left_stick_y;//gets information from joystick
        float right = -gamepad1.right_stick_y;


        right = Range.clip(right, -1, 1);//clips values into section
        left = Range.clip(left, -1, 1);

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.

        right = (float) scaleInput(right);
        left = (float) scaleInput(left);


        left = (float) (left * .9);
        right = (float) (right * .9);


        //  motor1.setPower(left); //Motor one goes counter clockwise//
        // motor2.setPower(right);


        if (gamepad2.a) {

            //rolls in
            telemetry.addData("Status", "button a");
            telemetry.update();
            frontintake.setPower(.1);//Motor one goes counter clockwise//

        }

        if (gamepad2.b) {
            telemetry.addData("Status", "button b");
            telemetry.update();//roll balls out
            frontintake.setPower(-.1);//otor one goes counter clockwise//

        }
        if (gamepad2.x) {//stops
            frontintake.setPower(0); //Motor one goes counter clockwise//

        }
    }


    //if (gamepad2.left_bumper) //left bumper tilts stick out
    // {
    // pushButton.setPosition(1);

    // }
    // if (gamepad2.right_bumper) //left bumper tilts stick back
    // {
    //  pushButton.setPosition(0);
    // }
    //   }

    public void stop() {}


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


