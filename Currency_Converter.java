import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Currency_Converter {

    public static void converter() {
        JFrame frame = new JFrame("Currency Converter");
        JLabel label1, label2;
        JTextField textField1, textField2;
        JButton buttonConvert, buttonClose;
        JComboBox<String> comboBox1, comboBox2;

        label1 = new JLabel("Amount:");
        label1.setBounds(30, 30, 100, 30);
        label2 = new JLabel("Converted Amount:");
        label2.setBounds(30, 80, 150, 30);

        textField1 = new JTextField();
        textField1.setBounds(150, 30, 150, 30);
        textField2 = new JTextField();
        textField2.setBounds(150, 80, 150, 30);
        textField2.setEditable(false);

        String[] currencies = {"INR", "USD", "EUR", "GBP", "JPY"};
        comboBox1 = new JComboBox<>(currencies);
        comboBox1.setBounds(310, 30, 80, 30);
        comboBox2 = new JComboBox<>(currencies);
        comboBox2.setBounds(310, 80, 80, 30);

        buttonConvert = new JButton("Convert");
        buttonConvert.setBounds(50, 130, 100, 30);
        buttonClose = new JButton("Close");
        buttonClose.setBounds(200, 130, 100, 30);

        final double INR_TO_USD = 0.012;
        final double USD_TO_INR = 83.0;
        final double INR_TO_EUR = 0.011;
        final double EUR_TO_INR = 90.0;
        final double INR_TO_GBP = 0.0095;
        final double GBP_TO_INR = 105.0;
        final double INR_TO_JPY = 1.70;
        final double JPY_TO_INR = 0.59;

        buttonConvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(textField1.getText());
                String fromCurrency = (String) comboBox1.getSelectedItem();
                String toCurrency = (String) comboBox2.getSelectedItem();
                double convertedAmount = 0.0;

                switch (fromCurrency) {
                    case "INR":
                        switch (toCurrency) {
                            case "USD":
                                convertedAmount = amount * INR_TO_USD;
                                break;
                            case "EUR":
                                convertedAmount = amount * INR_TO_EUR;
                                break;
                            case "GBP":
                                convertedAmount = amount * INR_TO_GBP;
                                break;
                            case "JPY":
                                convertedAmount = amount * INR_TO_JPY;
                                break;
                            default:
                                convertedAmount = amount;
                        }
                        break;
                    case "USD":
                        switch (toCurrency) {
                            case "INR":
                                convertedAmount = amount * USD_TO_INR;
                                break;
                            case "EUR":
                                convertedAmount = amount * USD_TO_INR * INR_TO_EUR;
                                break;
                            case "GBP":
                                convertedAmount = amount * USD_TO_INR * INR_TO_GBP;
                                break;
                            case "JPY":
                                convertedAmount = amount * USD_TO_INR * INR_TO_JPY;
                                break;
                            default:
                                convertedAmount = amount;
                        }
                        break;
                    case "EUR":
                        switch (toCurrency) {
                            case "INR":
                                convertedAmount = amount * EUR_TO_INR;
                                break;
                            case "USD":
                                convertedAmount = amount * EUR_TO_INR * INR_TO_USD;
                                break;
                            case "GBP":
                                convertedAmount = amount * EUR_TO_INR * INR_TO_GBP;
                                break;
                            case "JPY":
                                convertedAmount = amount * EUR_TO_INR * INR_TO_JPY;
                                break;
                            default:
                                convertedAmount = amount;
                        }
                        break;
                    case "GBP":
                        switch (toCurrency) {
                            case "INR":
                                convertedAmount = amount * GBP_TO_INR;
                                break;
                            case "USD":
                                convertedAmount = amount * GBP_TO_INR * INR_TO_USD;
                                break;
                            case "EUR":
                                convertedAmount = amount * GBP_TO_INR * INR_TO_EUR;
                                break;
                            case "JPY":
                                convertedAmount = amount * GBP_TO_INR * INR_TO_JPY;
                                break;
                            default:
                                convertedAmount = amount;
                        }
                        break;
                    case "JPY":
                        switch (toCurrency) {
                            case "INR":
                                convertedAmount = amount * JPY_TO_INR;
                                break;
                            case "USD":
                                convertedAmount = amount * JPY_TO_INR * INR_TO_USD;
                                break;
                            case "EUR":
                                convertedAmount = amount * JPY_TO_INR * INR_TO_EUR;
                                break;
                            case "GBP":
                                convertedAmount = amount * JPY_TO_INR * INR_TO_GBP;
                                break;
                            default:
                                convertedAmount = amount;
                        }
                        break;
                }

                textField2.setText(String.format("%.2f", convertedAmount));
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(label1);
        frame.add(textField1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(comboBox1);
        frame.add(comboBox2);
        frame.add(buttonConvert);
        frame.add(buttonClose);

        frame.setLayout(null);
        frame.setSize(420, 230);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        converter();
    }
}
