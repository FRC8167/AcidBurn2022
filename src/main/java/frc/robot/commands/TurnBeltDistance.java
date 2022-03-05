// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeltBallThing;

public class TurnBeltDistance extends CommandBase {
	// TODO: put these in Constants
	private static final int maxSpeed = 20000;
	private static final int maxAccelleration = 8000;
	
	private final BeltBallThing belt;
	private final int distance;
	private double startTime;
	
	public TurnBeltDistance(BeltBallThing belt, int distance) {
		this.belt = belt;
		this.distance = distance;
		addRequirements(this.belt);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		startTime = Timer.getFPGATimestamp();
		belt.setMotionMagic(distance, maxSpeed, maxAccelleration);
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		belt.stop();
	}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		// TODO: put this '5' in a constant
		return belt.isMotionMagicDone(distance) || startTime + 20 < Timer.getFPGATimestamp();
	}
}