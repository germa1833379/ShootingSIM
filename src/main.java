import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Vector;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Gun currGun = new Gun();
        Bullet currBullet=new Bullet();
        NumberAxis xAxis = new NumberAxis();
        xAxis.labelProperty().setValue("Distance");
        NumberAxis yAxis = new NumberAxis();
        yAxis.labelProperty().setValue("Height");
        XYChart.Series trajectory= getChart(10000,currBullet,currGun);
        LineChart<Number,Number> chart = new LineChart<Number,Number>(xAxis,yAxis);
        chart.getData().addAll(trajectory);
        Scene scene = new Scene(chart,1920,1080);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);





    }
    public XYChart.Series getChart(int distanceMax,Bullet currentBullet,Gun currentGun){
        XYChart.Series series = new XYChart.Series();
        series.setName("Trajectory");

        float startHeight=1;
        double tpmx=(1.0/(currentBullet.getSpeed()*Math.cos(Math.toRadians(currentGun.getAngleY())))); //TIME PER METRE X
        series.getData().add(new XYChart.Data(0,startHeight));
        float lastHeight=startHeight;
        float currYSpeed=(float)(Math.sin(Math.toRadians(currentGun.getAngleY()))*currentBullet.getSpeed());
        double timeElapsed = 0;
        for(int i=0;i<distanceMax;i++){
             timeElapsed += tpmx;
            //currYSpeed+=-9.8*tpmx;
            double temp=currYSpeed*timeElapsed-(0.5*9.8*timeElapsed*timeElapsed);
            series.getData().add(new XYChart.Data(i+1,temp+startHeight));
            lastHeight+=temp;
            System.out.println(lastHeight);
        }
        System.out.println(currYSpeed);
        /*double tpm=(1.0/(currentBullet.getSpeed()*Math.cos(currentGun.getAngleY()/180))); //TIME PER METRE
        System.out.println(Double.toString(tpm));
        double temp;
        double lastHeight=-0.2f;
        for(int i=0;i<distanceMax;i++){
            temp=tpm*tpm*-9.8*1/2;
            series.getData().add(new XYChart.Data(i+1,temp+lastHeight));
            lastHeight+=temp;
        }*/
        return series;
    }
}
