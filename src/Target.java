public class Target {
    private float widht,height,targetX;
    public Target(float widht,float height,float x){
        this.widht=widht;
        this.height=height;
        targetX=x;
    }
    public boolean isHit(double x,double y,double prevX,double prevY){
        if(prevX<targetX&&targetX<=x) {

            if(targetX==x){
                if(height-widht<y&&y<height+widht){
                    System.out.println("Touché "+y);
                    return true;
                }else
                    System.out.println("Pas touché");
            }else if(prevX<targetX&&targetX<x){
                double tv = (y - prevY) / (x - prevX);
                double hity = prevY+tv*(x-prevX-x-targetX);
                if(height-widht<hity&&hity<height+widht){
                    System.out.println("Touché "+y);
                    Main.hitHeight=(float)hity;
                    return true;
                }else
                    System.out.println("Pas touché");
            }
        }
        return false;
    }
}
