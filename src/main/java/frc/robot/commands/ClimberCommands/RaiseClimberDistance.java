// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class RaiseClimberDistance extends CommandBase {
	private final Climber climber;
	private final int distance;
	private double startTime;
	
	public RaiseClimberDistance(Climber climber, int distance) {
		this.climber = climber;
		this.distance = distance;
		addRequirements(climber);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		startTime = Timer.getFPGATimestamp();
		climber.setMotionMagic(distance, Constants.maxClimberSpeed, Constants.maxClimberAccelleration);
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		climber.stop();
	}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return startTime + Constants.timeoutRaiseClimber < Timer.getFPGATimestamp()
			|| climber.isMotionMagicDone(distance);
	}
}