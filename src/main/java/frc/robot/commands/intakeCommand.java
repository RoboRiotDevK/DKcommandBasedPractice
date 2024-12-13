package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.GroundIntake;

public class intakeCommand extends Command {
    double value;
    GroundIntake intake;
    public intakeCommand(double intakeValue, GroundIntake subsystem){
        this.value=intakeValue;
        this.intake=subsystem;
        addRequirements(subsystem);
    }
    @Override
    public void execute(){
        intake.Intake(value);    
    }
    @Override
    public boolean isFinished(){
        return false;
    }
    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            intake.Intake(0);
        }
    }
}
