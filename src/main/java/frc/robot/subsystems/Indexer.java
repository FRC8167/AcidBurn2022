// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  public static WPI_TalonFX indexMotor = new WPI_TalonFX(Constants.INDEXER_MOTOR);

  public Indexer() {

    indexMotor.setSafetyEnabled(false);
    indexMotor.configOpenloopRamp(0.05);
    indexMotor.setInverted(false); //this needs to be confirmed based on mounting configuration

  }

  

  public void indexCargo(double power){
    indexMotor.set(ControlMode.PercentOutput, power);
  }

  public void outdexCargo(double power){
    indexMotor.set(ControlMode.PercentOutput, -power);
  }


public void stop() {
  indexMotor.set(ControlMode.PercentOutput,0);;
}

@Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
