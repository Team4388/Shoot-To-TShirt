/* Dumb Command Version, Smart Subsytem Version */
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4388.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wbilib.Solnoid;

public class ShootTube extends SubsystemBase {
    /** Creates a new ShootTube. */
    Solenoid[] m_solenoids;
    int m_cycleCount = 0;
    int m_maxCount;
    public ShootTube(Solenoid[] solenoids) {
        m_solenoids = solenoids;
        m_maxCount = m_solenoids.length;
    }
    /* 
    Functions designed for the DPAD left and right buttions to
    Cycle the tube to be shooting out of
    */
    public void CycleUp() {
        m_cycleCount++;
        if (m_cycleCount >= m_maxCount || m_cycleCount < 0) {
            m_cycleCount = 0;
        }  
    }
    public void CycleDown() {
        m_cycleCount--;
        if (m_cycleCount >= m_maxCount || m_cycleCount < 0) {
            m_cycleCount = 0;
        }   
    }
    /*
    Normal Shoot Tube and Normal Shoot Tube Index Functions
    */
    public void ShootTubeSet(Boolean arg) {
        m_solenoids[CycleCount].set(arg);
        if (arg == false) {
            CycleCount++;
            if (m_cycleCount >= m_maxCount || m_cycleCount < 0) {
                m_cycleCount = 0;
            }
        }
    }
    public void ShootTubeIndex(Boolean arg, int i) {
        m_solenoids[i].set(arg);
    }
    /*
    Just Normal Shoot Tube But it Shoots all of the tubes
    Designed for DPAD Down
    */
    public void ShootTubeALL(Boolean arg) {
        for (Solenoid x : m_solenoids) {
            x.set(arg);
        }
        m_cycleCount = 0;
    }
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}