/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc4388.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Horn extends SubsystemBase {
  private Solenoid m_solenoid;
  /**
   * Creates a new Horn.
   */
  public Horn(Solenoid solenoid) {
    m_solenoid = solenoid;
  }

  public void hornSet(boolean arg) {
    m_solenoid.set(arg);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
