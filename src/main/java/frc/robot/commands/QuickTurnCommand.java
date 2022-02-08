// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

public class QuickTurnCommand extends CommandBase {
  /** Creates a new QuickTurnCommand. */
  public static double m_desiredTurnAngle;
  private final DriveTrain m_driveTrain;
  private final Gyro m_gyroSubsystem;
  private final double m_initialAngle;
  
  public QuickTurnCommand(DriveTrain driveTrain, Gyro gyroSubsystem, int desiredTurnAngle) {
    System.out.println("got here 1");
    m_driveTrain = driveTrain;
    m_gyroSubsystem = gyroSubsystem;

    m_desiredTurnAngle = desiredTurnAngle;
    m_initialAngle = m_gyroSubsystem.getAngle();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_gyroSubsystem);
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("got here 2");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("got here 3");
    m_driveTrain.arcadeDrive(0.0, 0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; //m_gyroSubsystem.getAngle() > m_initialAngle + m_desiredTurnAngle;
  }
}
