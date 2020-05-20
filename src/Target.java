import javafx.scene.Group;
import javafx.scene.Scene;

import javax.swing.*;

public class Target {
    private final float widht;
    private final float height;
    private final float targetX;
    public Target(float widht,float height,float x){
        this.widht=widht;
        this.height=height;
        targetX=x;
    }
    public boolean isHit(double x, double y, double prevX, double prevY, double y2, double prevY2){

        if(prevX<targetX&&targetX<=x) {

            if(targetX==x){
                if(height-widht<y&&y<height+widht&&widht>y2&&y2>-widht){
                    JOptionPane.showMessageDialog(null, "Touché ", "Cible", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }else
                    JOptionPane.showMessageDialog(null, "Pas touché ", "Cible", JOptionPane.INFORMATION_MESSAGE);
            }else if(prevX<targetX&&targetX<x){

                double tv = (y - prevY) / (x - prevX);
                double hity = prevY+tv*(x-prevX-x-targetX);

                double tv2 = (y2 - prevY2) / (x - prevX);
                double hity2 = prevY2+tv2*(x-prevX-x-targetX);

                if(height-widht<hity&&hity<height+widht&&widht>hity2&&hity2>-widht){
                    JOptionPane.showMessageDialog(null, "Touché ", "Cible", JOptionPane.INFORMATION_MESSAGE);
                    Main.hitHeight=(float)hity;
                    return true;
                }else
                    JOptionPane.showMessageDialog(null, "Pas touché ", "Cible", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return false;
    }
    public float getWidht() {
        return widht;
    }

    public float getHeight() {
        return height;
    }

    public float getTargetX() {
        return targetX;
    }
}
