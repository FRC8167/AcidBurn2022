// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class SetClimberDistance extends CommandBase {
	private final Climber climber;
	private final double distance;
	private double startTime;
	
	public SetClimberDistance(Climber climber, double distance) {
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
	
	private boolean isMotionMagicDone() {
		double sensorDistance = climber.getPosition();
		boolean isGoingUp = distance > sensorDistance;
		
		if (!isGoingUp && sensorDistance < 0) return true;
		if (isGoingUp && sensorDistance > Climber.maxExtensionTicks) return true;
		
		double error = sensorDistance - distance;
		
		if (!isGoingUp) return error < 200;
		else return error > 200;
	}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return startTime + Constants.timeoutRaiseClimber < Timer.getFPGATimestamp() || isMotionMagicDone();
	}
}