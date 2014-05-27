import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;  
import java.text.Normalizer;
import java.util.ArrayList;  
import java.util.Arrays;  
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.util.Collections;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;





import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.awt.event.KeyEvent;


public class tela_principal extends JFrame {

    ArrayList dadosbd = new ArrayList();
String pastaatual = "";
JButton btnConsultar = new JButton("Pesquisar");

	private JPanel contentPane;
	JTextField tfConsultaNome = new JTextField();
	int selecionado = 0;

	JLabel lblArqRestantes = new JLabel("");
	JLabel lblImageIcon = new JLabel("");
	String img = "F:\\primeira.jpg";
	ImageIcon imageIcon = new ImageIcon(img);
	JLabel lblResultados = new JLabel("Resultados:");
	JComboBox cboxLivros = new JComboBox();
	JComboBox cboxSubLivros = new JComboBox();
	JComboBox cboxArquivos = new JComboBox();

	JComboBox cboxPastasCNJ = new JComboBox();
	final int selecionadao = 0;



	JTable listagem = new JTable();
	



	/**
	 * Launch the application.
	 */
	
			public void run() {
				try {
					tela_principal frame = new tela_principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	

	/**
	 * Create the frame.
	 */
	public tela_principal() {
		setTitle("Indicadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1210, 630);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tfConsultaNome = new JTextField();

		
		
		JPanel painel_abas = new JPanel();
		painel_abas.setBounds(0, 0, 1201, 613);
		contentPane.add(painel_abas);
		painel_abas.setLayout(null);
		
		JTabbedPane abas = new JTabbedPane(JTabbedPane.TOP);
		abas.setBounds(0, 0, 1275, 589);
		painel_abas.add(abas);
		JPanel primeiraABA = new JPanel();
		JPanel segundaABA = new JPanel();
		abas.add(primeiraABA, "Indice");
		primeiraABA.setLayout(null);
		
		gerapastas();

		

		tfConsultaNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {

				if(evt.getKeyCode() == KeyEvent.VK_ENTER){   

					btnConsultar.doClick();  
					}  
			}
		});

		
		
		tfConsultaNome.setBounds(25, 12, 204, 20);
		primeiraABA.add(tfConsultaNome);
		tfConsultaNome.setColumns(10);
		abas.add(listagem);

		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listadados();
			}
		});
		btnConsultar.setBounds(239, 11, 108, 23);
		primeiraABA.add(btnConsultar);
		
		lblArqRestantes.setBounds(336, 34, 108, 14);
		primeiraABA.add(lblArqRestantes);
		
		listagem.getSelectionModel().addListSelectionListener(new ListSelectionListener() {  
			   @Override  
			   public void valueChanged(ListSelectionEvent evt) {  
			      if (evt.getValueIsAdjusting())  
			         return;  
			     int selected = listagem.getSelectedRow(); //Use getSelectedRows se vc permite seleção múltipla 
			     if(selected == -1) {
			    	 selected = 0;
			     }
			     
			      Object obj = null;
			      System.out.println("Linha selecionada: " + listagem.getValueAt(selected, 1));
			      obj  = (listagem.getValueAt(selected, 1));

			         String img = obj.toString();  
			         GeraraImagem(img);

			      //faça algo com selected  
			   }  
			}); 
		
		/*
		listagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				
				if(evt.getClickCount() == 1){   
					
			         Object obj  = (listagem.getValueAt(listagem.getSelectedRow(), 1));  
			         String img = obj.toString();  
			         System.out.println("Imagem é: " + img);
			         GeraraImagem(img);
			          
			}
			}
		});
		
		*/
		
		JScrollPane listaScroll = new JScrollPane(listagem);
		listaScroll.setBounds(25, 73, 353, 279);
		primeiraABA.add(listaScroll);
		
		lblImageIcon.setBounds(388, 12, 782, 462);
		primeiraABA.add(lblImageIcon);
		
		lblResultados.setBounds(25, 34, 348, 28);
		primeiraABA.add(lblResultados);
		cboxPastasCNJ.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cboxPastasCNJ.getSelectedIndex() > 0) {
					geralivros((String) cboxPastasCNJ.getSelectedItem());
			}
				
			}
			});
		cboxLivros.setModel(new DefaultComboBoxModel(new String[] {"Selecione"}));
		cboxLivros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				gerasublivros((String) cboxLivros.getSelectedItem());
			}
			});
		
		cboxSubLivros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				geraarquivos();
			}
			});
		
		/*
	
		
		
		
		cboxLivros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cboxLivros.getSelectedIndex() >= 0 && cboxPastas.getSelectedItem().equals("Livros PJ") ) {
					cboxFolha.removeAllItems();
					
					File file = new File("F:\\LIVROS-PESSOA-JURIDICA\\" + cboxLivros.getSelectedItem());
					File[] files = file.listFiles();
					Arrays.sort(files);
					int i = 0;
					while(i< files.length) {
						cboxFolha.addItem(files[i].getName());
						i++;
					
					
					}
					}
				if(cboxLivros.getSelectedIndex() >= 0 && cboxPastas.getSelectedItem().equals("Livros TD") ) {
					cboxFolha.removeAllItems();
					
					File file = new File("F:\\LIVROS-TITULOS-E-DOCUMENTOS\\" + cboxLivros.getSelectedItem());
					File[] files = file.listFiles();
					Arrays.sort(files);
					int i = 0;
					while(i< files.length) {
						cboxFolha.addItem(files[i].getName());
						i++;
					
					
					}
					}
				if(cboxLivros.getSelectedIndex() >= 0 && cboxPastas.getSelectedItem().equals("Livro Transcrição") ) {
					cboxFolha.removeAllItems();
					
					File file = new File("F:\\LIVROS-TRANSCRICAO\\" + cboxLivros.getSelectedItem());
					File[] files = file.listFiles();
					Arrays.sort(files);
					int i = 0;
					while(i< files.length) {
						cboxFolha.addItem(files[i].getName());
						i++;
					
					
					}
					}
					
					
					
			}
		});
		*/
		cboxLivros.setBounds(110, 388, 268, 20);
		primeiraABA.add(cboxLivros);
		cboxSubLivros.setModel(new DefaultComboBoxModel(new String[] {"Selecione"}));
		
		cboxSubLivros.setBounds(110, 422, 268, 20);
		primeiraABA.add(cboxSubLivros);
		
		JButton btnAbrirImagem = new JButton("Abrir Imagem");
		
		
		btnAbrirImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cboxArquivos.getSelectedIndex() > 0)
	
	try {
		Desktop.getDesktop().open(new File("F:\\LIVROS-CNJ\\" + cboxPastasCNJ.getSelectedItem() + "\\" + cboxLivros.getSelectedItem() + "\\" + cboxSubLivros.getSelectedItem() + "\\" + cboxArquivos.getSelectedItem()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
				
				
			}
		});
		
		
		btnAbrirImagem.setBounds(133, 477, 142, 23);
		primeiraABA.add(btnAbrirImagem);
		
		JButton btnAbrirTransc = new JButton("Abrir Transcri\u00E7\u00E3o");
		btnAbrirTransc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String diretorio = "";
				String transcricao = JOptionPane.showInputDialog(null, "Digite o número da transcrição") ;
				System.out.println(transcricao);
				System.out.println(transcricao.substring(1, transcricao.length()));
				int transc = Integer.parseInt(transcricao.substring(3, transcricao.length()));
				if(transc > 0 && transc < 99) {
					diretorio = "0000";
				}
				if(transc > 99 && transc < 999) {
					diretorio = "000" + transcricao.substring(5,6);
					System.out.println(diretorio);
				}
				if(transc > 999 && transc < 9999) {
					diretorio = "00" + transcricao.substring(4,6);
					System.out.println(diretorio);
				}
				if(transc > 9999 && transc < 99999) {
					diretorio = "0" + transcricao.substring(3,6);
					System.out.println(diretorio);
				}
				
				File file = new File("F:\\registro_sql\\LIVRO02\\" + diretorio);
				File[] lista = file.listFiles();
				File achou = null;
				for(int i = 0;i< lista.length;i++) {
					if(lista[i].getName().matches(transcricao + ".rtf")) {
						achou = lista[i];
					} 
					}
				
				if(achou == null) {
					JOptionPane.showMessageDialog(null, "Transcrição não encontrada");					
					
				} else { 
					
					try {
						Desktop.getDesktop().open(achou);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//List<File> files = listFiles(new File ("F:\\registro_sql\\LIVRO02"));  
		       // for (File f : files) {  
		       //     System.out.println (f.getName());  
		       // }  
			}
		});
		
		
		
			
		btnAbrirTransc.setBounds(133, 511, 142, 23);
		primeiraABA.add(btnAbrirTransc);
		
		cboxPastasCNJ.setBounds(110, 357, 268, 20);
		primeiraABA.add(cboxPastasCNJ);
		cboxArquivos.setModel(new DefaultComboBoxModel(new String[] {"Selecione"}));
		
		cboxArquivos.setBounds(110, 453, 268, 20);
		primeiraABA.add(cboxArquivos);
		
		JLabel lblArea = new JLabel("1) Pastas");
		lblArea.setBounds(25, 360, 71, 14);
		primeiraABA.add(lblArea);
		
		JLabel lblArea_1 = new JLabel("2) Livros");
		lblArea_1.setBounds(25, 394, 71, 14);
		primeiraABA.add(lblArea_1);
		
		JLabel lblArea_2 = new JLabel("3) Sub-Livros");
		lblArea_2.setBounds(25, 425, 77, 14);
		primeiraABA.add(lblArea_2);
		
		JLabel lblArea_3 = new JLabel("4) Arquivos");
		lblArea_3.setBounds(25, 456, 83, 14);
		primeiraABA.add(lblArea_3);
		
		JButton btnGerarIndice = new JButton("Gerar indice");
		btnGerarIndice.setEnabled(false);
		btnGerarIndice.setVisible(false);
		btnGerarIndice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				gerarindice();
			}
		});
		btnGerarIndice.setBounds(146, 43, 151, 23);
		primeiraABA.add(btnGerarIndice);
		abas.add(segundaABA,"Editor");
		
		JButton button = new JButton("Gerar \u00CDndice");
		segundaABA.add(button);
		
	}
	
	public void geraarquivos() {
		cboxArquivos.removeAllItems();
		cboxArquivos.addItem("Selecione");
		if(cboxSubLivros.getSelectedIndex() > 0) {
		File file = new File("F:\\LIVROS-CNJ\\" + cboxPastasCNJ.getSelectedItem() + "\\"+ cboxLivros.getSelectedItem() + "\\" + cboxSubLivros.getSelectedItem());
		File[] files = file.listFiles();
		if(files != null) {
		Arrays.sort(files);
		
		int i = 0;
		while(i< files.length) {
			cboxArquivos.addItem(files[i].getName());
			i++;
		
		
		}
		} else {
			cboxArquivos.removeAllItems();
			cboxArquivos.addItem("Pasta Vazia");
		}
	}
	}
	
	public void gerasublivros(String pastadossublivros) {
		if(cboxLivros.getSelectedIndex() > 0) { 
		cboxSubLivros.removeAllItems();	
		cboxSubLivros.addItem("Selecione");
		File file = null;
		file = new File("F:\\LIVROS-CNJ\\" + (String) cboxPastasCNJ.getSelectedItem() + "\\" + pastadossublivros);
		FileFilter fileFilter = new FileFilter() {  
	        public boolean accept(File file) {  
	            return file.isDirectory();  
	        }  
	    };
		File[] files = file.listFiles();  
		System.out.println(files);
		if(files != null) { 
	    files = file.listFiles(fileFilter); 
	    Arrays.sort(files);

	    int i = 0;
	    while(i < files.length) {
	    	cboxSubLivros.addItem(files[i].getName());
	    i++;
	    }
	}
	}
	}
	
	public void geralivros(String pastadoslivros) {
		cboxLivros.removeAllItems();	
		cboxLivros.addItem("Selecione");
		File file = null;
		file = new File("F:\\LIVROS-CNJ\\" + pastadoslivros);
		System.out.println(file.length());
		FileFilter fileFilter = new FileFilter() {  
	        public boolean accept(File file) {  
	            return file.isDirectory();  
	        }  
	    };
		File[] files = file.listFiles();  
	    

	    files = file.listFiles(fileFilter); 
	    Arrays.sort(files);

	    int i = 0;
	    while(i < files.length) {
	    	cboxLivros.addItem(files[i].getName());
	    i++;
	    }
	}
	
	public void gerapastas() {
		cboxPastasCNJ.removeAllItems();	
		cboxPastasCNJ.addItem("Selecione");

		File file = null;
		file = new File("F:\\LIVROS-CNJ");
		selecionado = 1;
		FileFilter fileFilter = new FileFilter() {  
	        public boolean accept(File file) {  
	            return file.isDirectory();  
	        }  
	    };
		File[] files = file.listFiles();  
	    

	    files = file.listFiles(fileFilter); 
	    Arrays.sort(files);

	    int i = 0;
	    while(i < files.length) {
	    	cboxPastasCNJ.addItem(files[i].getName());
	    i++;
	    }
		
	}
	
		  
	    public List<File> listFiles (File directory) {  
	        List<File> files = new ArrayList<File>();  
	        listFiles (files, directory);  
	        return files;  
	    }  
	      
	    private void listFiles (List<File> files, File directory) {  
	        for (File file : directory.listFiles()) {  
	            if (file.isDirectory()) {  
	                listFiles (files, file);  
	            } else {  
	                files.add (file);  
	            }  
	        }  
	    } 
	public void GeraraImagem(String link) {
		GeraImagem gi = new GeraImagem(link, this);
		
	}
	
	public void gerarindice() { 
		metodosgeraindice geraindice = new metodosgeraindice();
		try {
			geraindice.geraindice(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void listadados() {
		metodosbancodedados fazbusca = new metodosbancodedados();
		String nome = tfConsultaNome.getText();
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replace("'"," ").replaceAll("[^\\p{ASCII}]", "");
		try {
			dadosbd = fazbusca.consulta(nome);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String[] colunas = new String[] { 
			   "Nome",
			   "Caminho"
	
		    };  
		 if(dadosbd.size() == 0) { 
			 
			 lblResultados.setText("Busca por: '" + tfConsultaNome.getText() + "' Resultado foi de: 0 nomes");

			 
		 } else { 
			 lblResultados.setText("Busca por: '" + tfConsultaNome.getText() + "' = " + dadosbd.size() + " nomes");

				listagem.setModel(new SimpleTableModel(dadosbd, colunas));

		 }
		listagem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
    	 listagem.getColumnModel().getColumn(1).setMinWidth(0);

    	 listagem.getColumnModel().getColumn(1).setMaxWidth(0);
    	 listagem.revalidate();
    	 listagem.repaint();
	
	}
	
	public void alteraLabel(Image img) {
		lblImageIcon.setIcon(new ImageIcon(img.getScaledInstance(lblImageIcon.getWidth(),lblImageIcon.getHeight(), Image.SCALE_DEFAULT)));  
		
	     //    lblImageIcon.setIcon(new ImageIcon(img));  
	      
	//Revalidação e repaint do componente  
	      this.lblImageIcon.revalidate();  
	      this.lblImageIcon.repaint();  
	}
}
