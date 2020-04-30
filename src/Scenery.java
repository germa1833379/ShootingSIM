import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Scenery {
    private Group root;
    private static Button graph= new Button();
    public Scenery() {

        try {
            this.root = new Group();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("Objects/ground.fxml"));
            MeshView sol = fxmlLoader.<MeshView>load();

            Group[] panneaux = new Group[45];


            root.getChildren().add(sol);
            for (int i = 0; i < panneaux.length; i++) {
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                fxmlLoader2.setLocation(this.getClass().getResource("Objects/panel.fxml"));
                panneaux[i] = fxmlLoader2.<Group>load();
                panneaux[i].setScaleX(0.5);
                panneaux[i].setScaleY(0.5);
                panneaux[i].setScaleZ(0.5);
                panneaux[i].setRotationAxis(Rotate.Y_AXIS);
                panneaux[i].setRotate(90);
                panneaux[i].setTranslateX(1280);
                panneaux[i].setTranslateY(720.5);
                panneaux[i].setTranslateZ(-2074 + 50 * i);
                root.getChildren().add(panneaux[i]);
            }
            /*Label distance=new Label("allo");
            distance.setTranslateX(1280);
            distance.setTranslateY(710);
            distance.setTranslateZ(-2080);
            distance

            root.getChildren().add(distance);*/

            Group cible;
            FXMLLoader fxmlLoader3 = new FXMLLoader();
            fxmlLoader3.setLocation(this.getClass().getResource("Objects/cible.fxml"));
            cible = fxmlLoader3.<Group>load();

            cible.setTranslateX(1277);
            cible.setTranslateY(720);
            cible.setTranslateZ(-1500);
            cible.setScaleX(2);
            cible.setScaleY(2);
            cible.setScaleZ(2);
            cible.setRotationAxis(Rotate.Y_AXIS);
            cible.setRotate(90);
            root.getChildren().add(cible);

            Group[] boutons = new Group[8];
            for (int i = 0; i < boutons.length; i++) {
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                fxmlLoader2.setLocation(this.getClass().getResource("Objects/panel.fxml"));
                Label label=new Label(Integer.toString(i));
                boutons[i] = fxmlLoader2.<Group>load();
                boutons[i].setScaleX(0.1);
                boutons[i].setScaleY(0.1);
                boutons[i].setScaleZ(0.1);
                boutons[i].setRotationAxis(Rotate.Y_AXIS);
                boutons[i].setRotate(90);
                boutons[i].setTranslateX(1274);
                boutons[i].setTranslateY(715.3+0.25*i);
                boutons[i].setTranslateZ(-2095);
                boutons[i].setOnMouseClicked(event -> {
                    cible.setTranslateZ(-2074 + 50*Integer.parseInt(label.getText()));
                });
                root.getChildren().add(boutons[i]);
            }


            Sphere cylinder = new Sphere(0.01);
            Material material2 = new PhongMaterial();
            ((PhongMaterial) material2).setSpecularColor(Color.rgb(30, 200, 30));
            cylinder.setMaterial(material2);
            cylinder.setTranslateX(1280.15);
            cylinder.setTranslateY(715.9);
            cylinder.setTranslateZ(-2001);
            root.getChildren().add(cylinder);

            Sphere cylinde2r = new Sphere(0.01);
            Material material3 = new PhongMaterial();
            ((PhongMaterial) material3).setSpecularColor(Color.rgb(30, 200, 30));
            cylinde2r.setMaterial(material3);
            cylinde2r.setTranslateX(1278.15);
            cylinde2r.setTranslateY(715.9);
            cylinde2r.setTranslateZ(-2001);
            root.getChildren().add(cylinde2r);

            Sphere cylinde4r = new Sphere(0.01);
            Material material5 = new PhongMaterial();
            ((PhongMaterial) material5).setSpecularColor(Color.rgb(30, 200, 30));
            cylinde4r.setMaterial(material5);
            cylinde4r.setTranslateX(1282.15);
            cylinde4r.setTranslateY(715.9);
            cylinde4r.setTranslateZ(-2001);
            root.getChildren().add(cylinde4r);

            Sphere cylinde3r = new Sphere(0.01);
            Material material4 = new PhongMaterial();
            ((PhongMaterial) material4).setSpecularColor(Color.rgb(30, 200, 30));
            cylinde3r.setMaterial(material4);
            cylinde3r.setTranslateX(1280.15);
            cylinde3r.setTranslateY(713.9);
            cylinde3r.setTranslateZ(-2001);
            root.getChildren().add(cylinde3r);

            Sphere cylinde5r = new Sphere(0.01);
            Material material6 = new PhongMaterial();
            ((PhongMaterial) material6).setSpecularColor(Color.rgb(30, 200, 30));
            cylinde5r.setMaterial(material6);
            cylinde5r.setTranslateX(1280.15);
            cylinde5r.setTranslateY(717.9);
            cylinde5r.setTranslateZ(-2001);
            root.getChildren().add(cylinde5r);


            Material material = new PhongMaterial();


            ((PhongMaterial) material).setSpecularColor(Color.rgb(30, 30, 30));
            sol.setMaterial(material);
            Rotate rotateX = new Rotate(270, Rotate.X_AXIS);
            Rotate rotateY = new Rotate(90, Rotate.Z_AXIS);
            sol.getTransforms().addAll(rotateX, rotateY);

            sol.setTranslateX(1280);
            sol.setTranslateY(720);
            sol.setTranslateZ(-2100);

            sol.setScaleX(1);
            sol.setScaleY(1);
            sol.setScaleZ(1);


            /*PointLight light = new PointLight(Color.WHITE);
            light.setTranslateX(0);
            light.setTranslateY(-3000);
            light.setTranslateZ(-1600);
            root.getChildren().add(light);
            root.getChildren().add(new AmbientLight(Color.WHITE));*/


            graph.setTranslateX(2000);
            graph.setTranslateY(1200);
            graph.setScaleX(2);
            graph.setScaleY(2);
            graph.setText("Afficher la trajectoire");

            root.getChildren().add(graph);
        } catch (Exception e) {

        }
    }

    public Group getScene() {
        return root;
    }
    public static Button getChartButton(){
        return graph;
    }
}