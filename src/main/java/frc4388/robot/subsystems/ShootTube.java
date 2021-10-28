// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc4388.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc4388.utility.DPrint;
import frc4388.robot.Constants.DebugConstants;
import edu.wpi.first.wbilib.Solnoid;

DPrint DPrinter = new DPrint(DebugConstants.TYPE_INFO) 
public class ShootTube extends SubsystemBase {
    Solenoid[] m_solenoids;
    int m_cycleCount = 0;
    int m_maxCount;
    public ShootTube(Solenoid[] solenoids) {
        m_solenoids = solenoids;
        m_maxCount = m_solenoids.length;
    }
    /* 
    Functions designed for the Left and Right Bumpers to
    Cycle the tube slected manualy.
    */
    public void CycleUp() {
        m_cycleCount++;
        if (m_cycleCount >= m_maxCount || m_cycleCount < 0) {
            m_cycleCount = 0;
        }
        DPrinter.println("Current CycleCount: "+m_cycleCount)
    }
    public void CycleDown() {
        m_cycleCount--;
        if (m_cycleCount >= m_maxCount || m_cycleCount < 0) {
            m_cycleCount = 0;
        }
        DPrinter.println("Current CycleCount: "+m_cycleCount)
    }
    /*
    Normal Shoot Tube and Normal Shoot Tube Index Functions
    */
    /* Function "ShootTubeSet" cycles up automaticly */
    public void ShootTubeSet(Boolean arg) {
        m_solenoids[CycleCount].set(arg);
        /* If closing valve: 
        Increse the CycleCount by one, 
        if the CycleCount >= the length of the array OR CycleCount < 0:
        set CycleCount to 0 */
        if (arg == false) {
            m_cycleCount++;
            if (m_cycleCount >= m_maxCount || m_cycleCount < 0) {
                m_cycleCount = 0;
            }
        }
        DPrinter.println("Current CycleCount: "+m_cycleCount)
    }
    /* Function "ShootTubeIndex" won't cycle up automaticly */
    /* Not used, yet Useful */
    public void ShootTubeIndex(Boolean arg, int i) {
        m_solenoids[i].set(arg);
    }
    /*
    Just Normal Shoot Tube But it Shoots all of the tubes
    in the array in enumeration
    can't do m_solenoids[1].set(true), m_solenoids[2].set(true) due to 
    the fact that the object needs to be variable in the length of the array
    Designed for the X buttion 
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