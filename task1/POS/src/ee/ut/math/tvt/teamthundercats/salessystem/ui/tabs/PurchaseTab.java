package ee.ut.math.tvt.teamthundercats.salessystem.ui.tabs;

import ee.ut.math.tvt.teamthundercats.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.teamthundercats.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.teamthundercats.salessystem.ui.panels.PurchaseItemPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab {

  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  private final SalesDomainController domainController;
  

  private JButton newPurchase;

  private JButton submitPurchase;

  private JButton cancelPurchase;

  private PurchaseItemPanel purchasePane;
  
  
  //private String payment;

  private SalesSystemModel model;
  
  
  private JFrame confirmOrderFrame;

  private double tPrice;
  private double tPayed;
  private double tChange;
  
  private JTextField totalPrice;
  private JTextField totalPayed;
  private JTextField totalChange;
  private JButton confirmPayment;
  private JButton cancelPayment;


  public PurchaseTab(SalesDomainController controller,
      SalesSystemModel model)
  {
    this.domainController = controller;
    this.model = model;
  }


  /**
   * The purchase tab. Consists of the purchase menu, current purchase dialog and
   * shopping cart table.
   */
  public Component draw() {
    JPanel panel = new JPanel();

    // Layout
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new GridBagLayout());

    // Add the purchase menu
    panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

    // Add the main purchase-panel
    purchasePane = new PurchaseItemPanel(model);
    panel.add(purchasePane, getConstraintsForPurchasePanel());

    return panel;
  }


  // The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
  private Component getPurchaseMenuPane() {
    JPanel panel = new JPanel();

    // Initialize layout
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gc = getConstraintsForMenuButtons();

    // Initialize the buttons
    newPurchase = createNewPurchaseButton();
    submitPurchase = createConfirmButton();
    cancelPurchase = createCancelButton();

    // Add the buttons to the panel, using GridBagConstraints we defined above
    panel.add(newPurchase, gc);
    panel.add(submitPurchase, gc);
    panel.add(cancelPurchase, gc);

    return panel;
  }


  // Creates the button "New purchase"
  private JButton createNewPurchaseButton() {
    JButton b = new JButton("New purchase");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        newPurchaseButtonClicked();
      }
    });

    return b;
  }

  // Creates the "Confirm" button
  private JButton createConfirmButton() {
    JButton b = new JButton("Confirm");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }


  // Creates the "Cancel" button
  private JButton createCancelButton() {
    JButton b = new JButton("Cancel");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }
  

  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


  /** Event handler for the <code>new purchase</code> event. */
  protected void newPurchaseButtonClicked() {
    log.info("New sale process started");
    // TODO : fire event to fill initial product selection
    try {
      domainController.startNewOrder();
      startNewSale();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /**  Event handler for the <code>cancel purchase</code> event. */
  protected void cancelPurchaseButtonClicked() {
    log.info("Sale cancelled");
    
    try {
      domainController.cancelCurrentOrder();
      endSale();
      model.getCurrentPurchaseTableModel().clear();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }

  
  /** Event handler for the <code>submit purchase</code> event. */
  protected void submitPurchaseButtonClicked() {
	  log.info("Order Confirmed");
	  confirmOrder();
    
    
  }
  
  private JButton createConfirmPaymentButton() {
      JButton b = new JButton("Confirm payment");
      b.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                      confirmPaymentButtonClicked();
              }
      });
      b.setEnabled(false);

      return b;
}


// Creates the "Cancel" button
private JButton createCancelPaymentButton() {
      JButton b = new JButton("Cancel payment");
      b.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                      cancelPaymentButtonClicked();
              }
      });
      b.setEnabled(false);

      return b;
}

