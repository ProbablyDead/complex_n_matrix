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

  private class AddMatrixWindow extends JFrame {
    private JTextArea fillMatrix;

    private AddMatrixWindow (String matrixName) {
      super(matrixName);
      this.pack();
      
      fillMatrix = new JTextArea("3 3\n1 2 3\n1.2 1.3 1.4\n1.2,3.4 1,0 123.1,0.33\n", 70, 70);
      fillMatrix.setLineWrap(true);
      fillMatrix.setWrapStyleWord(true);

      JPanel panel = new JPanel();
      panel.add(new JScrollPane(fillMatrix));
      setContentPane(panel);


      setVisible(true);
    }

    private void createMatrix (String matrixName) {

    }
  }

  private JPanel getMainKeys () {
    createMatrix = new JButton("Create matrix");
    matrixCalculations = new JButton("Calculations");
    matrixList = new JButton("List");
    deleteMatrix = new JButton("Delete matrix");

    createMatrix.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        String matrixName = JOptionPane.showInputDialog("Input matrix name:");

        AddMatrixWindow win = new AddMatrixWindow(matrixName);
        win.createMatrix(matrixName);
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
