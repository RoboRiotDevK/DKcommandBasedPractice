package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter;

//look more into Command

public class aimAndShoot extends Command{
    private final shooter Shooter;
    private Timer timer;
    


    public aimAndShoot(shooter shoot) {
        this.Shooter=shoot;
        timer=new Timer();
        addRequirements(shoot);
    }
    @Override

    public void initialize() {
        //make flywheels start spinning and makes time
        Shooter.speakerShoot();
        timer.reset();
        timer.stop();
    }
    @Override
    public void execute() {
            if (Shooter.getTopShooterEncoder()<-4450&&Shooter.getBottomShooterEncoder()<-4450) {
               Shooter.feedForward(1);
               timer.start();
            }
    }
    @Override
    public void end(boolean interrupted) {
        Shooter.flywheelMotorBottom.set(0);
        Shooter.flywheelMotorTop.set(0);
        Shooter.feedForward(0);
        timer.reset();
    }
    @Override
    public boolean isFinished() {
        return timer.get()>1;
    }
}
