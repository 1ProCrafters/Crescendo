// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopCommand extends Command {

  Joystick joystick;
  double speed;
  double xAxis;
  double yAxis;
  double zAxis;

  boolean xButton;
  boolean yButton;
  boolean aButton;
  boolean bButton;

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private MecanumSubsystem mec_subsystem;
  private ShooterSubsystem shoot_subsystem;

  /** 
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopCommand(ShooterSubsystem shootSubsystem, Joystick controller) {
    shoot_subsystem = shootSubsystem;

    joystick = controller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shoot_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speed = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // xAxis = joystick.getX(); // Strafe
    // yAxis = joystick.getY(); // Front and Back
    // zAxis = joystick.getZ(); // Turning

    // mec_subsystem.drive(-1 * xAxis, yAxis, -1 * zAxis);

    xButton = joystick.getRawButton(3);
    yButton = joystick.getRawButton(4);
    aButton = joystick.getRawButton(1); // Endgame
    bButton = joystick.getRawButton(2);

    if (xButton) {
      shoot_subsystem.intake();
    } else if (bButton) {
      shoot_subsystem.shootAmp();
    } else if (yButton) {
      shoot_subsystem.shootTrap();
    } else {
      shoot_subsystem.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
