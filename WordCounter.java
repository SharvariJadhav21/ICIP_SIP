import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class WordCounter extends JFrame implements ActionListener {

    private JLabel charLabel, wordLabel, lineLabel, paragraphLabel;
    private JTextArea textArea;
    private JButton countButton, padColorButton, textColorButton, clearButton;

    public WordCounter() {
        setTitle("Character and Word Count Tool");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        textPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textPanel.add(scrollPane, BorderLayout.CENTER);

        add(textPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        controlPanel.setLayout(new GridLayout(0, 2, 10, 10));

        charLabel = new JLabel("Characters: 0");
        wordLabel = new JLabel("Words: 0");
        lineLabel = new JLabel("Lines: 0");
        paragraphLabel = new JLabel("Paragraphs: 0");

        countButton = new JButton("Count");
        countButton.addActionListener(this);

        padColorButton = new JButton("Pad Color");
        padColorButton.addActionListener(this);

        textColorButton = new JButton("Text Color");
        textColorButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        controlPanel.add(charLabel);
        controlPanel.add(wordLabel);
        controlPanel.add(lineLabel);
        controlPanel.add(paragraphLabel);
        controlPanel.add(countButton);
        controlPanel.add(padColorButton);
        controlPanel.add(textColorButton);
        controlPanel.add(clearButton);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == countButton) {
            String text = textArea.getText();
            charLabel.setText("Characters: " + text.length());

            String[] words = text.split("\\s+");
            wordLabel.setText("Words: " + words.length);

            String[] lines = text.split("\r\n|\r|\n");
            lineLabel.setText("Lines: " + lines.length);

            String[] paragraphs = text.split("\\n\\s*\\n");
            paragraphLabel.setText("Paragraphs: " + paragraphs.length);

        } else if (e.getSource() == padColorButton) {
            Color c = JColorChooser.showDialog(this, "Choose Background Color", textArea.getBackground());
            if (c != null) {
                textArea.setBackground(c);
            }

        } else if (e.getSource() == textColorButton) {
            Color c = JColorChooser.showDialog(this, "Choose Text Color", textArea.getForeground());
            if (c != null) {
                textArea.setForeground(c);
            }

        } else if (e.getSource() == clearButton) {
            textArea.setText("");
            charLabel.setText("Characters: 0");
            wordLabel.setText("Words: 0");
            lineLabel.setText("Lines: 0");
            paragraphLabel.setText("Paragraphs: 0");
        }
    }

    public static void main(String[] args) {
        new WordCounter();
    }
}
