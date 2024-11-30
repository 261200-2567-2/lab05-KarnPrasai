public class MagicStaff {
    boolean StaffEquipped;
    int[] Level_EXP_CAP;
    int StaffBaseDmg;
    int StaffDmg;
    double StaffSpeed;

    MagicStaff(boolean Equip){
        StaffEquipped = Equip;
        Level_EXP_CAP = new int[]{1,0,75};
        StaffBaseDmg = 5;
        StaffDmg = StaffBaseDmg + ((Level_EXP_CAP[0]-1)*2);
        StaffSpeed = 0.4*Level_EXP_CAP[0];
    }

    public void LevelUP(){
        if(Level_EXP_CAP[1] >= Level_EXP_CAP[2]) {
            Level_EXP_CAP[1] -= Level_EXP_CAP[2];
            Level_EXP_CAP[0]++;
            Level_EXP_CAP[2] = 100 + ((Level_EXP_CAP[0] - 1) * 20);
            StaffDmg = 5 + ((Level_EXP_CAP[0]-1)*2);
            StaffSpeed =  0.4*Level_EXP_CAP[0];
            System.out.println("------------------------------------");
            System.out.println("Your Sword has level up!!! Now it's level" + Level_EXP_CAP[0]);
        }
    }
}
