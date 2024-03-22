// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunLauncher;
import frc.robot.commands.RunMiddleMotor;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.subsystems.swervedrive.TowerSubsystem;

import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private SwerveSubsystem drivebase;
  private TowerSubsystem tower;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public static final CommandXboxController driverXbox = new CommandXboxController(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    tower = new TowerSubsystem();

    drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

    // Configure the trigger bindings
    configureBindings();

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the desired angle NOT angular rotation

    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), 0.1),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), 0.1),
        () -> driverXbox.getRightX(),
        () -> driverXbox.getRightY());

    drivebase.setDefaultCommand(driveFieldOrientedDirectAngle);


  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    driverXbox.a().onTrue((Commands.runOnce(drivebase::zeroGyro)));

    driverXbox.b().whileTrue(new RunIntake(tower, -0.50));
    driverXbox.b().whileFalse(new RunIntake(tower, 0));

    driverXbox.x().whileTrue(new RunMiddleMotor(tower, -0.50));
    driverXbox.x().whileFalse(new RunMiddleMotor(tower, 0));

    driverXbox.y().whileTrue(new RunLauncher(tower, 0.75));
    driverXbox.y().whileFalse(new RunLauncher(tower, 0));
  
    //driverXbox.rightTrigger().whileTrue(new RunIntake(tower, -0.25)).whileTrue(new RunMiddleMotor(tower, -0.25));
    //driverXbox.rightTrigger().whileFalse(new RunIntake(tower, 0)).whileFalse(new RunMiddleMotor(tower, 0));
  }


  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }

  /*public Command getAutonomousCommand() {
    return drivebase.driveToPose(new Pose2d(new Translation2d(0, 1), new Rotation2d(0, 0)));
  }*/
}
