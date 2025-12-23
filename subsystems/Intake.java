package frc.robot.subsystems;

import java.io.ObjectInputFilter;

import com.MAutils.DashBoard.DashBoard;
import com.MAutils.DashBoard.DashBoardTab;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.rollerForwardCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.intakeOpenCommand;




public class Intake extends SubsystemBase {
    private final TalonFX rollerMotor;
    private final TalonFX openerMotor;
    private final DigitalInput irSensor 
    DashBoardTab tab;
    StatusSignal<Voltage> statusSignalRoller;
    StatusSignal<Voltage> statusSignalOpener;
    StatusSignal<Angle> openerPos;
    StatusSignal<Current> openerCurrent;

    private VoltageOut voltageOutOpener;
    private VoltageOut voltageRoller;

    
     private static Intake intake;

     

    private Intake() {
        rollerMotor = new TalonFX(30);
        openerMotor = new TalonFX(100);
        irSensor = new DigitalInput(1)
        voltageOutOpener = new VoltageOut(0);
        voltageRoller = new VoltageOut(0);
        statusSignalOpener =  openerMotor.getMotorVoltage();
        statusSignalRoller =  rollerMotor.getMotorVoltage();
        openerPos = openerMotor.getMotorAngle();
        openerCurrent = openerMotor.getMotorStallCurrent();
        tab = new DashBoardTab("test");

    }

    public void periodic(){
        statusSignalRoller.refresh();
        statusSignalOpener.refresh();
        openerPos.refresh();
        openerCurrent.refresh();
       
    
        
      tab.addNum("Roller Motor Voltage", statusSignalRoller.getValueAsDouble());
      tab.addNum("Opener Motor Voltage", statusSignalOpener.getValueAsDouble());
      tab.addNum("Opener Motor Angle", openerPos.getValueAsDouble());
      tab.addNum("Opener Motor Current", openerCurrent.getValueAsDouble());
      tab.addBoolean("Sensor Test", irSensor.get());

     
      System.out.println(tab.getNum("Roller Motor Voltage"));
      System.out.println(tab.getNum("Opener Motor Voltage"));
      System.out.println(tab.getNum("Opener Motor Angle"));
      System.out.println(tab.getNum("Opener Motor Current"));
      System.out.println(tab.getNum("Sensor Test"));
    
    }

    public void setRollerMotor(double voltage){
       rollerMotor.setVoltage(voltage);
    }

    public void setOpenerMotor(double voltage){
        openerMotor.setVoltage(voltage);
    }

    

    public static Intake getInstance() {
        if (intake == null){
            intake = new Intake();
        }
        return intake;
       
    }

    

}

