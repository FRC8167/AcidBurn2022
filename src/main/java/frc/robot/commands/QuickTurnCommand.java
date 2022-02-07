// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class QuickTurnCommand extends CommandBase {
  /** Creates a new QuickTurnCommand. */
 public static double m_desiredTurnAngle;
 private final DriveTrain m_driveTrain;
 
  public QuickTurnCommand(DriveTrain driveTrain, int desiredTurnAngle) {
    m_driveTrain = driveTrain;
    m_desiredTurnAngle = desiredTurnAngle;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_gyro);
    addRequirements(RobotContainer.m_driveTrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.arcadeDrive (0.0, m_desiredTurnAngle);
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
