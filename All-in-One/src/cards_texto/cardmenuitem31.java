package cards_texto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class cardmenuitem31 {
	String caminhooriginal = null;
	static String caminhobackup = "F:\\registro_sql\\Livro2RTFBackups\\";
	JPanel menuitem31 = new JPanel(new MigLayout("fillx"));
	JLabel lblNumero = new JLabel("Número:");
	JTextField tfMatricula = new JTextField(10);
	JButton btnCorrigir = new JButton("Corrigir Texto");
	JLabel lblAvisoCorrigir = new JLabel(" ");
	JLabel lblComoUsar1 = new JLabel("");
	JLabel lblComoUsar2 = new JLabel("");

	public void montaPanel() {

		menuitem31.add(lblNumero, "span, split 3, center");
		menuitem31.add(tfMatricula);
		menuitem31.add(btnCorrigir, "wrap");
		btnCorrigir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfMatricula.getText().matches("[0-9]+") == false) {
					lblAvisoCorrigir
							.setText("Devem ser digitado apenas números");

				} else {
					if (tfMatricula.getText().length() > 8) {
						lblAvisoCorrigir
								.setText("Matriculas possuem 6 números, transcrições 8.");
					} else {
						// String numero = String.format("%08d",
						// Integer.parseInt(tfMatricula.getText()));

						fazCorrecao(tfMatricula.getText());
					}
				}
			}
		});

		menuitem31.add(lblAvisoCorrigir, "span, center, wrap");
		lblAvisoCorrigir.setForeground(Color.red);
		menuitem31.add(lblComoUsar1, "span, center, wrap");
		menuitem31.add(lblComoUsar2, "span, center");
		String comoUsar1 = "1) Para Matriculas devem ser digitado apenas o número dela, ex: 5000, 6883, 25555;";
		String comoUsar2 = "2) Para transcrições o número deve estar completo, contendo todos os digitos, ex: 31025000, 30008000, 80000005.";

		lblComoUsar1.setText(comoUsar1);
		lblComoUsar2.setText(comoUsar2);

	}

	public void fazCorrecao(String numero) {

		int indisponibilidade = Integer.parseInt(numero);
		String subdir = "0000";
		numero = Integer.parseInt(numero) + "";
		if (Integer.parseInt(numero) < 99999) {
			int confere = Integer.parseInt(numero);
			if (confere <= 999) {
				numero = String.format("%08d", Integer.parseInt(numero));

			}
			if (confere > 999 && confere <= 9999) {
				subdir = "00" + numero.substring(0, 2);

				numero = String.format("%08d", Integer.parseInt(numero));

			}

			if (confere > 9999 && confere <= 99999) {
				subdir = "0" + numero.substring(0, 3);

				numero = String.format("%08d", Integer.parseInt(numero));

			}
			if (Integer.parseInt(numero) > 50400000
					&& Integer.parseInt(numero) <= 60508000) {

			}
			
			System.out.println(numero);


			File file = new File("F:\\registro_sql\\LIVRO02\\" + subdir);
			File[] arquivos = file.listFiles();
			boolean achou = false;

			int i = 0;
			while (i < arquivos.length) {
				if (arquivos[i].getName().matches(numero + ".rtf")) {
					achou = true;
					String caminho = arquivos[i].getPath();
					corrigir(caminho, arquivos[i].getName());
					caminhooriginal = arquivos[i].getPath();
					try {
						fazacopia(arquivos[i].getPath(), arquivos[i].getName(),
								caminho);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (!achou) {
					lblAvisoCorrigir
							.setText("Matricula/Transcrição não encontrada.");
				} else {
					lblAvisoCorrigir.setText("Matricula [" + numero
							+ "] corrigida.");
				}
				i++;

			}
		} else {

			boolean achou = false;

			String matricula = String.format("%08d",
					Integer.parseInt(tfMatricula.getText()));
			int confere = Integer.parseInt(matricula.substring(3,
					matricula.length()));

			if (confere >= 1 && confere <= 99) {
				subdir = "0000";

			}
			if (confere > 99 && confere <= 999) {
				subdir = "000" + confere / 100;

			}
			if (confere > 999 && confere <= 9999) {
				subdir = "00" + confere / 100;

			}
			if (confere > 9999 && confere <= 99999) {
				subdir = "0" + confere / 100;

			}

			File file = new File("F:\\registro_sql\\LIVRO02\\" + subdir);
			File[] arquivos = file.listFiles();
			int i = 0;
			while (i < arquivos.length) {
				if (arquivos[i].getName().matches(numero + ".rtf")) {
					achou = true;
					String caminho = arquivos[i].getPath();
					corrigir(caminho, arquivos[i].getName());
					caminhooriginal = arquivos[i].getPath();
					try {
						fazacopia(arquivos[i].getPath(), arquivos[i].getName(),
								caminho);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (!achou) {
					lblAvisoCorrigir
							.setText("Matricula/Transcrição não encontrada.");
				} else {
					lblAvisoCorrigir.setText("Transcrição [" + numero
							+ "] corrigida.");
				}
				i++;

			}

		}
	}

	public void corrigir(String caminho, String nome) {
		System.out.println(caminho);
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new File(caminho));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file = new File(caminhobackup + nome);
		if (file.isFile()) {
			file.delete();
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				line = line.replace("fcharset128 Arial", "fcharset0 Arial")
						.replaceAll("\\\\line", "\\\\par");
				try {
					writeFile(line, nome, caminho);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				line = line.replace("fcharset128 Arial", "fcharset0 Arial")
						.replace("\\line", "\\par");
				try {
					writeFile(line, nome, caminho);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

	public static void writeFile(String copyText, String nome, String caminho)
			throws Exception {

		String newLine = System.getProperty("line.separator");

		// Location of file to output
		Writer output = null;

		File file = new File(caminhobackup + nome);
		output = new BufferedWriter(new FileWriter(file, true));
		output.write(copyText);
		output.write(newLine);
		output.close();

	}

	public void fazacopia(String origem, String nome, String caminho)
			throws IOException, InterruptedException {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy");
		GregorianCalendar teste = new GregorianCalendar();

		SimpleDateFormat teste2 = new SimpleDateFormat("HH_mm_hh");
		caminho = "F:\\";
		System.out.println(caminho);
		System.out.println(nome);
		String[] args = {
				"CMD",
				"/C",
				"COPY",
				"/Y",
				origem,
				caminhobackup + "\\" + nome.replace(".rtf", "") + "_"
						+ formatarDate.format(data) + "_"
						+ teste2.format(teste.getTime()) + "_" + "__backup.rtf" };
		Process p = Runtime.getRuntime().exec(args);
		p.waitFor();

		String[] args2 = { "CMD", "/C", "COPY", "/Y",
				caminhobackup + "\\" + nome, caminhooriginal };
		Process p2 = Runtime.getRuntime().exec(args2);
		p2.waitFor();
	}

	public JPanel getCard() {

		montaPanel();

		return menuitem31;
	}

}
