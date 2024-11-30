public class Shield {
    boolean ShieldEquipped;
    int[] Level_EXP_CAP ;
    int ShieldBaseDef;
    int ShieldDef;
    double ShieldSpd;

    Shield(boolean Equip){
        ShieldEquipped = Equip;
        Level_EXP_CAP = new int[]{1,0,75};
        ShieldBaseDef = 5;
        ShieldDef = ShieldBaseDef + ((Level_EXP_CAP[0]-1));
        ShieldSpd = 0.8*Level_EXP_CAP[0];

    }

    public void LevelUP(){
        if(Level_EXP_CAP[1] >= Level_EXP_CAP[2]) {
            Level_EXP_CAP[1] -= Level_EXP_CAP[2];
            Level_EXP_CAP[0]++;
            Level_EXP_CAP[2] = 100 + ((Level_EXP_CAP[0] - 1) * 25);
            ShieldDef = 5 + ((Level_EXP_CAP[0]-1)*2);
            ShieldSpd = 0.8*Level_EXP_CAP[0];
            System.out.println("------------------------------------");
            System.out.println("Your Shield has level up!!! Now it's level" + Level_EXP_CAP[0]);
        }
    }

}