protected void confirmPaymentButtonClicked() {
	       if(tPayed>=tPrice){
	               try {
	                       log.debug("Contents of the current basket:\n" + model.getCurrentPurchaseTableModel());
	                       
	                       domainController.submitCurrentOrder(model.getCurrentPurchaseTableModel().getTableRows());
	                       domainController.getCurrentOrder().calculateSum();
	                       model.getHistoryTableModel().addPurchase(domainController.getCurrentOrder());
	                       domainController.commitCurrentOrder();
	                       domainController.getCurrentOrder().refreshStock();	
	                       
	 
	                       endSale();
	                       model.getCurrentPurchaseTableModel().clear();
	                       try {
	                           domainController.cancelCurrentOrder();
	                   } catch (VerificationFailedException e) {
	                           // TODO Auto-generated catch block
	                           e.printStackTrace();
	                   }
	                       log.debug("Total price "+tPrice+" EUR. Payment of "+tPayed+" EUR confirmed. Change to return: "+tChange+" EUR.");
	                       confirmOrderFrame.dispose();
	               } catch (VerificationFailedException e1) {
	                       log.error(e1.getMessage());
	               }
	       } else {
	           JOptionPane.showMessageDialog(null,
	              "Error: Not enough. Total price "+tPrice+" EUR. Payment of "+tPayed+" EUR is not sufficient.", "Error Message",
	              JOptionPane.ERROR_MESSAGE);
	       }
	 }

protected void cancelPaymentButtonClicked() {
      try {
              domainController.cancelCurrentOrder();
              endSale();
              model.getCurrentPurchaseTableModel().clear();
              log.debug("Payment cancelled.");
      } catch (VerificationFailedException e1) {
              log.error(e1.getMessage());
      }
      confirmOrderFrame.dispose();
}

  
  protected void confirmOrder(){
      System.out.println("hi");
      confirmOrderFrame = new JFrame("Confirm payment");
      confirmOrderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(2,1));

      tPrice = model.getCurrentPurchaseTableModel().getPrice();
      totalPrice = new JTextField(String.valueOf(tPrice));
      totalPayed = new JTextField();
      totalChange = new JTextField();

      totalPrice.setEditable(false);
      totalChange.setEditable(false);

      // - total price
      panel.add(new JLabel("Order Price:"));
      panel.add(totalPrice);

      totalPayed.getDocument().addDocumentListener(new DocumentListener() {
              public void changedUpdate(DocumentEvent e) {
                      warn();
              }
              public void removeUpdate(DocumentEvent e) {
                      warn();
              }
              public void insertUpdate(DocumentEvent e) {
                      warn();
              }

              public void warn() {
                      tPayed = Double.parseDouble(totalPayed.getText());
                      tChange = tPayed-tPrice;
                      totalChange.setText(String.valueOf(tChange));

              }
      });

      // - payment
      panel.add(new JLabel("Payment:"));
      panel.add(totalPayed);

      // - change
      panel.add(new JLabel("Change:"));
      panel.add(totalChange);
      confirmPayment = createConfirmPaymentButton();
      cancelPayment = createCancelPaymentButton();
      confirmPayment.setEnabled(true);
      cancelPayment.setEnabled(true);

      // Add the buttons to the panel, using GridBagConstraints we defined above
      panel.add(confirmPayment);
      panel.add(cancelPayment);
      //Add content to the window.
      confirmOrderFrame.add(panel);

      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
      confirmOrderFrame.setLocation((screen.width - 300) / 2, (screen.height - 300) / 2);

      //Display the window.
      confirmOrderFrame.pack();
      confirmOrderFrame.setVisible(true);
      confirmOrderFrame.setAlwaysOnTop(true);
}



/* === Helper methods that bring the whole purchase-tab to a certain state
   *     when called.
   */

  // switch UI to the state that allows to proceed with the purchase
  private void startNewSale() {
    purchasePane.reset();

    purchasePane.setEnabled(true);
    submitPurchase.setEnabled(true);
    cancelPurchase.setEnabled(true);
    newPurchase.setEnabled(false);
  }

  // switch UI to the state that allows to initiate new purchase
  private void endSale() {
    purchasePane.reset();

    cancelPurchase.setEnabled(false);
    submitPurchase.setEnabled(false);
    newPurchase.setEnabled(true);
    purchasePane.setEnabled(false);
  }




  /* === Next methods just create the layout constraints objects that control the
   *     the layout of different elements in the purchase tab. These definitions are
   *     brought out here to separate contents from layout, and keep the methods
   *     that actually create the components shorter and cleaner.
   */

  private GridBagConstraints getConstraintsForPurchaseMenu() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    return gc;
  }


  private GridBagConstraints getConstraintsForPurchasePanel() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.BOTH;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 1.0;

    return gc;
  }


  // The constraints that control the layout of the buttons in the purchase menu
  private GridBagConstraints getConstraintsForMenuButtons() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.weightx = 0;
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridwidth = GridBagConstraints.RELATIVE;

    return gc;
  }

}
