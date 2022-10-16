// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.utils.XboxControllerUpgrade;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
        private final ChassisSubsystem chassis = new ChassisSubsystem();

        public final XboxControllerUpgrade joystick1 = new XboxControllerUpgrade(OIConstants.KDriverControllerPort,
                        0.2);

        SendableChooser<Command> chooser = new SendableChooser<>();

        private final Command tankDriveCommand = new RunCommand(
                        () -> chassis.tankDrive(joystick1.getLeftY(), joystick1.getRightY()),
                        chassis);

        private final Command tankDriveIKCommand = new RunCommand(
                        () -> chassis.tankDriveIK(joystick1.getLeftY(), joystick1.getRightY()),
                        chassis);

        private final Command arcadeDriveCommand = new RunCommand(() -> chassis.arcadeDrive(joystick1.getLeftY(),
                        joystick1.getRightX()), chassis);

        private final Command arcadeDriveIKCommand = new RunCommand(() -> chassis.arcadeDriveIK(joystick1.getLeftY(),
                        joystick1.getRightX()), chassis);

        private final Command curvatureDriveCommand = new RunCommand(
                        () -> chassis.curvatureDrive(joystick1.getLeftY(), joystick1.getRightX()), chassis);

        private final Command curvatureDriveIKCommand = new RunCommand(
                        () -> chassis.curvatureDriveIK(joystick1.getLeftY(), joystick1.getRightX()), chassis);

        public RobotContainer() {

                chooser.setDefaultOption("Tank Drive", tankDriveCommand);
                chooser.addOption("Tank Drive IK", tankDriveIKCommand);
                chooser.addOption("Arcade Drive", arcadeDriveCommand);
                chooser.addOption("Arcade Drive IK", arcadeDriveIKCommand);
                chooser.addOption("Curvature Drive", curvatureDriveCommand);
                chooser.addOption("Curvature Drive IK", curvatureDriveIKCommand);

                SmartDashboard.putData(chooser);

                /* Tank Drive */
                chassis.setDefaultCommand(chooserCommand());
                configureButtonBindings();
        }

        private void configureButtonBindings() {
        }

        public Command getAutonomousCommand() {
                return null;
        }

        public Command chooserCommand() {
                return chooser.getSelected();
        }
}