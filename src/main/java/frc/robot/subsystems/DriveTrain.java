// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
	
	private WPI_TalonFX leftFront = new WPI_TalonFX(Constants.LEFT_FRONT);
	private WPI_TalonFX rightFront = new WPI_TalonFX(Constants.RIGHT_FRONT);
	private WPI_TalonFX leftBack = new WPI_TalonFX(Constants.LEFT_BACK);
	private WPI_TalonFX rightBack = new WPI_TalonFX(Constants.RIGHT_BACK);
	
	private DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);

	/** Creates a new DriveTrain. */
	public DriveTrain() {
		//Set all motors to factory defaults for safety
		leftFront.configFactoryDefault();
		rightFront.configFactoryDefault();
		leftBack.configFactoryDefault();
		rightBack.configFactoryDefault();

		//Set all motors to brake mode
		leftFront.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		leftBack.setNeutralMode(NeutralMode.Brake);
		rightBack.setNeutralMode(NeutralMode.Brake);

		leftBack.follow(leftFront);
		rightBack.follow(rightFront);
	}
	
	public void arcadeDrive(double throttle, double rotation) {
		drive.arcadeDrive(throttle, rotation);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void stop() {
		drive.stopMotor();
	}
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
