package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Canvas canvas;
    @FXML
    private CheckBox rubber, square, circle, line;
    @FXML
    private Spinner spinner;

    GraphicsContext graphicsContext;

    public void onSave(){

        WritableImage writableImage = canvas.snapshot(null,null);
        BufferedImage bImage = SwingFXUtils.fromFXImage(writableImage,null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to");
        fileChooser.setInitialDirectory(new File("/Users"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("png","*.png"),
                new FileChooser.ExtensionFilter("jpg", "*.jpg"));
       try{
           File file = fileChooser.showSaveDialog(null);
           ImageIO.write(bImage,"png", new File(file.getAbsolutePath()));

       }
       catch (Exception ex){

           System.out.println("Not working");

       }

        }
    public void onExit(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        graphicsContext = canvas.getGraphicsContext2D();
        SpinnerValueFactory<Double> sizeFont = new SpinnerValueFactory.DoubleSpinnerValueFactory(1,72,12);
        spinner.setValueFactory(sizeFont);

        canvas.setOnMouseDragged(e->{

            double size = (double) spinner.getValue();
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
            else if(line.isSelected()){
                graphicsContext.setLineWidth(size);
                graphicsContext.setStroke(colorPicker.getValue());
                graphicsContext.lineTo(e.getSceneX()-15,e.getSceneY()-65);
                graphicsContext.stroke();
            }
        });
        canvas.setOnMousePressed(e->{
            graphicsContext.beginPath();
            graphicsContext.stroke();
        });


    }

}
