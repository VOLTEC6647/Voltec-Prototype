// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.utils.XboxControllerUpgrade;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
  private final ChassisSubsystem chassis = new ChassisSubsystem();

  public final XboxControllerUpgrade joystick1 = new XboxControllerUpgrade(OIConstants.KDriverControllerPort, 0.2);

  public RobotContainer() {
    // Tank Drive
    /* chassis.setDefaultCommand(
      new RunCommand(() -> chassis.TankDrive(joystick1.getLeftY() * OIConstants.multiplier, joystick1.getRightY() * OIConstants.multiplier), chassis));
    configureButtonBindings(); */

    // Arcade Drive
    chassis.setDefaultCommand(
        new RunCommand(() -> chassis.ArcadeDrive(joystick1.getLeftY() * OIConstants.multiplier, -joystick1.getRightX() * OIConstants.multiplier), chassis));
    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}