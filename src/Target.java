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
    public boolean isHit(double x, double y, double prevX, double prevY){

        if(prevX<targetX&&targetX<=x) {

            if(targetX==x){
                if(height-widht<y&&y<height+widht){
                    JOptionPane.showMessageDialog(null, "Touché "+y, "Cible", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }else
                    JOptionPane.showMessageDialog(null, "Pas touché "+y, "Cible", JOptionPane.INFORMATION_MESSAGE);
            }else if(prevX<targetX&&targetX<x){
                double tv = (y - prevY) / (x - prevX);
                double hity = prevY+tv*(x-prevX-x-targetX);
                if(height-widht<hity&&hity<height+widht){
                    JOptionPane.showMessageDialog(null, "Touché "+y, "Cible", JOptionPane.INFORMATION_MESSAGE);
                    Main.hitHeight=(float)hity;
                    return true;
                }else
                    JOptionPane.showMessageDialog(null, "Pas touché "+y, "Cible", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return false;
    }
}
