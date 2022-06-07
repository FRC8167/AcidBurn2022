// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.*;
// import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.MoveBelt;
import frc.robot.commands.QuickTurnCommand;
import frc.robot.commands.SetClimberDistance;
import frc.robot.commands.RunBelt;
import frc.robot.controllers.DualXbox;
import frc.robot.controllers.InputController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveForwardFeet;

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
	
	private final SendableChooser<Command> autoCommandSelector = new SendableChooser<>();
	
	// controller
	private final InputController controller;
	
	public RobotContainer() {
		// this can be easily changed to anything supporting the InputController interface
		controller = new DualXbox(Constants.DRIVERJOYSTICK_NUMBER, Constants.OPERATORJOYSTICK_NUMBER);
		
		camera = new Camera();
		
		// TODO: put these in constants
		camera.setFPS(20);
		camera.setResolution(640, 480);
		
		configureButtonBindings();
		addAutoCommands();
		SmartDashboard.putData(autoCommandSelector);
		
		// set default drivetrain command
		driveTrain.setDefaultCommand(
			new ArcadeDrive(driveTrain, controller::getForwardSpeed, controller::getTurnSpeed)
		);
		
		m_climber.setDefaultCommand(
			new SetClimberDistance(m_climber, 0)
		);
		
		m_climber.getDefaultCommand().schedule();
	}
	
	private void configureButtonBindings() {
		// 180 degree RIGHT Turn
		controller.getQuickTurnRightButton().whenPressed(new QuickTurnCommand(driveTrain, 180));
		// controller.getQuickTurnRightButton().whenPressed(new DriveForwardFeet(driveTrain, 3));
		
		//raise climber some distance (5 rotations)
		controller.getMotionMagicRaiseClimberButton().whenPressed(new SetClimberDistance(m_climber, 12.5));
		controller.getMotionMagicLowerClimberButton().whenPressed(new SetClimberDistance(m_climber, 0));
		
		controller.getBeltTurnButton().whenPressed(new RunBelt(belt, 1000000));
		
		controller.getBeltForwardButton().whileHeld(new MoveBelt(belt, 0.5));
		controller.getBeltBackwardButton().whileHeld(new MoveBelt(belt, -0.2));
	}
	
	private void addAutoCommands() {
		autoCommandSelector.addOption(
			"2 balls (Ball 1) (UNTESTED)", 
			new SequentialCommandGroup(
				new RunBelt(belt, 1000000),
				new DriveForwardFeet(driveTrain, 4.875),
				new QuickTurnCommand(driveTrain, 25),
				new ParallelCommandGroup(
					new DriveForwardFeet(driveTrain, 5.156),
					new RunBelt(belt, 1000000)
				),
				new DriveForwardFeet(driveTrain, -5.156),
				new QuickTurnCommand(driveTrain, -25),
				new DriveForwardFeet(driveTrain, -4.875),
				new RunBelt(belt, 1000000)
			)
		);
		
		autoCommandSelector.setDefaultOption(
			"2 balls (Ball 2)", 
			new SequentialCommandGroup(
				new RunBelt(belt, 1000000),
				new DriveForwardFeet(driveTrain, 4.875),
				new QuickTurnCommand(driveTrain, 65),
				new ParallelCommandGroup(
					new DriveForwardFeet(driveTrain, 6),
					new RunBelt(belt, 1000000)
				),
				new DriveForwardFeet(driveTrain, -6),
				new QuickTurnCommand(driveTrain, -65),
				new DriveForwardFeet(driveTrain, -4.875),
				new RunBelt(belt, 1000000)
			)
		);
		
		autoCommandSelector.addOption(
			"2 balls (Ball 3) (NOT TESTED)", 
			new SequentialCommandGroup(
				new RunBelt(belt, 1000000),
				new DriveForwardFeet(driveTrain, 1.5),
				new QuickTurnCommand(driveTrain, -21),
				new InstantCommand(() -> System.out.println("got here ball 3")),
				new ParallelCommandGroup(
					new DriveForwardFeet(driveTrain, 6.2),
					new RunBelt(belt, 1000000)
				),
				new DriveForwardFeet(driveTrain, -6.2),
				new QuickTurnCommand(driveTrain, 21),
				new DriveForwardFeet(driveTrain, -1.5),
				new RunBelt(belt, 1000000)
			)
		);
		
		
		autoCommandSelector.addOption(
			"DEFENSE (TODO: TEST)", 
			new SequentialCommandGroup(
				new DriveForwardFeet(driveTrain, 5),
				new QuickTurnCommand(driveTrain, 105),
				new DriveForwardFeet(driveTrain, 15),
				new QuickTurnCommand(driveTrain, 70),
				new DriveForwardFeet(driveTrain, 15)
			)
		);
		
		
		autoCommandSelector.addOption(
			"Launch and taxi", 
			new SequentialCommandGroup(
				new RunBelt(belt, 100000),
				new DriveForwardFeet(driveTrain, 8)
			)
		);
		
		autoCommandSelector.addOption(
			"just taxi", 
			new DriveForwardFeet(driveTrain, 10)
		);
	}
	
	// returns the command to run in autonomous
	public Command getAutonomousCommand() {
		return autoCommandSelector.getSelected();
	}
	
	public void runTest() {
		driveTrain.arcadeDrive(Constants.AUTONOMOUS_SPEED, 0.0);
	}
}
