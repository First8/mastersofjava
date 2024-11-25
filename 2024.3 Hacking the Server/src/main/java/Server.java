import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Server {

    private static final String SEAGATE_50MB = "Seagate_50MB";
    private static final String WD_100MB = "WD_100MB";
    private String primaryDevice = null;
    private String primaryMonitor = null;
    private boolean allStarted;
    private boolean loadBios;
    private boolean findOs;
    private boolean startOs;
    private String output = "";

    private String monitorId;

    public void boot() {
        loadBios();
        findOS();
        startOS();
        this.allStarted = true;
    }

    private void setPrimaryDevice(String primaryDevice) {
        this.primaryDevice = primaryDevice;
    }

    public String getPrimaryMonitor() {
        return primaryMonitor;
    }

    public void loadBios() {
        System.out.println("\n" +
                "______ _          _   _____ \n" +
                "|  ___(_)        | | |  _  |\n" +
                "| |_   _ _ __ ___| |_ \\ V / \n" +
                "|  _| | | '__/ __| __|/ _ \\ \n" +
                "| |   | | |  \\__ \\ |_| |_| |\n" +
                "\\_|   |_|_|  |___/\\__\\_____/\n" +
                "                            \n" +
                "                            \n");
        System.out.println("First8 Microsystems");
        writeProgress("Intel 486DX2-50 @ 50MHz");
        writeProgress("Speed: 50MHz    Count: 1");
        writeProgress("Bios loaded succesfully");
        System.out.println();
        writeProgress("Master Disk id: Seagate_50MB");
        writeProgress("Slave  Disk id: WD_100MB");
        this.loadBios = true;
    }

    public void findOS() {
        if ( this.primaryDevice == null) {
            writeProgress("[ERROR] No boot device available: no primary device set");
            throw new RuntimeException();
        } else {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            writeProgress("Operating system(s) found:");
            System.out.println("1: ");
        }
    }

    public void startOS() {
    	if (SEAGATE_50MB.equalsIgnoreCase(this.primaryDevice) ) {
            writeProgress("MS-DOS 6.22 Startup Menu");
            writeProgress("========================");
            writeProgress("1. Mouse + CD-Rom");
            writeProgress("");
            writeProgress("Enter a choice: 1");
            writeProgress("");
            writeProgress("");
            writeProgress("Microsoft (R) Mouse Driver Version 8.20");
            writeProgress("Copyright (C) Microsoft Corp. 1983-1992. All rights reserved.");
            writeProgress("Mouse driver installed.");
	        writeProgress("");
	        writeProgress("MODE prepare code page function completed");
	        writeProgress("");
	        writeProgress("MODE select code page function completed");
            writeProgress("");
            writeProgress("C:\\>cd games");
            writeProgress("");
            writeProgress("C:\\GAMES>cd wolf3d");
            writeProgress("");
            writeProgress("C:\\GAMES\\WOLF3D>wolf3d");
            writeProgress("");
            writeProgress("Wolfenstein 3-D");
            writeProgress("Copyright 1992 Id Software");
            writeProgress("");

            if (!monitorId.equalsIgnoreCase(this.primaryMonitor)) {
                writeProgress("ERROR: No DVI-D port found.");
                writeProgress("Please connect a VGA compatible monitor.");
                writeProgress("C:\\>");
            }
            
    	} else if (WD_100MB.equalsIgnoreCase(this.primaryDevice) ) {
            writeProgress("Booting into Linux...");
            writeProgress("Linux 2.0.38 ready for service");
            writeProgress("");
            writeProgress("");
            writeProgress("Hudson 3.3.3 (2016)");
            writeProgress("[Pipeline] dir");
            writeProgress("[Pipeline] findFiles");
            writeProgress("Running in /home/hudson/agent/workspace/devop-master/devops-server-pipeline/my-repo-dir/src");
            writeProgress("");
            
            if (!monitorId.equalsIgnoreCase(this.primaryMonitor)) {
            	writeProgress("ERROR: No DVI-D port found.");
                writeProgress("Please connect a VGA compatible monitor.");
                writeProgress("Exception in thread \"main\" java.awt.HeadlessException\n"
                		+ "    at java.desktop/java.awt.GraphicsEnvironment.checkHeadless(GraphicsEnvironment.java:165)\n"
                		+ "    at java.desktop/java.awt.Window.<init>(Window.java:540)\n"
                		+ "    at java.desktop/java.awt.Frame.<init>(Frame.java:423)\n"
                		+ "    at java.desktop/javax.swing.JFrame.<init>(JFrame.java:224)\n"
                		+ "    at HeadlessExceptionExample.main(HeadlessExceptionExample.java:5)            	\n"
                		+ "");
            	
                writeProgress(">");
            } else {
            	writeProgress("Hudson started");
            	output = "Hudson started";
            }
    	}
    }

    public boolean isHudsonRunning() {
        return WD_100MB.equalsIgnoreCase(this.primaryDevice) && output.contains("Hudson started");
    }

    public void connectPeripherals() {
        this.monitorId = scanForMonitors();
    }

    private void writeProgress(String progress) {
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + ": " + progress);
    }

    private String scanForMonitors() {
    	// should fetch this from the BIOS but for now good enough
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
