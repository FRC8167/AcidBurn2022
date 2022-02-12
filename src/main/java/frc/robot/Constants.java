// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	//CAN IDs for the wiring team
	public static final int LEFT_FRONT = 1;
	public static final int RIGHT_FRONT = 2;
	public static final int LEFT_BACK = 3;
	public static final int RIGHT_BACK = 4;
	// we do not have a PCM anymore
	// public static final int PCM_CAN_ID = 5;
	public static final int CLIMBER_MOTOR = 6;
	
	//Gamepad Controller
	public static final int DRIVERJOYSTICK_NUMBER = 0;
	public static final int OPERATORJOYSTICK_NUMBER = 1;
	
	// TODO: all of these are wrong
	public static final int gamepadAButton = 1;
	public static final int gamepadBButton = 2;
	public static final int gamepadXButton = 3;
	public static final int gamepadYButton = 4;
	
	// autonomous
	public static final double DRIVE_FORWARD_TIME = 10.0;
	public static final double AUTONOMOUS_SPEED = 0.4;
	
	//PID
	public static final int kpIDLoopIDx = 0;
	public static final int kSlotIDx = 0;
	public static final int pidLoopTimeout = 30;
	public static final int maxClimberSpeed = 30000;
	public static final int maxClimberAccelleration = 8000;
	public static final double quickTurnProportion = 0.008;  //may need to tweak
	
	// timeouts for different commands
	public static final double timeoutQuickTurn = 3;
	public static final double timeoutRaiseClimber = 5;
}
