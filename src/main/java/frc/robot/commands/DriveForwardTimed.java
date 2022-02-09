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
	
	private final Timer timer;
	private final double timeToDrive;
	
	// Creates a new DriveForwardTimed.
	public DriveForwardTimed(DriveTrain driveTrain, double driveTime) {
		m_driveTrain = driveTrain;
		
		timer = new Timer();
		timeToDrive = driveTime;
		
		addRequirements(m_driveTrain);
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
		m_driveTrain.tankDrive(-Constants.AUTONOMOUS_SPEED, -Constants.AUTONOMOUS_SPEED);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_driveTrain.stop();
	}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return timer.get() < timeToDrive;
	}
}
