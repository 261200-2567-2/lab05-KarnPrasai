public class Sword {
    boolean SwordEquipped;
    int[] Level_EXP_CAP;
    int SwordBaseDmg;
    int SwordDmg;
    double SwordSpd;

    Sword(boolean Equip){
        SwordEquipped = Equip;
        Level_EXP_CAP = new int[]{1,0,75};
        SwordBaseDmg = 5;
        SwordDmg = SwordBaseDmg + ((Level_EXP_CAP[0]-1)*2);
        SwordSpd = 0.4*Level_EXP_CAP[0];
    }

    public void LevelUP(){
        if(Level_EXP_CAP[1] >= Level_EXP_CAP[2]) {
            Level_EXP_CAP[1] -= Level_EXP_CAP[2];
            Level_EXP_CAP[0]++;
            Level_EXP_CAP[2] = 100 + ((Level_EXP_CAP[0] - 1) * 20);
            SwordDmg = 5 + ((Level_EXP_CAP[0]-1)*2);
            SwordSpd =  0.4*Level_EXP_CAP[0];
            System.out.println("------------------------------------");
            System.out.println("Your Sword has level up!!! Now it's level" + Level_EXP_CAP[0]);
        }
    }

}
