import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;


public class main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ground.fxml"));
        Group base = new Group();
        Camera camera;
        Parent root=new Parent(){};
        try {
            root = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        base=(Group)loader.getNamespace().get("Full3DScene");
        for (int i=0;i<base.getChildren().size();i++){
            if(base.getChildren().get(i).getId().equals("camera")){
                System.out.println(i);
            }
        }
        camera=(Camera)base.getChildren().get(19);
        base.scaleXProperty().set(5);
        base.scaleYProperty().set(5);
        base.scaleZProperty().set(5);
        base.setTranslateX(960);
        base.setTranslateY(540);
        base.setTranslateZ(-1200);

        //camera= (PerspectiveCamera) base.lookup("#camera");
        //camera= (PerspectiveCamera)loader.getNamespace().get("camera");
        Scene scene = new Scene(base,1920,1080);
        stage.setScene(scene);
        scene.setCamera(camera);
        stage.show();
        System.out.println(camera.getId());
    }
}
