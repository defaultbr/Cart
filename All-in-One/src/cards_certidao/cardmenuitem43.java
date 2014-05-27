package cards_certidao;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

import net.miginfocom.swing.MigLayout;
import com.sun.star.comp.helper.BootstrapException;

import ooo.connector.BootstrapSocketConnector;
import bancodedados.conexao;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XStorable;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.uno.UnoRuntime;

public class cardmenuitem43 {
	
	JDialog jdig = new JDialog();
	JPanel jdigpanel = new JPanel(new MigLayout("fillx"));
	
	int tamanhodoscampos = 25;
	
	com.sun.star.text.XTextDocument aTextDocument = null;
    com.sun.star.lang.XComponent xComp = null;
    com.sun.star.util.XReplaceDescriptor xReplaceDescr = null;
    com.sun.star.util.XSearchDescriptor xSearchDescriptor = null;
    com.sun.star.util.XReplaceable xReplaceable = null;
    com.sun.star.uno.XComponentContext xContext = null;
    com.sun.star.lang.XMultiComponentFactory xMCF = null;
    Object oDesktop = null;
    com.sun.star.frame.XComponentLoader xCompLoader = null;
    
	ArrayList campos = new ArrayList();
    ArrayList valores = new ArrayList();
       
    ///////STRING DOS CARDS/////
    
    String CARD0 = "Escolha uma Certidão";
    String CARD1 = "CARD1";
    String CARD2 = "";
    String CARD3 = "";
    String CARD4 = "";
    String CARD5 = "";
    String CARD6 = "";
    String CARD7 = "";
    String CARD8 = "";
    String CARD9 = "";
    String CARD10 = "";

    ///STRING SOLICITANTE
    
    String solicitante;
    String solicitantecpf;
    String solicitanteidentificacao;

       
       
	////////STRING DO TEXTO///
	String protocolo;
	String datadoprotocolo;
	String nomedaparte;
	String cirg;
	String cpfcnpj;
	String nacionalidade;
	String estadocivil;
	String profissao;
	String endereco;
	String cidadeestado;
	
	
	
	
	//dados da parte 2
	String nomedaparte2;
	String cirg2;
	String cpfcnpj2;
	String nacionalidade2;
	String estadocivil2;
	String profissao2;
	String endereco2;
	String cidadeestado2;
	
	
	String registro;
	String livro;
	String ato;
	String tipo;
	String loteamentocondominio;
	String quadra;
	String lote;
	String area;
	String zona;
	String iptuitr;
	String ccirincra;
	String logradouro;
	String bairro;
	String municipioestado;
	String complemento;
	String dataatual;
	String escrevente;
	String digitadapor;
	
	
	
	///LABELS e TFs das outras infoms
	
	JLabel lblsolicitante = new JLabel("Solicitante:");
	JTextField tfsolicitante = new JTextField(10);

	JLabel lblsolicitantecpf = new JLabel("CPF:");
	JTextField tfsolicitantecpf = new JTextField(10);

	JLabel lblsolicitanteidentificacao = new JLabel("Identificação:");
	JTextField tfsolicitanteidentificacao = new JTextField(10);
	
	JLabel solicitantecpfinvalido = new JLabel("  ");

	
	
	JLabel lblregistro = new JLabel("Registro:");
	JTextField tfregistro = new JTextField(10);
	
	JLabel lbllivro = new JLabel("Livro:");
	JTextField tflivro  = new JTextField(10);
	
	JLabel lblato = new JLabel("Ato:");
	JTextField tfato  = new JTextField(10);
	
	JLabel lbltipo = new JLabel("Tipo:");
	JTextField tftipo = new JTextField(10);
	
	JLabel lblquadra = new JLabel("Quadra:");
	JTextField tfquadra = new JTextField(10);
	
	
	JLabel lblloteamentocondominio = new JLabel("Loteamento/Condomínio:");
	JTextField tfloteamentocondominio = new JTextField(10);
	
	JLabel lbllote = new JLabel("Lote:");
	JTextField tflote = new JTextField(10);
	
	JLabel lblarea = new JLabel("Área:");
	JTextField tfarea = new JTextField(10);
	
	JLabel lblzona = new JLabel("Zona:");
	JTextField tfzona = new JTextField(10);
	
	JLabel lbliptuitr = new JLabel("IPTU/ITR:");
	JTextField tfiptuitr = new JTextField(10);
	
	JLabel lblccirincra = new JLabel("CCIR/INCRA:");
	JTextField tfccirinctra = new JTextField(10);
	
	JLabel lbllogradouro = new JLabel("Logradouro:");
	JTextField tflogradouro = new JTextField(10);
	
	JLabel lblbairro = new JLabel("Bairro:");
	JTextField tfbairro = new JTextField(10);
	
	JLabel lblmunicipioestado = new JLabel("Municipio/Estado:");
	JComboBox cboxmunicipioestado = new JComboBox();
	
	JLabel lblcomplemento = new JLabel("Complemento:");
	JTextField tfcomplemento = new JTextField(10);
	
	
	//////////////////////////////
	
	boolean validaCPFCNPJ = false;
	
	
	
    
	String modelo;
    String saidaODT;
    String saidaPDF;

    
    
	XStorable xStorable = null;
	static File file;
	static String nomeTemporaryFile = "";
	String nomedopc;
	JLabel lblProtocolo = new JLabel("Protocolo:");
	JFormattedTextField tfProtocolo = new JFormattedTextField();
	
	String oooExeFolder = "F:/OpenOffice 4/program/";

	String ipOrigem;
	String nomeUsuario;
	String nomeDataArquivo;
	String nomeDataArquivo2;

	JButton btnImprimirCertidao = new JButton("Imprimir Certidão");
	String datacriada;
	Image iconbtnimprimir;
	JLabel lblpdata = new JLabel("Data do Protocolo:");
	JXDatePicker tfpdata = new JXDatePicker();
	
	JLabel lblescrevente = new JLabel("Escrevente:");
	JComboBox cboxEscrevente = new JComboBox();
	JLabel lbldigitadapor = new JLabel("Digitada por:");
	JComboBox cboxDigitadapor = new JComboBox();

	
	

	JLabel lblnomedaparte = new JLabel("Nome da Parte:");
	JTextField tfnomedaparte  = new JTextField(10);
	JLabel lblnacionalidade = new JLabel("País:");
    @SuppressWarnings("rawtypes")
	JComboBox cboxNacionalidade = new JComboBox();
    
    JButton btnSalvarSegundaParte = new JButton("Salvar");

	JLabel lblcirg = new JLabel("CI/RG:");
	JTextField tfcirg  = new JTextField(10);
	JLabel lblcpfcnpj = new JLabel("CPF/CNPJ:");
	JTextField tfcpfcnpj = new JTextField(15);
	JLabel cpfcnpjinvalido = new JLabel("");
	
	JLabel lblEstados = new JLabel("Estado:");
	@SuppressWarnings("rawtypes")
	JComboBox cboxEstados = new JComboBox();
	@SuppressWarnings("rawtypes")
	JLabel lblCidades = new JLabel("Cidade:");
	JComboBox cboxCidades = new JComboBox();
	
	
	
	JLabel lblnomedaparte2 = new JLabel("Nome da Parte:");
	JTextField tfnomedaparte2  = new JTextField(10);
	JLabel lblnacionalidade2 = new JLabel("País:");
    @SuppressWarnings("rawtypes")
	JComboBox cboxNacionalidade2 = new JComboBox();

	JLabel lblcirg2 = new JLabel("CI/RG:");
	JTextField tfcirg2  = new JTextField(10);
	JLabel lblcpfcnpj2 = new JLabel("CPF/CNPJ:");
	JTextField tfcpfcnpj2 = new JTextField(15);
	JLabel cpfcnpjinvalido2 = new JLabel("");
	
	 JLabel lblestadocivil2 = new JLabel("Estado Civil:");
	 JComboBox cboxestadocivil2 = new JComboBox();
	 
	 JLabel lblprofissao2 = new JLabel("Profissão:");
	 JTextField tfprofissao2 = new JTextField(10);
	 
	 JLabel lblendereco2 = new JLabel("Endereço:");
	 JTextField tfendereco2 = new JTextField();
	
	JLabel lblEstados2 = new JLabel("Estado:");
	@SuppressWarnings("rawtypes")
	JComboBox cboxEstados2 = new JComboBox();
	@SuppressWarnings("rawtypes")
	JLabel lblCidades2 = new JLabel("Cidade:");
	JComboBox cboxCidades2 = new JComboBox();
	
	
	
