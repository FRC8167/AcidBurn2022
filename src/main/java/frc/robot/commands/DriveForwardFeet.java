package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveForwardFeet extends CommandBase {
	public static final double motorTicksPerFoot = 13994.16;
	private final DriveTrain driveTrain;
	private final double distance;
	private double startTime;
	
	public DriveForwardFeet(DriveTrain driveTrain, double distanceFeet) {
		distance = distanceFeet * motorTicksPerFoot;
		this.driveTrain = driveTrain;
		
		addRequirements(driveTrain);
	}
	
	@Override
	public void initialize() {
		startTime = Timer.getFPGATimestamp();
		
		driveTrain.setMotionMagic(distance, 10000, 4000);
	}
	
	@Override
	public void execute() {}
	
	@Override
	public void end(boolean interrupted) {
		driveTrain.stopMotionMagic();
	}
	
	@Override
	public boolean isFinished() {
		return Timer.getFPGATimestamp() > startTime + 5
			|| driveTrain.isMagicMotionDone(distance);
	}
}
