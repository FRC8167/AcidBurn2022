package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class XboxController implements InputController {
	
	private final Joystick joystick;
	
	private final JoystickButton quickTurnButton;
	
	// two xbox controllers - one with the driver and one with the operator
	// the driver is in charge of actually driving the robot around from place to place
	// the operator is in charge of controlling the robot's intake and shooter and stuff
	public XboxController (int driverPort, int operatorPort) {
		joystick = new Joystick(driverPort);
		
		quickTurnButton = new JoystickButton(joystick, Constants.gamepadAButton);
	}
	
	@Override
	public double getForwardSpeed() {
		return -joystick.getX();
	}
	
	@Override
	public double getTurnSpeed() {
		return joystick.getY();
	}
	
	@Override
	public JoystickButton getQuickTurnButton() {
		return quickTurnButton;
	}
}
