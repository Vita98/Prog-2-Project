import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Model.*;

public class JavaSwingForm {
	
	private JFrame frame;
	
	//All the labels
	private JLabel matricolaLabel = new JLabel("Matricola");
	private JLabel cognomeLabel = new JLabel("Cognome");
	private JLabel nomeLabel = new JLabel("Nome");
	
	//All the TextField
	private JTextField matricolaField = new JTextField("");
	private JTextField cognomeField = new JTextField("");
	private JTextField nomeField = new JTextField("");
	
	//ArrayList with all the students and professors
	ArrayList<PersonaUniversitaria> personeUniversitarie = new ArrayList<PersonaUniversitaria>();
	
	
	Dimension frameSize = new Dimension(500, 300);

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			JavaSwingForm window = new JavaSwingForm();
			window.frame.setVisible(true);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	public JavaSwingForm() {
		// TODO Auto-generated constructor stub
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setSize(frameSize);
		frame.setTitle("Sorino's Exam");
		frame.setVisible(true);
		frame.setLayout(new GridBagLayout());
		frame.addWindowListener(new DefaultWindowListener());
		frame.setMinimumSize(frameSize);
		
		GridBagConstraints c = new GridBagConstraints();
		
		//Setting the frame in the middle of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(new Point(screenSize.width / 2 - frame.getSize().width / 2, screenSize.height / 2 - frame.getSize().height / 2));
		
		//Setting the panel with the all the info
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3,2));
		
		infoPanel.add(matricolaLabel);
		infoPanel.add(matricolaField);
		
		infoPanel.add(cognomeLabel);
		infoPanel.add(cognomeField);
		
		infoPanel.add(nomeLabel);
		infoPanel.add(nomeField);
		
		//Setting the constraint to the infoPanel for the GridBadLayout
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.4;
		
		//Adding the infoPanel to the frame with the constraints
		frame.add(infoPanel,c);
		
		
		//Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Comandi"));
		
		JButton buttonInserisciStudente = new JButton("Inserisci studente");
		buttonInserisciStudente.addActionListener(inserisciStudenteButtonAL);
		buttonPanel.add(buttonInserisciStudente);
		
		JButton buttonStampa = new JButton("Stampa");
		buttonStampa.addActionListener(stampaButtonAL);
		buttonPanel.add(buttonStampa);
		
		//Adding the "insersci docente" button
		JButton buttonInserisciDocente = new JButton("Inserisci docente");
		buttonInserisciDocente.addActionListener(inserisciDocenteButtonAL);
		buttonPanel.add(buttonInserisciDocente);
		
		//Setting the constraint to the buttonPanel for the GridBadLayout
		c.gridy = 1;
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		
		//Adding the buttonPanel to the frame with the constraints
		frame.add(buttonPanel,c);
		
		//Setting the window style
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getClassName().contains("WindowsLookAndFeel")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Method used to check all the JTextField and if is found some error, a JOptionPane is shown
	 * 
	 * @return true if all the field are correct, false otherwise
	 */
	private boolean checkAllField() {
		
		//Checking if the given matricola is an Integer
		int matricola = 0;
		try {
			matricola = Integer.parseInt(matricolaField.getText());
		}catch(NumberFormatException e) {
			JOptionPane p = new JOptionPane("Matricola non valida!",JOptionPane.ERROR_MESSAGE,JOptionPane.DEFAULT_OPTION);
			p.createDialog("Errore!").setVisible(true);
			return false;
		}
		
		//Checking if the matricola already exist inside the ArrayList
		PersonaUniversitaria conf = new Studente(matricola, "", "");
		
		if (personeUniversitarie.contains(conf)) {
			JOptionPane p = new JOptionPane("Matricola gia esistente!",JOptionPane.ERROR_MESSAGE,JOptionPane.DEFAULT_OPTION);
			p.createDialog("Errore!").setVisible(true);
			return false;
		}
		
		
		//Checking if the nome and cognome field given in input are not empty
		String nome = nomeField.getText();
		String cognome = cognomeField.getText();
		
		if (nome.equals("")  || cognome.equals("") ) {
			JOptionPane p = new JOptionPane("Inserisci prima i campi!",JOptionPane.ERROR_MESSAGE,JOptionPane.DEFAULT_OPTION);
			p.createDialog("Errore!").setVisible(true);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method used to clear all the JTextField
	 */
	private void clearAllField() {
		matricolaField.setText("");
		nomeField.setText("");
		cognomeField.setText("");
	}
	
	/**
	 * Method used to present an Information JOptionPane with the specified text
	 * @param text text to show inside the JOptionPane
	 */
	private void showAddConfirmation(String text) {
		JOptionPane p = new JOptionPane(text,JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION);
		p.createDialog("Fatto!").setVisible(true);
	}
	
	/**
	 * ActionListener for the inserisci studente JButton
	 */
	ActionListener inserisciStudenteButtonAL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			//Popolo un oggetto di classe studente
			if (checkAllField() == true) {
				int matricola = Integer.parseInt(matricolaField.getText());
				String nome = nomeField.getText();
				String cognome = cognomeField.getText();
				Studente s = new Studente(matricola, cognome, nome);
				
				//Adding the new studente to the ArrayList
				personeUniversitarie.add(s);
				
				//Clearing all the fields
				clearAllField();
				
				//Showing the confirmation message
				showAddConfirmation("Studente inserito con successo!");
			}
		}
	};
	
	/**
	 * ActionListener for the stampa JButton
	 */
	ActionListener stampaButtonAL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			for(PersonaUniversitaria p : personeUniversitarie) {
				p.chiSono();
			}
		}
	};
	
	/**
	 * ActionListener for the inserisci docente JButton
	 */
	ActionListener inserisciDocenteButtonAL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			//Popolo un oggetto di classe docente
			if (checkAllField() == true) {
				int matricola = Integer.parseInt(matricolaField.getText());
				String nome = nomeField.getText();
				String cognome = cognomeField.getText();
				Docente d = new Docente(matricola, cognome, nome);
				
				//Adding the new docente to the ArrayList
				personeUniversitarie.add(d);
				
				//Clearing all the fields
				clearAllField();
				
				//Showing the confirmation message
				showAddConfirmation("Docente inserito con successo!");
			}
		}
	};

}
