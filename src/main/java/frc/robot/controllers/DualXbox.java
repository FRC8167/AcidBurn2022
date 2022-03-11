package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;


public class DualXbox implements InputController {
	
	// two xbox controllers - one for the driver and one for the operator
	private final Joystick driverJoystick;
	private final Joystick operatorJoystick;
	
	private final JoystickButton driverQuickTurnRightButton;
	private final JoystickButton operatorMotionMagicRaiseClimberButton;
	private final JoystickButton operatorMotionMagicLowerClimberButton;
	private final JoystickButton operatorBeltTurnButton;
	
	private final JoystickButton beltForwardButton;
	private final JoystickButton beltBackwardButton;
	
	// the driver is in charge of actually driving the robot around from place to place
	// the operator is in charge of controlling the robot's intake and shooter and stuff
	public DualXbox (int driverPort, int operatorPort) {
		driverJoystick = new Joystick(driverPort);
		operatorJoystick = new Joystick(operatorPort);
		
		driverQuickTurnRightButton = new JoystickButton(operatorJoystick, Constants.gamepadAButton);
		operatorMotionMagicRaiseClimberButton = new JoystickButton(operatorJoystick, Constants.gamepadXButton);
		operatorBeltTurnButton = new JoystickButton(operatorJoystick, Constants.gamepadBButton);
		operatorMotionMagicLowerClimberButton = new JoystickButton(operatorJoystick, Constants.gamepadYButton);

		//TODO: put these in constants
		beltForwardButton = new JoystickButton(driverJoystick, 7);
		beltBackwardButton = new JoystickButton(driverJoystick, 8);
	}
	
	@Override
	public double getForwardSpeed() {
		return 0.5*driverJoystick.getY();
	}
	
	@Override
	public double getTurnSpeed() {
		return 0.5*driverJoystick.getX();
	}
	
	@Override
	public JoystickButton getQuickTurnRightButton() {
		return driverQuickTurnRightButton;
	}

	@Override
	public JoystickButton getMotionMagicRaiseClimberButton() {
		return operatorMotionMagicRaiseClimberButton;
	}
	
	@Override
	public JoystickButton getMotionMagicLowerClimberButton() {
		return operatorMotionMagicLowerClimberButton;
	}
	
	@Override
	public JoystickButton getBeltTurnButton() {
		return operatorBeltTurnButton;
	}
	
	@Override
	public JoystickButton getBeltForwardButton() {
		return beltForwardButton;
	}
	
	@Override
	public JoystickButton getBeltBackwardButton() {
		return beltBackwardButton;
	}
}
