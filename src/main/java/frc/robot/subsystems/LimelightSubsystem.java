// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.stuypulse.stuylib.network.limelight.Limelight;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstats;

public class LimelightSubsystem extends SubsystemBase {

  public final ChassisSubsystem chassis = new ChassisSubsystem(); 

  public final Limelight limelight;

  private final Object lock = new Object();
  private final Notifier notifier;
  private boolean aiming = false, firstRun = true;

  public LimelightSubsystem() {
    limelight = Limelight.getInstance();

    notifier = new Notifier(() -> {
      synchronized (lock) { //Locks the process to an independent thread
        if (firstRun) {
          Thread.currentThread().setName("limelight");
          Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
          firstRun = false;
        }
        if (!aiming)
          return;

        synchronized (LimelightSubsystem.this) {
          if (!limelight.getValidTarget())
            return;
          
          //Get tx and ty
          double tx = limelight.getTargetXAngle(),
            ty = limelight.getTargetYAngle();

          //Get vision variables
          double kpAim = VisionConstats.kpAim, kpDistance = VisionConstats.kpDistance,
            min_aim_command = VisionConstats.min_aim_command;          

          //Calculates distance and aim adjustments
          var steeringAdjust = tx > 1 ? (kpAim * -tx - min_aim_command) 
            : (tx < -1 ? (kpAim * -tx + min_aim_command) : 0);
          var distanceAdjust = kpDistance * ty;

          //Send adjustments
          chassis.TankDrive(steeringAdjust + distanceAdjust, -steeringAdjust + distanceAdjust);
        }
      }
    });

    notifier.startPeriodic(0.01); //Sets notifier at 10ms
  }

  @Override
  public void periodic() {}

  public void toggleAim(){
    aiming = !aiming;
  }
}
