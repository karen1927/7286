            package org.firstinspires.ftc.teamcode;

            import com.qualcomm.robotcore.hardware.DcMotor;
            import com.qualcomm.robotcore.hardware.ServoController;
            import com.qualcomm.robotcore.hardware.Servo;

            public class Bot {
                private DcMotor frontLeft;
                private DcMotor frontRight;
                private DcMotor backLeft;
                private DcMotor backRight;

                public Servo pushButton;//Declares servo hooks on front for bars









               // private DcMotor frontClaw;
               // private DcMotor backClaw;

                public Bot(DcMotor fLMotor, DcMotor fRMotor, DcMotor bLMotor, DcMotor bRMotor){
                    frontLeft = fLMotor;
                    frontRight = fRMotor;
                    backLeft = bLMotor;
                    backRight = bRMotor;


                }


                public void forward(double speed){
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
                public void forRight(double speed){
                        // forward and right
                        frontLeft.setDirection(DcMotor.Direction.REVERSE);

                        backRight.setDirection(DcMotor.Direction.FORWARD);

                        frontLeft.setPower(speed);
                        backLeft.setPower(0);

                        frontRight.setPower(0);
                        backRight.setPower(speed);
                }
                public void right(double speed){
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
                public void backwRight(double speed){
                        // back and right
                        backLeft.setDirection(DcMotor.Direction.FORWARD);

                        frontRight.setDirection(DcMotor.Direction.REVERSE);

                        frontLeft.setPower(0);
                        backLeft.setPower(speed);

                        frontRight.setPower(speed);
                        backRight.setPower(0);
                }
                public void back(double speed){
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
                public void backwLeft(double speed){
                        // back and left
                        frontLeft.setDirection(DcMotor.Direction.FORWARD);

                        backRight.setDirection(DcMotor.Direction.REVERSE);

                        frontLeft.setPower(speed);
                        backLeft.setPower(0);

                        frontRight.setPower(0);
                        backRight.setPower(speed);
                }
                public void left(double speed){
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
                public void forLeft(double speed){
                        // forward and left
                        backLeft.setDirection(DcMotor.Direction.REVERSE);

                        frontRight.setDirection(DcMotor.Direction.FORWARD);

                        frontLeft.setPower(0);
                        backLeft.setPower(speed);

                        frontRight.setPower(speed);
                        backRight.setPower(0);
                }
                public void cWise(double speed){
                        frontLeft.setDirection(DcMotor.Direction.REVERSE);
                        backLeft.setDirection(DcMotor.Direction.REVERSE);

                        frontRight.setDirection(DcMotor.Direction.REVERSE);
                        backRight.setDirection(DcMotor.Direction.REVERSE);

                        frontLeft.setPower(speed);
                        backLeft.setPower(speed);

                        frontRight.setPower(speed);
                        backRight.setPower(speed);
                }
                public void cCWise(double speed){
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
