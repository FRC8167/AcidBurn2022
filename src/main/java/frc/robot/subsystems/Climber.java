// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
	public final WPI_TalonFX climberMotor;
	
	/* constructor */
	public Climber() {
		// creates and configures the climber motor
		climberMotor = new WPI_TalonFX(Constants.CLIMBER_MOTOR);
		configmotor();
		
		//Important:  Must have zeroSensors() function
		zeroSensors();
	}
	
	private void configmotor() {
		// TODO: make sure this is all optimized and not redundant
		//Set motor to factory default for safety reasons.
		climberMotor.configFactoryDefault();
		
		climberMotor.setNeutralMode(NeutralMode.Brake);
		climberMotor.set(ControlMode.PercentOutput, 0);
		climberMotor.configNeutralDeadband(0, 30);
		
		climberMotor.configClosedloopRamp(0.5);
		climberMotor.configOpenloopRamp(0.5);
		
		climberMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, Constants.pidLoopTimeout);
		climberMotor.selectProfileSlot(0, 0);
		
		climberMotor.config_kF(0, 0.045, 30);  //.045 needs to be tweaked
		climberMotor.config_kP(0, 0.049, 30);  //.049 needs to be tweaked
		climberMotor.config_kI(0, 0, 30);
		climberMotor.config_kD(0, 0, 30);
		
		climberMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		climberMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10,30);
		
		climberMotor.setInverted(false);
		climberMotor.setSensorPhase(false);
		
		climberMotor.configNominalOutputForward(0, 30);
		climberMotor.configNominalOutputReverse(0,30);
		climberMotor.configPeakOutputForward(1, 30);
		climberMotor.configPeakOutputReverse(-1, 30);
		
		climberMotor.selectProfileSlot(Constants.kSlotIDx, Constants.kpIDLoopIDx);
	}
	
	//This is used to reset (or zero) the Quadrature encoders built into the Falcon 500 motors
	public void zeroSensors() {
		// TODO: do we need Constants.kpIDLoopIDx???
		climberMotor.setSelectedSensorPosition(0, Constants.kpIDLoopIDx, Constants.pidLoopTimeout);
	}
	
	
	public void testClimber(double power) {
		climberMotor.set(ControlMode.PercentOutput, power);
	}
	
	public void setMotionMagic(double distance, int cruiseVelocity, int accelerationVelocity) {
		//Set the Acceleration and Cruise Velocity for Motion Magic
		// ~21950 ticks per 100ms is max speed
		climberMotor.configMotionCruiseVelocity(cruiseVelocity, Constants.pidLoopTimeout);
		climberMotor.configMotionAcceleration(accelerationVelocity, Constants.pidLoopTimeout);
		
		climberMotor.set(ControlMode.MotionMagic, distance);
	}
	
	public boolean isMotionMagicDone (double targetDistanceInNativeUnit) {
		double sensorDistance = climberMotor.getSelectedSensorPosition(0);
		double percentError = 100 * (targetDistanceInNativeUnit - sensorDistance) / targetDistanceInNativeUnit;
		
		return percentError < 0.3 || percentError < 0;
	}
	
	public void stop() {
		climberMotor.getSensorCollection().setIntegratedSensorPosition(0, Constants.pidLoopTimeout);
		climberMotor.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
