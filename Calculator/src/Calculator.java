import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;;
class RoundButton extends JButton {

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = 20;

        graphics.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arc, arc));

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = 20;

        graphics.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arc, arc));
    }
}


public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;

    JButton[] numberButton = new JButton[10];
    JButton[] functionButton = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    double num1 = 0, num2 = 0, results = 0;
    char operator;


    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 600);
        frame.setLayout(null);

        ImageIcon icon= new ImageIcon ("C:\\Users\\imanc\\Desktop\\Projects\\Calculator\\Icon\\calculator.png");
        frame.setIconImage(icon.getImage());

        frame.getContentPane().setBackground(Color.lightGray);


        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setBackground(Color.white);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        negButton = new JButton("(-)");

        
        addButton.setBackground(Color.orange);
        subButton.setBackground(Color.orange);
        mulButton.setBackground(Color.orange);
        divButton.setBackground(Color.orange);
        decButton.setBackground(Color.orange);
        equButton.setBackground(Color.orange);
        delButton.setBackground(Color.orange);
        clrButton.setBackground(Color.orange);
        negButton.setBackground(Color.orange);


        functionButton[0] = addButton;
        functionButton[1] = subButton;
        functionButton[2] = mulButton;
        functionButton[3] = divButton;
        functionButton[4] = decButton;
        functionButton[5] = equButton;
        functionButton[6] = delButton;
        functionButton[7] = clrButton;
        functionButton[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButton[i].addActionListener(this);
            functionButton[i].setFont(myFont);
            functionButton[i].setFocusable(false);
        }
        for (int i = 0; i < 10; i++) {
            numberButton[i] = new RoundButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(myFont);
            numberButton[i].setFocusable(false);
            numberButton[i].setBackground(Color.white);


        }
        negButton.setBounds(50, 420, 100, 60);
        delButton.setBounds(150, 420, 125, 60);
        clrButton.setBounds(275, 420, 125, 60);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subButton);
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButton[0]);
        panel.add(equButton);
        panel.add(divButton);


        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);


    }

    public static void main(String[] args) {

        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButton[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");

        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    results= num1 + num2;
                    break;
                case '-':
                    results= num1 - num2;
                    break;
                case '*':
                    results = num1 * num2;
                    break;
                case '/':
                    results = num1 / num2;
                    break;

            }
            textField.setText(String.valueOf(results));
            num1 = results;
        }
        if (e.getSource() == clrButton) {
            textField.setText(" ");
        }
        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText(" ");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText((textField.getText() + string.charAt(i)));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }
    }
}

    

