package frc.robot.subsystems.drive;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DiffDriveSubsystem extends SubsystemBase {

    // With eager singleton initialization, any static variables/fields used in the 
    // constructor must appear before the "INSTANCE" variable so that they are initialized 
    // before the constructor is called when the "INSTANCE" variable initializes.

    /**
     * The Singleton instance of this DiffDriveSubsystem. Code should use
     * the {@link #getInstance()} method to get the single instance (rather
     * than trying to construct an instance of this class.)
     */
    private final static DiffDriveSubsystem INSTANCE = new DiffDriveSubsystem();

    /**
     * Returns the Singleton instance of this DiffDriveSubsystem. This static method
     * should be used, rather than the constructor, to get the single instance
     * of this class. For example: {@code DiffDriveSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static DiffDriveSubsystem getInstance() {
        return INSTANCE;
    }

    Constants.DriveConstants driveConstants = new Constants.DriveConstants();
    private final MotorControllerGroup left;
    private final WPI_TalonSRX left1;
    private final WPI_TalonSRX left2;
    private final MotorControllerGroup right;
    private final WPI_TalonSRX right1;
    private final WPI_TalonSRX right2;
    private final DifferentialDrive drive;

    /**
     * Creates a new instance of this DiffDriveSubsystem. This constructor
     * is private since this class is a Singleton. Code should use
     * the {@link #getInstance()} method to get the singleton instance.
     */
    private DiffDriveSubsystem() {
        left1 = new WPI_TalonSRX(driveConstants.LEFT_FRONT_MOTOR_ID);
        left2 = new WPI_TalonSRX(driveConstants.LEFT_BACK_MOTOR_ID);
        left = new MotorControllerGroup(left1, left2);

        right1 = new WPI_TalonSRX(driveConstants.RIGHT_FRONT_MOTOR_ID);
        right2 = new WPI_TalonSRX(driveConstants.RIGHT_BACK_MOTOR_ID);
        right = new MotorControllerGroup(right1, right2);

        // Properly invert motors
        left.setInverted(driveConstants.LEFT_INVERTED);
        right.setInverted(driveConstants.RIGHT_INVERTED);

        // Instantiate the drive class
        drive = new DifferentialDrive(left, right);
    }

    @Override
    public void setDefaultCommand(Command defaultCommand) {
        super.setDefaultCommand(defaultCommand);
    }


    public void arcadeDrive(double fwd, double turn) {
        drive.arcadeDrive(fwd, turn);
    }

    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }
}

