import java.util.Random;

public class Ring implements Knightgear, Magegear {
    private static final String[] ringName = {"Strength Ring","Magic Ring","Tank Ring","Speed Ring","Normal Ring"};
    private static final String[] type = {"Knight","Mage","Tank","Thief","None"};
    private String Name;
    private final String RingType;
    private int Level ;
    int[] RingStat;


    public Ring(boolean Dropped, int level) {
        if(!Dropped)
        {
            this.Name = "Starter Ring";
            this.RingType = "None";
            RingStat = new int[]{1, 1, 1, 1};
        }
        else
        {
            Random rand = new Random();
            this.Name = ringName[rand.nextInt(0,2)];
            RingType = type[rand.nextInt(type.length)];
            Level = level + rand.nextInt(0,5);
            AddRingStat(level);
        }

    }

    private void AddRingStat(int level){
        Random rand = new Random();
        if(RingType.equals(type[0])){
            this.Name = ringName[0];
            RingStat = new int[]{rand.nextInt(3, 4 + level / 2)
                    ,rand.nextInt(1, 5 + level / 2)
                    ,rand.nextInt(0, 1 + level / 2)
                    ,0
            };

        }
        else if(RingType.equals(type[1])){
            this.Name = ringName[1];
            RingStat = new int[]{0
                    ,rand.nextInt(0,1+level/2)
                    ,rand.nextInt(1,2+level/2)
                    ,rand.nextInt(3,4+level/2)
            };

        }
        else if(RingType.equals(type[2])){
            this.Name = ringName[2];
            RingStat = new int[]{rand.nextInt(0, 2 + level / 2)
                    ,rand.nextInt(4,5+level/2)
                    ,rand.nextInt(1,2+level/2)
                    ,rand.nextInt(0,1+level/2)
            };

        }
        else if(RingType.equals(type[3])){
            this.Name = ringName[3];
            RingStat = new int[]{rand.nextInt(2,2+level/2)
                    ,rand.nextInt(0,1+level/2)
                    ,rand.nextInt(5,6+level/2)
                    ,rand.nextInt(0,1+level/2)

            };

        }
        else if(RingType.equals(type[4])){
            this.Name = ringName[4];
            RingStat = new int[]{rand.nextInt(0,1+level/2)
                    ,rand.nextInt(0,1+level/2)
                    ,rand.nextInt(0,1+level/2)
                    ,rand.nextInt(0,1+level/2)
            };
        }


    }
    @Override
    public int[] KnightBuff() {
        return new int[]{4,3,1,0};
    }

    @Override
    public int[] MageBuff() {
        return new int[]{0,2,2,5};
    }

    @Override
    public void Stat() {
        System.out.println("Ring : "+ Name + " Level " + Level);
        System.out.println("GearType : "+ RingType + " Class");
        System.out.println("ATK : " + RingStat[0] + " ,DEF : " + RingStat[1] + " ,SPD : " + RingStat[2] + " ,INT :" + RingStat[3]);
        System.out.println("------------------------------------");

    }

    @Override
    public void Equip(RPGCharacter player) {
        double[] PlayerBase = player.ATK_DEF_SPD;
        int[] TypeBuff = new int[4];
        if(RingType.equals(type[0]) && player.PlayerClass.equals("Knight")){
            TypeBuff = KnightBuff();
        }
        else if(RingType.equals(type[1]) && player.PlayerClass.equals("Mage")){
            TypeBuff = MageBuff();
        }

        player.ATK_DEF_SPD = new double[]{
                PlayerBase[0] + RingStat[0] + TypeBuff[0],
                PlayerBase[1] + RingStat[1] + TypeBuff[1],
                PlayerBase[2] + RingStat[2] + TypeBuff[2],
                PlayerBase[3] + RingStat[3] + TypeBuff[3]};
    }
}
