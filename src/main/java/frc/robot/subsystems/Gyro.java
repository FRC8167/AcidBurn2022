// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
	/** Creates a new Gyro. */
	private final ADXRS450_Gyro gyroSensor;
	
	public Gyro() {
		gyroSensor = new ADXRS450_Gyro();
		// TODO: CALLING THIS BREAKS LITERALLY EVERYTHING!!!
		// FINDING THIS OUT TOOK LITERALLY 6 HOURS!!!!
		// WHYYYYYYYYYY??!??!?!?!?
		gyroSensor.calibrate();
	}
	
	public double getAngle(){
		return gyroSensor.getAngle();
	}
	
	public void reset() {
		gyroSensor.reset();
	}
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}