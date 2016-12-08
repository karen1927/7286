package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by techco on 12/6/2016.
 */
@TeleOp(name="Rollers", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
public class DcRollers extends OpMode {

    public DcMotor rollOne;
    public DcMotor rollTwo;

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init() {
        rollOne= hardwareMap.dcMotor.get("roll_1");
        rollTwo= hardwareMap.dcMotor.get("roll_2");

        rollOne.setPower(0);
        rollTwo.setPower(0);


        rollOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rollTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }


    @Override
    public void init_loop() {
        // save reference toW Map



    }
    // write the values to the motors



    // leadScrew.setPower(powerScrew);
                /*
                 * Code to run ONCE when the driver hits PLAY
                 */
    @Override
    public void start() {
}
        @Override
        public void loop() {

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            // leftMotor.setPower(-gamepad1.left_stick_y);
            // rightMotor.setPower(-gamepad1.right_stick_y);
            float rollLeft = -gamepad2.left_stick_y;

            rollLeft = Range.clip(rollLeft, -1, 1);//clips values into section


            // scale the joystick value to make it easier to control
            // the robot more precisely at slower speeds.


            rollLeft = (float)scaleInput(rollLeft);

            rollLeft = (float) (rollLeft*.9);

            rollTwo.setPower(rollLeft);
            rollOne.setPower(rollLeft);
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

