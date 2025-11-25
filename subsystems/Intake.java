package frc.robot.subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
    private final TalonFX rollerMotor;
    private final TalonFX openerMotor;
    private VoltageOut voltageOutOpener;
    private VoltageOut voltageRoller;

    private static Intake intake;

   

    public Intake() {
        rollerMotor = new TalonFX(1);
        openerMotor = new TalonFX(2);
        voltageOutOpener = new VoltageOut(0);
        voltageRoller = new VoltageOut(0);
        
        
    }

    public void setRollerMotor(double voltage){
        voltageRoller.withOutput(voltage);
        rollerMotor.setControl(voltageRoller);
    }

    public void setOpenerMotor(double voltage){
        voltageOutOpener.withOutput(voltage);
        rollerMotor.setControl(voltageOutOpener);
    }

    public static Intake getInstance() {
        if (intake == null){
            intake = new Intake();
        }
        return intake;
       
    }

}

