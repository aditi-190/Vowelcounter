package Vowelcounter;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.*;

public class main extends JFrame {
    private Container c;
    private JLabel textLabel, vowelLabel, aLabel, eLabel, iLabel, oLabel, uLabel, mostFrequentLabel, consonantLabel, imgLabel;
    private JTextPane ta;
    private JButton countButton, clearButton;
    private JScrollPane sp;
    private StyledDocument doc;
    private ImageIcon img;

    public main() {
        count();
    }

    private void count() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 600, 550);
        this.setTitle("Counting Vowel App");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.white);

        textLabel = new JLabel("Enter the text:");
        textLabel.setBounds(10, 60, 100, 30);
        c.add(textLabel);

        ta = new JTextPane();
        doc = ta.getStyledDocument();

        sp = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBounds(110, 60, 450, 100);
        c.add(sp);

        countButton = new JButton("Count Vowels");
        countButton.setBounds(110, 170, 140, 30);
        countButton.setBackground(Color.pink);
        countButton.setForeground(Color.BLACK);
        c.add(countButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(300, 170, 140, 30);
        clearButton.setBackground(Color.pink);
        clearButton.setForeground(Color.BLACK);
        c.add(clearButton);

        vowelLabel = new JLabel("Vowel Counts:");
        vowelLabel.setBounds(10, 220, 100, 30);
        c.add(vowelLabel);

        Font boldFont = new Font("Arial", Font.BOLD, 14);
        Color highlightColor = Color.pink;

        aLabel = new JLabel("A: 0");
        aLabel.setBounds(10, 260, 100, 30);
        aLabel.setFont(boldFont);
        aLabel.setOpaque(true);
        aLabel.setBackground(highlightColor);
        c.add(aLabel);

        eLabel = new JLabel("E: 0");
        eLabel.setBounds(120, 260, 100, 30);
        eLabel.setFont(boldFont);
        eLabel.setOpaque(true);
        eLabel.setBackground(highlightColor);
        c.add(eLabel);

        iLabel = new JLabel("I: 0");
        iLabel.setBounds(230, 260, 100, 30);
        iLabel.setFont(boldFont);
        iLabel.setOpaque(true);
        iLabel.setBackground(highlightColor);
        c.add(iLabel);

        oLabel = new JLabel("O: 0");
        oLabel.setBounds(340, 260, 100, 30);
        oLabel.setFont(boldFont);
        oLabel.setOpaque(true);
        oLabel.setBackground(highlightColor);
        c.add(oLabel);

        uLabel = new JLabel("U: 0");
        uLabel.setBounds(450, 260, 100, 30);
        uLabel.setFont(boldFont);
        uLabel.setOpaque(true);
        uLabel.setBackground(highlightColor);
        c.add(uLabel);

        mostFrequentLabel = new JLabel("Most Frequent Vowel: None");
        mostFrequentLabel.setBounds(10, 300, 300, 30);
        mostFrequentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mostFrequentLabel.setOpaque(true);
        c.add(mostFrequentLabel);

        consonantLabel = new JLabel("Consonant Count: 0");
        consonantLabel.setBounds(10, 340, 300, 30);
        consonantLabel.setFont(new Font("Arial", Font.BOLD, 14));
        consonantLabel.setOpaque(true);
        c.add(consonantLabel);

        // Replace "Vo.jpg" with an actual image file path if needed.
      try {
            img = new ImageIcon(getClass().getResource("Vo.jpg"));
            imgLabel = new JLabel(img);
            imgLabel.setBounds(0, 0, 700, 550);
            c.add(imgLabel);
        } catch (Exception e) {
            System.out.println("Image not found. Skipping background image.");
        }

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countVowelsConsonants();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
    }

    private void countVowelsConsonants() {
        String text = ta.getText();
        int[] counts = new int[5]; // a, e, i, o, u
        int consonantCount = 0;

        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        doc.setCharacterAttributes(0, doc.getLength(), new SimpleAttributeSet(), true);

        SimpleAttributeSet vowelStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(vowelStyle, Color.RED);
        StyleConstants.setBold(vowelStyle, true);

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            boolean isVowel = false;

            for (int j = 0; j < vowels.length; j++) {
                if (ch == vowels[j]) {
                    counts[j]++;
                    applyStyle(i, vowelStyle);
                    isVowel = true;
                    break;
                }
            }

            if (Character.isLetter(ch) && !isVowel) {
                consonantCount++;
            }
        }

        aLabel.setText("A: " + counts[0]);
        eLabel.setText("E: " + counts[1]);
        iLabel.setText("I: " + counts[2]);
        oLabel.setText("O: " + counts[3]);
        uLabel.setText("U: " + counts[4]);
        consonantLabel.setText("Consonant Count: " + consonantCount);

        int maxCount = 0;
        String mostFrequent = "None";

        for (int j = 0; j < counts.length; j++) {
            if (counts[j] > maxCount) {
                maxCount = counts[j];
                mostFrequent = String.valueOf(vowels[j]);//convert string
            } else if (counts[j] == maxCount && maxCount > 0) {
                mostFrequent += " " + String.valueOf(vowels[j]);
            }
        }

        mostFrequentLabel.setText("Most Frequent Vowel: " + mostFrequent);
    }

    private void applyStyle(int index, AttributeSet style) {
        doc.setCharacterAttributes(index, 1, style, false);
    }

    private void clearAll() {
        ta.setText("");
        doc.setCharacterAttributes(0, doc.getLength(), new SimpleAttributeSet(), true);
        aLabel.setText("A: 0");
        eLabel.setText("E: 0");
        iLabel.setText("I: 0");
        oLabel.setText("O: 0");
        uLabel.setText("U: 0");
        consonantLabel.setText("Consonant Count: 0");
        mostFrequentLabel.setText("Most Frequent Vowel: None");
    }

    public static void main(String[] args) {
        main frame = new main();
        frame.setVisible(true);
    }
}
