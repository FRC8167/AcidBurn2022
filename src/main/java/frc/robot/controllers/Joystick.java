package frc.robot.controllers;

import edu.wpi.first.wpilibj2.command.button.Button;

public class Joystick implements InputController {
	@Override
	public double getForwardSpeed() {
		// TODO Auto-generated method stub (Joystick.getForwardSpeed)
		return 0;
	}
	
	@Override
	public double getTurnSpeed() {
		// TODO Auto-generated method stub (Joystick.getTurnSpeed)
		return 0;
	}
	
	public double getSpeedMultiplier() {
		// TODO: use the little paddle thing on the joystick to use as the speed multiplier for driving
		return 1;
	}
	
	@Override
	public Button getQuickTurnRightButton() {
		// TODO Auto-generated method stub (Joystick.getQuickTurnButton)
		return null;
	}

	@Override
	public Button getMotionMagicRaiseClimberButton() {
		return null;
	}
	
	@Override
	public Button getMotionMagicLowerClimberButton() {
		return null;
	}

	@Override
	public Button getBeltTurnButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button getBeltForwardButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button getBeltBackwardButton() {
		// TODO Auto-generated method stub
		return null;
	}
}
