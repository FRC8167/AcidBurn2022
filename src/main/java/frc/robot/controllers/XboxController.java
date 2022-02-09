package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class XboxController implements InputController {
	
	private final Joystick joystick;
	
	// two xbox controllers - one with the driver and one with the operator
	// the driver is in charge of actually driving the robot around from place to place
	// the operator is in charge of controlling the robot's intake and shooter and stuff
	public XboxController (int driverPort, int operatorPort) {
		joystick = new Joystick(driverPort);
	}
	
	public double getForwardSpeed() {
		return -joystick.getX();
	}
	
	public double getTurnSpeed() {
		return joystick.getY();
	}
	
	
	public JoystickButton getQuickTurnButton() {
		return new JoystickButton(joystick, Constants.gamepadAButton);
	}
}
