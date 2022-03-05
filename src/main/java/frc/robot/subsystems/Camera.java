package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
	private final UsbCamera camera;
	
	public Camera(int usbPortNumber) {
		this.camera = new UsbCamera("pov: gamer", usbPortNumber);
		
		// TODO: put these in constants
		setFPS(20);
		setResolution(320, 240);
		CameraServer.startAutomaticCapture();
	}
	
	public void setFPS(int fps) {
		camera.setFPS(fps);
	}
	
	public void setResolution(int width, int height) {
		camera.setResolution(width, height);
	}
}
