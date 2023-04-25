/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc4388.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc4388.robot.Constants.*;
import frc4388.robot.subsystems.Drive;
import frc4388.robot.subsystems.Horn;
import frc4388.robot.subsystems.ShootTube;
import frc4388.robot.subsystems.LED;
import frc4388.utility.LEDPatterns;
import frc4388.utility.DPrint;
import frc4388.utility.controller.IHandController;
import frc4388.utility.controller.XboxController;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* RobotMap */
    private final RobotMap m_robotMap = new RobotMap();

    /* Subsystems */
    private final Drive m_robotDrive = new Drive(m_robotMap.leftFrontMotor, m_robotMap.rightFrontMotor,
            m_robotMap.leftBackMotor, m_robotMap.rightBackMotor, m_robotMap.driveTrain, m_robotMap.gyroDrive);

    private final LED m_robotLED = new LED(m_robotMap.LEDController);
    
    //private final Horn m_robotHorn = new Horn(m_robotMap.HornSolenoid);
    /* final code
    private final Solenoid[] SolenoidArray = {
        m_robotMap.ShooterSolenoid0, 
        m_robotMap.ShooterSolenoid1,
        m_robotMap.ShooterSolenoid2,
        m_robotMap.ShooterSolenoid3,
        m_robotMap.ShooterSolenoid4,
        m_robotMap.ShooterSolenoid5,
        m_robotMap.ShooterSolenoid6,
        m_robotMap.ShooterSolenoid7
        };
    */
    // test code, NOT FINAL, DO NOT LET THIS BE IN THE FINAL COMMIT
    private final Solenoid[] SolenoidArray = {
        new Solenoid(0), 
        new Solenoid(1),
        new Solenoid(2),
        new Solenoid(3),
        new Solenoid(4),
        new Solenoid(5),
        };
    private final ShootTube m_robotShooter = new ShootTube(SolenoidArray);

    private final DPrint m_debugLogger = m_robotMap.DPrinter;
    /* Controllers */
    private final XboxController m_driverXbox = new XboxController(OIConstants.XBOX_DRIVER_ID);
    private final XboxController m_operatorXbox = new XboxController(OIConstants.XBOX_OPERATOR_ID);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        configureButtonBindings();

        /* Default Commands */
        // drives the robot with a two-axis input from the driver controller
        m_robotDrive.setDefaultCommand(
                new RunCommand(() -> m_robotDrive.driveWithInput(getDriverController().getLeftYAxis(),
                        getDriverController().getRightXAxis()), m_robotDrive));
        // continually sends updates to the Blinkin LED controller to keep the lights on
        m_robotLED.setDefaultCommand(new RunCommand(() -> m_robotLED.updateLED(), m_robotLED));
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        // test command to spin the robot while pressing A on the driver controller
        /*new JoystickButton(getDriverJoystick(), XboxController.A_BUTTON)
                .whileHeld(() -> m_robotDrive.driveWithInput(0, 1));
        */
        /* Operator Buttons */
        // activates "lit" Mode"
        /*
        new JoystickButton(getOperatorJoystick(), XboxController.A_BUTTON)
                .whenPressed(() -> m_robotLED.setPattern(LEDPatterns.LAVA_RAINBOW))
                .whenReleased(() -> m_robotLED.setPattern(LEDConstants.DEFAULT_PATTERN));
        */
        /* Fire horn */
        /*new JoystickButton(getOperatorJoystick(), XboxController.LEFT_TRIGGER_AXIS)
                .whenPressed(() -> m_robotHorn.hornSet(true))
                .whenReleased(() -> m_robotHorn.hornSet(false));
        */
        /* Shoot T-Shirt, Also cycles the array */
        new JoystickButton(getOperatorJoystick(), XboxController.A_BUTTON)
                .whenPressed(() -> m_robotShooter.ShootTubeSet(true))
                .whenReleased(() -> m_robotShooter.ShootTubeSet(false));
        

        new JoystickButton(getOperatorJoystick(), XboxController.Y_BUTTON)
                .whenPressed(() -> m_robotShooter.ShootTubeIndex(true, 0))
                .whenReleased(() -> m_robotShooter.ShootTubeIndex(false, 0));
        
        /* Cycle Between Solenoids */
        new JoystickButton(getOperatorJoystick(), XboxController.LEFT_BUMPER_BUTTON)
                .whenPressed(() -> m_robotShooter.CycleDown());
        
        new JoystickButton(getOperatorJoystick(), XboxController.RIGHT_BUMPER_BUTTON)
                .whenPressed(() -> m_robotShooter.CycleUp());
        
        /* Fire All of the Solenoids In the Shooter Array */
        /* Extra power. this will be truly worth "I Survived the T Shirt Cannon" T-shirt */
        new JoystickButton(getOperatorJoystick(), XboxController.X_BUTTON)
                .whenPressed(() -> m_robotShooter.ShootTubeALL(true))
                .whenReleased(() -> m_robotShooter.ShootTubeALL(false));
        
        /* Just A test of the DPrint object */
        new JoystickButton(getOperatorJoystick(), XboxController.B_BUTTON)
                .whenPressed(() -> m_debugLogger.println("This is a Debug Message"));
        
        
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // no auto
        return new InstantCommand();
    }

    /**
     * Add your docs here.
     */
    /* No I don't think i will */
    public IHandController getDriverController() {
        return m_driverXbox;
    }

    /**
     * Add your docs here.
     */
    /* No I don't think i will */
    public IHandController getOperatorController() {
        return m_operatorXbox;
    }

    /**
     * Add your docs here.
     */
    /* No I don't think i will */
    public Joystick getOperatorJoystick() {
        return m_operatorXbox.getJoyStick();
    }

    /**
     * Add your docs here.
     */
    /* No I don't think i will */
    public Joystick getDriverJoystick() {
        return m_driverXbox.getJoyStick();
    }
}