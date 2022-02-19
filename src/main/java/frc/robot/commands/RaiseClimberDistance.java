// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class RaiseClimberDistance extends CommandBase {
	// TODO: put these in Constants
	private static final int maxSpeed = 30000;
	private static final int maxAccelleration = 8000;
	
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
		climber.setMotionMagic(distance, maxSpeed, maxAccelleration);
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
		// TODO: put this '5' in a constant
		return climber.isMotionMagicDone(distance) || startTime + 5 < Timer.getFPGATimestamp();
	}
}