package ee.ut.math.tvt.teamthundercats;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.SalesSystemUI;

@SuppressWarnings("serial")
public class Intro{

	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";

	public static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

	public static void main(String[] args) {
		final SalesDomainController domainController = new SalesDomainControllerImpl();
		BasicConfigurator.configure();
		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {
			//Schedule a job for the event dispatch thread:
			//creating and showing this application's GUI.
			
			JFrame frame = new JFrame("Intro team ThunderCats");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//Add content to the window.
			frame.add(new IntroUI());


			//Display the window.
			frame.pack();
			centerWindow(frame);
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);

			frame.setAlwaysOnTop(false);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frame.setVisible(false);
			centerWindow(frame);
		}
	}


}
