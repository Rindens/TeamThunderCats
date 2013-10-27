package ee.ut.math.tvt.teamthundercats;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.SalesSystemUI;

@SuppressWarnings("serial")
public class Intro{

	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";

	/*private static void createAndShowGUI(SalesDomainController domainController) {
		//Create and set up the window.
		JFrame frame = new JFrame("Intro team ThunderCats");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new IntroUI());


		//Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		
		final SalesSystemUI ui = new SalesSystemUI(domainController);
		ui.setVisible(true);

		frame.setAlwaysOnTop(false);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		frame.setVisible(false);
	}*/

	public static void main(String[] args) {
		final SalesDomainController domainController = new SalesDomainControllerImpl();

		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {
			//Schedule a job for the event dispatch thread:
			//creating and showing this application's GUI.
			/*SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					//Turn off metal's use of bold fonts
					UIManager.put("swing.boldMetal", Boolean.FALSE);

					createAndShowGUI(domainController);
				}
			});*/
			
			JFrame frame = new JFrame("Intro team ThunderCats");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//Add content to the window.
			frame.add(new IntroUI());


			//Display the window.
			frame.pack();
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
		}
	}


}
