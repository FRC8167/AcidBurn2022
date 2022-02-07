// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveForwardTimed extends CommandBase {
  private final DriveTrain m_driveTrain;
  private boolean finish = false;
  private final Timer timer;

  /** Creates a new DriveForwardTimed. */
  public DriveForwardTimed(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    while(timer.get() < Constants.DRIVE_FORWARD_TIME)
      {
        //I added a negative before constants so this really should be called
        //DriveBackwardTimes
        
        
        m_driveTrain.tankDrive(-Constants.AUTONOMOUS_SPEED, -Constants.AUTONOMOUS_SPEED);
      }
      finish = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}
