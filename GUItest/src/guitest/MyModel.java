package guitest;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class MyModel extends AbstractTableModel  
{
    private String[] columns;
    private Object[][] rows;
    
    public MyModel(){}
    
    public MyModel(Object[][] data, String[] columnName){
    
        this.rows = data;
        this.columns = columnName;
    }

    
     @Override
    public Class getColumnClass(int column){
// 5 is the index of the column image
        if(column == 5){
            return Icon.class;
        }
        else{
            return getValueAt(0,column).getClass();
        }
    }
    
    
     @Override
    public int getRowCount() {
     return this.rows.length;
    }

     @Override
    public int getColumnCount() {
     return this.columns.length;
    }

    
     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    
    return this.rows[rowIndex][columnIndex];
    }
     @Override
    public String getColumnName(int col){
        return this.columns[col];
    }    
}
