package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.RobotHardwareMap;

import java.util.List;

public class camera {
    RobotHardwareMap robotHardwareMap;

    LinearOpMode opMode;

    private Limelight3A limelight;
    private int goalColor;// 0 is 20 blue , 1 is 24 red
    private LLResult llResult ;

    public camera(RobotHardwareMap robotHardwareMap, LinearOpMode opMode) {
        this.opMode = opMode;
        this.robotHardwareMap = robotHardwareMap;
        limelight = robotHardwareMap.baseHMap.get(Limelight3A.class, "limelight");
    }

    public void start(){
        limelight.start();
    }

    public void loop(){

        YawPitchRollAngles orentation = robotHardwareMap.chImu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orentation.getYaw());
        llResult = limelight.getLatestResult();

        // Print every visible AprilTag ID
        if (llResult != null) {
            List<LLResultTypes.FiducialResult> tags = llResult.getFiducialResults();

            if (tags == null || tags.isEmpty()) {
                opMode.telemetry.addData("Visible Tags", "none");
                //opMode.telemetry.addData("thing",llResult.getStaleness(),llResult.getPipelineIndex(), llResult.getFiducialResults());

            } else {
                StringBuilder ids = new StringBuilder();
                for (LLResultTypes.FiducialResult tag : tags) {
                    if (ids.length() > 0) {
                        ids.append(", ");
                    }
                    ids.append(tag.getFiducialId());
                }
                opMode.telemetry.addData("Visible Tags", ids.toString());
                opMode.telemetry.addData("Tag Count", tags.size());
            }
        } else {
            opMode.telemetry.addData("Visible Tags", "no result");
        }

        if ((llResult != null) && llResult.isValid()){
            Pose3D botpos = llResult.getBotpose_MT2();
            opMode.telemetry.addLine(String.format("XYA %6.1f %6.1f  %6.3f ", llResult.getTx(),  llResult.getTy(), llResult.getTa()));
            opMode.telemetry.addData("BotPos", botpos.toString());
            opMode.telemetry.addData("Yaw", botpos.getOrientation().getYaw());
            opMode.telemetry.addData("AprilTag Distance (area proxy)", String.format("%.2f%%", llResult.getTa()));
        }
    }

    public double Robotallignwithgoal(double twistin) {
        double twistout = twistin;
        double powerFactor = 90; // long range value by default

        if ((llResult != null) && llResult.isValid()){
            if ((llResult.getTx() <= 10) && (llResult.getTx() >= -10)) {
                powerFactor = 35;
            }
            twistout= (llResult.getTx() / powerFactor) ;
        }
        return twistout;
    }

    public double outtakespeedfordistance(double powerin) {
        double power = powerin;
        double y2 = 1.0;
        double y1 = 0.5;
        double p2 = 900;
        double p1 = 960;
        double m = (p2 - p1) / (y2 - y1);
        double b = (p2 - (m * y2));
        if ((llResult != null) && llResult.isValid()){
            power= (m*llResult.getTa()) + b;
        }

        return power;
    }

    public void goalcolor (int goalColorIn) {
        goalColor = goalColorIn;
        limelight.pipelineSwitch(goalColor); // 0 is 20 blue, 1 is 24 red
    }
}