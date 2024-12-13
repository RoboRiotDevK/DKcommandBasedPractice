// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.climbCommand;
import frc.robot.commands.controlPivotCommand;
import frc.robot.commands.intakeCommand;
import frc.robot.commands.setPivotCommand;
import frc.robot.commands.shootCommand;
import frc.robot.commands.simpleDriveCommand;
import frc.robot.commands.autoCommands.accelerateTest;
import frc.robot.commands.autoCommands.aimAndShoot;
import frc.robot.commands.autoCommands.simplerDriveCommand1;
import frc.robot.commands.autoCommands.timedIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GroundIntake;
import frc.robot.subsystems.pivot;
import frc.robot.subsystems.shooter;

public class RobotContainer {
  public final Joystick primary = Setup.primaryJoystick;
  public final Joystick secondary = Setup.secondaryJoystick;
  private final Drivetrain driveSubsystem = Drivetrain.getInstance();
  private final shooter shooterSubsystem = shooter.getInstance();
  private final simpleDriveCommand drive = new simpleDriveCommand(driveSubsystem);
  private final pivot pivotSubsystem = pivot.getInstance();
  private final GroundIntake intakeSubstystem = GroundIntake.getInstance();
  private final Climber climb = Climber.getInstance();
  JoystickButton goToSpeaker;
  JoystickButton intake;
  JoystickButton outtake;
  JoystickButton accelerate;
  

  public RobotContainer() {
    
    configureBindings();
    driveSubsystem.setDefaultCommand(drive);
    shooterSubsystem.setDefaultCommand(new shootCommand(shooterSubsystem));
    pivotSubsystem.setDefaultCommand(new controlPivotCommand(pivotSubsystem));
    goToSpeaker = new JoystickButton(secondary, 2);
    goToSpeaker.onTrue(new setPivotCommand(pivotSubsystem,305));
    intake = new JoystickButton(primary, 1);
    outtake = new JoystickButton(primary, 10);
    intake.whileTrue(new intakeCommand(1, intakeSubstystem));
    outtake.whileTrue(new intakeCommand(-.7, intakeSubstystem));
    climb.setDefaultCommand(new climbCommand(climb));
    accelerate = new JoystickButton(primary, 2);
    accelerate.whileTrue(new accelerateTest(driveSubsystem, new ChassisSpeeds(1,0,0)));
    //Autonomous
    NamedCommands.registerCommand("pickup", new ParallelCommandGroup(new timedIntake(intakeSubstystem, shooterSubsystem),new setPivotCommand(pivotSubsystem, 305)));
    NamedCommands.registerCommand("aimAndShoot", new aimAndShoot(shooterSubsystem));
    NamedCommands.registerCommand("stopTheBot", new simplerDriveCommand1(driveSubsystem, new ChassisSpeeds(0,0,0)));
  }

  private void configureBindings() {
    
  }

  public Command getAutonomousCommand() {
    return new PathPlannerAuto("New Auto");
  }
}
