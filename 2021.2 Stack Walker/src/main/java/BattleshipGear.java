
public class BattleshipGear {
    String speed;
    public String switchToWarpSpeed(String warpDescription) {
        int gear = Integer.parseInt(warpDescription);
        switch (gear) {
            case 1 : speed= "1x lightspeed"; break;
            case 2 : speed= "10x lightspeed"; break;
            case 3 : speed= "39x lightspeed"; break;
            case 4 : speed= "102x lightspeed"; break;
            default : speed="determined by autopilot"; break;
        };
        return speed;
    }
}
