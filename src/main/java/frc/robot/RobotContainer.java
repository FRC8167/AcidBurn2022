// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.QuickTurnCommand;
import frc.robot.commands.ArcadeDrive;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //Subsystems
  public static DriveTrain m_driveTrain = new DriveTrain();
  public static Joystick driverJoystick = new Joystick(Constants.DRIVERJOYSTICK_NUMBER);
  public static Joystick operatorJoystick = new Joystick(Constants.OPERATORJOYSTICK_NUMBER);
  public final Indexer m_indexer = new Indexer();
  private final Intake m_intake = new Intake();
  //private final Shooter m_shooter = new Shooter();
  public static Gyro m_gyro = new Gyro();


  //Commands
  //private final ArcadeDrive arcadeDrive;
  private final DriveForwardTimed m_driveForwardTimed = new DriveForwardTimed(m_driveTrain);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    configureButtonBindings();
    //arcadeDrive = new ArcadeDrive(m_driveTrain);

    m_driveTrain.setDefaultCommand(
    (new ArcadeDrive(m_driveTrain, ()->-driverJoystick.getY(), ()->driverJoystick.getX())
    )
    );

  
   
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {


   
  //Intake Cargo
  new JoystickButton(driverJoystick, Constants.kGamepadBumperRight)
    .whileHeld(() -> m_intake.collect(Constants.INTAKE_SPEED))
    .whenReleased(() ->m_intake.stop());

  //Eject Cargo
  new JoystickButton(driverJoystick, Constants.kGamepadBumperLeft)
    .whileHeld(() -> m_intake.eject(Constants.INTAKE_SPEED))
    .whenReleased(() ->m_intake.stop());

  //Index Cargo toward shooter
  new JoystickButton(driverJoystick, Constants.gamepadAButton)
    .whileHeld(() -> m_indexer.indexCargo(Constants.INDEX_SPEED))
    .whenReleased(() -> m_indexer.stop());

  //Dedex Cargo toward intake
  new JoystickButton(driverJoystick, Constants.gamepadBButton)
  .whileHeld(() -> m_indexer.outdexCargo(Constants.INDEX_SPEED))
  .whenReleased(() -> m_indexer.stop());

  //SpinUp for Shooting
 /* new JoystickButton(operatorJoystick, Constants.gamepadXButton)
  .whileHeld(() -> m_shooter.spinUp(Constants.SHOOTER_SPINUP_SPEED))
  .whenReleased(() -> m_shooter.stop());

  //SpinDown for Shooting
  new JoystickButton(operatorJoystick, Constants.gamepadYButton)
  .whileHeld(() -> m_shooter.spinDown(Constants.SHOOTER_SPINDOWN_SPEED))
  .whenReleased(() -> m_shooter.stop());*/
  
  //90 RIGHT Turn
  new JoystickButton(operatorJoystick, Constants.gamepadAButton)
  .whenPressed(new QuickTurnCommand(m_driveTrain, 90));

  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // This command needs to be tweaked for Rapid React Game
    return m_driveForwardTimed;
  }
}
