public class Wind {
    private static float forceX=0;
    private static float forceZ=0.1f;

    public Wind(){
        forceZ=0.1f;
    }
    public static float getForceX() {
        return forceX;
    }

    public static void setForceX(float input) {
        forceX = input;
    }

    public static float getForceZ() {
        return forceZ;
    }

    public static void setForceZ(float input) { forceZ = input;    }
}
