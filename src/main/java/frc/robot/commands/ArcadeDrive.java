// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
	/** Creates a new DriveWithJoysticks. */
	private final DriveTrain m_driveTrain;
	// two functions that return a double, each of which represents a joystick axis
	private final DoubleSupplier throttleGetter, rotationGetter;
	
	public ArcadeDrive(DriveTrain driveTrain, DoubleSupplier throttle, DoubleSupplier rotation) {
		// Use addRequirements() here to declare subsystem dependencies.
		m_driveTrain = driveTrain;
		throttleGetter = throttle;
		rotationGetter = rotation;
		
		addRequirements(m_driveTrain);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		m_driveTrain.arcadeDrive(throttleGetter.getAsDouble(), rotationGetter.getAsDouble());
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		m_driveTrain.stop();
	}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
