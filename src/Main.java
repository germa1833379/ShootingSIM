import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Camera camera=new PerspectiveCamera();
        camera.setNearClip(0);

        double sceneWidth=1285;

        try {
            Scenery scenery=new Scenery();
            Group root=scenery.getScene();
            Scene scene = new Scene(root);
            root.setOnMouseClicked(event -> {
                camera.setTranslateZ(camera.getTranslateZ()+0.01);
                camera.setTranslateZ(camera.getTranslateZ()-0.01);
            });




































            scene.setFill(Color.SKYBLUE);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.W){
                    camera.setTranslateZ(camera.getTranslateZ()+10);
                }
                if (event.getCode() == KeyCode.S){
                    camera.setTranslateZ(camera.getTranslateZ()-10);
                }
                if (event.getCode() == KeyCode.A){
                    camera.setTranslateX(camera.getTranslateX()-10);
                }
                if (event.getCode() == KeyCode.D){
                    camera.setTranslateX(camera.getTranslateX()+10);
                }
                if (event.getCode() == KeyCode.SPACE){
                    camera.setTranslateY(camera.getTranslateY()-10);
                }
                if (event.getCode() == KeyCode.E){
                    camera.setTranslateY(camera.getTranslateY()+10);
                }
                if (event.getCode() == KeyCode.UP){
                    camera.setTranslateZ(camera.getTranslateZ()+0.5);
                }
                if (event.getCode() == KeyCode.DOWN){
                    camera.setTranslateZ(camera.getTranslateZ()-0.5);
                }
                if (event.getCode() == KeyCode.LEFT){
                    camera.setTranslateX(camera.getTranslateX()-0.5);
                }
                if (event.getCode() == KeyCode.RIGHT){
                    camera.setTranslateX(camera.getTranslateX()+0.5);
                }
                if (event.getCode() == KeyCode.NUMPAD8){
                    camera.setTranslateY(camera.getTranslateY()-0.5);
                }
                if (event.getCode() == KeyCode.NUMPAD2){
                    camera.setTranslateY(camera.getTranslateY()+0.5);
                }
                if (event.getCode() == KeyCode.ENTER){
                    System.out.println(camera.getTranslateX() + " " + camera.getTranslateY() + " " + camera.getTranslateZ());;
                    System.out.println(scene.getWidth());
                    System.out.println(scene.getHeight());
                    System.out.println(root.getTranslateX());
                    System.out.println(root.getTranslateY());
                    System.out.println(root.getTranslateZ());
                }
            });
            scene.setCamera(camera);


            scene.widthProperty().addListener((observable, oldValue, newValue) -> {
                root.setTranslateX(scene.getWidth());


                camera.setTranslateX(1922.5+(scene.getWidth()-1285)/2);


                //root.setTranslateX(((double)oldValue-(double)newValue)/2);

            });
            scene.heightProperty().addListener((observable, oldValue, newValue) -> {
                root.setTranslateY(scene.getHeight());
                root.setTranslateZ(scene.getHeight());
                camera.setTranslateZ(-36+(2.865*(scene.getHeight()-721)));
                camera.setTranslateY(1079.5+(scene.getHeight()-721)/2);
            });
            primaryStage.setScene(scene);
            primaryStage.show();
            root.setTranslateZ(scene.getHeight());
            root.setTranslateY(scene.getHeight());
        }catch (Exception e){

        }
    }
}
