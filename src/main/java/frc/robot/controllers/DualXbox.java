package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class DualXbox implements InputController {
	
	// two xbox controllers - one for the driver and one for the operator
	private final Joystick driverJoystick;
	private final Joystick operatorJoystick;
	
	private final JoystickButton driverQuickTurnButton;
	
	// the driver is in charge of actually driving the robot around from place to place
	// the operator is in charge of controlling the robot's intake and shooter and stuff
	public DualXbox (int driverPort, int operatorPort) {
		driverJoystick = new Joystick(driverPort);
		operatorJoystick = new Joystick(operatorPort);
		
		driverQuickTurnButton = new JoystickButton(operatorJoystick, Constants.gamepadAButton);
	}
	
	@Override
	public double getForwardSpeed() {
		return -driverJoystick.getX();
	}
	
	@Override
	public double getTurnSpeed() {
		return driverJoystick.getY();
	}
	
	@Override
	public JoystickButton getQuickTurnButton() {
		return driverQuickTurnButton;
	}
}
