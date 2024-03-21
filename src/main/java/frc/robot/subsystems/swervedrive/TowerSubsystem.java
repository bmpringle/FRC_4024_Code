package frc.robot.subsystems.swervedrive;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TowerSubsystem extends SubsystemBase {
    private final CANSparkMax bottomMotor;
    private final TalonFX middleMotor;
    private final TalonFX topMotor;
    
    public TowerSubsystem() {
        super();
        bottomMotor = new CANSparkMax(8, MotorType.kBrushless);        
        bottomMotor.setIdleMode(IdleMode.kBrake);
        middleMotor = new TalonFX(11);
        topMotor = new TalonFX(12);
    }

    public void setIntakeMotor(double speed) {
        bottomMotor.set(speed);
    }

    public void setMiddleMotor(double speed) {
        middleMotor.set(speed);
    }

    public void setLauncherMotor(double speed) {
        topMotor.set(speed);
    }
}
