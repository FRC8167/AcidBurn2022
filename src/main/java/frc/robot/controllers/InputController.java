package frc.robot.controllers;

import edu.wpi.first.wpilibj2.command.button.Button;

public abstract interface InputController {
	// The forward speed to drive at
	public abstract double getForwardSpeed();
	
	// The turn speed to drive at
	public abstract double getTurnSpeed();
	
	// The button to use for quick turn
	public abstract Button getQuickTurnButton();
}