// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Manipulator extends SubsystemBase {
  TalonFXConfiguration _taolnConfig = new TalonFXConfiguration();
  TalonFX m_talonFX;
 
  public enum ManipulatorState {
    COLLECT(Constants.Manipulator.collectRPM),
    SHOOT(Constants.Manipulator.shootRPM),
    SHOOTLOW(Constants.Manipulator.shootLowRPM),
    KEEPIN(Constants.Manipulator.keepInRPM),
    OFF(0);

    public final double rpm;

    private ManipulatorState(double rpm) {
        this.rpm = rpm;
    }
  }

  public Manipulator() {
    this.m_talonFX = new TalonFX(Constants.Manipulator.followerId);
    // set slot 0 gains

_taolnConfig.Slot0.kV = Constants.Manipulator.kF;
_taolnConfig.Slot0.kP = Constants.Manipulator.kP;

m_talonFX.getConfigurator().apply(_taolnConfig, 0.050);
  
}

public void setState(ManipulatorState state) {
    if (state == ManipulatorState.OFF)
      m_talonFX.setControl(new DutyCycleOut(0));
    else
        setTargetRPM(state.rpm);
}

private void setTargetRPM(double rpm) {
  m_talonFX.setControl(new VelocityVoltage (rpm / Constants.Manipulator.kVelocityConversionFactor).withSlot(0));

}

 private double getTargetRPM() {
        return m_talonFX.getClosedLoopReference().getValue() *
                Constants.Manipulator.kVelocityConversionFactor;
    }

    private double getRPM() {
      return m_talonFX.getRotorVelocity().getValue() *
              Constants.Manipulator.kVelocityConversionFactor;
  }


    private void updateConfig() {
      _taolnConfig.Slot0.kV = SmartDashboard.getNumber(
              "Manipulator kF",
              Constants.Manipulator.kF);
      _taolnConfig.Slot0.kP = SmartDashboard.getNumber(
              "Manipulator kP",
              Constants.Manipulator.kP);

  

m_talonFX.getConfigurator().apply(_taolnConfig, 0.050);  }


}
