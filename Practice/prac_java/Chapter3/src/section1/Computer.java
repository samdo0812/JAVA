package section1;

public class Computer {
	protected String manufacturer;
	protected String processor;
	protected int ramSize;
	protected int diskSize;
	protected double processorSpeed;
	
	public Computer(String man, String proc, int ram, int disk, double procSpeed) {
		manufacturer = man;
		processor = proc;
		ramSize = ram;
		diskSize = disk;
		processorSpeed = procSpeed;
	}

	public double computePower() {
		return ramSize * processorSpeed;
	}

	public int getRamSize() {
		return ramSize;
	}
	
	public double getProcessorSpeed() {
		return processorSpeed;
	}
	
	public int getDiskSzie() {
		return diskSize;
	}
	
	public String toString() {
		String result = "Manufacturer: " + manufacturer + 
						"\nCPU: " + processor +
						"\nRAM: " + ramSize + " megabytes" +
						"\nDisk: " + diskSize + " gigabytes " +
						"\nProcessor Speed: " + processorSpeed + " gigahertz";
		return result;
	}
}
