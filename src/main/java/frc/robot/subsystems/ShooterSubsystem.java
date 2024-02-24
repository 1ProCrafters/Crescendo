// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.OperatorConstants;

public class ShooterSubsystem extends SubsystemBase {

    DigitalInput noteDetector = new DigitalInput(2);

    private CANSparkMax frontRollers = new CANSparkMax(12, MotorType.kBrushless);
    private CANSparkMax backRollers = new CANSparkMax(11, MotorType.kBrushless);

    private RelativeEncoder frontRollersEncoder = frontRollers.getEncoder();
    public boolean intake = false; 



    // CANSparkMax
    public ShooterSubsystem() {
        frontRollers.restoreFactoryDefaults();
        backRollers.restoreFactoryDefaults();
    }

    public void intake() {
        System.err.println("intake");
        frontRollers.setInverted(true);
        backRollers.setInverted(true);

        frontRollers.set(OperatorConstants.intakeSpeed);
        backRollers.set(OperatorConstants.intakeSpeed);
    }

    public void shootAmp() {
        System.err.println("amp");
        frontRollers.setInverted(false);
        backRollers.setInverted(false);
        
        frontRollers.set(OperatorConstants.shootAmpSpeed);
        if (frontRollersEncoder.getVelocity() == OperatorConstants.shootAmpSpeed) {
            backRollers.set(OperatorConstants.backRollersShootSpeed);
        }
    }

    public void shootTrap() {
        System.err.println("trap");
        frontRollers.setInverted(false);
        backRollers.setInverted(false);

        frontRollers.set(OperatorConstants.shootTrapSpeed);
        if (frontRollersEncoder.getVelocity() == OperatorConstants.shootTrapSpeed) {
            backRollers.set(OperatorConstants.backRollersShootSpeed);
        }
    }

    public void stop() {
        frontRollers.set(0);
        backRollers.set(0);
    }

    @Override
    public void periodic() {
        if(intake == true && !noteDetector.get()) {
            intake();
        } else if (noteDetector.get()) {
            intake = false;
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}