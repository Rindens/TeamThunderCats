package ee.ut.math.tvt.salessystem.ui;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.ClientTab;
import ee.ut.math.tvt.salessystem.ui.tabs.HistoryTab;
import ee.ut.math.tvt.salessystem.ui.tabs.AnyOneTab;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;
import ee.ut.math.tvt.salessystem.ui.tabs.StockTab;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

/**
 * Graphical user interface of the sales system.
 */
public class SalesSystemUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SalesSystemUI.class);

	private SalesSystemModel model;

	// Instances of tab classes
	private AnyOneTab historyTab;
	private AnyOneTab stockTab;
	private AnyOneTab purchaseTab;
	private AnyOneTab clientTab;
	private SalesDomainController domainController;

	public static final String WAREHOUSE = "Warehouse";
	public static final String POINTOFSALE = "Point Of Sale";
	public static final String CLIENTS = "Clients";
	public static final String HISTORY = "History";

	/**
	 * Constructs sales system GUI.
	 * @param domainController Sales domain controller.
	 */
	public SalesSystemUI(SalesDomainController domainController) {
		this.domainController = domainController;
		this.model = new SalesSystemModel(domainController);
		domainController.setModel(model);

		// instances of the tab classes
		historyTab = new HistoryTab(model, HISTORY);
		stockTab = new StockTab(model, domainController, WAREHOUSE);
		clientTab = new ClientTab(model, CLIENTS);
		purchaseTab = new PurchaseTab(domainController, model, this, POINTOFSALE);


		setTitle("Sales system");

		// set L&F to the nice Windows style
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			log.warn(e1.getMessage());
		}

		drawWidgets();

		// size & location
		int width = 600;
		int height = 400;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SalesSystemUI.this.domainController.endSession();
				log.info("SalesSystem closed");
				System.exit(0);
			}
		});
	}
	
	private void addTab(JTabbedPane pane, AnyOneTab aTab){

		pane.add(aTab.getName(), aTab.draw());
		aTab.setName(aTab.getName());

	}

	private void drawWidgets() {
		final JTabbedPane tabbedPane = new JTabbedPane();

		addTab(tabbedPane, purchaseTab);
		addTab(tabbedPane, stockTab);
		addTab(tabbedPane, historyTab);
		addTab(tabbedPane, clientTab);

		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				domainController.refresh(tabbedPane.getSelectedComponent().getName());
			}
		});

		getContentPane().add(tabbedPane);
	}



}
