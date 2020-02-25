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
            Group root=new Group();
            Scene scene = new Scene(root);


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("ground%20(1).fxml"));
            MeshView sol = fxmlLoader.<MeshView>load();

            Group[] panneaux=new Group[45];


            root.getChildren().add(sol);
            for (int i=0;i<panneaux.length;i++){
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                fxmlLoader2.setLocation(this.getClass().getResource("panel.fxml"));
                panneaux[i]=fxmlLoader2.<Group>load();
                panneaux[i].setScaleX(0.1);
                panneaux[i].setScaleY(0.1);
                panneaux[i].setScaleZ(0.1);
                panneaux[i].setRotationAxis(Rotate.Y_AXIS);
                panneaux[i].setRotate(90);
                panneaux[i].setTranslateX(1280);
                panneaux[i].setTranslateY(717.5);
                panneaux[i].setTranslateZ(-2074+50*i);
                root.getChildren().add(panneaux[i]);
            }

            Group cible;
            FXMLLoader fxmlLoader3 = new FXMLLoader();
            fxmlLoader3.setLocation(this.getClass().getResource("cible.fxml"));
            cible=fxmlLoader3.<Group>load();

            cible.setTranslateX(1270);
            cible.setTranslateY(700);
            cible.setTranslateZ(-2000);
            cible.setScaleX(10);
            cible.setScaleY(10);
            cible.setScaleZ(10);
            cible.setRotationAxis(Rotate.Y_AXIS);
            cible.setRotate(90);
            root.getChildren().add(cible);










            Material material = new PhongMaterial();

            ((PhongMaterial) material).setSpecularColor(Color.rgb(30, 30, 30));
            sol.setMaterial(material);
            Rotate rotateX=new Rotate(270,Rotate.X_AXIS);
            Rotate rotateY=new Rotate(90,Rotate.Z_AXIS);
            sol.getTransforms().addAll(rotateX,rotateY);

            sol.setTranslateX(1280);
            sol.setTranslateY(720);
            sol.setTranslateZ(-2100);

            sol.setScaleX(1);
            sol.setScaleY(1);
            sol.setScaleZ(1);

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
            PointLight light = new PointLight(Color.WHITE);
            light.setTranslateX(0);
            light.setTranslateY(-3000);
            light.setTranslateZ(-1600);
            root.getChildren().add(light);
           root.getChildren().add(new AmbientLight(Color.WHITE));

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