	JButton btnGerar1 = new JButton("Gerar Prévia");
	JButton btnGerar2 = new JButton("Gerar Prévia");
	JButton btnGerar3 = new JButton("Gerar Prévia");
	JButton btnGerar4 = new JButton("Gerar Prévia");
	JButton btnGerar5 = new JButton("Gerar Prévia");
	JButton btnGerar6 = new JButton("Gerar Prévia");
	JButton btnGerar7 = new JButton("Gerar Prévia");
	JButton btnGerar8 = new JButton("Gerar Prévia");
	
	
	JButton btnAdicionarOutraPessoa = new JButton("Adicionar +1 parte");

	JButton btnLimpar1 = new JButton("Limpar Campos");
	JButton btnLimpar2 = new JButton("Limpar Campos");
	
	
	
	
	
	JPanel menuitem43 = new JPanel(new MigLayout("fillx"));
	JLabel lblTitulo = new JLabel("Certidão Negativa/Positiva");
	JLabel lblTipo = new JLabel("Tipo:");
	
	 static JComboBox cboxTipos;
	 
	 JLabel lblestadocivil = new JLabel("Estado Civil:");
	 JComboBox cboxestadocivil = new JComboBox();
	 
	 JLabel lblprofissao = new JLabel("Profissão:");
	 JTextField tfprofissao = new JTextField(10);
	 
	 JLabel lblendereco = new JLabel("Endereço:");
	 JTextField tfendereco = new JTextField();
	 
	 
	JPanel dadosprotocolo = new JPanel(new MigLayout("fillx"));
	JPanel dadossolicitante = new JPanel(new MigLayout("fillx"));
	JPanel dadosdaparte = new JPanel(new MigLayout("fillx"));
	
	JPanel cardsTipos = new JPanel(new CardLayout());

	JPanel card0 = new JPanel();
	JPanel card1 = new JPanel(new MigLayout("fillx"));
	JPanel card2 = new JPanel();
	JPanel card3 = new JPanel();
	JPanel card4 = new JPanel();
	JPanel card5 = new JPanel();
	JPanel card6 = new JPanel();
	JPanel card7 = new JPanel();
	JPanel card8 = new JPanel();
	JComboBox cboxDestino = new JComboBox();

