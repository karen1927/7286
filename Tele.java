

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
public class Tele extends OpMode {
    /* Public OpMode members. */

    public DcMotor motor1;
    public DcMotor motor2;
    public DcMotor leadscrew1;
    public DcMotor intake1;
        /* Constructor */  /* Constructor */








        @Override
        public void init(){
            // save reference to HW Map

            motor1=hardwareMap.dcMotor.get("motor_1");
            motor2=hardwareMap.dcMotor.get("motor_2");
            leadscrew1=hardwareMap.dcMotor.get("leadscrew_1");
            intake1=hardwareMap.dcMotor.get("intake_1");

            motor1.setPower(0);
            motor2.setPower(0);
            leadscrew1.setPower(0);
            intake1.setPower(0);

            motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leadscrew1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            intake1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            motor2.setDirection(DcMotor.Direction.REVERSE);


        }





        @Override
        public void init_loop() {







        }



        @Override
        public void start() {

        }

        @Override
        public void loop() {

            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            // leftMotor.setPower(-gamepad1.left_stick_y);
            // rightMotor.setPower(-gamepad1.right_stick_y);
            float left = -gamepad1.left_stick_y;//gets information from joystick
            float right = -gamepad1.right_stick_y;
            float screwpower = -gamepad2.right_stick_y;
            float roll = -gamepad2.left_stick_y;

            right = Range.clip(right, -1, 1);//clips values into section
            left = Range.clip(left, -1, 1);
            screwpower = Range.clip(screwpower, -1, 1);
            roll = Range.clip(roll, -1, 1);
            // scale the joystick value to make it easier to control
            // the robot more precisely at slower speeds.
            right = (float)scaleInput(right);
            left =  (float)scaleInput(left);
            left = (float) (left*.9);
            right = (float) (right*.9);
            screwpower = (float) (screwpower*.9);
            screwpower = (float)scaleInput(screwpower);
            roll = (float)scaleInput(roll);
            roll = (float) (roll*.9);

            motor1.setPower(right); //Motor one goes counter clockwise//
            motor2.setPower(left);
            leadscrew1.setPower(screwpower);
            intake1.setPower(roll);
        }
        @Override
        public void stop() {
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

