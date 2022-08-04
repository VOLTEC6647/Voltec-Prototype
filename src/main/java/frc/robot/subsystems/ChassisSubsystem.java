// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;


  /*
   * This is the chassis subsystem. 
   * Change the motor type to the used motor in your robot
   */
public class ChassisSubsystem extends SubsystemBase {

  private static CANSparkMax frontLeft = new CANSparkMax(ChassisConstants.frontLeft, MotorType.kBrushed);
  private static CANSparkMax frontRight = new CANSparkMax(ChassisConstants.frontRight, MotorType.kBrushed); 
  private static CANSparkMax rearLeft = new CANSparkMax(ChassisConstants.backLeft, MotorType.kBrushed); 
  private static CANSparkMax rearRight = new CANSparkMax(ChassisConstants.backRight, MotorType.kBrushed); 

  private static DifferentialDrive chassis;

  private double leftSpeed, rightSpeed;

  public ChassisSubsystem() {
    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);

    frontLeft.setInverted(true);
    frontRight.setInverted(false);

    rearLeft.setInverted(true);
    rearRight.setInverted(false);

    chassis = new DifferentialDrive(frontLeft, frontRight);

    // Set Coast Mode
    // frontLeft.Mode(NeutralMode.Coast);
    // frontRight.setNeutralMode(NeutralMode.Coast);
    // rearLeft.setNeutralMode(NeutralMode.Coast);
    // rearRight.setNeutralMode(NeutralMode.Coast);
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
