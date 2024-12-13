package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter;
import frc.robot.subsystems.GroundIntake;

public class timedIntake extends Command{
    private final GroundIntake groundIntake;
    private final shooter shooty;

    public timedIntake(GroundIntake pickup,shooter Shoot) {
        this.groundIntake= pickup;
        this.shooty=Shoot;
        addRequirements(pickup,Shoot);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute() {
        groundIntake.Intake(.5);
        shooty.feedForward(1);
    }

    @Override
    public void end(boolean interrupted) {
            groundIntake.Intake(0);
            shooty.feedForward(0);
    }
    @Override
    public boolean isFinished() {
        return !groundIntake.getNoteInShooter();
    }

}
