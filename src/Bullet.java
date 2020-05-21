import java.util.ArrayList;

public class Bullet {
    float startSpeed;// m/s
    float weight;// kg
    float currSpeed;
    float caliber;
    float dragCoeff;
    static float[] g1DrgMdl =new float[]{840,718,615,522,440,374,328,299,278,261,248};
    static int[] dragMdlDist = new int[]{0,300,600,900,1200,1500,1800,2100,2400,2700,3000};
    float[] avgDecel;
    float bC= .208f;
    float areaFront= 0.01031f;
    float lenght = 0.03251f;

    public float getLenght() {
        return lenght;
    }

    public float getbC() {
        return bC;
    }

    public float getAreaFront() {
        return areaFront;
    }



    public Bullet(float startSpeed, float weight, float caliber, float[] currDragMdl){
        this.startSpeed=startSpeed;
        this.weight=weight;
        currSpeed=startSpeed;
        this.caliber=caliber;
        avgDecel=new float[10];
        for(int i=0;i<10;i++){
            avgDecel[i]=(currDragMdl[i]-currDragMdl[i+1])/300;
        }
    }



    public float getCurrSpeed(float totDistTraveled) {
        boolean found=false;
        int i=0;
        while(!found){
            if(i==10){
                System.out.println("lol");
            }
            if(dragMdlDist[i]<totDistTraveled&&totDistTraveled<dragMdlDist[i+1]){
                currSpeed=g1DrgMdl[i]-(avgDecel[i]*(totDistTraveled%300));
                found=true;
            }
            i++;
        }
        return currSpeed;
    }

    public void reset(){
        currSpeed=startSpeed;
    }

    public float getStartSpeed(){
        return startSpeed;
    }
    //https://en.wikipedia.org/wiki/Table_of_handgun_and_rifle_cartridges


    public static Bullet getG1Bullet(){
        return new Bullet(830,0.02f,0.38f, g1DrgMdl); //Based on .38 Special
    }
}
