package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    public void onSave(){

        WritableImage file = canvas.snapshot(null,null);
        BufferedImage bImage = SwingFXUtils.fromFXImage(file,null);
        try{
            ImageIO.write(bImage, "png", new File("src/Image/image.png"));
        }
        catch (IOException e){
            System.out.println("coś nie działa xD");
        }


        }
    public void onExit(){
        System.exit(0);
    }
    public void numberOnly(KeyEvent keyEvent) {


    }


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
            else if(square.isSelected()) {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillRect(x,y,size,size);
            }
            else if(circle.isSelected()){
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillOval(x,y,size,size);
            }
        });

    }


}
