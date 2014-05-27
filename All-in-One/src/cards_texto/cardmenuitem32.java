package cards_texto;

import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import net.miginfocom.swing.MigLayout;
import bancodedados.conexao;

class checkBoxes {
	int id;
	String titulo;
	String texto;
	JCheckBox chx = new JCheckBox();	
	ArrayList listados = new ArrayList<>();
		 

		 
	
	
	 public void adicionaTexto() {
		 System.out.println("Adiciona o Texto " + this.texto);
	 }
	 public void removeTexto() {
		 System.out.println("Remove o texto " + this.texto);

	 }
	 
	 public void adicionaListener() {
			this.chx.addItemListener(new ItemListener() {
				
				   @Override
		            public void itemStateChanged(ItemEvent e) {
		                if(e.getStateChange() == ItemEvent.SELECTED) {
		                	
		                	listados.add(chx);
		                	
		                }
		                if(e.getStateChange() == ItemEvent.DESELECTED) {
		                	listados.remove(chx);

		                }
				   }
		        });
	 }
	 
	public JCheckBox getChx() {
		return chx;
	}
	public void setChx(JCheckBox chx) {
		this.chx = chx;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public void setDadosChx() {
		this.chx.setText(this.titulo);
		
		
	}
	public void disponibilizaListados(ArrayList selecionados) {
		this.listados = selecionados;
	}
	
}


public class cardmenuitem32 {
	
	ArrayList<checkBoxes> selecionados = new ArrayList<checkBoxes>();
	
	
	
	JPanel menuitem32 = new JPanel(new MigLayout("fillx"));
	JPanel titledCheckBox = new JPanel(new MigLayout("fillx"));
	JButton btnGerar = new JButton("Gerar Texto");
	JScrollPane jsPane = new JScrollPane(titledCheckBox);
	JLabel lblDica = new JLabel("Selecione os itens que deseja na exigência e depois clique em Gerar");
	public void montaPanel() {
		titledCheckBox.setBorder(BorderFactory.createTitledBorder("Confirme quais itens deseja na Exigência"));
		menuitem32.add(jsPane, "growx, growy ,center, wrap");
		
		
		menuitem32.add(btnGerar, "center");
		
		btnGerar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = "";
				int i = 0;
				int posicao = 1;
				JCheckBox selecionado;
				while(i < selecionados.size()) {
					if(selecionados.get(i).getChx().isSelected()) {
					texto+= posicao + ") " + selecionados.get(i).getTexto() + ";\n";
					posicao++;
					}
					i++;
				}
				
				Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();  
		        ClipboardOwner selection = new StringSelection(texto);  
		        board.setContents((Transferable) selection, selection);  
			}
		});
		
		lblDica.setForeground(Color.red);
		titledCheckBox.add(lblDica, "center, span, wrap");
		
		consultaCheckBoxes();
		

	}
	
	public void consultaCheckBoxes() {
		Connection conn = null;
		ResultSet results = null;
		PreparedStatement statement = null;
		conexao atualiza = new conexao();
		String sql = "SELECT * FROM exigencia_pj";
		try {
			conn = atualiza.abreconexao();
			statement = conn.prepareStatement(sql);
			results = statement.executeQuery();
			
			while(results.next()) {
				JCheckBox chx = new JCheckBox();
				
				checkBoxes chxs = new checkBoxes();
				chxs.setId(results.getInt("id"));
				chxs.setTitulo(results.getString("titulo"));
				chxs.setTexto(results.getString("texto"));
				chxs.setChx(chx);
				chxs.setDadosChx();
				chxs.adicionaListener();
				selecionados.add(chxs);		
			titledCheckBox.add(chxs.getChx(), "wrap");
				
			}
			
			
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adicionarCheck(int id, String titulo, String texto) {
		
	}
	

	public JPanel getCard() {

		montaPanel();

		return menuitem32;
	}

}
