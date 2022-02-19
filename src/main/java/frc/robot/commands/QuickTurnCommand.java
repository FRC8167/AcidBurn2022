// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

public class QuickTurnCommand extends CommandBase {
	private final DriveTrain driveTrain;
	private final Gyro gyro;
	
	private final double turnAngle;
	private final double initialAngle;
	private double startTimeTurn = 0;
	
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
		startTimeTurn = Timer.getFPGATimestamp();
	}
	
	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double turnError = gyro.getAngle() -(initialAngle + turnAngle);
		
		double turnPower = turnError * Constants.turnDegreeProportion;
		if (Math.abs(turnError) > 90) {
			turnPower = turnPower * 0.75;  //for large angles we tune it down a tad  could do 0.5
		}
		turnPower = Math.min(1, turnPower);
		turnPower = Math.max(-1, turnPower);
		// System.out.println(turnPower);
		// idk why but arcadeDrive(0, turnPower) doesnt seem to want to work for some reason
		driveTrain.tankDrive(-turnPower, -turnPower);
	}
	
	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {}
	
	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		if (Math.abs(gyro.getAngle() - (initialAngle + turnAngle)) < 3) {
			return true;
		}
		if((startTimeTurn + Constants.turnDegreeTimeout) < Timer.getFPGATimestamp()) {
			return true;
		}
		return false;
		//gyro.getAngle() > initialAngle + turnAngle;
	}
}
