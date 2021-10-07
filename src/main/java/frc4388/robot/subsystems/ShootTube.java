// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4388.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wbilib.Solnoid;

public class ShootTube extends SubsystemBase {
  /** Creates a new ShootTube. */
  public ShootTube() {
    m_solenoid = new solenoid(0);
  }

  public void ShootTubeset(boolean arg) {
    m_solenoid.set(arg);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
