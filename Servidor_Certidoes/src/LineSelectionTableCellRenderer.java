import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LineSelectionTableCellRenderer   
	    extends DefaultTableCellRenderer   
	{  
	  
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Override  
	    public Component getTableCellRendererComponent(  
	        JTable table,  
	        Object value,  
	        boolean isSelected,  
	        boolean hasFocus,  
	        int row,  
	        int column)  
	    {  
	        Component result = super.getTableCellRendererComponent(  
	            table,  
	            value,  
	            isSelected,  
	            hasFocus,  
	            row,  
	            column  
	        ); 
	        
	        
	        
	        if(isSelected) {  
		           result.setFont(new Font("arial", Font.BOLD, 13));  
		           result.setForeground(Color.white);  
		           result.setBackground(Color.blue); 
		        
		        } 
	        

	        else if(column == 2 && table.getModel().getValueAt(row, 2).toString().contains("Matricula")) {
	            
	         result.setFont(new Font("arial", Font.BOLD, 13));  
	           result.setBackground(Color.green); 
	        		   }
	        
	        
	        else if(column == 2 && table.getModel().getValueAt(row, 2).toString().contains("Transcrição")) {

	          result.setFont(new Font("arial", Font.BOLD, 13));  
	            result.setBackground(Color.yellow); 
	        }
	        else if(column == 2 && table.getModel().getValueAt(row, 2).toString().contains("Transcrição") == false && column == 2 && table.getModel().getValueAt(row, 2).toString().contains("Matricula") == false  ) {
	        	
	            result.setFont(new Font("arial", Font.BOLD, 13));  

	            result.setBackground(Color.red); 

	        }

	        
	        

	       else {  
	    	  
	           result.setFont(new Font("arial", Font.PLAIN, 12));  
	          result.setBackground(Color.white);
	          result.setForeground(Color.black);
	      }  
	        
	        return result;  
	    }  
	      
	}  