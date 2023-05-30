package gui.vues;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.Question;

public class ReviserText {
    private List<String> originalTexts;
    private List<Question> originalQA;
    private List<String> texteTroue;
    String text = "";
    private int currentIndex;
    private JTextArea res;
    List<JLabel> qs;
    List<List<JRadioButton>> rs;

    public ReviserText(List<String> originalTexts, List<String> texteTroue) {
        this.originalTexts = originalTexts;
        this.texteTroue = texteTroue;
        this.currentIndex = 0;
        this.res=new JTextArea(texteTroue.get(currentIndex));
    }

    public ReviserText(List<Question> originalQA) {
        this.originalQA = originalQA;
        this.currentIndex = 0;
    }

    
    public JPanel afficher() {
        JPanel panel = new JPanel();
        JPanel textPanel = new JPanel();
        panel.setLayout(new BorderLayout());
        textPanel.setLayout(new GridLayout(1, 1));
        
        res.setPreferredSize(new Dimension(600, 800));
        panel.setPreferredSize(new Dimension(600, 600));
        textPanel.add(res);
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            Next();
        });
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);
        return panel;
    }

    public JPanel afficherForQA() {
        JPanel panel = new JPanel();
        JPanel textPanel = new JPanel();
        qs = new ArrayList<JLabel>();
        rs = new ArrayList<List<JRadioButton>>();
        panel.setLayout(new BorderLayout());
        textPanel.setLayout(new GridLayout(16, 1));
        int kuestion = 1;
        for(Question d: originalQA) {
        	qs.add(new JLabel(d.getQuestion()));
        	List<JRadioButton> j = new ArrayList<JRadioButton>();
        	ButtonGroup bg =new ButtonGroup();
        	for(String r : d.getReponses()) {
        	  JRadioButton r1 = new JRadioButton(r);
        	  bg.add(r1);
    		  j.add(r1);
    	    }
        	rs.add(j);
        }
        panel.setPreferredSize(new Dimension(600, 600));
        for(int i=0;i<qs.size();i++) {
        	textPanel.add(new JLabel("Question " + kuestion + ":"));
        	textPanel.add(qs.get(i));
        	for(int k=0;k<rs.get(i).size();k++) {
               	textPanel.add(rs.get(i).get(k));        			
        	}
        	kuestion++;
        }
        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(e -> {
            Check();
        });
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(checkButton, BorderLayout.SOUTH);
        return panel;
    }
    
    private void Next() {
        if (!res.getText().isEmpty()) {
            String guess = res.getText();
            if (guess.equalsIgnoreCase(originalTexts.get(currentIndex))) {
              JOptionPane.showMessageDialog(res, "Bravo! Votre réponse est bonne.", "Succ�s",JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(res, "Désolé, votre supposition était incorrecte.", "Incorrecte", JOptionPane.ERROR_MESSAGE);
            }
            res.setText("");
        } 

        currentIndex++;
        if (currentIndex >= originalTexts.size()) {
            currentIndex = 0;
        }
        res.setText(texteTroue.get(currentIndex));
    }
    
    private void Check() {
      int count = 1;
      for(int i = 0; i < rs.size(); i++) {
    	  for(int j=0; j < rs.get(i).size(); j++) {
    		  if(rs.get(i).get(j).isSelected()) {
    			  if(originalQA.get(i).estJuste(rs.get(i).get(j).getText())) {
    				  JOptionPane.showMessageDialog(res, "Bravo! Votre "+count+" r�ponse est bonne.", "Succ�s",JOptionPane.INFORMATION_MESSAGE);
    			  }
    			  else {
    				  JOptionPane.showMessageDialog(res, "Désolé, votre "+count+" supposition était incorrecte.", "Incorrecte", JOptionPane.ERROR_MESSAGE); 
    			  }
    			  count++;
    		  }
    	  }
      }
    }
    
    /** 
     * Est-il possible de réviser la fiche à l'aide d'un texte à trous ?
     * @return true si c'est possible, false sinon.
    */
    public boolean estPossible() {
        return texteTroue.size() > 0;
    }

}

