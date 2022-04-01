// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Camera;
// import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Climber;
// import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.MoveBelt;
import frc.robot.commands.QuickTurnCommand;
import frc.robot.commands.SetClimberDistance;
import frc.robot.commands.RunBelt;
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
	// private Gyro gyro = new Gyro();
	
	public final Climber m_climber = new Climber();
	private final Belt belt = new Belt();
	private final Camera camera;
	
	// controller
	private final InputController controller;
	
	public RobotContainer() {
		// this can be easily changed to anything supporting the InputController interface
		controller = new DualXbox(Constants.DRIVERJOYSTICK_NUMBER, Constants.OPERATORJOYSTICK_NUMBER);
		
		camera = new Camera();
		
		// TODO: put these in constants
		camera.setFPS(20);
		camera.setResolution(320, 240);
		
		configureButtonBindings();
		
		// set default drivetrain command
		driveTrain.setDefaultCommand(
			new ArcadeDrive(driveTrain, controller::getForwardSpeed, controller::getTurnSpeed)
		);
		
		m_climber.setDefaultCommand(
			new SetClimberDistance(m_climber, 0)
		);
		
		m_climber.getDefaultCommand().schedule();
		
		// System.out.println(m_climber.getCurrentCommand());
	}
	
	private void configureButtonBindings() {
		// 180 degree RIGHT Turn
		controller.getQuickTurnRightButton().whenPressed(new QuickTurnCommand(driveTrain, 180));
		
		//raise climber some distance (5 rotations)
		controller.getMotionMagicRaiseClimberButton().whenPressed(new SetClimberDistance(m_climber, 15));
		controller.getMotionMagicLowerClimberButton().whenPressed(new SetClimberDistance(m_climber, 0));
		
		controller.getBeltTurnButton().whenPressed(new RunBelt(belt, 1000000));
		
		controller.getBeltForwardButton().whileHeld(new MoveBelt(belt, 0.5));
		controller.getBeltBackwardButton().whileHeld(new MoveBelt(belt, -0.2));
	}
	
	// returns the command to run in autonomous
	public Command getAutonomousCommand() {
		//return m_choose.getSelected();
		return new SequentialCommandGroup(
			new RunBelt(belt, 1000000),
			new DriveForwardTimed(driveTrain, 2.5, 0.4),
			new QuickTurnCommand(driveTrain, 15),
			new ParallelCommandGroup(
				new DriveForwardTimed(driveTrain, 3, Constants.AUTONOMOUS_SPEED),
				new RunBelt(belt, 1000000)
			),
			new DriveForwardTimed(driveTrain, 3, -Constants.AUTONOMOUS_SPEED),
			new QuickTurnCommand(driveTrain, -15),
			new DriveForwardTimed(driveTrain, 2.5, -0.4),
			new RunBelt(belt, 1000000)
		);
	}
	
	public void runTest() {
		driveTrain.arcadeDrive(Constants.AUTONOMOUS_SPEED, 0.0);
	}
}
