package frc.robot.subsystems.swervedrive;

import java.util.Optional;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import swervelib.imu.SwerveIMU;

public class FakeIMU extends SwerveIMU {
    protected BuiltInAccelerometer accel;

    @Override
    public void factoryDefault() {
        
    }

    @Override
    public void clearStickyFaults() {
        
    }

    @Override
    public void setOffset(Rotation3d offset) {
    
    }

    @Override
    public void setInverted(boolean invertIMU) {
       
    }

    @Override
    public Rotation3d getRawRotation3d() {
        return new Rotation3d();
    }

    @Override
    public Rotation3d getRotation3d() {
        return new Rotation3d();
    }

    @Override
    public Optional<Translation3d> getAccel() {
        Translation3d accel_val = new Translation3d(accel.getX(), accel.getY(), accel.getZ());
        return Optional.of(accel_val);
    }

    @Override
    public Object getIMU() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIMU'");
    }

}
