package gui.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReviserText {
    private List<String> originalTexts;
    private List<String> texteTroue;
    private int currentIndex;
    private JTextField response;
    private JLabel textLabel;

    public ReviserText(List<String> originalTexts, List<String> texteTroue) {
        this.originalTexts = originalTexts;
        this.texteTroue = texteTroue;
        this.currentIndex = 0;
        this.response = new JTextField();
        this.textLabel = new JLabel("<html><div style='text-align: center;'>" + texteTroue.get(currentIndex) + "</div></html>");
    }

    public JPanel afficher() {
        JPanel panel = new JPanel();
        JPanel textPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        textPanel.setLayout(new GridLayout(2, 1));
        textLabel.setPreferredSize(new Dimension(600, 600));
        panel.setPreferredSize(new Dimension(600, 600));
        textPanel.add(textLabel);
        textPanel.add(response);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            Next();
        });
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);

       
        response.addActionListener(e -> {
            String guess = response.getText();
            if (guess.equalsIgnoreCase(originalTexts.get(currentIndex))) {
                textLabel.setText("<html><div style='text-align: center;'>Bravo! Votre réponse est bonne</div></html>");
            } else {
                textLabel.setText("<html><div style='text-align: center;'>Désolé, votre supposition était incorrecte.</div></html>");
            }
            response.setText("");
        });

        return panel;
    }

    private void Next() {
        if (!response.getText().isEmpty()) {
            String guess = response.getText();
            if (guess.equalsIgnoreCase(originalTexts.get(currentIndex))) {
                textLabel.setText("<html><div style='text-align: center;'>Bravo! Votre réponse est bonne</div></html>");
            } else {
                textLabel.setText("<html><div style='text-align: center;'>Désolé, votre supposition était incorrecte.</div></html>");
            }
            response.setText("");
        } else {
            textLabel.setText("<html><div style='text-align: center;'>" + texteTroue.get(currentIndex) + "</div></html>");
        }

        currentIndex++;
        if (currentIndex >= originalTexts.size()) {
            currentIndex = 0;
        }
        textLabel.setText("<html><div style='text-align: center;'>" + texteTroue.get(currentIndex) + "</div></html>");
    }
}

