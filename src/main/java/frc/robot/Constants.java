// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/* 
 * Modify the motor ID constants using the appropiate software (Phoenix Tuner, Rev Client, etc.)
 */

public final class Constants {
    public static final class ChassisConstants {
        public static final int frontRight = 2;
        public static final int frontLeft = 1;
        public static final int backLeft = 3;
        public static final int backRight = 4;
    }

    public static final class OIConstants {
        public static double multiplier = 0;
        public static final int KDriverControllerPort = 0;
    }
}
