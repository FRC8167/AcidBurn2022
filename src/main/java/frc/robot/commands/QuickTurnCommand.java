// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

public class QuickTurnCommand extends CommandBase {
	private final DriveTrain driveTrain;
	private final Gyro gyro;
	
	private final double turnAngle;
	private final double initialAngle;
	
	/** Creates a new QuickTurnCommand. */
	public QuickTurnCommand(DriveTrain driveTrain, Gyro gyroSubsystem, int desiredTurnAngle) {
		this.driveTrain = driveTrain;
		this.gyro = gyroSubsystem;
		
		this.turnAngle = desiredTurnAngle;
		this.initialAngle = gyro.getAngle();
		
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(gyro);
		addRequirements(driveTrain);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		driveTrain.arcadeDrive(0.0, 0.1);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return gyro.getAngle() > initialAngle + turnAngle;
	}
}
