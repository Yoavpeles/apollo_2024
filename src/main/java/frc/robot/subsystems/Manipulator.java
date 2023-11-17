// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Manipulator extends SubsystemBase {
  TalonFXConfiguration _taolnConfig = new TalonFXConfiguration();
  TalonFX m_talonFX;
 

 

  public Manipulator() {
    this.m_talonFX = new TalonFX(Constants.Manipulator.followerId);
    // set slot 0 gains

_taolnConfig.Slot0.kV = Constants.Manipulator.kF;
_taolnConfig.Slot0.kP = Constants.Manipulator.kP;


 
m_talonFX.getConfigurator().apply(_taolnConfig, 0.050);
  

 
  

}
}
