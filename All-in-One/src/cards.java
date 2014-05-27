

import java.awt.CardLayout;

import javax.swing.JPanel;

import cards_administracao.cardmenuitem51;
import cards_administracao.cardmenuitem52;
import cards_certidao.card_primeira_tela;
import cards_certidao.cardmenuitem11;
import cards_certidao.cardmenuitem41;
import cards_certidao.cardmenuitem42;
import cards_certidao.cardmenuitem43;
import cards_certidao.cardmenuitem44;
import cards_certidao.cardmenuitem45;
import cards_certidao.cardmenuitem46;
import cards_servicos_consultas.cardmenuitem21;
import cards_servicos_consultas.cardmenuitem22;
import cards_servicos_consultas.cardmenuitem23;
import cards_texto.cardmenuitem31;
import cards_texto.cardmenuitem32;

public class cards {
	
	String nomeUsuario;
	String ipOrigem;
	JPanel cards = new JPanel(new CardLayout());
	
	
	public JPanel getCards() {
		card_primeira_tela c1 = new card_primeira_tela();
		cardmenuitem11 c11 = new cardmenuitem11();
		cardmenuitem21 c21 = new cardmenuitem21();
		c21.setUsuario(nomeUsuario);
		cardmenuitem22 c22 = new cardmenuitem22();
		c22.setUsuario(nomeUsuario);
		cardmenuitem23 c23 = new cardmenuitem23();
		c23.setUsuario(nomeUsuario);
	
		
		
		cardmenuitem31 c31 = new cardmenuitem31();

		
		cardmenuitem32 c32 = new cardmenuitem32();
		
		cardmenuitem41 c41 = new cardmenuitem41();
		c41.setUsuario(nomeUsuario);
		cardmenuitem42 c42 = new cardmenuitem42();
		c42.setUsuario(nomeUsuario);

		cardmenuitem43 c43 = new cardmenuitem43();
		c43.setUsuario(nomeUsuario);

		cardmenuitem44 c44 = new cardmenuitem44();
		cardmenuitem45 c45 = new cardmenuitem45();
		cardmenuitem46 c46 = new cardmenuitem46();
		
		
		cardmenuitem51 c51 = new cardmenuitem51();
		cardmenuitem52 c52 = new cardmenuitem52();

		cards.add(c1.getCard(), "Home");
		cards.add(c11.getCard(), "menuitem11");
		
		cards.add(c21.getCard(), "menuitem21");
		cards.add(c22.getCard(), "menuitem22");
		cards.add(c23.getCard(), "menuitem23");
		cards.add(c23.getCard(), "menuitem24");
		
		cards.add(c31.getCard(), "menuitem31");
		cards.add(c32.getCard(), "menuitem32");

		cards.add(c41.getCard(), "menuitem41");
		cards.add(c42.getCard(), "menuitem42");
		cards.add(c43.getCard(), "menuitem43");
		cards.add(c44.getCard(), "menuitem44");
		cards.add(c45.getCard(), "menuitem45");
		cards.add(c46.getCard(), "menuitem46");
		
		
		
		cards.add(c51.getCard(), "menuitem51");
		cards.add(c52.getCard(), "menuitem52");
		
		return cards;
	}
	
	public void setUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public void setIp(String ipOrigem) {
		this.ipOrigem = ipOrigem;
	}

}
