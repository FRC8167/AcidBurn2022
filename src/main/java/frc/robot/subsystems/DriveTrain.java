// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
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
		configMotors();
	}
	
	public void configMotors() {
		//TODO make this
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
		
		// invert these motors
		rightBack.setInverted(true);
		rightFront.setInverted(true);
		
		// limit accelleration
		leftFront.configOpenloopRamp(0.5);
		rightFront.configOpenloopRamp(0.5);
		leftFront.configClosedloopRamp(0.5);
		rightFront.configClosedloopRamp(0.5);
		
		leftFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
		leftFront.selectProfileSlot(0, 0);
		rightFront.selectProfileSlot(0, 0);
		
		leftFront.config_kF(0, 0.045);
		leftFront.config_kP(0, 0.049);
		leftFront.config_kI(0, 0);
		leftFront.config_kD(0, 0);
		rightFront.config_kF(0, 0.045);
		rightFront.config_kP(0, 0.049);
		rightFront.config_kI(0, 0);
		rightFront.config_kD(0, 0);
		
		rightFront.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		rightFront.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);
		leftFront.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		leftFront.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);
		
		rightFront.configNominalOutputForward(0);
		leftFront.configNominalOutputForward(0);
		rightFront.configNominalOutputReverse(0);
		leftFront.configNominalOutputReverse(0);
		
		rightFront.configPeakOutputForward(1);
		leftFront.configPeakOutputForward(1);
		rightFront.configPeakOutputReverse(-1);
		leftFront.configPeakOutputReverse(-1);
		
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
	
	
	public void setMotionMagic(double distance, double maxVelocity, double maxAcceleration) {
		drive.setSafetyEnabled(false);
		
		leftFront.getSensorCollection().setIntegratedSensorPosition(0, 30);
		rightFront.getSensorCollection().setIntegratedSensorPosition(0, 30);
		
		rightFront.configMotionCruiseVelocity(maxVelocity);
		leftFront.configMotionCruiseVelocity(maxVelocity);
		
		rightFront.configMotionAcceleration(maxAcceleration);
		leftFront.configMotionAcceleration(maxAcceleration);
		
		rightFront.set(ControlMode.MotionMagic, -distance);
		leftFront.set(ControlMode.MotionMagic, -distance);
	}
	
	public void stopMotionMagic() {
		leftFront.set(ControlMode.PercentOutput, 0);
		rightFront.set(ControlMode.PercentOutput, 0);
		
		drive.setSafetyEnabled(true);
	}
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
	
	public boolean isMagicMotionDone(double distanceTicks) {
		double sensorDistance = rightFront.getSelectedSensorPosition(0);
		double percentError = 100 * (-distanceTicks - sensorDistance) / -distanceTicks;
		
		return (distanceTicks < 14000 && percentError < 5) || percentError < 1 ;
	}
}
