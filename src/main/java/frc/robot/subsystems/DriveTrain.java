// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
	private WPI_TalonFX leftFront = new WPI_TalonFX(Constants.LEFT_FRONT);
	private WPI_TalonFX rightFront = new WPI_TalonFX(Constants.RIGHT_FRONT);
	private WPI_TalonFX leftBack = new WPI_TalonFX(Constants.LEFT_BACK);
	private WPI_TalonFX rightBack = new WPI_TalonFX(Constants.RIGHT_BACK);
	
	private DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);
	
	private Pigeon2 pigeon = new Pigeon2(Constants.PIGEON_CANID);

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
		
		rightBack.setInverted(true);
		rightFront.setInverted(true);
		
		leftBack.follow(leftFront);
		rightBack.follow(rightFront);
	}
	
	public void configMotorsForMotionMagic() {
		//TODO make this
		leftFront.setSelectedSensorPosition(0);
		rightFront.setSelectedSensorPosition(0);
		leftBack.setSelectedSensorPosition(0);
		rightBack.setSelectedSensorPosition(0);
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
	
	
	public double getYaw() {
		return pigeon.getYaw();
	}
	
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
