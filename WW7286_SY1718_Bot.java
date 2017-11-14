package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Karen on 11/14/2017.
 *
 * @version 11/14/2017
 */

public class WW7286_SY1718_Bot
{
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    public DcMotor frontIntake;
    public DcMotor lift;

    /**
     * Constructor for Bot
     *
     * @param fLMotor  Front left motor
     * @param fRMotor  Front right motor
     * @param bLMotor  Back left motor
     * @param bRMotor  Back right motor
     */
    public WW7286_SY1718_Bot(DcMotor fLMotor, DcMotor fRMotor, DcMotor bLMotor, DcMotor bRMotor,
                             DcMotor pFrontIntake,
                             DcMotor pLift)
    {
        frontLeft = fLMotor;
        frontRight = fRMotor;
        backLeft = bLMotor;
        backRight = bRMotor;

        frontIntake = pFrontIntake;
        lift = pLift;
    }

    public void init()
    {
        lift.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors

        // Set all motors to zero power
        lift.setPower(0);
        frontIntake.setPower(0);

        // Run lift using encoders
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Run intake without encoders
        frontIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Holonomic Drive - Forward motion
     *
     * @param speed  Speed to move at
     */
    public void forward(double speed)
    {
        // forward
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(speed);
    }


    public void forRight(double speed)
    {
        // forward and right
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(speed);
        backLeft.setPower(0);

        frontRight.setPower(0);
        backRight.setPower(speed);
    }


    public void right(double speed)
    {
        // right
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(speed);
    }

    public void backwRight(double speed)
    {
        // back and right
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(0);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(0);
    }

    public void back(double speed)
    {
        // back
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(speed);
    }

    public void backwLeft(double speed)
    {
        // back and left
        frontLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(speed);
        backLeft.setPower(0);

        frontRight.setPower(0);
        backRight.setPower(speed);
    }

    public void left(double speed)
    {
        // left
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(speed);
    }

    public void forLeft(double speed)
    {
        // forward and left
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(0);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(0);
    }

    public void clockWise(double speed)
    {
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(speed);
    }

    public void counterClockWise(double speed)
    {
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setPower(speed);
        backLeft.setPower(speed);

        frontRight.setPower(speed);
        backRight.setPower(speed);
    }

    public void shutdown(){
        frontLeft.setPower(0);
        backLeft.setPower(0);

        frontRight.setPower(0);
        backRight.setPower(0);
    }


}
