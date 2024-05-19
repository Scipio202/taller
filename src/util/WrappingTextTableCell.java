package util;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class WrappingTextTableCell extends TableCell<String[], String> {

    /*private final Label label;

    public WrappingTextTableCell() {
        this.label = new Label();
        this.label.setWrapText(true);
        this.label.setTextAlignment(TextAlignment.JUSTIFY);
        this.label.setMaxWidth(Double.MAX_VALUE);
        setGraphic(label);
        setPrefHeight(Control.USE_COMPUTED_SIZE);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
    	 super.updateItem(item, empty);
    	    if (empty || item == null) {
    	        label.setText(null);
    	        setGraphic(null);
    	    } else {
    	        // Establecer el ancho máximo de la celda para envolver el texto cada cierta cantidad de caracteres
    	        int maxCharactersPerLine = 60; // Cambia este valor según tus necesidades
    	        label.setMaxWidth(maxCharactersPerLine * 7); // Aproximadamente 7 píxeles por carácter
    	        label.setText(item);
    	        setGraphic(label);
    	    }
    }*/
	
	 @Override
	    protected void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        if (empty || item == null) {
	            setText(null);
	            setGraphic(null);
	        } else {
	            setText(item);
	            setWrapText(true); // Asegúrate de que esto está habilitado
	        }
	    }

	    public static Callback<TableColumn<String[], String>, TableCell<String[], String>> forTableColumn() {
	        return param -> new WrappingTextTableCell();
	    }
}
