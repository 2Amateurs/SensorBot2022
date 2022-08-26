// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase
{
    public enum State {
        IN,
        OUT,
        IDLE
    }
    public enum Intakes {
        INTAKE_FRONT,
        INTAKE_BACK
    }
    MotorController intake1 = new CANSparkMax(Constants.CanIds.INTAKE_FRONT, CANSparkMaxLowLevel.MotorType.kBrushless);
    MotorController intake2 = new WPI_VictorSPX(Constants.CanIds.INTAKE_BACK);

    Servo setup = new Servo(3);

    private State state = State.IDLE;

    private Intakes intakeMotor = Intakes.INTAKE_FRONT;

    private MotorController intakeMotorArray[] = {intake1, intake2};
    private final double inSpeed = -0.45;
    private final double outSpeed = 0.45;

    public IntakeSubsystem() {}
    
    public void setState(State state) {
        this.state = state;
    }
    public void setIntakeMotor(Intakes intakeMotor) {this.intakeMotor = intakeMotor;}

    public void intakePeriodic() {
        switch (state) {
            case IN:
                intakeMotorArray[intakeMotor.ordinal()].set(inSpeed);
                break;
            case OUT:
                intakeMotorArray[intakeMotor.ordinal()].set(outSpeed);
                break;
            case IDLE:
                intakeMotorArray[intakeMotor.ordinal()].set(0);
                break;
        }
    }
    public void releaseIntake() {
        setup.setAngle(Constants.servoAngle);
    }
    public void storeIntake() {
        setup.setAngle(0);
    }
}


