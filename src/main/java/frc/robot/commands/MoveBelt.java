package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BeltBallThing;

public class MoveBelt extends CommandBase {
	private final BeltBallThing intake;
	private final double power;
	
	public MoveBelt(BeltBallThing intake, double power) {
		this.intake = intake;
		this.power = power;
		addRequirements(this.intake);
	}
	
	@Override
	public void initialize() {
		
	}
	
	@Override
	public void execute() {
		intake.drivePercentOutput(power);
	}
	
	@Override
	public boolean isFinished() {
		return false;
	}
	
	@Override
	public void end(boolean interrupted) {
		intake.stop();
	}
}
