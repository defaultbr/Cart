package cards_administracao;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class SimpleTableModel extends AbstractTableModel{  

    private ArrayList linhas = null;  
    private String [] colunas = null;  
    public String[] getColunas() {return colunas;}  
    public ArrayList getLinhas() {return linhas;}  
    public void setColunas(String[] strings) {colunas = strings;}  
    public void setLinhas(ArrayList list) {linhas = list;}
	@Override
	/** 
	 * Retorna o numero de colunas no modelo 
	 * @see javax.swing.table.TableModel#getColumnCount() 
	 */  
	public int getColumnCount() {return getColunas().length;}  
	  
	/** 
	 * Retorna o numero de linhas existentes no modelo 
	 * @see javax.swing.table.TableModel#getRowCount() 
	 */  
	public String getColumnName(int col){  
	    return getColunas()[col];  
	}  
	
	public int getRowCount() {return getLinhas().size();}  
	  
	/** 
	 * Obtem o valor na linha e coluna 
	 * @see javax.swing.table.TableModel#getValueAt(int, int) 
	 */  
	public Object getValueAt(int rowIndex, int columnIndex) {  
	    // Obtem a linha, que é uma String []  

	    String [] linha = (String [])getLinhas().get(rowIndex); 
	    
	    // Retorna o objeto que esta na coluna  
	    return linha[columnIndex];  
	}  

	public void removeRow(int row){    
        getLinhas().remove(row);           
    }   

	public boolean deleteX(int line) {
        this.removeRow(line);
        this.fireTableRowsDeleted(line, line);
        return true;
    }
	
	

public SimpleTableModel(ArrayList dados, String[] colunas){  
    setLinhas(dados);  
    setColunas(colunas);  
}  
 
}