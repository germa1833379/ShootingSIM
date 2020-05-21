import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    ArrayList<XY> trajectoire = new ArrayList<>();
    ArrayList<XY> trajectoire2 = new ArrayList<>();
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

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                fixingView(camera);
            }
        },1,1);
        Scenery scenery = new Scenery();
        Group root = scenery.getScene();
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
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        Gun currGun = new Gun();

        root.setOnMouseClicked(event -> {
            fixingView(camera);
        });

        Scenery.getAngleXSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            currGun.setAngleX(newValue.floatValue());
        });
        Scenery.getAngleYSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            currGun.setAngleY(newValue.floatValue());
        });

        Scenery.getShoot().setOnAction(event -> {
            //Audio
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Main.class.getResourceAsStream("Sounds/GunShot.wav"));
                clip.open(inputStream);
                clip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Chart Trajectoire
            XYChart.Series trajectoireChart=getHorizontalChart(2000,Bullet.getG1Bullet(),currGun);
            Target currTarget=new Target(0.5f,1,scenery.getPositionCible()*100+100);
            XYChart.Series cibleSeries= new XYChart.Series();
            cibleSeries.setName("Cible");
            cibleSeries.getData().add(new XYChart.Data(currTarget.getTargetX(),currTarget.getHeight()+currTarget.getWidht()));
            cibleSeries.getData().add(new XYChart.Data(currTarget.getTargetX(),currTarget.getHeight()-currTarget.getWidht()));
            Scenery.chart.getData().clear();
            Scenery.chart.getData().addAll(trajectoireChart);
            Scenery.chart.getData().addAll(cibleSeries);
            //Chart BirdEye
            XYChart.Series birdEyeChart=getBirdEyesChart(2000,Bullet.getG1Bullet(),currGun);
            XYChart.Series cibleSeries2= new XYChart.Series();cibleSeries2.setName("Cible");
            cibleSeries2.getData().add(new XYChart.Data(currTarget.getTargetX(),currTarget.getWidht()));
            cibleSeries2.getData().add(new XYChart.Data(currTarget.getTargetX(),-currTarget.getWidht()));
            Scenery.chart2.getData().clear();
            Scenery.chart2.getData().addAll(birdEyeChart);
            Scenery.chart2.getData().addAll(cibleSeries2);
            boolean passedTarget=false;
            for(int i=0;i<trajectoireChart.getData().size()-1;i++){
                if(currTarget.isHit(
                        trajectoire.get(i+1).getX(),
                        trajectoire.get(i+1).getY(),
                        trajectoire.get(i).getX(),
                        trajectoire.get(i).getY(),
                        trajectoire2.get(i+1).getY(),
                        trajectoire2.get(i).getY()
                        )){
                    passedTarget=true;
                    }
            }
            if (!passedTarget){
                JOptionPane.showMessageDialog(null, "Pas touché ", "Cible", JOptionPane.INFORMATION_MESSAGE);
            }
        });



        Scenery.getAngleXSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            currGun.setAngleX(newValue.floatValue());
        });
        Scenery.getAngleYSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            currGun.setAngleY(newValue.floatValue());
        });
        Scenery.getVentX().valueProperty().addListener((observable, oldValue, newValue) -> {
            Wind.setForceX(newValue.intValue());
        });
        Scenery.getVentZ().valueProperty().addListener((observable, oldValue, newValue) -> {
            Wind.setForceZ(newValue.intValue());
        });

    }
    int maxX;
    public XYChart.Series getHorizontalChart(int distanceMax,Bullet currentBullet,Gun currentGun){
        XYChart.Series series = new XYChart.Series();
        series.setName("Trajectoire et Hauteur");


        float startHeight=1;
        float lastHeight=startHeight;
        float currYSpeed=(float)((Math.sin(Math.toRadians(currentGun.getAngleY()))*currentBullet.getStartSpeed()));
        series.getData().add(new XYChart.Data(0,startHeight));
        trajectoire.clear();
        trajectoire.add(new XY(0,startHeight));

        double timeElapsed = 0;
        float totDistTraveled=1; // REFERING TO THE DISTANCE TRAVELLED IN THE AIR STARTS AT 1 BECAUSE OF FOR COMPOSITION
        for(int i=0;i<distanceMax;i++){
            double wtf =Math.cos(Math.toRadians(currentGun.getAngleX()));
            double tpmx=(1.0/((currentBullet.getCurrSpeed(totDistTraveled)*Math.cos(Math.toRadians(currentGun.getAngleY())))*wtf
                    +Wind.getForceZ()*timeElapsed)); //TIME PER METRE X
            timeElapsed += tpmx;
            //Force en descendant
            float Yforce=-9.8f;
            double variationY=currYSpeed*timeElapsed+(0.5*Yforce*timeElapsed*timeElapsed);
            if (variationY+startHeight<0){
                maxX=i+1;
                break;
            }
            series.getData().add(new XYChart.Data(i+1,variationY+startHeight));
            trajectoire.add(new XY((double)i+1.0,variationY+startHeight));
            totDistTraveled+=1+Math.abs(variationY);
            lastHeight+=variationY;
        }
        currentBullet.reset();
        return series;
    }
    public XYChart.Series getBirdEyesChart(int distanceMax,Bullet currentBullet,Gun currentGun){
        XYChart.Series series = new XYChart.Series();
        series.setName("Bird Eye View");

        float totDistTraveled=1;
        float startHeight=0;
        float lastHeight=startHeight;
        float currYSpeed=(float)(Math.sin(Math.toRadians(currentGun.getAngleX()))*currentBullet.getStartSpeed());

        series.getData().add(new XYChart.Data(0,startHeight));
        trajectoire2.clear();
        trajectoire2.add(new XY(0,startHeight));

        double timeElapsed = 0;
        for(int i=0;i<distanceMax;i++){
            if(i>=maxX)
                break;
            double tpmx=(1.0/(currentBullet.getStartSpeed()*Math.cos(Math.toRadians(currentGun.getAngleY()))*Math.cos(Math.toRadians(currentGun.getAngleX()))
                    +Wind.getForceX()*timeElapsed)); //TIME PER METRE X
            timeElapsed += tpmx;
            //Force en descendant
            float Yforce=Wind.getForceX();
            double variationY=currYSpeed*timeElapsed+(0.5*Yforce*timeElapsed*timeElapsed);
            series.getData().add(new XYChart.Data(i+1,variationY+startHeight));
            trajectoire2.add(new XY((double)i+1.0,variationY+startHeight));
            lastHeight+=variationY;

        }
        currentBullet.reset();
        return series;
    }

    public void fixingView(Camera camera) {
        camera.setTranslateZ(camera.getTranslateZ() + 0.01);
        camera.setTranslateZ(camera.getTranslateZ() - 0.01);
    }

}
