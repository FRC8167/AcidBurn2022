package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveForwardFeet extends CommandBase {
	private final DriveTrain driveTrain;
	private final double distance;
	private double startTime;
	
	public DriveForwardFeet(DriveTrain driveTrain, double distance) {
		this.distance = distance;
		this.driveTrain = driveTrain;
		
		addRequirements(driveTrain);
	}
	
	@Override
	public void initialize() {
		startTime = Timer.getFPGATimestamp();
		
		// driveTrain.setMotionMagic();
	}
}
