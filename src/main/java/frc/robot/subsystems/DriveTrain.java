// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.InvertType;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
	//TODO: look up "velocity mode" - compensates for slightly different speeds
	//TODO: look up "position mode" (USE THIS??? v good for autonomous)
	//TODO: look up motion profiling
	
	private WPI_TalonFX leftFront = new WPI_TalonFX(Constants.LEFT_FRONT);
	private WPI_TalonFX rightFront = new WPI_TalonFX(Constants.RIGHT_FRONT);
	private WPI_TalonFX leftBack = new WPI_TalonFX(Constants.LEFT_BACK);
	private WPI_TalonFX rightBack = new WPI_TalonFX(Constants.RIGHT_BACK);
	
	// PIDController leftPIDController = new PIDController(Constants.kpDriveVelocity, 0.0, 0.0);
	// PIDController rightPIDController = new PIDController(Constants.kpDriveVelocity, 0.0, 0.0);
	
	
	private DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);
	
	/** Creates a new DriveTrain. */
	public DriveTrain() {
		leftFront.configFactoryDefault();
		rightFront.configFactoryDefault();
		leftBack.configFactoryDefault();
		rightBack.configFactoryDefault();
		
		leftFront.set(ControlMode.PercentOutput, 0);
		rightFront.set(ControlMode.PercentOutput, 0);
		
		/* leftFront.configNeutralDeadband(0.05, 30);
				rightFront.configNeutralDeadband(0.05, 30);
				leftBack.configNeutralDeadband(0.05, 30);
				rightBack.configNeutralDeadband(0.05, 30);*/
		
		leftBack.follow(leftFront);
		rightBack.follow(rightFront);
		
		/*	What does this do?
		leftFront.setSafetyEnabled(false);
		leftBack.setSafetyEnabled(false);
		rightFront.setSafetyEnabled(false);
		rightBack.setSafetyEnabled(false);
		
		//Use this when not using PID control
		leftFront.configOpenloopRamp(0.05);
		leftBack.configOpenloopRamp(0.05);
		rightFront.configOpenloopRamp(0.05);
		rightBack.configOpenloopRamp(0.05);
		
		//Use below for closed loop PID control
		
		//leftFront.configClosedloopRamp(1);
		//rightFront.configClosedloopRamp(1);
		//leftBack.configClosedloopRamp(1);
		//rightBack.configClosedloopRamp(1);
		
		//rightFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
		// leftFront.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30);
		
		// leftFront.selectProfileSlot(0,0);
		//rightFront.selectProfileSlot(0, 0);
		
		//IMPORTANT:	These numbers need tweaking; they were taken from example code
		// leftFront.config_kF(0, 0.045,30); //will need to adjust the 0.045 through testing
		// leftFront.config_kP(0, 0.049, 30); //will need to adjust the 0.049 through testing
		// leftFront.config_kI(0, 0, 30);
		// leftFront.config_kD(0, 0, 30);
		
		// rightFront.config_kF(0, 0.045,30); //will need to adjust the 0.045 through testing
		// rightFront.config_kP(0, 0.049, 30); //will need to adjust the 0.049 through testing
		// rightFront.config_kI(0, 0, 30);
		// rightFront.config_kD(0, 0, 30);
		
		// leftFront.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		// leftFront.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);
		
		// rightFront.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		// rightFront.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);
		
		//Other teams have this the other way?????
		rightFront.setInverted(false);
		rightBack.setInverted(InvertType.FollowMaster);
		leftFront.setInverted(true);
		leftBack.setInverted(InvertType.FollowMaster);
		
		//leftFront.setSensorPhase(false);
		//rightFront.setSensorPhase(false);
		//leftBack.setSensorPhase(false);
		//rightBack.setSensorPhase(false);
		
		leftFront.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		leftBack.setNeutralMode(NeutralMode.Brake);
		rightBack.setNeutralMode(NeutralMode.Brake);
		
		//leftFront.configNominalOutputForward(0, 30);
		//leftFront.configNominalOutputReverse(0, 30);
		//leftFront.configPeakOutputForward(1, 30);
		// leftFront.configPeakOutputReverse(-1, 30);
		
		//This needs tweaking.	The integrated Falcon 500 sensor is 2048 clicks per rotation
		//The wheel is a 6-inch diameter
		//The toughbox gearing is a 10.75:1 reduction
		//The first number in the cruise velocity is sensorUnitsPer100ms
		//The second number is the timeout in milliseconds
		
		//leftFront.configMotionCruiseVelocity(8000, 30);	//8000 taken from sample code.	Needs testing
		//leftFront.configMotionAcceleration(8000,30);	//8000 taken from sample code.	Needs testing
		
		//rightFront.configMotionCruiseVelocity(8000, 30);	//8000 taken from sample code.	Needs testing
		// rightFront.configMotionAcceleration(8000,30);	//8000 taken from sample code.	Needs testing
		
		//leftFront.selectProfileSlot(Constants.kSlotIDx, Constants.kpIDLoopIDx);
		// rightFront.selectProfileSlot(Constants.kSlotIDx, Constants.kpIDLoopIDx);
		
		// zeroSensors();*/
	}
	
	/*
	public void driveWithJoysticks(Joystick driverJoystick, double speed)
	{
		drive.arcadeDrive(driverJoystick.getRawAxis(Constants.GP_LEFT_Y_AXIS) * speed, driverJoystick.getRawAxis(Constants.GP_LEFT_X_AXIS) * speed);
	}*/
	
	/*public void driveForward(double speed) {
		drive.tankDrive(speed, speed);
	}*/
	
	 /* public void arcadeDrive(Joystick driverJoystick, double speed) {
		//drive.arcadeDrive(driverJoystick.getRawAxis(Constants.GP_LEFT_Y_AXIS) * speed, driverJoystick.getRawAxis(Constants.GP_LEFT_X_AXIS) * speed);
			drive.arcadeDrive(-driverJoystick.getY(), driverJoystick.getX());
				drive.feed(); //resets the timer
	}	*/
	
	public void arcadeDrive(double throttle, double rotation) {
		drive.arcadeDrive(throttle, rotation);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	/*
	//Zero the internal sensors/encoders on the leader motors
	public void zeroSensors() {
			leftFront.setSelectedSensorPosition(0, Constants.kpIDLoopIDx,Constants.pidLoopTimeout);
			rightFront.setSelectedSensorPosition(0, Constants.kpIDLoopIDx,Constants.pidLoopTimeout);
			System.out.println("[Falcon Drivetrain Encoders]	All four drive sensors are zeroed.\n");
	}
	
	public void resetToPercentAndZeroDistance() {
		leftFront.getSensorCollection().setIntegratedSensorPosition(0, Constants.pidLoopTimeout);
		rightFront.getSensorCollection().setIntegratedSensorPosition(0, Constants.pidLoopTimeout);
		leftFront.set(ControlMode.PercentOutput, 0);
		rightFront.set(ControlMode.PercentOutput, 0);
	}
	
	//If you use a distance sensor
	public int lowGoalDistanceSensorValue() }
		int distanceSensorInches = 0;
		if (digitalLowGoalDS != null) {
			distanceSensorInches = digitalLowGoalDS.getAverageValue();
			SmartDashboard.putNumber("Digital Low Cargo Goal Distance Sensor raw value = ", distanceSensorInches");
		}
		return DistanceSensorInches;
	}
	
	//get the sensor readings from all four motor encoders
	public double[] getRawReadings(){
			double[] sensorRawList = new double[4];
			sensorRawList[0] = leftFront.getSelectedSensorPosition(0);
			sensorRawList[1] = leftFront.getSelectedSensorVelocity(0);
			sensorRawList[2] = rightFront.getSelectedSensorPosition(0);
			sensorRawList[3] = rightFront.getSelectedSensorVelocity(0);
			return sensorRawList;
	}
	
	public void resetEncoders() {
		leftFront.setSelectedSensorPosition(0);
		rightFront.setSelectedSensorPosition(0);
	}
	
	public void setMotionMagic(double distance, double turnAngle, int cruiseVelocity, int accelerationVelocity, boolean useAuxPID) {
		leftFront.configMotionCruiseVelocity(cruiseVelocity, Constants.pidLoopTimeout);
		leftFront.configMotionAcceleration(accelerationVelocity, Constants.pidLoopTimeout);
		rightFront.configMotionCruiseVelocity(cruiseVelocity, Constants.pidLoopTimeout);
		rightFront.configMotionAcceleration(accelerationVelocity, Constants.pidLoopTimeout);
		
		leftFront.selectProfileSlot(Constants.kSlotIDx, Constants.kpIDLoopIDx);
		rightFront.selectProfileSlot(Constants.kSlotIDx, Constants.kpIDLoopIDx);
		
		if (useAuxPID == false) {
			leftFront.set(ControlMode.MotionMagic, distance);
			rightFront.set(ControlMode.MotionMagic, distance);
		}
		
		else {	//attempting code to turn in an arc
			leftFront.selectProfileSlot(Constants.turnAutoSlotIDx, Constants.PID_TURN);	//what is this?
			rightFront.selectProfileSlot(Constants.turnAutoSlotIDx, Constants.PID_TURN);	//what is this?
			
			leftFront.set(ControlMode.MotionMagic, distance, DemandType.AuxPID,turnAngle);
			rightFront.set(ControlMode.MotionMagic, distance, DemandType.AuxPID,turnAngle);
		}
	}
	
	public boolean isMotionMagicDone (double targetDistanceInNativeUnit, boolean resetToPercentMode)	{
		boolean ret = false;
		double sensorDistance = (leftFront.getSelectedSensorPosition(0) + rightFront.getSelectedSensorPosition(0))/2.0;
		double percentError = 100 * (targetDistanceInNativeUnit - sensorDistance) / targetDistanceInNativeUnit;
		
		if (percentError < 0.3	|| percentError < 0) {
				if (resetToPercentMode == true) {
						leftFront.getSensorCollection().setIntegratedSensorPosition(0, Constants.pidLoopTimeout);
						rightFront.getSensorCollection().setIntegratedSensorPosition(0, Constants.pidLoopTimeout);
						leftFront.set(ControlMode.PercentOutput, 0);
						rightFront.set(ControlMode.PercentOutput, 0);
				}
				return true;
		}
		
		SmartDashboard.putNumber("SensorMagicVel", leftFront.getSelectedSensorVelocity(0));
		return ret;
	}
	*/
	
	public void stop(){
		drive.stopMotor();
	}
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
