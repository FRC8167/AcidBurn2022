package frc.robot.controllers;

import edu.wpi.first.wpilibj2.command.button.Button;

public abstract interface InputController {
	// The forward speed to drive at
	public abstract double getForwardSpeed();
	
	// The turn speed to drive at
	public abstract double getTurnSpeed();
	
	// The button to use for quick turn
	public abstract Button getQuickTurnRightButton();

	//The button to use for raising the climber
	public abstract Button getMotionMagicRaiseClimberButton();
	
		//The button to use for lower the climber
	public abstract Button getMotionMagicLowerClimberButton();
	
	//the button to use for moving the belt
	public abstract Button getBeltTurnButton();
	
	//the buttons for manually moving the belt
	public abstract Button getBeltForwardButton();
	public abstract Button getBeltBackwardButton();
}