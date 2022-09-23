// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.utils.XboxControllerUpgrade;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private final ChassisSubsystem chassis = new ChassisSubsystem();
  private final LimelightSubsystem limelight = new LimelightSubsystem();

  public final XboxControllerUpgrade joystick1 = new XboxControllerUpgrade(OIConstants.KDriverControllerPort, 0.2);

  public RobotContainer() {

    /* Tank Drive */
    chassis.setDefaultCommand(
      new RunCommand(() -> chassis.TankDrive(joystick1.getLeftY(), joystick1.getRightY()), chassis));
    configureButtonBindings();

    /* Arcade Drive
    chassis.setDefaultCommand(
      new RunCommand(() -> chassis.ArcadeDrive(joystick1.getLeftY(), joystick1.getRightX()), chassis));
    configureButtonBindings(); */
  }

  private void configureButtonBindings() {
    new JoystickButton(joystick1, Button.kRightBumper.value)
      .whileHeld( new StartEndCommand(() -> limelight.toggleAim(), () -> limelight.toggleAim(), limelight));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    return new StartEndCommand(() -> limelight.toggleAim(), () -> limelight.toggleAim(), limelight);
  }
}