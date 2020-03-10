public class Target {
    private float widht,height,targetPos,targetDepth;
    public Target(float widht,float height,float y,float z){
        widht=widht;
        height=height;
        targetDepth=y;
        targetPos=z;
    }
    public boolean isHit(double x,double y,double prevX,double prevY){
        double hitPoint=0.0;
        if(prevX<targetDepth<x) {
            double tv = (y - prevY) / (x - prevX);


            if (x > targetPos && x < lastX) {
                return true;
            }
        }
        return false;
    }
}
