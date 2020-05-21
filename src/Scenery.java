import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

public class Scenery {

    public static LineChart<Number,Number> chart;
    public static LineChart<Number,Number> chart2;
    private Group root;
    private static Slider angleX = new Slider(-1, 1, 0);
    private static Slider angleY = new Slider(0, 0.62, 0);
    private static Button shoot = new Button("Fire in the hole");
    private static Slider ventX = new Slider(-15, 15, 0);
    private static Slider ventZ = new Slider(-15, 15, 0);
    private int positionCible = 0;


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
            Group cible;
            FXMLLoader fxmlLoader3 = new FXMLLoader();
            fxmlLoader3.setLocation(this.getClass().getResource("Objects/cible.fxml"));
            cible = fxmlLoader3.<Group>load();

            cible.setTranslateX(1277);
            cible.setTranslateY(720);
            cible.setTranslateZ(-2074);
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
                Label label = new Label(Integer.toString(i));
                boutons[i] = fxmlLoader2.<Group>load();
                boutons[i].setScaleX(0.1);
                boutons[i].setScaleY(0.1);
                boutons[i].setScaleZ(0.1);
                boutons[i].setRotationAxis(Rotate.Y_AXIS);
                boutons[i].setRotate(90);
                boutons[i].setTranslateX(1274);
                boutons[i].setTranslateY(715.3 + 0.25 * i);
                boutons[i].setTranslateZ(-2095);
                boutons[i].setOnMouseClicked(event -> {
                    cible.setTranslateZ(-2074 + 100 * Integer.parseInt(label.getText()));
                    positionCible = Integer.parseInt(label.getText());
                });
                root.getChildren().add(boutons[i]);
            }
            for (int i = 0; i < boutons.length; i++) {
                Label labeldistance = new Label(i * 100 + 100 + " mètres");
                labeldistance.setTextFill(Color.WHITE);
                labeldistance.setTranslateX(390);
                labeldistance.setTranslateY(210 + 94 * i);
                //210 à 870 environ
                labeldistance.setScaleX(1.5);
                labeldistance.setScaleY(1.5);
                labeldistance.mouseTransparentProperty().setValue(true);
                root.getChildren().add(labeldistance);
            }

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

            angleX.setTranslateX(1300);
            angleX.setTranslateY(1200);
            angleX.setBlockIncrement(0.2);
            angleX.setShowTickMarks(true);
            angleX.setMajorTickUnit(0.5);
            angleX.setShowTickLabels(true);

            angleY.setTranslateX(1200);
            angleY.setTranslateY(1100);
            angleY.setOrientation(Orientation.VERTICAL);
            angleY.setBlockIncrement(0.05);
            angleY.setShowTickMarks(true);
            angleY.setMajorTickUnit(0.1);
            angleY.setShowTickLabels(true);

            ventX.setTranslateX(500);
            ventX.setTranslateY(1200);
            ventX.setBlockIncrement(3);
            ventX.setShowTickMarks(true);
            ventX.setMajorTickUnit(5);
            ventX.setShowTickLabels(true);

            ventZ.setTranslateX(400);
            ventZ.setTranslateY(1100);
            ventZ.setOrientation(Orientation.VERTICAL);
            ventZ.setBlockIncrement(3);
            ventZ.setShowTickMarks(true);
            ventZ.setMajorTickUnit(5);
            ventZ.setShowTickLabels(true);

            shoot.setTranslateX(1700);
            shoot.setTranslateY(1200);
            shoot.setScaleX(2);
            shoot.setScaleY(2);
            Group controls = new Group(angleX, angleY, shoot, ventX, ventZ);
            root.getChildren().add(controls);

            NumberAxis xAxis = new NumberAxis();
            NumberAxis yAxis = new NumberAxis();
            yAxis.labelProperty().setValue("Hauteur");
            xAxis.labelProperty().setValue("Distance en profondeur");
            chart =new LineChart<>(xAxis,yAxis);
            chart.setTranslateX(1820);
            chart.setTranslateY(870);
            root.getChildren().add(chart);

            NumberAxis xAxis2 = new NumberAxis();
            NumberAxis yAxis2 = new NumberAxis();
            yAxis2.labelProperty().setValue("Distance de Gauche et Droite");
            xAxis2.labelProperty().setValue("Distance en profondeur");
            chart2 =new LineChart<>(xAxis2,yAxis2);
            chart2.setTranslateX(1820);
            chart2.setTranslateY(300);
            root.getChildren().add(chart2);

        } catch (Exception e) {

        }
    }

    public Group getScene() {
        return root;
    }


    public static Slider getAngleXSlider() {
        return angleX;
    }

    public static Slider getAngleYSlider() {
        return angleY;
    }

    public static Button getShoot() {
        return shoot;
    }

    public int getPositionCible() {
        return positionCible;
    }

    public static Slider getVentX() {
        return ventX;
    }

    public static Slider getVentZ() {
        return ventZ;
    }
}
