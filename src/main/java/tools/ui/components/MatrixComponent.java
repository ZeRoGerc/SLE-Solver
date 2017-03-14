package tools.ui.components;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

import static java.lang.Double.valueOf;
import static tools.ui.components.UIConstants.COLUMNS;
import static tools.ui.components.UIConstants.ROWS;

public class MatrixComponent extends UIComponent {

    private MatrixComponent(@NotNull Component component) {
        super(component);
    }

    @NotNull
    public static MatrixComponent createMatrix() {
        TableModel dataModel = new Table(ROWS, COLUMNS);
        JTable table = new JTable(dataModel);

        table.setTableHeader(null);
        table.setCellSelectionEnabled(true);

        JScrollPane scrollpane = new JScrollPane(table);

        return new MatrixComponent(scrollpane);
    }

    private static class Table extends AbstractTableModel {

        final int rows;

        final int columns;

        @NotNull
        final Double[][] data;

        public Table(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            this.data = new Double[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    data[i][j] = valueOf(i + j);
                }
            }
        }

        public int getRowCount() {
            return rows;
        }

        public int getColumnCount() {
            return columns;
        }

        public boolean isCellEditable(int row, int col) {
            return true;
        }

        @NotNull
        public Double getValueAt(int row, int col) { return data[row][col]; }

        public void setValueAt(@NotNull Object value, int row, int col) {
            try {
                if (value instanceof String) {
                    Double newValue = Double.parseDouble(((String) value));
                    data[row][col] = newValue;
                }
            } catch (NumberFormatException e) {
                // TODO: do something
            } finally {
                fireTableCellUpdated(row, col);
            }
        }
    }
}
