// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.ArcadeDrive;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	//Subsystems
	public static DriveTrain m_driveTrain = new DriveTrain();
	public static Joystick driverJoystick = new Joystick(Constants.DRIVERJOYSTICK_NUMBER);
	public static Joystick operatorJoystick = new Joystick(Constants.OPERATORJOYSTICK_NUMBER);
	public final Indexer m_indexer = new Indexer();
	// private final Intake m_intake = new Intake();
	// private final Shooter m_shooter = new Shooter();
	public static Gyro m_gyro = new Gyro();
	
	// autonomous command
	private final DriveForwardTimed m_driveForwardTimed = new DriveForwardTimed(m_driveTrain);
	
	public RobotContainer() {
		configureButtonBindings();
		
		// set default drivetrain command
		m_driveTrain.setDefaultCommand(
			new ArcadeDrive(m_driveTrain, ()->-driverJoystick.getX(), ()->driverJoystick.getY())
		);
	}
	
	private void configureButtonBindings() {
		// idec rn - no buttons for you
		/*
		//Intake Cargo
		// new JoystickButton(driverJoystick, Constants.kGamepadBumperRight)
		//	 .whileHeld(() -> m_intake.collect(Constants.INTAKE_SPEED))
		//	 .whenReleased(() ->m_intake.stop());
		
		// //Eject Cargo
		// new JoystickButton(driverJoystick, Constants.kGamepadBumperLeft)
		//	 .whileHeld(() -> m_intake.eject(Constants.INTAKE_SPEED))
		//	 .whenReleased(() ->m_intake.stop());
		
		// //Index Cargo toward shooter
		// new JoystickButton(driverJoystick, Constants.gamepadAButton)
		// 	.whileHeld(() -> m_indexer.indexCargo(Constants.INDEX_SPEED))
		// 	.whenReleased(() -> m_indexer.stop());
		
		// //Dedex Cargo toward intake
		// new JoystickButton(driverJoystick, Constants.gamepadBButton)
		// .whileHeld(() -> m_indexer.outdexCargo(Constants.INDEX_SPEED))
		// .whenReleased(() -> m_indexer.stop());
		
		// //SpinUp for Shooting
		// new JoystickButton(operatorJoystick, Constants.gamepadXButton)
		// .whileHeld(() -> m_shooter.spinUp(Constants.SHOOTER_SPINUP_SPEED))
		// .whenReleased(() -> m_shooter.stop());
		
		// //SpinDown for Shooting
		// new JoystickButton(operatorJoystick, Constants.gamepadYButton)
		// .whileHeld(() -> m_shooter.spinDown(Constants.SHOOTER_SPINDOWN_SPEED))
		// .whenReleased(() -> m_shooter.stop());
		
		// //90 RIGHT Turn
		// new JoystickButton(operatorJoystick, Constants.gamepadAButton)
		// .whenPressed(new QuickTurnCommand(m_driveTrain, m_gyro, 90));
		
		// m_chooser.addOption("Drive Forward Timed", new DriveForwardTimed(m_driveTrain));
		// m_chooser.setDefaultOption("Drive Forward Timed", new DriveForwardTimed(m_driveTrain));
		// SmartDashboard.putData(m_chooser);
		*/
	}
	
	// returns the command to run in autonomous
	public Command getAutonomousCommand() {
		// TODO: actual autonomous code
		return m_driveForwardTimed;
	}
}
