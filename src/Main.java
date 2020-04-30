import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Vector;

public class Main extends Application {
    ArrayList<XY> trajectoire = new ArrayList<>();
    public static float hitHeight;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Scene
        Camera camera=new PerspectiveCamera();
        camera.setNearClip(0);
        double sceneWidth=1285;

        Scenery scenery=new Scenery();
        Group root=scenery.getScene();
        Scene scene = new Scene(root);

        scene.setFill(Color.SKYBLUE);
        root.setOnMouseClicked(event -> {
            camera.setTranslateZ(camera.getTranslateZ()+0.01);
            camera.setTranslateZ(camera.getTranslateZ()-0.01);
        });

        scene.setCamera(camera);

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            root.setTranslateX(scene.getWidth());
            camera.setTranslateX(1922.5+(scene.getWidth()-1285)/2);
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            root.setTranslateY(scene.getHeight());
            root.setTranslateZ(scene.getHeight());
            camera.setTranslateZ(-36+(2.865*(scene.getHeight()-721)));
            camera.setTranslateY(1079.5+(scene.getHeight()-721)/2);
        });

        Gun currGun = new Gun();
        Bullet currBullet=new Bullet();

        NumberAxis xAxis = new NumberAxis();xAxis.labelProperty().setValue("Distance");
        NumberAxis yAxis = new NumberAxis();yAxis.labelProperty().setValue("Height");

        Target currTarget=new Target(0.5f,1,100);

        XYChart.Series trajectoireChart= getChart(1000,currBullet,currGun);
        LineChart<Number,Number> chart = new LineChart<Number,Number>(xAxis,yAxis);
        chart.getData().addAll(trajectoireChart);


        for(int i=0;i<trajectoireChart.getData().size()-1;i++){
            currTarget.isHit(
                    trajectoire.get(i+1).getX(),
                    trajectoire.get(i+1).getY(),
                    trajectoire.get(i).getX(),
                    trajectoire.get(i).getY());
        }



        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        Stage secWindow = new Stage();
        secWindow.setTitle("Graph");
        secWindow.setScene(new Scene(chart));
        Scenery.getChartButton().setOnAction(Event->{
            secWindow.show();
        });

    }
    public XYChart.Series getChart(int distanceMax,Bullet currentBullet,Gun currentGun){
        XYChart.Series series = new XYChart.Series();
        series.setName("Trajectory");


        float startHeight=1;
        double tpmx=(1.0/(currentBullet.getSpeed()*Math.cos(Math.toRadians(currentGun.getAngleY())))); //TIME PER METRE X
        float lastHeight=startHeight;
        float currYSpeed=(float)(Math.sin(Math.toRadians(currentGun.getAngleY()))*currentBullet.getSpeed());

        series.getData().add(new XYChart.Data(0,startHeight));
        trajectoire.add(new XY(0,startHeight));

        double timeElapsed = 0;
        for(int i=0;i<distanceMax;i++){
             timeElapsed += tpmx;
            //Force en descendant
            float Yforce=9.8f;
            double temp=currYSpeed*timeElapsed-(0.5*Yforce*timeElapsed*timeElapsed);




            series.getData().add(new XYChart.Data(i+1,temp+startHeight));
            trajectoire.add(new XY((double)i+1.0,temp+startHeight));
            lastHeight+=temp;
        }
        System.out.println(currYSpeed);
        return series;
    }

}
