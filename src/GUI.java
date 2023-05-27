import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GUI extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

    	
    	if (row % 2 == 0) {
            this.setBackground(new Color(236, 240, 241));
        } else {
        	this.setBackground(new Color(236, 240, 241));
        }
    	
    	
    	if(column == 3) {
    		this.setBackground(Color.decode("#f08080"));
    	}
    	
    	if(column == 2) {
    		this.setBackground(Color.decode("#95d5b2"));
    	}
    	
    	if(column ==4 && value.equals("EFECTIVO")) {
    		this.setBackground(Color.decode("#ffea83"));
    	}
    	if(column ==4 && value.equals("QR")) {
    		this.setBackground(Color.decode("#60b6fb"));
    	}
    	if(column ==4 && value.equals("TARJETA")) {
    		this.setBackground(Color.decode("#f4a261"));
    	}
    	
    	
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}