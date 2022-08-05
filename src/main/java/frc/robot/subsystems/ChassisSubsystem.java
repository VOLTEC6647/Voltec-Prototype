// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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

  //Create motor objects
  private static WPI_TalonSRX frontLeft = new WPI_TalonSRX(ChassisConstants.frontLeft);
  private static WPI_TalonSRX frontRight = new WPI_TalonSRX(ChassisConstants.frontRight); 
  private static WPI_TalonSRX rearLeft = new WPI_TalonSRX(ChassisConstants.backLeft); 
  private static WPI_TalonSRX rearRight = new WPI_TalonSRX(ChassisConstants.backRight); 

  private static DifferentialDrive chassis;
  
  private double leftSpeed, rightSpeed;

  //Constructor method, is called when object is created
  public ChassisSubsystem() {
    rearLeft.follow(frontLeft);
    rearRight.follow(frontRight);

    frontLeft.setInverted(true);
    frontRight.setInverted(false);

    rearLeft.setInverted(true);
    rearRight.setInverted(false);

    chassis = new DifferentialDrive(frontLeft, frontRight);

    //Uncommment when using WPI types
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
