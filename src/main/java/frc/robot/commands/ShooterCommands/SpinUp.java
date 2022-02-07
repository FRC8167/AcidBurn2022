// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class SpinUp extends CommandBase {
  /** Creates a new SpinUp. */
  private final Shooter m_shooter;
  private final DoubleSupplier m_power;
 

  public SpinUp(Shooter shooter, DoubleSupplier power) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = shooter;
    m_power = power;
    addRequirements(m_shooter);;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.spinDown(m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
