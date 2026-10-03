package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.camera;

@Autonomous(name = "Auton Red Hive Up", group = "Autons")
public class AutonBlueUp extends AutonBase {

    double imuSpeed = 1;

    @Override
    public void runOpMode() {
        initialize();

        // GUESS: assumes AutonBase gives you a robotHardwareMap field
        camera cam = new camera(theHardwareMap, this);
        cam.start();

        waitForStart();

        while (opModeIsActive()) {
            cam.loop();
            telemetry.update();
        }
        //shoot all pollen

        //go to garden to collect pollen
        //imuTurn(imuSpeed, -90);
    }
}