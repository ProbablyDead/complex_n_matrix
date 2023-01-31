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
    private JButton enter;

    private AddMatrixWindow (String matrixName) {
      super(matrixName);
      setBounds(100, 100, 800, 680);
      
      fillMatrix = new JTextArea("3 3\n1 2 3\n1.2 1.3 1.4\n1.2,3.4 1,0 123.1,0.33\n", 40, 40);
      fillMatrix.setLineWrap(true);
      fillMatrix.setWrapStyleWord(true);

      enter = new JButton("Add matrix");
      enter.setPreferredSize(new Dimension(150, 60));
      enter.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent event) {
            Matrix.addMatrix(matrixName, fillMatrix.getText());
            deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
            setVisible(false);
            dispose();
        }
      });

      JPanel panel = new JPanel();
      panel.add(new JScrollPane(fillMatrix));
      panel.add(enter);
      setContentPane(panel);

      setVisible(true);
    }

    private AddMatrixWindow () {
      super("All matricies");
      setBounds(100, 100, 800, 680);

      fillMatrix = new JTextArea(Matrix.getMatricies(), 60, 40);
      fillMatrix.setLineWrap(true);
      fillMatrix.setWrapStyleWord(true);
      fillMatrix.setEditable(false);
      
      JPanel panel = new JPanel();
      panel.add(new JScrollPane(fillMatrix));
      setContentPane(panel);

      setVisible(true);
    }
  }

  private JPanel getMainKeys () {
    createMatrix = new JButton("Create/edit matrix");
    createMatrix.setPreferredSize(new Dimension(150, 60));
    matrixCalculations = new JButton("Calculations");
    matrixList = new JButton("List");
    deleteMatrix = new JButton("Delete matrix");
    deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());

    createMatrix.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        String str = "";
        while (str.isEmpty()) {
          str = JOptionPane.showInputDialog("Input matrix name:");
        }
        new AddMatrixWindow(str);
      }
    });
    
    matrixCalculations.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
      }
    });

    matrixList.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        new AddMatrixWindow();
        deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
      }
    });

    deleteMatrix.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        String[] arr = Matrix.getMatriciesNames();
        String result = (String)JOptionPane.showInputDialog( null, "Choose matrix", "Delete matrix", 
            JOptionPane.QUESTION_MESSAGE, null, arr, arr[0]);
        Matrix.deleteMatrix(result);
        deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
      }
    });

    JPanel buttonsPanel = new JPanel(new FlowLayout());
    buttonsPanel.setLayout(new GridLayout(4, 1, 100, 5));

    buttonsPanel.add(createMatrix, BorderLayout.NORTH);
    buttonsPanel.add(matrixCalculations, BorderLayout.NORTH);
    buttonsPanel.add(matrixList, BorderLayout.NORTH);
    buttonsPanel.add(deleteMatrix, BorderLayout.NORTH);

    return buttonsPanel;
  }

  public Application () {
    super("Matricies");
    setBounds(300, 300, 300, 300);

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
