import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;








import menubar.menubar;


public class tela_do_programa {
	menubar menubar = new menubar();
	String ipOrigem;
	String nomeUsuario;
	int nivelUsuario;
	
	JFrame telaprincipal = new JFrame();
	
	
	JPanel panelprincipal = new JPanel(new GridBagLayout());
	
	JPanel panelmenubar = new JPanel(new GridBagLayout());
	
	JPanel panelcentral = new JPanel(new GridBagLayout());
	
	
	
	
	
	
	
	JPanel panelstatusbar = new JPanel();
	
	
	
	
	public void abrir(String nomeUsuario, int nivelUsuario, String nomeCompleto) {
		telaprincipal.add(panelprincipal);
		telaprincipal.setSize(1000,780);
		telaprincipal.setLocationRelativeTo(null);
		telaprincipal.setTitle("Conectado como: " + nomeUsuario + " & NOME: " + nomeCompleto);
		this.nivelUsuario = nivelUsuario;
		this.nomeUsuario = nomeUsuario;
		telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		posicionar3paineis();
		adicionacards();

		adicionamenubar();
		
		
		telaprincipal.setVisible(true);
	}
	
	public void posicionar3paineis() {
		panelstatusbar.setBackground(Color.red);
		
		
	GridBagConstraints gbc1 = new GridBagConstraints();
	gbc1.anchor = GridBagConstraints.FIRST_LINE_START;
	gbc1.fill = GridBagConstraints.BOTH;
	gbc1.gridx = 0;
	gbc1.gridy = 0;
	gbc1.weightx = 1;
	



	
	panelprincipal.add(panelmenubar, gbc1);
	
	gbc1.gridy = 1;
	gbc1.weighty = 1;
	
	panelprincipal.add(panelcentral,gbc1);
	
	gbc1.gridy = 2;
	gbc1.weighty = 0;
	panelprincipal.add(panelstatusbar,gbc1);
	
	
	
	}
	
	public void adicionamenubar() {
		GridBagConstraints gbcmenubar = new GridBagConstraints();
		gbcmenubar.anchor = GridBagConstraints.FIRST_LINE_START;
		gbcmenubar.fill = GridBagConstraints.BOTH;
		gbcmenubar.gridx = 0;
		gbcmenubar.gridy= 0;
	

		gbcmenubar.weighty = 1;
		gbcmenubar.weightx = 1;
		
		
		telaprincipal.setJMenuBar(menubar.getMenu(nivelUsuario));
		//panelmenubar.add(menubar.getMenu(nivelUsuario), gbcmenubar);
		
		
	}
	
	
	public void adicionacards() {
		JPanel cardis = new JPanel();
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		
		cards cards = new cards();
		cards.setUsuario(nomeUsuario);
		try {
			cards.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardis = cards.getCards();
		panelcentral.add(cardis, gbc2);
		
		menubar.setCards(cardis);
		
		
	}
	
	
	
	
	
	
}
