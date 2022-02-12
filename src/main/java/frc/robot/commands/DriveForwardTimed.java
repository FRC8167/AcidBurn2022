// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveForwardTimed extends CommandBase {
	private final DriveTrain driveTrain;
	
	private final double timeToDrive;
	private double startTime;
	private final double speed;
	
	// Creates a new DriveForwardTimed.
	public DriveForwardTimed(DriveTrain driveTrain, double driveTime, double speed) {
		this.driveTrain = driveTrain;
		this.timeToDrive = driveTime;
		this.speed = speed;
		
		addRequirements(driveTrain);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		startTime = Timer.getFPGATimestamp();
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		// these are negative because we want to drive forward (?) (TODO: check)
		driveTrain.tankDrive(-speed, -speed);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		driveTrain.stop();
	}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return startTime + timeToDrive < Timer.getFPGATimestamp();
	}
}
