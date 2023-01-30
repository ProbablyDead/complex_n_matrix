import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Application extends JFrame {
  private JButton createMatrix;
  private JButton matrixCalculations;
  private JButton matrixList;
  private JButton deleteMatrix;

  private JPanel getMainKeys () {
    createMatrix = new JButton("Create matrix");
    matrixCalculations = new JButton("Calculations");
    matrixList = new JButton("List");
    deleteMatrix = new JButton("Delete matrix");

    createMatrix.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {

        String matrixName = JOptionPane.showInputDialog("Input matrix name:");
        System.out.println(matrixName);

      }
    });

    JPanel buttonsPanel = new JPanel(new FlowLayout());

    buttonsPanel.add(createMatrix, BorderLayout.NORTH);
    buttonsPanel.add(matrixCalculations, BorderLayout.NORTH);
    buttonsPanel.add(matrixList, BorderLayout.NORTH);
    buttonsPanel.add(deleteMatrix, BorderLayout.NORTH);

    return buttonsPanel;
  }

  public Application () {
    super("Matricies");

    add(getMainKeys(), BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void open () {
    this.pack();
    this.setVisible(true);
  }

  public void close () {
    this.setVisible(false);
  }
}
