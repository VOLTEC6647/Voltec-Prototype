// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;

public class ChassisSubsystem extends SubsystemBase {
  private static WPI_VictorSPX frontLeft = new WPI_VictorSPX(ChassisConstants.frontLeftID);
  private static WPI_VictorSPX frontRight = new WPI_VictorSPX(ChassisConstants.frontRightID); 
  private static WPI_VictorSPX rearLeft = new WPI_VictorSPX(ChassisConstants.rearLeftID); 
  private static WPI_VictorSPX rearRight = new WPI_VictorSPX(ChassisConstants.rearRightID); 

  private static DifferentialDrive chassis;

  private double leftSpeed, rightSpeed;

  public ChassisSubsystem() {
    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);

    frontLeft.setInverted(true);
    frontRight.setInverted(false);

    chassis = new DifferentialDrive(frontLeft, frontRight);

    // Set Coast Mode
    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    rearLeft.setNeutralMode(NeutralMode.Coast);
    rearRight.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    publishData();
  }

  private void publishData() {
    SmartDashboard.putNumber("LeftSpeed", leftSpeed);
    SmartDashboard.putNumber("RightSpeed", rightSpeed);
  }

  public void TankDrive(double leftSpeed, double rightSpeed)
  {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    chassis.tankDrive(leftSpeed, rightSpeed);
  }

  public void ArcadeDrive(double linearSpeed, double rotSpeed)
  {
    chassis.arcadeDrive(linearSpeed, rotSpeed);
  }
}
