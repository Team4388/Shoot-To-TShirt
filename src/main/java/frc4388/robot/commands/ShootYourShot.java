// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4388.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootYourShot extends CommandBase {
  private Solenoid[] m_solenoids = {};
  private int counter;

  /** Creates a new ShootYourShot
   *. */
  public ShootYourShot(Solenoid[] solenoids) {
    m_solenoids = solenoids;
    counter = 0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShootTube.ShootTubeSet(m_solenoids[counter]);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    counter += 1;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
