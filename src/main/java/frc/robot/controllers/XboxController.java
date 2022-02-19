package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
// import frc.robot.commands.ClimberCommands.MotionMagicRaiseClimber;

public class XboxController implements InputController {
	
	private final Joystick joystick;
	
	private final JoystickButton quickTurnRightButton;
	private final JoystickButton motionMagicRaiseClimberButton;
	
	// two xbox controllers - one with the driver and one with the operator
	// the driver is in charge of actually driving the robot around from place to place
	// the operator is in charge of controlling the robot's intake and shooter and stuff
	public XboxController (int driverPort, int operatorPort) {
		joystick = new Joystick(driverPort);
		quickTurnRightButton = new JoystickButton(joystick, Constants.gamepadAButton);
		motionMagicRaiseClimberButton = new JoystickButton(joystick, Constants.gamepadXButton);
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
	public JoystickButton getQuickTurnRightButton() {
		return quickTurnRightButton;
	}

	@Override
	public JoystickButton getMotionMagicRaiseClimberButton() {
		return motionMagicRaiseClimberButton;
	}

	@Override
	public Button getBeltTurnButton() {
		// TODO Auto-generated method stub
		return null;
	}
}
