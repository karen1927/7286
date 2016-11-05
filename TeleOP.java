
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
public  class TeleOp extends OpMode {
    /* Public OpMode members. */
    public DcMotor TeleOp1;
        /* Constructor */



    /* Initialize standard Hardware interfaces */
    @Override
    public void init() {
        // save reference to HW Map


        TeleOp1=hardwareMap.dcMotor.get("TeleOp_1");


        TeleOp1.setPower(0);


        TeleOp1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
 ]









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

        float screwpower = -gamepad1.left_stick_y;
        screwpower = Range.clip(screwpower, -1, 1);//clips values into section


        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.

        screwpower = (float)scaleInput(screwpower);

        screwpower = (float) (screwpower*.9);

        TeleOp1.setPower(screwpower);
    }

                /*
                 * Code to run ONCE after the driver hits STOP
                 */



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