	ArrayList<JPanel> jpanels = new ArrayList<JPanel>();
	ArrayList jpanelsNome = new ArrayList();
	ArrayList jpanelsArquivo = new ArrayList();
	ArrayList estadosSiglas = new ArrayList();
	@SuppressWarnings("unchecked")
	public void montaPanel() {
		btnAdicionarOutraPessoa.setVisible(false);
		cboxDestino.addItem("Selecione o Destino para Impressão.");
		cboxDestino.addItem("Balcão");
		cboxDestino.addItem("Escreventes");
		
		   tfpdata.setDate(Calendar.getInstance().getTime());
	        tfpdata.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		
		MaskFormatter dateMask;
        try {
			dateMask = new MaskFormatter("######");
	        dateMask.install(tfProtocolo);

		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        

		
		jpanels.add(card0);
		jpanels.add(card1);
		jpanels.add(card2);
		jpanels.add(card3);
		jpanels.add(card4);
		jpanels.add(card5);
		jpanels.add(card6);
		jpanels.add(card7);
		jpanels.add(card8);
		 cboxTipos = new JComboBox<>();
		adicionaTipos();
		int i = 0;
		
		cardsTipos.add(card0, "card0");
		cardsTipos.add(card1, "card1");

	//	while(i < jpanelsNome.size()) {
	//		cardsTipos.add(jpanels.get(i), jpanelsNome.get(i));
	//		i++;
	//	}

		 
		 cboxCidades.addItem("   ");

		try {
			nomedopc = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			iconbtnimprimir = ImageIO.read(getClass().getResource("/Imagens/btn_imprimir.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cboxTipos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				
				if(evt.getStateChange() == ItemEvent.SELECTED) {
				  CardLayout cl = (CardLayout)(cardsTipos.getLayout());
				  
				  if(((String) evt.getItem()).matches("Escolha uma Certidão") == true) {
					  cl.show(cardsTipos, "card0");

				  } else if(((String) evt.getItem()).contains("2 PESSOA") == true) {
					  btnAdicionarOutraPessoa.setVisible(true);
					  cl.show(cardsTipos, "card1");

				  } else {
					  btnAdicionarOutraPessoa.setVisible(false);
					  cl.show(cardsTipos, "card1");
				  }

				  String selecionado = (String) evt.getItem();
				  getModelo(selecionado);
			}
			}
		});

		menuitem43.add(lblTitulo,"span, center, wrap");
		menuitem43.add(lblTipo, "span, split 2, center");
		menuitem43.add(cboxTipos, "wrap");
		cboxTipos.setPreferredSize(new Dimension(0,tamanhodoscampos));
		
		
		menuitem43.add(dadosprotocolo,"growx,wrap");
		dadosprotocolo.setBorder(BorderFactory.createTitledBorder("Dados do Protocolo"));
		dadosprotocolo.add(lblProtocolo, "span, split 8");
		dadosprotocolo.add(tfProtocolo);
		tfProtocolo.setPreferredSize(new Dimension(0,tamanhodoscampos));
		tfProtocolo.setColumns(10);
	
		dadosprotocolo.add(lblpdata);
		dadosprotocolo.add(tfpdata);
		tfpdata.setPreferredSize(new Dimension(0,tamanhodoscampos));
		
		dadosprotocolo.add(lbldigitadapor);
		dadosprotocolo.add(cboxDigitadapor);
		
		dadosprotocolo.add(lblescrevente);
		dadosprotocolo.add(cboxEscrevente);
		adicionaEscreventesAuxiliares();
	
		dadossolicitante.setBorder(BorderFactory.createTitledBorder("Dados do Solicitante"));

		menuitem43.add(dadossolicitante, "growx, wrap");
		
		dadossolicitante.add(lblsolicitante, "");
		dadossolicitante.add(tfsolicitante, "push, span, growx, wrap");
		tfsolicitante.setPreferredSize(new Dimension(0,tamanhodoscampos));

		dadossolicitante.add(lblsolicitantecpf, "");
		dadossolicitante.add(tfsolicitantecpf, "growx");
		
		tfsolicitantecpf.addFocusListener(new FocusListener(){
	        public void focusGained(FocusEvent e){ }
			public void focusLost(FocusEvent arg0) {				
				if(tfsolicitantecpf.getText().length() > 8 && tfsolicitantecpf.isValid()) { 
				String numeroDigitado = tfsolicitantecpf.getText().replaceAll("\\D", "");
				if(numeroDigitado.length() < 12) {
					if(validaCPF(numeroDigitado) == true) {
						solicitantecpfinvalido.setText("CPF VÁLIDO.");
						validaCPFCNPJ = true;
						tfsolicitantecpf.setText(formataValor(numeroDigitado, "###.###.###-##"));
					} else {
						validaCPFCNPJ = false;

						solicitantecpfinvalido.setText("CPF INVÁLIDO");
					}
					
				} else {
					if(validaCNPJ(numeroDigitado) == true) {
						validaCPFCNPJ = true;

						solicitantecpfinvalido.setText("CNPJ VÁLIDO.");
						tfsolicitantecpf.setText(formataValor(numeroDigitado, "##.###.###/####-##"));

					} else {
						validaCPFCNPJ = false;

						solicitantecpfinvalido.setText("CNPJ INVÁLIDO");
					}					
				}
				}				
			}
	    });
		
		
		
		dadossolicitante.add(lblsolicitanteidentificacao);
		dadossolicitante.add(tfsolicitanteidentificacao, "push, growx, wrap");
		dadossolicitante.add(solicitantecpfinvalido, "span");
		
		

		
		menuitem43.add(dadosdaparte,"growx, wrap");
		dadosdaparte.setBorder(BorderFactory.createTitledBorder("Dados da Parte"));
		btnImprimirCertidao.setIcon(new ImageIcon(iconbtnimprimir));
		montaDadosdaParte();
		montacard1();
	}
	
	
	public void montacard1() {
		tfProtocolo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				tfProtocolo.setCaretPosition(0);
			}
		});
		btnImprimirCertidao.setEnabled(false);
		
	//	dadosdaparte.add(lblnomedaparte, "");
	//	dadosdaparte.add(tfnomedaparte, "span, growx");
	//tfnomedaparte.setPreferredSize(new Dimension(0,tamanhodoscampos));
		card1.add(lblregistro,"split 2");
		card1.add(tfregistro,"growx");
		card1.add(lbllivro,"split 2");
		card1.add(tflivro,"growx");
		card1.add(lblato,"split 2");
		card1.add(tfato,"growx");
		
		card1.add(lbltipo,"split 2");
		card1.add(tftipo,"growx");
		card1.add(lblquadra,"split 2");
		card1.add(tfquadra,"growx");
		card1.add(lbllote,"split 2");
		card1.add(tflote,"growx");
		card1.add(lblarea,"split 2");
		card1.add(tfarea,"growx");
		card1.add(lblzona,"split 2");
		card1.add(tfzona,"growx, wrap");
		card1.add(lbliptuitr,"span, split 4");
		card1.add(tfiptuitr,"growx");
		card1.add(lblccirincra,"");
		card1.add(tfccirinctra,"growx, wrap");
		card1.add(lbllogradouro,"span, split 8");
		card1.add(tflogradouro,"growx");
		card1.add(lblbairro,"");
		card1.add(tfbairro,"growx");
		card1.add(lblmunicipioestado,"");
		card1.add(cboxmunicipioestado,"growx, wrap");
		card1.add(lblcomplemento,"span, split 2");
		card1.add(tfcomplemento,"growx, wrap");
		card1.add(lblloteamentocondominio,"span, split 2");
		card1.add(tfloteamentocondominio,"growx, wrap");
		

	
		card1.add(btnGerar1);

		
		
		
		
	}

	private String formataValor(String numero, String mascara){  
	    MaskFormatter mask = null;  
	    String resultado = null;
	    try{  
	    	
	        mask = new MaskFormatter(mascara);                        
	        mask.setValueContainsLiteralCharacters(false);    
	    }catch(java.text.ParseException ex){}  
	    try {
			resultado = mask.valueToString(numero);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}  
	

	public void populaMunicipio(int estadoID) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		conexao conexao = new bancodedados.conexao();
		String query = "SELECT * FROM cidade WHERE estado =" + estadoID;

		try {
			conn = conexao.abreconexao();
			stmt = conn.createStatement ();
			results = stmt.executeQuery (query + " order by ID ASC");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(results.next()) {
				cboxCidades.addItem(results.getString("nome"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		cboxCidades.validate();
		
		
		
		
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	public void populaMunicipio2(int estadoID) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		conexao conexao = new bancodedados.conexao();
		String query = "SELECT * FROM cidade WHERE estado =" + estadoID;

		try {
			conn = conexao.abreconexao();
			stmt = conn.createStatement ();
			results = stmt.executeQuery (query + " order by ID ASC");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(results.next()) {
				cboxCidades2.addItem(results.getString("nome"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		cboxCidades2.validate();
		
		
		
		
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void montaDadosdaParte() {
		dadosdaparte.add(lblnomedaparte, "");
		dadosdaparte.add(tfnomedaparte, "span, growx");
		tfnomedaparte.setPreferredSize(new Dimension(0,tamanhodoscampos));

		dadosdaparte.add(lblcpfcnpj, "");
		dadosdaparte.add(tfcpfcnpj);
		tfcpfcnpj.setPreferredSize(new Dimension(0,tamanhodoscampos));
		adicionaNacionalidades();
		dadosdaparte.add(lblcirg);
		dadosdaparte.add(tfcirg);
		tfcirg.setPreferredSize(new Dimension(0,tamanhodoscampos));
		dadosdaparte.add(lblnacionalidade);
		dadosdaparte.add(cboxNacionalidade);
		cboxNacionalidade.setPreferredSize(new Dimension(0,tamanhodoscampos));
		dadosdaparte.add(cpfcnpjinvalido, "push, span, wrap");
		tfcpfcnpj.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){ }
			public void focusLost(FocusEvent arg0) {
				if(tfcpfcnpj.getText().length() > 8 && tfcpfcnpj.isValid()) { 
				String numeroDigitado = tfcpfcnpj.getText().replaceAll("\\D", "");
				if(numeroDigitado.length() < 12) {
					if(validaCPF(numeroDigitado) == true) {
						cpfcnpjinvalido.setText("CPF VÁLIDO.");
						validaCPFCNPJ = true;
						tfcpfcnpj.setText(formataValor(numeroDigitado, "###.###.###-##"));
					} else {
						validaCPFCNPJ = false;

						cpfcnpjinvalido.setText("CPF INVÁLIDO");
					}
					
				} else {
					if(validaCNPJ(numeroDigitado) == true) {
						validaCPFCNPJ = true;

						cpfcnpjinvalido.setText("CNPJ VÁLIDO.");
						tfcpfcnpj.setText(formataValor(numeroDigitado, "##.###.###/####-##"));

					} else {
						validaCPFCNPJ = false;

						cpfcnpjinvalido.setText("CNPJ INVÁLIDO");
					}					
				}
				}				
			}
	    });
		
		
		
		dadosdaparte.add(lblestadocivil, "");
		dadosdaparte.add(cboxestadocivil);
		cboxestadocivil.setPreferredSize(new Dimension(0,tamanhodoscampos));
		adicionaEstadoCivil();
		dadosdaparte.add(lblprofissao);
		dadosdaparte.add(tfprofissao, "wrap");
		tfprofissao.setPreferredSize(new Dimension(0,tamanhodoscampos));
		dadosdaparte.add(lblendereco,"");
		dadosdaparte.add(tfendereco, "span, growx, wrap");
		tfendereco.setPreferredSize(new Dimension(0,tamanhodoscampos));
		dadosdaparte.add(lblEstados, "");
		dadosdaparte.add(cboxEstados);
		cboxEstados.setPreferredSize(new Dimension(0,tamanhodoscampos));
		adicionaEstados();
		cboxEstados.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
            	if(e.getStateChange() == ItemEvent.SELECTED && cboxEstados.getSelectedIndex() > 0) {
            		cboxCidades.removeAllItems();
            	    populaMunicipio(cboxEstados.getSelectedIndex());
            	} else {
            		cboxCidades.removeAllItems();
            		cboxCidades.addItem("");
            		
            	}
            	}
        });
		
		dadosdaparte.add(lblCidades);
		dadosdaparte.add(cboxCidades, "span, wrap");
		dadosdaparte.add(btnLimpar1, "span, center");
		dadosdaparte.add(btnAdicionarOutraPessoa, "span, align right");
		btnAdicionarOutraPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarSegundaPessoa();
			}
		});

		
		
		
		menuitem43.add(cardsTipos, "span, push, growy, growx");
		cardsTipos.setBorder(BorderFactory.createTitledBorder("Informações Adicionais"));

	
		menuitem43.add(btnImprimirCertidao, "split 2, center");
		 menuitem43.add(cboxDestino);
		
		btnImprimirCertidao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cboxDestino.getSelectedIndex() == 0) { 
		    		JOptionPane.showMessageDialog(null, "Selecione um Destino");
		    	} else {
				btnImprimirCertidao.setEnabled(false);

				if(tfProtocolo.getText().trim().isEmpty() || tfProtocolo.getText() == null) {
	    			JOptionPane.showMessageDialog(null, "Certidão deve ter um protocolo");
	    			
	    			tfProtocolo.setBackground(Color.yellow);
				
				
				
				} else	if(validaCPFCNPJ == false) {
						JOptionPane.showMessageDialog(null, "Deve ser fornecido um CPF ou CNPJ válidos.");
					}  else {
						
					imprimirNegativa();
					
					

					}

	    		
	    		
			}
			}
		});
		card2.add(btnGerar2);
		card3.add(btnGerar3);
		card4.add(btnGerar4);
		card5.add(btnGerar5);
		card6.add(btnGerar6);
		btnLimpar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		clearPanelTextBoxes();
				
			}
		});
		
		
		btnGerar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pegaDados();
				
				gerarPrevia();
				getModelo(cboxTipos.getSelectedItem().toString());
    			btnImprimirCertidao.setEnabled(true);

				
			}
		});
		
		btnGerar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pegaDados();
				
				gerarPrevia();
				getModelo(cboxTipos.getSelectedItem().toString());
				
			}
		});
		btnGerar3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pegaDados();
				
				gerarPrevia();
				getModelo(cboxTipos.getSelectedItem().toString());
				
			}
		});
		

	
		
		
	}
	public void clearPanelTextBoxes() 
	{ 

	Component[] components = dadosdaparte.getComponents(); 
	JTextField t = new JTextField();
	JComboBox  cbx = new JComboBox();
	JFormattedTextField jftf = new JFormattedTextField();
	for ( Component c : components ) 
	{ 
	if (c instanceof JTextField ) 
	{ 
	t = ( JTextField ) c ; 
	t.setText("");//cleat the fields 
	} 
	if (c instanceof JComboBox ) 
	{ 
	cbx = ( JComboBox ) c ; 
	cbx.setSelectedIndex(0);//cleat the fields 
	} 
	
	if (c instanceof JFormattedTextField ) 
	{ 
	jftf = ( JFormattedTextField ) c ; 
	jftf.setText("");;//cleat the fields 
	} 
	
	} 
	
	cboxNacionalidade.setSelectedItem("Brasil");
	validaCPFCNPJ = false;
	tfProtocolo.setText("");
	tfpdata.setDate(null);
	cboxEstados.setSelectedItem("São Paulo");

	}
	
	
	
	public String validaTextField(String texto) {
		
		if(texto == null || texto.isEmpty()) {
			texto = "x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   x   ";
		} 
		
		return texto;
	
	}
	
	public String pegaNomedoUsuario(String usuario) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		conexao conexao = new bancodedados.conexao();
		String query = "SELECT * FROM usuarios WHERE usuario = '" + usuario + "'";
		String nomecompleto = null;
		try {
			conn = conexao.abreconexao();
			stmt = conn.createStatement ();
			results = stmt.executeQuery (query + " order by ID ASC");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(results.next()) {
				nomecompleto = results.getString("nomecompleto");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nomecompleto;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void  pegaDados() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		campos.clear();
		valores.clear();
		DateFormat dfmt = new SimpleDateFormat("dd MMMM yyyy");  
        Date hoje = Calendar.getInstance(Locale.getDefault()).getTime();  
		///////////
		
		
		protocolo = validaTextField(tfProtocolo.getText().trim().toString());
		
		if(tfpdata.getDate() == null) { 
			datadoprotocolo = validaTextField("");
			
		} else {
		datadoprotocolo = validaTextField(dateFormat.format(tfpdata.getDate()).toString());
		}
		
		
		
		solicitante = validaTextField(tfsolicitante.getText());
		solicitantecpf = validaTextField(tfsolicitantecpf.getText());
		solicitanteidentificacao = validaTextField(tfsolicitanteidentificacao.getText());
		
		
		 nomedaparte = validaTextField(tfnomedaparte.getText());
		 cirg = validaTextField(tfcirg.getText());
		 cpfcnpj = validaTextField(tfcpfcnpj.getText());
		 nacionalidade = validaTextField(cboxNacionalidade.getSelectedItem().toString());
		 estadocivil = validaTextField(cboxestadocivil.getSelectedItem().toString());
		 profissao = validaTextField(tfprofissao.getText());
		 endereco = validaTextField(tfendereco.getText()); 
		 cidadeestado = validaTextField(cboxCidades.getSelectedItem().toString() + "/" + estadosSiglas.get(cboxEstados.getSelectedIndex()));
		 
		 nomedaparte2 = validaTextField(nomedaparte2);
		 cirg2 = validaTextField(cirg2);
		 cpfcnpj2 = validaTextField(cpfcnpj2);
		 nacionalidade2 = validaTextField(nacionalidade2);
		 estadocivil2 = validaTextField(estadocivil2);
		 profissao2 = validaTextField(profissao2);
		 endereco2 = validaTextField(endereco2); 
		 cidadeestado2 = validaTextField(cidadeestado2);
		 
		 
		 
		 registro = validaTextField(tfregistro.getText());
		 livro = validaTextField(tflivro.getText());
		 ato = validaTextField(tfato.getText());
		 tipo = validaTextField(tftipo.getText());
		 quadra = validaTextField(tfquadra.getText());
		 lote = validaTextField(tflote.getText());
		 area = validaTextField(tfarea.getText());
		 zona = validaTextField(tfzona.getText());
		 iptuitr = validaTextField(tfiptuitr.getText());
		 ccirincra = validaTextField(tfccirinctra.getText());
		 logradouro = validaTextField(tflogradouro.getText());
		 bairro = validaTextField(tfbairro.getText());
		 municipioestado = validaTextField(cboxmunicipioestado.getSelectedItem().toString());
		 complemento = validaTextField(tfcomplemento.getText());
		 loteamentocondominio = validaTextField(tfloteamentocondominio.getText());

		 dataatual = validaTextField(dfmt.format(hoje));
		 escrevente = validaTextField(pegaNomedoUsuario(cboxEscrevente.getSelectedItem().toString()));
		 digitadapor = validaTextField(pegaNomedoUsuario(cboxDigitadapor.getSelectedItem().toString())); 
		 
		 campos.add("%protocolo%");
         campos.add("%datadoprotocolo%");
         
         campos.add("%solicitantenome%");
         campos.add("%solicitantecpf%");
         campos.add("%solicitanteidentificacao%");
         
         campos.add("%nomedaparte%");
         campos.add("%cirg%");
         campos.add("%cpfcnpj%");
         campos.add("%nacionalidade%");
         campos.add("%estadocivil%");
         campos.add("%profissao%");
         campos.add("%endereco%");
         campos.add("%cidadeestado%");
         
         
		 campos.add("%nomedaparte2%");
         campos.add("%cirg2%");
         campos.add("%cpfcnpj2%");
         campos.add("%nacionalidade2%");
         campos.add("%estadocivil2%");
         campos.add("%profissao2%");
         campos.add("%endereco2%");
         campos.add("%cidadeestado2%");
         
        
         
         
       
         campos.add("%registro%");
         campos.add("%livro%");
         campos.add("%ato%");
         campos.add("%tipo%");
         campos.add("%quadra%");
         campos.add("%lote%");
         campos.add("%area%");
         campos.add("%zona%");
         campos.add("%iptuitr%");
         campos.add("%ccirincra%");
         campos.add("%logradouro%");
         campos.add("%bairro%");
         campos.add("%municipioestado%");
         campos.add("%complemento%");   
         campos.add("%loteamentocondominio%");
         campos.add("%dataatual%");
         campos.add("%escrevente%");
         campos.add("%digitadapor%");

         
         
         valores.add(protocolo);
         valores.add(datadoprotocolo);
         
         valores.add(solicitante);
         valores.add(solicitantecpf);
         valores.add(solicitanteidentificacao);
         
         valores.add(nomedaparte);
         valores.add(cirg);
         valores.add(cpfcnpj);
         valores.add(nacionalidade);
         valores.add(estadocivil);
         valores.add(profissao);
         valores.add(endereco);
         valores.add(cidadeestado);
         
         valores.add(nomedaparte2);
         valores.add(cirg2);
         valores.add(cpfcnpj2);
         valores.add(nacionalidade2);
         valores.add(estadocivil2);
         valores.add(profissao2);
         valores.add(endereco2);
         valores.add(cidadeestado2);
         
         valores.add(registro);
         valores.add(livro);
         valores.add(ato);
         valores.add(tipo);
         valores.add(quadra);
         valores.add(lote);
         valores.add(area);
         valores.add(zona);
         valores.add(iptuitr);
         valores.add(ccirincra);
         valores.add(logradouro);
         valores.add(bairro);
         valores.add(municipioestado);
         valores.add(complemento);
         valores.add(loteamentocondominio);
         
         
         valores.add(dataatual);
         valores.add(escrevente);
         valores.add(digitadapor);






         
		 
		/*
         
         campos.add("%registro%");
         campos.add("%livro%");
         campos.add("%tipo%");
		 registro = null;
		 livro = null;
		 tipo = null;
		 quadra = null;
		 lote = null;
		 area = null;
		 zona = null;
		 iptuitr = null;
		 ccirincra = null;
		 logradouro = null;
		 bairro = null;
		 municipioestado = null;
		 complemento = null;
		 dataatual = null;
		 escrevente = null;
		 digitadapor = null;
		*/
	}
	
	public void gerarPrevia() {
		try {
			nomeTemporaryFile = InetAddress.getLocalHost().getHostName() + "_" + cboxTipos.getSelectedItem().toString();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		oooExeFolder = "C:\\OpenOffice 4\\program\\";
		//modelo = "F:\\GeradorCertidoesNegativas\\modelo\\" + cboxTipos.getSelectedItem().toString() + ".odt";
        saidaODT = "F:\\GeradorCertidoesNegativas\\temporary\\" + nomeTemporaryFile + ".odt";
        
		

		boolean fileLocked = false;
		Scanner fileIn =null;
		try {
			fileIn = new Scanner(new File(modelo));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 file = new File(saidaODT);

		    if(file.isFile()) {
		    	fileLocked = file.renameTo(file);
		    	file.delete();
		    } else {
		    	fileLocked = true;
		    }
		 
		    if(fileLocked) {
		         try {
		        	 xContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
		             System.out.println("Connected to a running office ...");
		             xMCF =
		                 xContext.getServiceManager();

		             oDesktop = xMCF.createInstanceWithContext(
		                 "com.sun.star.frame.Desktop", xContext);

		             xCompLoader =
		                 UnoRuntime.queryInterface(
		                  com.sun.star.frame.XComponentLoader.class, oDesktop);

		            if ( modelo.indexOf("private:") != 0) {
		                java.io.File sourceFile = new java.io.File(modelo);
		                StringBuffer sbTmp = new StringBuffer("file:///");
		                sbTmp.append(sourceFile.getCanonicalPath().replace('\\', '/').replace('\\', '/'));
		                modelo = sbTmp.toString();
		             }
		             if ( saidaODT.indexOf("private:") != 0) {
		                 java.io.File sourceFile = new java.io.File(saidaODT);
		                 StringBuffer sbTmp = new StringBuffer("file:///");
		                 sbTmp.append(sourceFile.getCanonicalPath().replace('\\', '/'));
		                 saidaODT = sbTmp.toString();
		             }
		           

		             
		             // Load a Writer document, which will be automaticly displayed
		             xComp = xCompLoader.loadComponentFromURL(
		                 modelo, "_blank", 0, new com.sun.star.beans.PropertyValue[0]);
		             
		         }
		         catch( Exception e ) {
		             e.printStackTrace(System.err);
		         }
		         
		      
		         
		        
		         
		         aTextDocument = UnoRuntime.queryInterface(
		                 com.sun.star.text.XTextDocument.class, xComp);
		         xReplaceable = UnoRuntime.queryInterface(
		                 com.sun.star.util.XReplaceable.class, aTextDocument);
		         xReplaceDescr = xReplaceable.createReplaceDescriptor();
		         
		         
		         int i = 0;
		         while(i<campos.size()) {
		        	 System.out.println(campos.get(i) + " -> " + valores.get(i));

		        	 xReplaceDescr.setSearchString(campos.get(i).toString());
			         xReplaceDescr.setReplaceString(valores.get(i).toString());
			         xReplaceable.replaceAll( xReplaceDescr );

		        	 i++;
		         }
		         
		         
		         //xReplaceDescr.setSearchString("%protocolo%");
		        // xReplaceDescr.setReplaceString(tfProtocolo.getText());
		        // xReplaceDescr.setSearchString("%valor%");
		        // xReplaceDescr.setReplaceString(tfcirg.getText());
		         
		         //xReplaceable.replaceAll( xReplaceDescr );
		         
		        xStorable =
		                 UnoRuntime.queryInterface(
		                 com.sun.star.frame.XStorable.class, aTextDocument );
		         
		         com.sun.star.beans.PropertyValue[] propertyValue =
		                 new com.sun.star.beans.PropertyValue[1];
		         propertyValue = new com.sun.star.beans.PropertyValue[ 2 ];
		         propertyValue[0] = new com.sun.star.beans.PropertyValue();
		         propertyValue[0].Name = "Overwrite";
		         propertyValue[0].Value = new Boolean(true);
		         propertyValue[1] = new com.sun.star.beans.PropertyValue();
		         propertyValue[1].Name = "FilterName";
		         propertyValue[1].Value = "StarOffice XML (Writer)";
		 

		         try {
		 			xStorable.storeAsURL( saidaODT.toString(), propertyValue );
		 		} catch (com.sun.star.io.IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
		

		        
  

	}else {
		JOptionPane.showMessageDialog(null, "O Arquivo está aberto, feche-o.");
	}
	} 
	
	

		  
	
	

public void imprimirNegativa() {
	if(file != null) {
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	DateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	DateFormat fileNameFormat2 = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
	String protocolo = tfProtocolo.getText().trim();
	
	datacriada = dateFormat.format(cal.getTime()).toString(); 
	nomeDataArquivo = fileNameFormat.format(cal.getTime()).toString(); 
	nomeDataArquivo2 = fileNameFormat2.format(cal.getTime()).toString(); 
	saidaPDF = "F:\\GeradorCertidoesNegativas\\feitas\\" + "["+ nomeDataArquivo + "]" + cboxTipos.getSelectedItem().toString() +"_" + protocolo +"_"
				+ nomedopc + "_" + nomeDataArquivo2 + ".pdf";

      if ( saidaPDF.indexOf("private:") != 0) {
          java.io.File sourceFile = new java.io.File(saidaPDF);
          StringBuffer sbTmp = new StringBuffer("file:///");
          try {
			sbTmp.append(sourceFile.getCanonicalPath().replace('\\', '/'));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          saidaPDF = sbTmp.toString();
      }
	 try {
		xContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
	} catch (BootstrapException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}

	 xMCF =
             xContext.getServiceManager();

         try {
			oDesktop = xMCF.createInstanceWithContext(
			     "com.sun.star.frame.Desktop", xContext);
		} catch (com.sun.star.uno.Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

         xCompLoader =
             UnoRuntime.queryInterface(
              com.sun.star.frame.XComponentLoader.class, oDesktop);
         
	  try {
		xComp = xCompLoader.loadComponentFromURL(
		          saidaODT, "_blank", 0, new com.sun.star.beans.PropertyValue[0]);
	} catch (com.sun.star.io.IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalArgumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  
	  
	  aTextDocument = UnoRuntime.queryInterface(
            com.sun.star.text.XTextDocument.class, xComp);
	 xStorable = 
             UnoRuntime.queryInterface(
             com.sun.star.frame.XStorable.class, aTextDocument );
    PropertyValue[] storeProps = new PropertyValue[0];
    storeProps = new PropertyValue[1];
    storeProps[0] = new PropertyValue();
    storeProps[0].Name = "FilterName";
    storeProps[0].Value = "writer_pdf_Export";

    PropertyValue[] aMediaDescriptor = new PropertyValue[1];
    aMediaDescriptor[0] = new PropertyValue();
    aMediaDescriptor[0].Name = "FilterName";
    aMediaDescriptor[0].Value = "writer_pdf_Export";
    

    
    try {
		xStorable.storeToURL(saidaPDF, aMediaDescriptor);
	} catch (com.sun.star.io.IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    aTextDocument.dispose();
	
	
	
	
	EnviarCertidao();
	
	} else {
		JOptionPane.showMessageDialog(null, "Você deve primeiro gerar a certidão.");
	}
	
	
}
public void setUsuario(String nomeUsuario) {
	 this.nomeUsuario = nomeUsuario;
	 
}


public void EnviarCertidao() {
	 
	 enviaCertidao enviar = new enviaCertidao();
	 enviar.setDestino(cboxDestino.getSelectedItem().toString());
	enviar.enviacaminho(tfProtocolo.getText().trim(), nomeDataArquivo + "", cboxTipos.getSelectedItem().toString(), nomeUsuario, "");
	 
}


public boolean validaCPF(String strCpf){
    int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
    int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
    String strDigitoVerificador, strDigitoResultado;
 
    if (! strCpf.substring(0,1).equals("")){
        try{
            strCpf = strCpf.replace('.',' ');
            strCpf = strCpf.replace('-',' ');
            strCpf = strCpf.replaceAll(" ","");
            for (int iCont = 1; iCont < strCpf.length() -1; iCont++) {
                iDigitoCPF = Integer.valueOf(strCpf.substring(iCont -1, iCont)).intValue();
                iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
                iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
            }
            iRestoDivisao = (iDigito1Aux % 11);
            if (iRestoDivisao < 2) {
                iDigito1 = 0;
            } else {
                iDigito1 = 11 - iRestoDivisao;
            }
            iDigito2Aux += 2 * iDigito1;
            iRestoDivisao = (iDigito2Aux % 11);
            if (iRestoDivisao < 2) {
                iDigito2 = 0;
            } else {
                iDigito2 = 11 - iRestoDivisao;
            }
            strDigitoVerificador = strCpf.substring(strCpf.length()-2, strCpf.length());
            strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
            return strDigitoVerificador.equals(strDigitoResultado);
        } catch (Exception e) {
            return false;
        }
    } else {
        return false;
    }
}

public boolean validaCNPJ(String strCNPJ) {
    int iSoma = 0, iDigito;
    char[] chCaracteresCNPJ;
    String strCNPJ_Calculado;
 
    if (! strCNPJ.substring(0,1).equals("")){
        try{
            strCNPJ=strCNPJ.replace('.',' ');
            strCNPJ=strCNPJ.replace('/',' ');
            strCNPJ=strCNPJ.replace('-',' ');
            strCNPJ=strCNPJ.replaceAll(" ","");
            strCNPJ_Calculado = strCNPJ.substring(0,12);
            if ( strCNPJ.length() != 14 ) return false;
            chCaracteresCNPJ = strCNPJ.toCharArray();
            for(int i = 0; i < 4; i++) {
                if ((chCaracteresCNPJ[i]-48 >= 0) && (chCaracteresCNPJ[i]-48 <= 9)) {
                    iSoma += (chCaracteresCNPJ[i] - 48 ) * (6 - (i + 1)) ;
                }
            }
            for( int i = 0; i < 8; i++ ) {
                if ((chCaracteresCNPJ[i+4]-48 >= 0) && (chCaracteresCNPJ[i+4]-48 <= 9)) {
                    iSoma += (chCaracteresCNPJ[i+4] - 48 ) * (10 - (i + 1)) ;
                }
            }
            iDigito = 11 - (iSoma % 11);
            strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
               /* Segunda parte */
            iSoma = 0;
            for (int i = 0; i < 5; i++) {
                if ((chCaracteresCNPJ[i]-48 >= 0) && (chCaracteresCNPJ[i]-48 <= 9)) {
                    iSoma += (chCaracteresCNPJ[i] - 48) * (7 - (i + 1)) ;
                }
            }
            for (int i = 0; i < 8; i++) {
                if ((chCaracteresCNPJ[i+5]-48 >= 0) && (chCaracteresCNPJ[i+5]-48 <= 9)) {
                    iSoma += (chCaracteresCNPJ[i+5] - 48) * (10 - (i + 1)) ;
                }
            }
            iDigito = 11 - (iSoma % 11);
            strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
            return strCNPJ.equals(strCNPJ_Calculado);
        } catch (Exception e) {
            return false;
        }
    } else return false;
}

public void getModelo(String selecionado) {
	File model = new File("F:\\GeradorCertidoesNegativas\\modelo\\");
	File[] modelos = model.listFiles();
	int i = 0;
	while(i < modelos.length) {
		String nome = modelos[i].getName().replace(".odt", "");
		if(nome.matches(selecionado)) {
			modelo = modelos[i].getPath();
		}
		i++;
		
	}
	
}

public void adicionaTipos() {
	File tipo = new File("F:\\GeradorCertidoesNegativas\\modelo\\");
	File[] tipos = tipo.listFiles();
	int i = 0;
	cboxTipos.addItem("Escolha uma Certidão");
	jpanelsNome.add("Escolha uma Certidão");
	while(i < tipos.length) {
		if(tipos[i].getName().contains("lock") == false) {
		cboxTipos.addItem(tipos[i].getName().replace(".odt", ""));
		//jpanelsNome.add(tipos[i].getName().replace(".odt", ""));
		i++;
		}
	}
	
}

public void adicionarSegundaPessoa() {

	jdig.add(jdigpanel);
	jdig.setLocationRelativeTo(null);
	jdig.setSize(750,250);
	jdig.setVisible(true);
	
	jdigpanel.add(lblnomedaparte2);
	jdigpanel.add(tfnomedaparte2, "span, growx, wrap");
	tfnomedaparte2.setPreferredSize(new Dimension(0,tamanhodoscampos));

	jdigpanel.add(lblcpfcnpj2, "");
	jdigpanel.add(tfcpfcnpj2);
	tfcpfcnpj2.setPreferredSize(new Dimension(0,tamanhodoscampos));
	jdigpanel.add(lblcirg2);
	jdigpanel.add(tfcirg2);
	tfcirg2.setPreferredSize(new Dimension(0,tamanhodoscampos));
	jdigpanel.add(lblnacionalidade2);
	jdigpanel.add(cboxNacionalidade2, "wrap");
	cboxNacionalidade2.setPreferredSize(new Dimension(0,tamanhodoscampos));
	jdigpanel.add(cpfcnpjinvalido2, "wrap");
	tfcpfcnpj2.addFocusListener(new FocusListener(){
        @Override
        public void focusGained(FocusEvent e){ }
		public void focusLost(FocusEvent arg0) {
			if(tfcpfcnpj2.getText().length() > 8 && tfcpfcnpj2.isValid()) { 
			String numeroDigitado = tfcpfcnpj2.getText().replaceAll("\\D", "");
			if(numeroDigitado.length() < 12) {
				if(validaCPF(numeroDigitado) == true) {
					cpfcnpjinvalido2.setText("CPF VÁLIDO.");
					validaCPFCNPJ = true;
					tfcpfcnpj2.setText(formataValor(numeroDigitado, "###.###.###-##"));
				} else {
					validaCPFCNPJ = false;

					cpfcnpjinvalido2.setText("CPF INVÁLIDO");
				}
				
			} else {
				if(validaCNPJ(numeroDigitado) == true) {
					validaCPFCNPJ = true;

					cpfcnpjinvalido2.setText("CNPJ VÁLIDO.");
					tfcpfcnpj2.setText(formataValor(numeroDigitado, "##.###.###/####-##"));

				} else {
					validaCPFCNPJ = false;

					cpfcnpjinvalido2.setText("CNPJ INVÁLIDO");
				}					
			}
			}				
		}
    });
	
	
	
	jdigpanel.add(lblestadocivil2, "");
	jdigpanel.add(cboxestadocivil2);
	cboxestadocivil2.setPreferredSize(new Dimension(0,tamanhodoscampos));
	jdigpanel.add(lblprofissao2);
	jdigpanel.add(tfprofissao2, "wrap");
	tfprofissao.setPreferredSize(new Dimension(0,tamanhodoscampos));
	jdigpanel.add(lblendereco2,"");
	jdigpanel.add(tfendereco2, "span, growx, wrap");
	tfendereco.setPreferredSize(new Dimension(0,tamanhodoscampos));
	jdigpanel.add(lblEstados2, "");
	jdigpanel.add(cboxEstados2);
	cboxEstados2.setPreferredSize(new Dimension(0,tamanhodoscampos));
	cboxEstados2.addItemListener(new ItemListener(){
        public void itemStateChanged(ItemEvent e){
        	if(e.getStateChange() == ItemEvent.SELECTED && cboxEstados2.getSelectedIndex() > 0) {
        		cboxCidades2.removeAllItems();
        	    populaMunicipio2(cboxEstados2.getSelectedIndex());
        	} else {
        		cboxCidades2.removeAllItems();
        		cboxCidades2.addItem("");
        		
        	}
        	}
    });
	
	jdigpanel.add(lblCidades2);
	jdigpanel.add(cboxCidades2, "span, wrap");
	jdigpanel.add(btnSalvarSegundaParte, "span, center, wrap");
	//jdigpanel.add(btnLimpar2, "span, right");
	
	btnSalvarSegundaParte.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(cpfcnpjinvalido2.getText().contains("INVÁLIDO") || cpfcnpjinvalido2.getText().matches("") == true ) {
				JOptionPane.showMessageDialog(null, "Digite um CPF ou CNPJ VÁLIDO");
			} else {
			 nomedaparte2 = tfnomedaparte2.getText();
			 cirg2 = tfcirg2.getText();
			 cpfcnpj2 = tfcpfcnpj2.getText();
			 nacionalidade2 = cboxNacionalidade2.getSelectedItem().toString();
			 estadocivil2 = cboxestadocivil2.getSelectedItem().toString();
			 profissao2 = tfprofissao2.getText();
			 endereco2 = tfendereco2.getText(); 
			
			 if(cboxEstados2.getSelectedIndex() == 0) { 
				 cidadeestado2 = "";
			 } else {
				 cidadeestado2 = validaTextField(cboxCidades2.getSelectedItem().toString() + "/" + estadosSiglas.get(cboxEstados2.getSelectedIndex()));

			 }
			 
			 jdig.dispose();
			}
		
		}
	});
	
	
	
	
	
	
}

public void adicionaEstados() {
	cboxEstados.addItem("  ");
	cboxEstados.addItem("Acre");
	cboxEstados.addItem("Alagoas");
	cboxEstados.addItem("Amazonas");
	cboxEstados.addItem("Amapá");
	cboxEstados.addItem("Bahia");
	cboxEstados.addItem("Ceará");
	cboxEstados.addItem("Distrito Federal");
	cboxEstados.addItem("Espírito Santo");
	cboxEstados.addItem("Goiás");
	cboxEstados.addItem("Maranhão");
	cboxEstados.addItem("Minas Gerais");
	cboxEstados.addItem("Mato Grosso do Sul");
	cboxEstados.addItem("Mato Grosso");
	cboxEstados.addItem("Pará");
	cboxEstados.addItem("Paraíba");
	cboxEstados.addItem("Pernambuco");
	cboxEstados.addItem("Piauí");
	cboxEstados.addItem("Paraná");
	cboxEstados.addItem("Rio de Janeiro");
	cboxEstados.addItem("Rio Grande do Norte");
	cboxEstados.addItem("Rondônia");
	cboxEstados.addItem("Roraima");
	cboxEstados.addItem("Rio Grande do Sul");
	cboxEstados.addItem("Santa Catarina");
	cboxEstados.addItem("Sergipe");
	cboxEstados.addItem("São Paulo");
	cboxEstados.addItem("Tocantins");
	
	cboxEstados2.addItem("  ");
	cboxEstados2.addItem("Acre");
	cboxEstados2.addItem("Alagoas");
	cboxEstados2.addItem("Amazonas");
	cboxEstados2.addItem("Amapá");
	cboxEstados2.addItem("Bahia");
	cboxEstados2.addItem("Ceará");
	cboxEstados2.addItem("Distrito Federal");
	cboxEstados2.addItem("Espírito Santo");
	cboxEstados2.addItem("Goiás");
	cboxEstados2.addItem("Maranhão");
	cboxEstados2.addItem("Minas Gerais");
	cboxEstados2.addItem("Mato Grosso do Sul");
	cboxEstados2.addItem("Mato Grosso");
	cboxEstados2.addItem("Pará");
	cboxEstados2.addItem("Paraíba");
	cboxEstados2.addItem("Pernambuco");
	cboxEstados2.addItem("Piauí");
	cboxEstados2.addItem("Paraná");
	cboxEstados2.addItem("Rio de Janeiro");
	cboxEstados2.addItem("Rio Grande do Norte");
	cboxEstados2.addItem("Rondônia");
	cboxEstados2.addItem("Roraima");
	cboxEstados2.addItem("Rio Grande do Sul");
	cboxEstados2.addItem("Santa Catarina");
	cboxEstados2.addItem("Sergipe");
	cboxEstados2.addItem("São Paulo");
	cboxEstados2.addItem("Tocantins");
	
	estadosSiglas.add("");
	estadosSiglas.add("AC");
	estadosSiglas.add("AL");
	estadosSiglas.add("AP");
	estadosSiglas.add("AM");
	estadosSiglas.add("BA");
	estadosSiglas.add("CE");
	estadosSiglas.add("DF");
	estadosSiglas.add("ES");
	estadosSiglas.add("GO");
	estadosSiglas.add("MA");
	estadosSiglas.add("MT");
	estadosSiglas.add("MS");
	estadosSiglas.add("MG");
	estadosSiglas.add("PR");
	estadosSiglas.add("PB");
	estadosSiglas.add("PA");
	estadosSiglas.add("PE");
	estadosSiglas.add("PI");
	estadosSiglas.add("RJ");
	estadosSiglas.add("RN");
	estadosSiglas.add("RS");
	estadosSiglas.add("RO");
	estadosSiglas.add("RR");
	estadosSiglas.add("SC");
	estadosSiglas.add("SE");
	estadosSiglas.add("SP");
	estadosSiglas.add("TO");
	
	cboxmunicipioestado.addItem("Aruja/SP");
	cboxmunicipioestado.addItem("Igaratá/SP");
	cboxmunicipioestado.addItem("Santa Isabel/SP");
	
	
	
	
	
}

public void adicionaEscreventesAuxiliares() {
	Connection conn = null;
	Statement stmt = null;
	ResultSet results = null;
	String query = "SELECT * FROM usuarios";
	conexao conecta = new conexao();
	try {
		conn = conecta.abreconexao();
		stmt = conn.createStatement ();
		results = stmt.executeQuery (query + " order by usuario asc");

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		while(results.next()) {
			cboxEscrevente.addItem(results.getString("usuario"));
			cboxDigitadapor.addItem(results.getString("usuario"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
	/*
	
	ArrayList nomes = new ArrayList();
	nomes.add("Escrevente 111111");
	
	
	int i = 0;
	while(i < nomes.size()) {
		cboxDigitadapor.addItem(nomes.get(i));
		cboxEscrevente.addItem(nomes.get(i));
		i++;
	}
	*/
}

public void adicionaNacionalidades() {
	ArrayList nacionalidades = new ArrayList();
	nacionalidades.add("Afeganistão");
	nacionalidades.add("África do Sul");
	nacionalidades.add("Akrotiri");
	nacionalidades.add("Albânia");
	nacionalidades.add("Alemanha");
	nacionalidades.add("Andorra");
	nacionalidades.add("Angola");
	nacionalidades.add("Anguila");
	nacionalidades.add("Antárctida");
	nacionalidades.add("Antígua e Barbuda");
	nacionalidades.add("Antilhas Neerlandesas");
	nacionalidades.add("Arábia Saudita");
	nacionalidades.add("Arctic Ocean");
	nacionalidades.add("Argélia");
	nacionalidades.add("Argentina");
	nacionalidades.add("Arménia");
	nacionalidades.add("Aruba");
	nacionalidades.add("Ashmore and Cartier Islands");
	nacionalidades.add("Atlantic Ocean");
	nacionalidades.add("Austrália");
	nacionalidades.add("Áustria");
	nacionalidades.add("Azerbaijão");
	nacionalidades.add("Baamas");
	nacionalidades.add("Bangladeche");
	nacionalidades.add("Barbados");
	nacionalidades.add("Barém");
	nacionalidades.add("Bélgica");
	nacionalidades.add("Belize");
	nacionalidades.add("Benim");
	nacionalidades.add("Bermudas");
	nacionalidades.add("Bielorrússia");
	nacionalidades.add("Birmânia");
	nacionalidades.add("Bolívia");
	nacionalidades.add("Bósnia e Herzegovina");
	nacionalidades.add("Botsuana");
	nacionalidades.add("Brasil");
	nacionalidades.add("Brunei");
	nacionalidades.add("Bulgária");
	nacionalidades.add("Burquina Faso");
	nacionalidades.add("Burúndi");
	nacionalidades.add("Butão");
	nacionalidades.add("Cabo Verde");
	nacionalidades.add("Camarões");
	nacionalidades.add("Camboja");
	nacionalidades.add("Canadá");
	nacionalidades.add("Catar");
	nacionalidades.add("Cazaquistão");
	nacionalidades.add("Chade");
	nacionalidades.add("Chile");
	nacionalidades.add("China");
	nacionalidades.add("Chipre");
	nacionalidades.add("Clipperton Island");
	nacionalidades.add("Colômbia");
	nacionalidades.add("Comores");
	nacionalidades.add("Congo-Brazzaville");
	nacionalidades.add("Congo-Kinshasa");
	nacionalidades.add("Coral Sea Islands");
	nacionalidades.add("Coreia do Norte");
	nacionalidades.add("Coreia do Sul");
	nacionalidades.add("Costa do Marfim");
	nacionalidades.add("Costa Rica");
	nacionalidades.add("Croácia");
	nacionalidades.add("Cuba");
	nacionalidades.add("Dhekelia");
	nacionalidades.add("Dinamarca");
	nacionalidades.add("Domínica");
	nacionalidades.add("Egipto");
	nacionalidades.add("Emiratos Árabes Unidos");
	nacionalidades.add("Equador");
	nacionalidades.add("Eritreia");
	nacionalidades.add("Eslováquia");
	nacionalidades.add("Eslovénia");
	nacionalidades.add("Espanha");
	nacionalidades.add("Estados Unidos");
	nacionalidades.add("Estónia");
	nacionalidades.add("Etiópia");
	nacionalidades.add("Faroé");
	nacionalidades.add("Fiji");
	nacionalidades.add("Filipinas");
	nacionalidades.add("Finlândia");
	nacionalidades.add("França");
	nacionalidades.add("Gabão");
	nacionalidades.add("Gâmbia");
	nacionalidades.add("Gana");
	nacionalidades.add("Gaza Strip");
	nacionalidades.add("Geórgia");
	nacionalidades.add("Geórgia do Sul e Sandwich do Sul");
	nacionalidades.add("Gibraltar");
	nacionalidades.add("Granada");
	nacionalidades.add("Grécia");
	nacionalidades.add("Gronelândia");
	nacionalidades.add("Guame");
	nacionalidades.add("Guatemala");
	nacionalidades.add("Guernsey");
	nacionalidades.add("Guiana");
	nacionalidades.add("Guiné");
	nacionalidades.add("Guiné Equatorial");
	nacionalidades.add("Guiné-Bissau");
	nacionalidades.add("Haiti");
	nacionalidades.add("Honduras");
	nacionalidades.add("Hong Kong");
	nacionalidades.add("Hungria");
	nacionalidades.add("Iémen");
	nacionalidades.add("Ilha Bouvet");
	nacionalidades.add("Ilha do Natal");
	nacionalidades.add("Ilha Norfolk");
	nacionalidades.add("Ilhas Caimão");
	nacionalidades.add("Ilhas Cook");
	nacionalidades.add("Ilhas dos Cocos");
	nacionalidades.add("Ilhas Falkland");
	nacionalidades.add("Ilhas Heard e McDonald");
	nacionalidades.add("Ilhas Marshall");
	nacionalidades.add("Ilhas Salomão");
	nacionalidades.add("Ilhas Turcas e Caicos");
	nacionalidades.add("Ilhas Virgens Americanas");
	nacionalidades.add("Ilhas Virgens Britânicas");
	nacionalidades.add("Índia");
	nacionalidades.add("Indian Ocean");
	nacionalidades.add("Indonésia");
	nacionalidades.add("Irão");
	nacionalidades.add("Iraque");
	nacionalidades.add("Irlanda");
	nacionalidades.add("Islândia");
	nacionalidades.add("Israel");
	nacionalidades.add("Itália");
	nacionalidades.add("Jamaica");
	nacionalidades.add("Jan Mayen");
	nacionalidades.add("Japão");
	nacionalidades.add("Jersey");
	nacionalidades.add("Jibuti");
	nacionalidades.add("Jordânia");
	nacionalidades.add("Kuwait");
	nacionalidades.add("Laos");
	nacionalidades.add("Lesoto");
	nacionalidades.add("Letónia");
	nacionalidades.add("Líbano");
	nacionalidades.add("Libéria");
	nacionalidades.add("Líbia");
	nacionalidades.add("Listenstaine");
	nacionalidades.add("Lituânia");
	nacionalidades.add("Luxemburgo");
	nacionalidades.add("Macau");
	nacionalidades.add("Macedónia");
	nacionalidades.add("Madagáscar");
	nacionalidades.add("Malásia");
	nacionalidades.add("Malávi");
	nacionalidades.add("Maldivas");
	nacionalidades.add("Mali");
	nacionalidades.add("Malta");
	nacionalidades.add("Man, Isle of");
	nacionalidades.add("Marianas do Norte");
	nacionalidades.add("Marrocos");
	nacionalidades.add("Maurícia");
	nacionalidades.add("Mauritânia");
	nacionalidades.add("Mayotte");
	nacionalidades.add("México");
	nacionalidades.add("Micronésia");
	nacionalidades.add("Moçambique");
	nacionalidades.add("Moldávia");
	nacionalidades.add("Mónaco");
	nacionalidades.add("Mongólia");
	nacionalidades.add("Monserrate");
	nacionalidades.add("Montenegro");
	nacionalidades.add("Mundo");
	nacionalidades.add("Namíbia");
	nacionalidades.add("Nauru");
	nacionalidades.add("Navassa Island");
	nacionalidades.add("Nepal");
	nacionalidades.add("Nicarágua");
	nacionalidades.add("Níger");
	nacionalidades.add("Nigéria");
	nacionalidades.add("Niue");
	nacionalidades.add("Noruega");
	nacionalidades.add("Nova Caledónia");
	nacionalidades.add("Nova Zelândia");
	nacionalidades.add("Omã");
	nacionalidades.add("Pacific Ocean");
	nacionalidades.add("Países Baixos");
	nacionalidades.add("Palau");
	nacionalidades.add("Panamá");
	nacionalidades.add("Papua-Nova Guiné");
	nacionalidades.add("Paquistão");
	nacionalidades.add("Paracel Islands");
	nacionalidades.add("Paraguai");
	nacionalidades.add("Peru");
	nacionalidades.add("Pitcairn");
	nacionalidades.add("Polinésia Francesa");
	nacionalidades.add("Polónia");
	nacionalidades.add("Porto Rico");
	nacionalidades.add("Portugal");
	nacionalidades.add("Quénia");
	nacionalidades.add("Quirguizistão");
	nacionalidades.add("Quiribáti");
	nacionalidades.add("Reino Unido");
	nacionalidades.add("República Centro-Africana");
	nacionalidades.add("República Checa");
	nacionalidades.add("República Dominicana");
	nacionalidades.add("Roménia");
	nacionalidades.add("Ruanda");
	nacionalidades.add("Rússia");
	nacionalidades.add("Salvador");
	nacionalidades.add("Samoa");
	nacionalidades.add("Samoa Americana");
	nacionalidades.add("Santa Helena");
	nacionalidades.add("Santa Lúcia");
	nacionalidades.add("São Cristóvão e Neves");
	nacionalidades.add("São Marinho");
	nacionalidades.add("São Pedro e Miquelon");
	nacionalidades.add("São Tomé e Príncipe");
	nacionalidades.add("São Vicente e Granadinas");
	nacionalidades.add("Sara Ocidental");
	nacionalidades.add("Seicheles");
	nacionalidades.add("Senegal");
	nacionalidades.add("Serra Leoa");
	nacionalidades.add("Sérvia");
	nacionalidades.add("Singapura");
	nacionalidades.add("Síria");
	nacionalidades.add("Somália");
	nacionalidades.add("Southern Ocean");
	nacionalidades.add("Spratly Islands");
	nacionalidades.add("Sri Lanca");
	nacionalidades.add("Suazilândia");
	nacionalidades.add("Sudão");
	nacionalidades.add("Suécia");
	nacionalidades.add("Suíça");
	nacionalidades.add("Suriname");
	nacionalidades.add("Svalbard e Jan Mayen");
	nacionalidades.add("Tailândia");
	nacionalidades.add("Taiwan");
	nacionalidades.add("Tajiquistão");
	nacionalidades.add("Tanzânia");
	nacionalidades.add("Território Britânico do Oceano Índico");
	nacionalidades.add("Territórios Austrais Franceses");
	nacionalidades.add("Timor Leste");
	nacionalidades.add("Togo");
	nacionalidades.add("Tokelau");
	nacionalidades.add("Tonga");
	nacionalidades.add("Trindade e Tobago");
	nacionalidades.add("Tunísia");
	nacionalidades.add("Turquemenistão");
	nacionalidades.add("Turquia");
	nacionalidades.add("Tuvalu");
	nacionalidades.add("Ucrânia");
	nacionalidades.add("Uganda");
	nacionalidades.add("União Europeia");
	nacionalidades.add("Uruguai");
	nacionalidades.add("Usbequistão");
	nacionalidades.add("Vanuatu");
	nacionalidades.add("Vaticano");
	nacionalidades.add("Venezuela");
	nacionalidades.add("Vietname");
	nacionalidades.add("Wake Island");
	nacionalidades.add("Wallis e Futuna");
	nacionalidades.add("West Bank");
	nacionalidades.add("Zâmbia");
	nacionalidades.add("Zimbabué");

	int i = 0;
	while(i < nacionalidades.size()) {
		cboxNacionalidade.addItem(nacionalidades.get(i));
		cboxNacionalidade2.addItem(nacionalidades.get(i));
		i++;
	}
	
	cboxNacionalidade.setSelectedItem("Brasil");
	cboxNacionalidade2.setSelectedItem("Brasil");

}





public void adicionaEstadoCivil() {
	 cboxestadocivil.addItem("");
	 cboxestadocivil.addItem("Casado(a)");
	 cboxestadocivil.addItem("Divorciado(a)");
	 cboxestadocivil.addItem("Solteiro(a)");
	 cboxestadocivil.addItem("Viúvo(a)");
	 cboxestadocivil.addItem("Separado(a) Judicialmente");
	 cboxestadocivil.addItem("Desquitado(a)");
	 
	 cboxestadocivil2.addItem("");
	 cboxestadocivil2.addItem("Casado(a)");
	 cboxestadocivil2.addItem("Divorciado(a)");
	 cboxestadocivil2.addItem("Solteiro(a)");
	 cboxestadocivil2.addItem("Viúvo(a)");
	 cboxestadocivil2.addItem("Separado(a) Judicialmente");
	 cboxestadocivil2.addItem("Desquitado(a)");

	
}

	
	public JPanel getCard() {
		montaPanel();
		return menuitem43;
	}
	
}
