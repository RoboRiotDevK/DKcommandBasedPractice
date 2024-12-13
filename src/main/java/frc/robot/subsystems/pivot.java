package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Setup;

public class pivot extends SubsystemBase{
    private CANSparkMax pivotMotor;
    private AbsoluteEncoder encoder;
    public double pivotMaxAngle = 350;
    public double pivotMinAngle = 290;
    public double slowZone = 10;
    public double extraSlowZone = 2;

    private static pivot instance;
    public static pivot getInstance() {
        if (instance==null) {
            instance=new pivot();
        }
        return instance;
     }
    public pivot() {
      pivotMotor = new CANSparkMax(Setup.PivotMotorID, MotorType.kBrushless);
      encoder = pivotMotor.getAbsoluteEncoder(com.revrobotics.SparkAbsoluteEncoder.Type.kDutyCycle);
   }

   public CANSparkMax getPivotMotor(){
      return pivotMotor;
   }

   public AbsoluteEncoder getPivotEncoder(){
      return encoder;
   }

   public double getPivotPosition(){
      return encoder.getPosition()*360;
}
}
