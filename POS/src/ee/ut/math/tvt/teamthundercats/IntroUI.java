package ee.ut.math.tvt.teamthundercats;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class IntroUI extends JPanel {
	 

	public IntroUI(){
	super(new GridLayout(2,1));  //2 rows, 1 column
		
		ImageIcon teamLogo =  new ImageIcon("./images/thunderCats.jpg");
		System.out.println("Hello World!");
		
		Properties properties = new Properties();
		try {
		  properties.load(new FileInputStream("./application.properties"));
		} catch (IOException e) {
			System.out.println("No file found.");
		}
		
		Properties versions = new Properties();
			try {
			  versions.load(new FileInputStream("./version.properties"));
			} catch (IOException e) {
				System.out.println("No file found-2.");
			}

        
		versions.put("build.number", versions.getProperty("build.major.number") + "." 
					+ versions.getProperty("build.minor.number") + "." 
					+ versions.getProperty("build.revision.number"));
        	
        	JPanel textPanel = new JPanel(new GridLayout(5,1));  //5 rows, 1 column
        	
        	JLabel team = new JLabel("team: " + properties.getProperty("t"));
		JLabel teamLeader = new JLabel("lead: " + properties.getProperty("l"));
		JLabel teamEmail= new JLabel("e-mail: " + properties.getProperty("e"));
		JLabel teamMembers = new JLabel(properties.getProperty("m"));
		JLabel projectVersion= new JLabel(versions.getProperty("build.number"));
		
		textPanel.add(team);
		textPanel.add(teamLeader);
		textPanel.add(teamEmail);
		textPanel.add(teamMembers);
		textPanel.add(projectVersion);
		
		//add(teamLabel);
		add(textPanel);

	}
	
	
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Intro.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
