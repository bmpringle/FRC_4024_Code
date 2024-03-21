package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.swervedrive.TowerSubsystem;

public class RunMiddleMotor extends Command {
    TowerSubsystem tower;
    double speed;

    public RunMiddleMotor(TowerSubsystem tower, double speed) {
        super();
        this.tower = tower;
        this.speed = speed;
    }

    @Override
    public void execute() {
        tower.setMiddleMotor(speed);
    };
}
