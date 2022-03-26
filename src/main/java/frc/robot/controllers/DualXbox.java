package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.Constants;


public class DualXbox implements InputController {
	
	// two xbox controllers - one for the driver and one for the operator
	private final XboxController driverJoystick;
	private final Joystick operatorJoystick;
	
	private final JoystickButton driverQuickTurnRightButton;
	private final JoystickButton operatorMotionMagicRaiseClimberButton;
	private final JoystickButton operatorMotionMagicLowerClimberButton;
	private final JoystickButton operatorBeltTurnButton;
	
	private final JoystickButton beltForwardButton;
	private final JoystickButton beltBackwardButton;
	
	private final double baseSpeedFactor = 0.60;
	private final double boostFactor = 0.25;
	
	// the driver is in charge of actually driving the robot around from place to place
	// the operator is in charge of controlling the robot's intake and shooter and stuff
	public DualXbox (int driverPort, int operatorPort) {
		driverJoystick = new XboxController(driverPort);
		operatorJoystick = new Joystick(operatorPort);
		
		//TODO: put these in constants
		driverQuickTurnRightButton = new JoystickButton(driverJoystick, 1);
		operatorBeltTurnButton = new JoystickButton(operatorJoystick, 2);
		
		operatorMotionMagicRaiseClimberButton = new JoystickButton(operatorJoystick, 6);
		operatorMotionMagicLowerClimberButton = new JoystickButton(operatorJoystick, 5);
		
		beltForwardButton = new JoystickButton(operatorJoystick, 8);
		beltBackwardButton = new JoystickButton(operatorJoystick, 7);
	}
	
	@Override
	public double getForwardSpeed() {
		return driverJoystick.getLeftY()*(baseSpeedFactor
			 - boostFactor*driverJoystick.getRawAxis(2)	// LT value (in range [0, 1])
			 + boostFactor*driverJoystick.getRawAxis(3));	// RT value (in range [0, 1])
	}
	
	@Override
	public double getTurnSpeed() {
		return driverJoystick.getLeftX()*(baseSpeedFactor
			 - boostFactor*driverJoystick.getRawAxis(2)
			 + boostFactor*driverJoystick.getRawAxis(3));
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
