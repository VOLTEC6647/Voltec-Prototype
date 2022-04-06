// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisTankDriveController extends CommandBase {
  private ChassisSubsystem chassis;
  private final double leftSpeed;
  private final double rightSpeed;

  public ChassisTankDriveController(ChassisSubsystem chassis, double leftSpeed, double rightSpeed) {
    this.chassis = chassis;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    chassis.TankDrive(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
