// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	// TODO: there are a LOT of these - not ALL of them are needed, right?
	
	//CAN IDs for the wiring team
	public static final int LEFT_FRONT = 1;
	public static final int RIGHT_FRONT = 2;
	public static final int LEFT_BACK = 3;
	public static final int RIGHT_BACK = 4;
	public static final int PCM_CAN_ID = 5;
	public static final int INTAKE_MOTOR = 6;
	public static final int INDEXER_MOTOR = 7;
	public static final int LEFT_SHOOTER_MOTOR = 8;
	public static final int RIGHT_SHOOTER_MOTOR = 9;
	
	//Gamepad Controller
	public static final int GP_LEFT_X_AXIS = 0; //may not be needed in 2022
	public static final int GP_LEFT_Y_AXIS = 1;  // may not be needed in 2022
	public static final double DRIVETRAINSPEED = 0.7;
	public static final double DRIVE_FORWARD_TIME = 10.0;
	public static final double AUTONOMOUS_SPEED = 0.4;
	public static final int DRIVERJOYSTICK_NUMBER = 0;
	public static final int OPERATORJOYSTICK_NUMBER = 1;
	public static final double INTAKE_SPEED = 0.1;  //775 WHICH IS WICKED FAST SO THIS SLOWS IT DOWN????
	public static final int kGamepadBumperLeft = 5;
	public static final int kGamepadBumperRight = 6;
	public static final double INDEX_SPEED = 0.3;  //NEEDS PROTOTYPE TESTING
	public static final double SHOOTER_SPINUP_SPEED = 0.75;
	public static final double SHOOTER_SPINDOWN_SPEED = 0.4;
	
	public static int gamepadAButton = 1;
	public static int gamepadBButton = 2;
	public static int gamepadXButton = 3;
	public static int gamepadYButton = 4;
	
	//PID
	public static final int kpIDLoopIDx = 0;
	public static final int kSlotIDx = 0;
	public static final int pidLoopTimeout = 30;
	public static final double kpDriveVelocity = 1.5;  //this needs tweaking.  Could be higher or lower
	
	//Encoders
	public static final double encoderCPR = 2048;  //internal Falcon encoders CPR = clicks per revolution
	public static final double chassisWheelDiameterMeters = Units.inchesToMeters(6.0);  //0.1524
	public static final double chassisWheelCircumferenceMeters = Units.inchesToMeters(Math.PI*6.0);  //~0.4785
	public static final double chassisGearRatio = 1 / 10.75;  //per AndyMark KOP documentation
	public static final double distancePerEncoderPulseMeters = chassisWheelCircumferenceMeters * chassisGearRatio / encoderCPR;
	public static final int turnAutoSlotIDx = 1;
	public static final int PID_TURN = 1;
}
