package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.LynxModuleImuType;

@Autonomous(name = "Auton Test", group = "Autons")
public class AutonTest extends AutonBase {
    // Robot starts with back against center of blue depot

    double imuSpeed = 1;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        /*for (int i = 1; i <= 4; i++) {
            imuStrafe(0.5, 50, 0, 30);
            imuStrafe(0.5, -50, 0, 30);
        }


        /*
        for (int i = 1; i <= 4; i++) {
            System.out.println("Number: " + i);
            imuDrive(imuSpeed, 50, 0);
            imuTurn(imuSpeed, -90);
        }




        /* ran out of time so stop after intakeing 3 artifatics
        outtake.outtakeonAfterIntake();

        //go to score
        imuDrive(imuSpeed,-54,0);
        imuTurn(imuSpeed,-90);
        imuDrive(imuSpeed,-21,0);
        imuTurn(imuSpeed,-45);

        transferAndLaunchArtifacts();

        //drive outside launch line for rp
        imuTurn(imuSpeed,45);
        imuDrive(imuSpeed,30,0);*/
    }
}