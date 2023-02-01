import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Класс приложения (скорее стартового окна) */
public class Application extends JFrame {
  private JButton createMatrix;
  private JButton matrixCalculations;
  private JButton matrixList;
  private JButton deleteMatrix;

  /** Вложенный класс окна с вычислениями */
  private class calculationWindow extends JFrame {
    private JComboBox<String> chooseMatrixForSave;
    private JComboBox<String> chooseMatrix1;
    private JComboBox<String> chooseAction;
    private JComboBox<String> chooseMatrix2;
    private JButton enter;
    private JButton refresh;
    private JButton addMatrix;
    private JTextArea field;
    private Container panel;
    private final static String[] actions = {"+", "-", "*", "T", "det"};

    /** Конструктор окна */
    private calculationWindow () {
      super("Calculations");
      setLocation(600, 100);

      String[] arr = Matrix.getMatriciesNames();

      chooseMatrix1 = new JComboBox<>(arr);
      chooseMatrix2 = new JComboBox<>(arr);
      chooseAction = new JComboBox<>(actions);
      chooseMatrixForSave = new JComboBox<>(arr);
      createMatrix.setPreferredSize(new Dimension(200, 30));

      refresh = new JButton();
      refresh.setIcon(new ImageIcon("src/refresh_icon.png"));
      refresh.setContentAreaFilled(false);
      refresh.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent event) {
          setVisible(false);
          dispose();
          new calculationWindow();
        }
      });

      addMatrix = new JButton("Create/edit matrix");
      addMatrix.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent event) {
          String str = "";
          while (str.isEmpty()) {
            str = JOptionPane.showInputDialog("Input matrix name:");
          }
          new AddMatrixWindow(str);
        }
      });

      enter = new JButton("Enter");
      enter.addActionListener(new ActionListener() {
        final String noneStr = "None";

        private boolean checkFilling (String first, String second) {
          if (first == noneStr || second == noneStr) return false;
          return true;
        }
        
        private boolean checkFilling (String first) {
          if (first == noneStr) return false;
          return true;
        }

        public void actionPerformed (ActionEvent event) {
          final String action = (String)chooseAction.getSelectedItem();
          if (action == noneStr) return;
          final String matrixToSave = (String)chooseMatrixForSave.getSelectedItem();
          final String matrix1 = (String)chooseMatrix1.getSelectedItem();
          final String matrix2 = (String)chooseMatrix2.getSelectedItem();
          Matrix result = null;
          
          switch (action) {
            case ("+") :
              {
                if (!checkFilling(matrix1, matrix2)) return;
                result = Matrix.getMatrix(matrix1).add(Matrix.getMatrix(matrix2));
                break;
              }
            case ("-") :
              {
                if (!checkFilling(matrix1, matrix2)) return;
                result = Matrix.getMatrix(matrix1).sub(Matrix.getMatrix(matrix2));
                break;
              }
            case ("*") :
              {
                if (!checkFilling(matrix1, matrix2)) return;
                result = Matrix.getMatrix(matrix1).multiply(Matrix.getMatrix(matrix2));
                break;
              }
            case ("T") :
              {
                if (!checkFilling(matrix1)) return;
                result = Matrix.getMatrix(matrix1).transposition();
                break;
              }
            case ("det") :
              {
                if (!checkFilling(matrix1)) return;
                field.setText(Matrix.getMatrix(matrix1).det().toString()); 
                return;
              }
          }

          if (result == null) {
            field.setText("Cannot do such operation");
            return;
          }

          if (matrixToSave != noneStr) {
            Matrix.updateMatrix(matrixToSave, result);
          }

          field.setText(result.getSize() + result.toString());
        }
      });

      field = new JTextArea("", 30, 40);
      field.setEditable(false);
      field.setLineWrap(true);
      field.setWrapStyleWord(true);

      panel = getContentPane();
      panel.setLayout(new FlowLayout(FlowLayout.CENTER));
      panel.add(chooseMatrix1);
      panel.add(chooseAction);
      panel.add(chooseMatrix2);
      panel.add(new JScrollPane(field));
      panel.add(chooseMatrixForSave);
      panel.add(addMatrix);
      panel.add(refresh);
      panel.add(enter);
      setContentPane(panel);

      setSize(500, 615);
      setVisible(true);
    }
  }

  /** Вложенный класс окна добавления матрицы */
  private class AddMatrixWindow extends JFrame {
    private JTextArea fillMatrix;
    private JButton enter;
    private JButton refresh;

    /** Конструктор окна с заполнением матрицы 
     * @param matrixName имя создаваемой/обновляемой матрицы*/
    private AddMatrixWindow (String matrixName) {
      super(matrixName);
      setLocation(100, 100);
      
      fillMatrix = new JTextArea("3 3\n1 2 3\n1.2 1.3 1.4\n1.2,3.4 1,0 123.1,0.33\n", 40, 40);
      fillMatrix.setLineWrap(true);
      fillMatrix.setWrapStyleWord(true);

      enter = new JButton("Add matrix");
      enter.setPreferredSize(new Dimension(150, 60));
      enter.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent event) {
            Matrix.addMatrix(matrixName, fillMatrix.getText());
            deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
            matrixList.setEnabled(!Matrix.isMatrixEmpty());
            setVisible(false);
            dispose();
        }
      });

      JPanel panel = new JPanel();
      panel.add(new JScrollPane(fillMatrix));
      panel.add(enter);
      setContentPane(panel);

      pack();
      setVisible(true);
    }

    /** Немного кривого кода
     * Это конструктор окна вывода всех матриц */
    private AddMatrixWindow () {
      super("All matricies");
      setBounds(100, 100, 800, 680);

      fillMatrix = new JTextArea(Matrix.getMatricies(), 40, 40);
      fillMatrix.setLineWrap(true);
      fillMatrix.setWrapStyleWord(true);
      fillMatrix.setEditable(false);

      refresh = new JButton();
      refresh.setIcon(new ImageIcon("src/refresh_icon.png"));
      refresh.setContentAreaFilled(false);
      refresh.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent event) {
          setVisible(false);
          dispose();
          new AddMatrixWindow();
        }
      });
      
      JPanel panel = new JPanel();
      panel.add(new JScrollPane(fillMatrix));
      panel.add(refresh);
      setContentPane(panel);

      pack();
      setVisible(true);
    }
  }

  /** Получчение кномок главного экрана */
  private JPanel getMainKeys () {
    createMatrix = new JButton("Create/edit matrix");
    createMatrix.setPreferredSize(new Dimension(150, 60));
    matrixCalculations = new JButton("Calculations");
    matrixList = new JButton("List");
    deleteMatrix = new JButton("Delete matrix");
    matrixList.setEnabled(!Matrix.isMatrixEmpty());
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
        new calculationWindow ();
        deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
        matrixCalculations.setEnabled(!Matrix.isMatrixEmpty());
      }
    });

    matrixList.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        new AddMatrixWindow();
        deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
        matrixList.setEnabled(!Matrix.isMatrixEmpty());
      }
    });

    deleteMatrix.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent event) {
        String[] arr = Matrix.getMatriciesNames();
        String result = (String)JOptionPane.showInputDialog( null, "Choose matrix", "Delete matrix", 
            JOptionPane.QUESTION_MESSAGE, null, arr, arr[0]);
        Matrix.deleteMatrix(result);
        deleteMatrix.setEnabled(!Matrix.isMatrixEmpty());
        matrixList.setEnabled(!Matrix.isMatrixEmpty());
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

  /** Дефолтный конструктор */
  public Application () {
    super("Matricies");
    setBounds(300, 300, 300, 300);

    add(getMainKeys(), BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pack();
    setVisible(true);
  }

}
