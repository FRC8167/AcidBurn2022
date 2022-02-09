// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveForwardTimed extends CommandBase {
	private final DriveTrain driveTrain;
	
	private final Timer timer;
	private final double timeToDrive;
	private final double speed;
	
	// Creates a new DriveForwardTimed.
	public DriveForwardTimed(DriveTrain driveTrain, double driveTime) {
		// TODO: can i factor this out without java complaining abt uninitialiszed final variables?
		// this.DriveForwardTimed(driveTrain, driveTime, Constants.AUTONOMOUS_SPEED);
		this.driveTrain = driveTrain;
		this.timer = new Timer();
		this.timeToDrive = driveTime;
		this.speed = Constants.AUTONOMOUS_SPEED;
		
		addRequirements(driveTrain);
	}
	
	public DriveForwardTimed(DriveTrain driveTrain, double driveTime, double speed) {
		this.driveTrain = driveTrain;
		this.timer = new Timer();
		this.timeToDrive = driveTime;
		this.speed = speed;
		
		addRequirements(driveTrain);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.reset();
		timer.start();
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
		return timer.get() < timeToDrive;
	}
}
