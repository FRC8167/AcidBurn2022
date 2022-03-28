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
	private double initialAngle;
	private double startTimeTurn = 0;
	
	/** Creates a new QuickTurnCommand. */
	public QuickTurnCommand(DriveTrain driveTrain, double turnAngleDegrees) {
		this.driveTrain = driveTrain;
		
		this.turnAngle = turnAngleDegrees;
		
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
		// double turnError = gyro.getAngle() -(initialAngle + turnAngle);
		
		// double turnPower = turnError * Constants.quickTurnProportion;
		// if (Math.abs(turnError) > 90) {
		// 	turnPower = turnPower * 0.75;  //for large angles we tune it down a tad  could do 0.5
		// }
		// turnPower = Math.min(1, turnPower);
		// turnPower = Math.max(-1, turnPower);
		
		// System.out.println(turnPower);
		// idk why but arcadeDrive(0, turnPower) doesnt seem to want to work for some reason
		driveTrain.tankDrive(0.4, -0.4);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (Math.abs((driveTrain.getYaw() - (initialAngle - turnAngle))%360) < 1.5) {
			return true;
		}
		return false; //startTimeTurn + Constants.timeoutQuickTurn < Timer.getFPGATimestamp();
	}
}
