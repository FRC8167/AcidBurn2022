// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Belt extends SubsystemBase {
	private final WPI_TalonFX intakeMotor;
	private DigitalInput intakeSwitch;
	
	/* constructor */
	public Belt() {
		// creates and configures the climber motor
		intakeMotor = new WPI_TalonFX(Constants.INTAKE_MOTOR);
		configmotor();
		
		intakeSwitch = new DigitalInput(Constants.intakeSensorPort);
		
		//Important:  Must have zeroSensors() function
		zeroSensors();
	}
	
	private void configmotor() {
		// TODO: make sure this is all optimized and not redundant
		//Set motor to factory default for safety reasons.
		intakeMotor.configFactoryDefault();
		
		intakeMotor.setNeutralMode(NeutralMode.Brake);
		intakeMotor.set(ControlMode.PercentOutput, 0);
		intakeMotor.configNeutralDeadband(0, 30);
		
		intakeMotor.configClosedloopRamp(0.5);
		intakeMotor.configOpenloopRamp(0.5);
		
		intakeMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, Constants.pidLoopTimeout);
		intakeMotor.selectProfileSlot(0, 0);
		
		intakeMotor.config_kF(0, 0.045, 30);  //.045 needs to be tweaked
		intakeMotor.config_kP(0, 0.049, 30);  //.049 needs to be tweaked
		intakeMotor.config_kI(0, 0, 30);
		intakeMotor.config_kD(0, 0, 30);
		
		intakeMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		intakeMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,30);
		
		intakeMotor.setInverted(false);
		intakeMotor.setSensorPhase(false);
		
		intakeMotor.configNominalOutputForward(0, 30);
		intakeMotor.configNominalOutputReverse(0,30);
		intakeMotor.configPeakOutputForward(1, 30);
		intakeMotor.configPeakOutputReverse(-1, 30);
		
		intakeMotor.selectProfileSlot(Constants.kSlotIDx, Constants.kpIDLoopIDx);
	}
	
	//This is used to reset (or zero) the Quadrature encoders built into the Falcon 500 motors
	public void zeroSensors() {
		// TODO: do we need Constants.kpIDLoopIDx???
		intakeMotor.setSelectedSensorPosition(0, Constants.kpIDLoopIDx, Constants.pidLoopTimeout);
	}
	
	public boolean isBallInTheThing() {
		return !intakeSwitch.get();
	}
	
	
	public void drivePercentOutput(double power) {
		intakeMotor.set(ControlMode.PercentOutput, power);
	}
	
	public void setMotionMagic(double distance, int cruiseVelocity, int accelerationVelocity) {
		//Set the Acceleration and Cruise Velocity for Motion Magic
		// ~21950 ticks per 100ms is max speed
		intakeMotor.configMotionCruiseVelocity(cruiseVelocity, Constants.pidLoopTimeout);
		intakeMotor.configMotionAcceleration(accelerationVelocity, Constants.pidLoopTimeout);
		
		intakeMotor.set(ControlMode.MotionMagic, distance);
	}
	
	public boolean isMotionMagicDone (double targetDistanceInNativeUnit) {
		double sensorDistance = intakeMotor.getSelectedSensorPosition(0);
		double percentError = 100 * (targetDistanceInNativeUnit - sensorDistance) / targetDistanceInNativeUnit;
		
		return percentError < Constants.beltPercentError;
	}
	
	public void stop() {
		intakeMotor.getSensorCollection().setIntegratedSensorPosition(0, Constants.pidLoopTimeout);
		intakeMotor.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
