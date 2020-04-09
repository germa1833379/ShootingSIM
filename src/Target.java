public class Target {
    private float widht,height,targetX;
    public Target(float widht,float height,float x){
        this.widht=widht;
        this.height=height;
        targetX=x;
    }
    public boolean isHit(double x,double y,double prevX,double prevY){
        double hitPoint=0.0;
        String errorMsg= "Pas touché ";
        String hitMsg= "Touché ";
        if(prevX<targetX&&targetX<=x) {
            if(targetX==x){
                if(height-widht<y&&y<height+widht){
                    System.out.println(hitMsg + y);
                }else
                    System.out.println(errorMsg);
            }else {
                double hity, hitx;
                double tv = (y - prevY) / (x - prevX);
                double distanceToApprox = x - prevX - x - targetX;
                hity=distanceToApprox * tv + prevY;
                if (height - widht < hity && hity < height + widht) {
                    System.out.println(hitMsg+ hity);
                }else
                    System.out.println(errorMsg);
            }

        }
        return false;
    }
}
