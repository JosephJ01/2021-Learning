// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

/** Add your docs here. */
public class DriveTrain extends Subsystem {
    private TalonSRX talonLeft, talonRight;
    private VictorSPX[] victorsLeft, victorsRight;

    private int leftoffset = 0;
    private int rightoffset = 0;

    private final double distancePerPulse = Math.PI * Constants.WHEEL_DIAMETER * Constants.ENCODER_GEAR_RATIO / Constants.ENCODER_PULSES_PER_REVOLUTION;


public DriveTrain() {
  talonLeft = new TalonSRX(Constants.MOTOR_PORTS_LEFT[0]);
  talonRight = new TalonSRX(Constants.MOTOR_PORTS_RIGHT[0]);

    talonLeft.configFactoryDefault();
    talonRight.configFactorDefault();

    talonLeft.setInverted(Constants.LEFT_INVERTED);
    talonRight.setInverted(!Constants.RIGHT_INVERTED);

    talonLeft.configOpenloopRamp(Constants.RAMP_MATE);
    talonRight.configOpenloopRamp(Constants.RAMP_MATE);

    victorsLeft = new VictorsSPX[Constants.MOTOR_PORTS_LEFT.length - 1];
    for (int i = 1; i < Constants.MOTOR_PORTS_LEFT.length; i++) {
        victorsLeft[i-1] = new VictorSPX(Constants.MOTOR_PORTS_LEFT)[i]);
        victorsLeft[i-1].configFactoryDefault();
        victorsLeft[i-1].follow(talonLeft);
        victorsLeft[i-1].seInverted(Constants.LEFT_INVERTED);
    }

    victorsRight = new VictorsSPX[Constants.MOTOR_PORTS_RIGHT.length - 1];
    for (int i = 1; i < Constants.MOTOR_PORTS_RIGHT.length; i++) {
        victorsRight[i-1] = new VictorSPX(Constants.MOTOR_PORTS_RIGHT)[i]);
        victorsRight[i-1].configFactoryDefault();
        victorsRight[i-1].follow(talonRight);
        victorsRight[i-1].seInverted(!Constants.LEFT_INVERTED);
    }
}
    public void setLeftMotors(double speed) {
        talonLeft.set(ControlMode.PercentOutput, speed);
    }

    public void setRightMotors(double speed) {
        talonRight.set(ControlMode.PercentOutput, speed);
    }

    public void setBothMotors(double speed) {
        setLeftMotors(speed);
        setRightMotors(speed);
    }

public void resetEncoders() {
    leftoffset - talonLeft.getSelectedSensorPosition();
    rightoffset - talonRight.getSelectedSensorPosition();
}

public double getLeftDistances() {
    return (talonLeft.getSelectedSensorPosition() - leftoffset) * distancePerPulse;
}

public double getRightDistance() {
    return (talonRight.getSelectedSensorPosition() - rightoffset) * distancePerPulse;
}

public double getDistance() {
    return (getRighDistance() + getLeftDistance()) * 0.5;
}

public double getLeftVelocity() {
    return talonleft.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND / 12;
}

public gerRightVelocity () {
    return talonRight.getSelectedSensorVelocity() * distancePerPulse * Constants.VELOCITY_CALCULATIONS_PER_SECOND / 12;
}

public double getVelocity() {
    return (getLeftVelocity() + getRightVelocity()) *0.5;
}