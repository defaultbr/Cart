package menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class menubar {
	
	
	JPanel cards = new JPanel();
	
	
	
	
	
	JMenuBar menu = new JMenuBar();
	
	JMenu menu10 = new JMenu("Balcão");
	JMenu menu20 = new JMenu("Serviços/Consultas");
	JMenu menu30 = new JMenu("Editor");
	JMenu menu40 = new JMenu("Certidão");
	JMenu menu50 = new JMenu("Administração");
	
	
	JMenuItem menuitem11 = new JMenuItem("Protocolar");
	JMenuItem menuitem12 = new JMenuItem("Entregar");
	
	JMenuItem menuitem21 = new JMenuItem("Alterar Senha");
	JMenuItem menuitem22 = new JMenuItem("Abrir Chamado");
	JMenuItem menuitem23 = new JMenuItem("Listar Chamados");
	
	JMenuItem menuitem31 = new JMenuItem("Corrigir Texto");
	JMenuItem menuitem32 = new JMenuItem("Texto Exigência PJ");

	JMenuItem menuitem41 = new JMenuItem("Certidão Matricula");
	JMenuItem menuitem42 = new JMenuItem("Certidão Transcrição");
	JMenuItem menuitem43 = new JMenuItem("Certidão Negativa/Positiva");
	JMenuItem menuitem44 = new JMenuItem("Cadastrar Erradas");
	JMenuItem menuitem45 = new JMenuItem("Gerar Relatório");
	JMenuItem menuitem46 = new JMenuItem("Folhas não Utilizadas");
	
	JMenuItem menuitem51 = new JMenuItem("Usuários");
	JMenuItem menuitem52 = new JMenuItem("Configurações");
	

	

	public void montamenu() {
		menu.add(menu10);
		menu10.setEnabled(false);
		
		menu.add(menu20);
		menu20.setEnabled(false);

		menu.add(menu30);
		menu30.setEnabled(false);

		menu.add(menu40);
		menu40.setEnabled(false);

		menu.add(menu50);
		menu50.setEnabled(false);
		
		menu10.add(menuitem11);
		menuitem11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem11");
				
			}
		});
		menu10.add(menuitem12);
		
		
		menu20.add(menuitem21);
		menuitem21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem21");
				
			}
		});
		menu20.add(menuitem22);

		menuitem22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem22");
				
			}
		});
		menu20.add(menuitem23);

		menuitem23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem23");
				
			}
		});
		
		
		
		menu30.add(menuitem31);
		menuitem31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem31");
				
			}
		});
		
		menu30.add(menuitem32);

		menuitem32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem32");
				
			}
		});

		menu40.add(menuitem41);
		menuitem41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem41");
				
			}
		});
		
		
		
		
		menu40.add(menuitem42);
		menuitem42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem42");
				
			}
		});
		menu40.add(menuitem43);
		menuitem43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem43");
				
			}
		});
		//menu40.add(menuitem44);
		menuitem44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem44");
				
			}
		});
		menu40.add(menuitem45);
		menuitem45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem45");
				
			}
		});
		//menu40.add(menuitem46);
		menuitem46.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem46");
				
			}
		});
		
		menu50.add(menuitem51);
		menuitem51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem51");
				
			}
		});
		menu50.add(menuitem52);
		menuitem52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudacard("menuitem52");
				
			}
		});
	}
	
	
	
	
	public JMenuBar getMenu(int nivelUsuario) {
		
		montamenu();
		
		controlenivel(nivelUsuario);
		
		return menu;
		
		
		
		
	}
	
	public void controlenivel(int nivel) {
		/*
		if(nivel == 1) {
			menu10.setEnabled(false);
			
		}
		if(nivel == 2) {
			menu10.setEnabled(true);
			menu20.setEnabled(true);

			
		}
		if(nivel == 3) {
			
			menu10.setEnabled(true);
			menu20.setEnabled(true);
			menu30.setEnabled(true);

	
		}
		if(nivel == 4) {
			
			menu10.setEnabled(true);
			menu20.setEnabled(true);
			menu30.setEnabled(true);
			menu40.setEnabled(true);

	
		}
		*/
		if(nivel > 1 && nivel < 5) {
			
			menu10.setEnabled(false);
			menu20.setEnabled(true);
			menu30.setEnabled(true);
			menu40.setEnabled(true);
			menu50.setEnabled(false);

	
		}
		
		if(nivel > 4) {
			menu20.setEnabled(true);
			menu30.setEnabled(true);
			menu40.setEnabled(true);
			menu50.setEnabled(true);
		}

		
		
	}
	
	public void mudacard(String cardname) {
		CardLayout cl = (CardLayout)(cards.getLayout());
	    cl.show(cards, cardname);
		
	}
	
	
	public void setCards(JPanel cards) {
		
	 this.cards = cards;
		
	}

	
	
	
}
