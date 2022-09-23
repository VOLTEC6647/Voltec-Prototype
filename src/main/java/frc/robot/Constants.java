// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/* 
 * Modify the motor ID constants using the appropiate software (Phoenix Tuner, Rev Client, etc.)
 */

public final class Constants {
    public static final class ChassisConstants {
        public static final int frontRight = 6;
        public static final int frontLeft = 9; // 5
        public static final int backLeft = 8; //8
        public static final int backRight = 5; //9
    }

    public static final class OIConstants {
        public static double multiplier = 0;
        public static final int KDriverControllerPort = 0;
    }

    public static final class VisionConstats{
        public static final double kpAim = -0.03; //How hard to turn 
        public static final double kpDistance = -0.06; //How hard to drive fwd
        public static final double min_aim_command = 0.02; //Minimum command to rotate
        public static final double steeringAdjust = 0.0; 
    }
}
