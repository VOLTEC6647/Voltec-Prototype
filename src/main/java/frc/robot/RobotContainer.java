// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.utils.XboxControllerUpgrade;

public class RobotContainer {
        public final ChassisSubsystem chassis = new ChassisSubsystem();

        private final XboxControllerUpgrade joystick1 = new XboxControllerUpgrade(OIConstants.KDriverControllerPort,
                        0.2);

        SendableChooser<Command> driveChooser = new SendableChooser<>();

        private final Command tankDriveCommand = new RunCommand(
                        () -> chassis.tankDrive(joystick1.getLeftY(), joystick1.getRightY()),
                        chassis);

        private final Command arcadeDriveCommand = new RunCommand(() -> chassis.arcadeDrive(joystick1.getLeftY(),
                        -joystick1.getRightX()), chassis);

        private final Command curvatureDriveCommand = new RunCommand(
                        () -> chassis.curvatureDrive(joystick1.getLeftY(), joystick1.getRightX()), chassis);

        private final Command triggerDriveCommand = new RunCommand(
                        () -> chassis.arcadeDrive(
                                        (joystick1.getRightTriggerAxis() - joystick1.getLeftTriggerAxis()) * -1,
                                        joystick1.getLeftX() * -1),
                        chassis);

        public RobotContainer() {

                //Adds options to choose different drive modes on the run
                driveChooser.setDefaultOption("Tank Drive", tankDriveCommand);
                driveChooser.addOption("Arcade Drive", arcadeDriveCommand);
                driveChooser.addOption("Curvature Drive", curvatureDriveCommand);
                driveChooser.addOption("Trigger Drive", triggerDriveCommand);
                SmartDashboard.putData(driveChooser);

                chassis.setDefaultCommand(chooserCommand());

                configureButtonBindings();
        }

        private void configureButtonBindings() {}

        public Command getAutonomousCommand() {
                return null;
        }

        /* Retunrs driveChooser selected drive command */
        public Command chooserCommand() {
                return driveChooser.getSelected();
        }
}