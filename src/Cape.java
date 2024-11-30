import java.util.Random;

public class Cape implements Knightgear, Magegear {
    private static final String[] CapeName = {"Strength Cape","Magic Cape","Tank Cape","Speed Cape","Normal Cape"};
    private static final String[] type = {"Knight","Mage","Tank","Thief","None"};
    private String Name;
    private final String CapeType;
    private int Level ;
    int[] CapeStat;


    public Cape(boolean Dropped, int level) {
        if(!Dropped)
        {
            this.Name = "Starter Cape";
            this.CapeType = "None";
            CapeStat = new int[]{1, 1, 1, 1};
        }
        else
        {
            Random rand = new Random();
            this.Name = CapeName[rand.nextInt(0,2)];
            CapeType = type[rand.nextInt(type.length)];
            Level = level + rand.nextInt(0,5);
            AddRingStat(level);
        }

    }

    private void AddRingStat(int level){
        Random rand = new Random();
        if(CapeType.equals(type[0])){
            this.Name = CapeName[0];
            CapeStat = new int[]{rand.nextInt(2, 3 + level / 2)
                    ,rand.nextInt(3, 4 + level / 2)
                    ,rand.nextInt(2, 3 + level / 2)
                    ,0
            };

        }
        else if(CapeType.equals(type[1])){
            this.Name = CapeName[1];
            CapeStat = new int[]{0
                    ,rand.nextInt(3,4+level/2)
                    ,rand.nextInt(2,3+level/2)
                    ,rand.nextInt(2,3+level/2)
            };

        }
        else if(CapeType.equals(type[2])){
            this.Name = CapeName[2];
            CapeStat = new int[]{rand.nextInt(0, 1 + level / 2)
                    ,rand.nextInt(6,7+level/2)
                    ,rand.nextInt(2,3+level/2)
                    ,rand.nextInt(0,1+level/2)
            };

        }
        else if(CapeType.equals(type[3])){
            this.Name = CapeName[3];
            CapeStat = new int[]{rand.nextInt(0,1+level/2)
                    ,rand.nextInt(2,3+level/2)
                    ,rand.nextInt(6,7+level/2)
                    ,rand.nextInt(0,1+level/2)

            };

        }
        else if(CapeType.equals(type[4])){
            this.Name = CapeName[4];
            CapeStat = new int[]{rand.nextInt(0,1+level/2)
                    ,rand.nextInt(0,1+level/2)
                    ,rand.nextInt(0,1+level/2)
                    ,rand.nextInt(0,1+level/2)
            };
        }


    }
    @Override
    public int[] KnightBuff() {
        return new int[]{2,5,2,0};
    }

    @Override
    public int[] MageBuff() {
        return new int[]{0,4,3,2};
    }

    @Override
    public void Stat() {
        System.out.println("Cape : "+ Name + " Level " + Level);
        System.out.println("GearType : "+ CapeType + " Class");
        System.out.println("ATK : " + CapeStat[0] + " ,DEF : " + CapeStat[1] + " ,SPD : " + CapeStat[2] + " ,INT :" + CapeStat[3]);
        System.out.println("------------------------------------");

    }

    @Override
    public void Equip(RPGCharacter player) {
        double[] PlayerBase = player.ATK_DEF_SPD;
        int[] TypeBuff = new int[4];
        if(CapeType.equals(type[0]) && player.PlayerClass.equals("Knight")){
            TypeBuff = KnightBuff();
        }
        else if(CapeType.equals(type[1]) && player.PlayerClass.equals("Mage")){
            TypeBuff = MageBuff();
        }
        player.ATK_DEF_SPD = new double[]{
                PlayerBase[0] + CapeStat[0] + TypeBuff[0],
                PlayerBase[1] + CapeStat[1] + TypeBuff[1],
                PlayerBase[2] + CapeStat[2] + TypeBuff[2],
                PlayerBase[3] + CapeStat[3] + TypeBuff[3]};
    }
}
