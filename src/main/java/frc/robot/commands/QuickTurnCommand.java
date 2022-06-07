// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class QuickTurnCommand extends CommandBase {
	private final DriveTrain driveTrain;
	
	private final double turnAngle;
	private final int reversed;
	private double initialAngle;
	private double startTimeTurn;
	
	
	/** Creates a new QuickTurnCommand. */
	public QuickTurnCommand(DriveTrain driveTrain, double turnAngleDegrees) {
		this.driveTrain = driveTrain;
		
		this.turnAngle = turnAngleDegrees;
		this.reversed = ((this.turnAngle < 0)?-1:1);
		
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(driveTrain);
	}
	
	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		startTimeTurn = Timer.getFPGATimestamp();
		this.initialAngle = driveTrain.getYaw();
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		driveTrain.tankDrive(0.5*reversed, -0.5*reversed);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (Math.abs((driveTrain.getYaw() - (initialAngle - turnAngle))%360) < 2) {
			return true;
		}
		return startTimeTurn + Constants.timeoutQuickTurn < Timer.getFPGATimestamp();
	}
}
