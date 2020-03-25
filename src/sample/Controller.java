package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField sizeBtn;
    @FXML
    private Canvas canvas;
    @FXML
    private CheckBox rubber, square, circle;

    GraphicsContext graphicsContext;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        graphicsContext = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e->{
            double size = Double.parseDouble(sizeBtn.getText());
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;
            if(rubber.isSelected()){
                graphicsContext.clearRect(x,y,size,size);
            }
            if(square.isSelected()) {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillRect(x,y,size,size);
            }
            if(circle.isSelected()){
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillOval(x,y,size,size);
            }
        });

    }

}
