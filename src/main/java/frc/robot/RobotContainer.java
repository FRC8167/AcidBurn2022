// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.BeltBallThing;
// import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Climber;
// import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.MoveBelt;
import frc.robot.commands.QuickTurnCommand;
import frc.robot.commands.SetClimberDistance;
import frc.robot.commands.IntakeOuttakeBall;
import frc.robot.controllers.DualXbox;
import frc.robot.controllers.InputController;
import frc.robot.commands.ArcadeDrive;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	//Subsystems
	private DriveTrain driveTrain = new DriveTrain();
	private Gyro gyro = new Gyro();
	
	// public final Indexer m_indexer = new Indexer();
	// private final Intake m_intake = new Intake();
	// private final Shooter m_shooter = new Shooter();
	private final Climber m_climber = new Climber();
	private final BeltBallThing belt = new BeltBallThing();
	
	// controller
	private InputController controller;

	//climber command
	//what goes here???
	
	public RobotContainer() {
		// this can be easily changed to anything supporting the InputController interface
		controller = new DualXbox(Constants.DRIVERJOYSTICK_NUMBER, Constants.OPERATORJOYSTICK_NUMBER);
		
		
		configureButtonBindings();
		
		// set default drivetrain command
		driveTrain.setDefaultCommand(
			new ArcadeDrive(driveTrain, controller::getForwardSpeed, controller::getTurnSpeed)
		);
	}
	
	private void configureButtonBindings() {
		// idec rn - no more buttons for you
		//TODO: get all these to work with the new InputController interface
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
		// .whenReleased(() -> m_indexer.stop()); */
		
		// Raise Climber
		////
		
		
		/*/ //SpinDown for Shooting
		// new JoystickButton(operatorJoystick, Constants.gamepadYButton)
		// .whileHeld(() -> m_shooter.spinDown(Constants.SHOOTER_SPINDOWN_SPEED))
		// .whenReleased(() -> m_shooter.stop());
		
		// Setup SmartDashboard options
		// m_chooser.addOption("Drive Forward Timed", new DriveForwardTimed(m_driveTrain));
		// m_chooser.setDefaultOption("Drive Forward Timed", new DriveForwardTimed(m_driveTrain));
		// SmartDashboard.putData(m_chooser);
		*/
		
		// NOTE: this is how buttons work in the new InputController interface
		//90 RIGHT Turn
		controller.getQuickTurnRightButton().whenPressed(new QuickTurnCommand(driveTrain, gyro, 90));
		
		//raise climber some distance (5 rotations)
		controller.getMotionMagicRaiseClimberButton().whenPressed(new SetClimberDistance(m_climber, 122880));
		//lower climber
		controller.getMotionMagicLowerClimberButton().whenPressed(new SetClimberDistance(m_climber, 0));
		
		controller.getBeltTurnButton().whenPressed(new IntakeOuttakeBall(belt, 1000000));
		
		controller.getBeltForwardButton().whileHeld(new MoveBelt(belt, 0.5));
		controller.getBeltBackwardButton().whileHeld(new MoveBelt(belt, -0.2));
	}
	
	// returns the command to run in autonomous
	public Command getAutonomousCommand() {
		// TODO: actual autonomous code
		//return m_choose.getSelected();
		return new SequentialCommandGroup(
			new MoveBelt(belt, 0.8, 5),
			new DriveForwardTimed(driveTrain, 5, Constants.AUTONOMOUS_SPEED)
		);
	}
	
	public void runTest() {
		driveTrain.arcadeDrive(Constants.AUTONOMOUS_SPEED, 0.0);
	}
}
