package cards_certidao;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class cardmenuitem46 {
	JPanel menuitem46 = new JPanel(new MigLayout("fillx"));
	JButton listarCertidoes = new JButton("Listar Certidões");
	
	public void montaPanel() {
		
		menuitem46.add(listarCertidoes, "span, center");

		
	}
	
	
	
	
	public JPanel getCard() {
		montaPanel();
		return menuitem46;
	}
	
}
