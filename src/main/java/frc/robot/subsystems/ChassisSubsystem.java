// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ChassisConstants;

/*
 * This is the chassis subsystem. 
 * Change the motor type to the used motor in your robot
 */
public class ChassisSubsystem extends SubsystemBase {

 /*  private static final WPI_VictorSPX fRight = new WPI_VictorSPX(1);
  private static final WPI_VictorSPX fLeft = new WPI_VictorSPX(2);
  private static final WPI_VictorSPX bRight = new WPI_VictorSPX(3);
  private static final WPI_VictorSPX bLeft = new WPI_VictorSPX(4); */

  // Create motor objects
  private static CANSparkMax frontLeft = new CANSparkMax(ChassisConstants.frontLeft, MotorType.kBrushless);
  private static CANSparkMax frontRight = new CANSparkMax(ChassisConstants.frontRight, MotorType.kBrushless);
  private static CANSparkMax rearLeft = new CANSparkMax(ChassisConstants.backLeft, MotorType.kBrushless);
  private static CANSparkMax rearRight = new CANSparkMax(ChassisConstants.backRight, MotorType.kBrushless);

  private static MotorControllerGroup leftControllerGroup = new MotorControllerGroup(frontLeft, rearLeft);
  private static MotorControllerGroup rightControllerGroup = new MotorControllerGroup(frontRight, rearRight);

  private static DifferentialDrive chassis;

  private double leftSpeed, rightSpeed;

  // Constructor method, is called when object is created
  public ChassisSubsystem() {
    frontLeft.restoreFactoryDefaults();
    frontRight.restoreFactoryDefaults();

    rearLeft.restoreFactoryDefaults();
    rearRight.restoreFactoryDefaults();

    setMotorsIdleMode(IdleMode.kCoast);

    leftControllerGroup.setInverted(true);

    chassis = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

    /* Uncommment when using WPI types. Do not use for CANSPark Max */
    /* Set Coast Mode */
    /*
     * frontLeft.setNeutralMode(NeutralMode.Coast);
     * frontRight.setNeutralMode(NeutralMode.Coast);
     * rearLeft.setNeutralMode(NeutralMode.Coast);
     * rearRight.setNeutralMode(NeutralMode.Coast)
     */;
  }

  @Override
  public void periodic() {
    publishData();
  }

  /* Set motor idle mode */
  private void setMotorsIdleMode(IdleMode idleMode) {
    frontLeft.setIdleMode(idleMode);
    frontRight.setIdleMode(idleMode);
    rearLeft.setIdleMode(idleMode);
    rearRight.setIdleMode(idleMode);
  }

  // Publish to SmartDashboard for debugging
  private void publishData() {
    SmartDashboard.putNumber("LeftSpeed", leftSpeed);
    SmartDashboard.putNumber("RightSpeed", rightSpeed);
  }

  /* Different drive modes */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    chassis.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double linearSpeed, double rotSpeed) {
    this.leftSpeed = linearSpeed;
    this.rightSpeed = rotSpeed;
    chassis.arcadeDrive(linearSpeed, rotSpeed);
  }

  public void curvatureDrive(double rightSpeed, double leftSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    chassis.curvatureDrive(leftSpeed, rightSpeed, true);
  }

  public void curvatureDriveIK(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    DifferentialDrive.curvatureDriveIK(leftSpeed, rightSpeed, true);
  }

  public void arcadeDriveIK(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    DifferentialDrive.arcadeDriveIK(leftSpeed, rightSpeed, true);
  }

  public void tankDriveIK(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    DifferentialDrive.tankDriveIK(leftSpeed, rightSpeed, true);
  }
}
