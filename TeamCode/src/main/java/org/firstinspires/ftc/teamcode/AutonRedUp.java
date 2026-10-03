package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auton Red Hive Up", group = "Autons")
public class AutonRedUp extends AutonBase{

    double imuSpeed = 1;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();



    }
}
