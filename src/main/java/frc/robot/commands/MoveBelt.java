package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Belt;

public class MoveBelt extends CommandBase {
	private final Belt intake;
	private final double power;
	private final double time;
	private double startTime;
	
	public MoveBelt(Belt intake, double power) {
		this.intake = intake;
		this.power = power;
		this.time = Double.POSITIVE_INFINITY;
		addRequirements(this.intake);
	}
	
	public MoveBelt(Belt intake, double power, double time) {
		this.intake = intake;
		this.power = power;
		this.time = time;
		addRequirements(this.intake);
	}
	
	@Override
	public void initialize() {
		this.startTime = Timer.getFPGATimestamp();
	}
	
	@Override
	public void execute() {
		intake.drivePercentOutput(power);
	}
	
	@Override
	public boolean isFinished() {
		return startTime + time < Timer.getFPGATimestamp();
	}
	
	@Override
	public void end(boolean interrupted) {
		intake.stop();
	}
}
