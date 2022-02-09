// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
	//Create TalonFx objects to control the motors
	public static WPI_TalonFX leftShooterMotor = new WPI_TalonFX(Constants.LEFT_SHOOTER_MOTOR);
	public static WPI_TalonFX rightShooterMotor = new WPI_TalonFX(Constants.RIGHT_SHOOTER_MOTOR);
	/** Creates a new Shooter. */
	public Shooter() {
		//Set both motors to Coast mode.
		leftShooterMotor.setNeutralMode(NeutralMode.Coast);
		rightShooterMotor.setNeutralMode(NeutralMode.Coast);
		
		//Invert the right motor.	
		//This will have to be tested depending on mounting configuration
		rightShooterMotor.setInverted(true);
	}

	//spinUp:	spin the motors to prepare to shoot the cargo into a goal
	public void spinUp(double power) {
		//Apply the voltage to both motors.	Note the right motor is inverted
		leftShooterMotor.set(ControlMode.PercentOutput, -power);
		rightShooterMotor.set(ControlMode.PercentOutput, -power);
	}

	//spinDown:	sping the motors in reverse to bring the balls back into the robot
	public void spinDown(double power){
		leftShooterMotor.set(ControlMode.PercentOutput, power);
		rightShooterMotor.set(ControlMode.PercentOutput, power);
	}
	
	public void stop(){
		leftShooterMotor.set(ControlMode.PercentOutput, 0);
		rightShooterMotor.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
