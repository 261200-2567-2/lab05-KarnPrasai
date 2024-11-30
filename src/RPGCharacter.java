import java.text.DecimalFormat;
import java.util.Random;

public class RPGCharacter implements Knight, Mage {
    String name;
    String PlayerClass;
    String AttackType;
    int[] Level_EXP_CAP;
    boolean end;
    private double[] BaseHP_MP = new double[]{0,0};
    double[] HP_MP;
    private double[] BaseATK_DEF_SPD = new double[]{0,0,0,0};
    double[] ATK_DEF_SPD;
    boolean isEnemy = false;


    Sword mysword = new Sword(false);
    MagicStaff myStaff = new MagicStaff(false);
    Shield myshield = new Shield(false);
    Ring myRing = new Ring(false,1);
    Cape myCape = new Cape(false,1);

    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    private static final String[] All_Enemy = {"Slime","Goblin","Skeleton","Orc"};
    private static final String[] All_charClass = {"Knight","Mage"};
    private static final String[] Attack_Type = {"Strength","Magic"};

    RPGCharacter(String name,int level,String Class,boolean isEnemy) {
        if(!isEnemy)
        {
            this.name = name;
            this.PlayerClass = Class;
            this.end = false;
            this.Level_EXP_CAP = new int[]{level, 0, 100};
            this.BaseHP_MP = new double[]{100, 50};
            this.HP_MP = new double[]{BaseHP_MP[0], BaseHP_MP[1]};
            if(PlayerClass.equals(All_charClass[0]))
            {
                this.BaseATK_DEF_SPD = new double[]{2, 2, 5, 0};
                this.AttackType = "Strength";
                mysword.SwordEquipped = true;
                myshield.ShieldEquipped = true;
            }
            else if(PlayerClass.equals(All_charClass[1]))
            {
                this.BaseATK_DEF_SPD = new double[]{1, 1, 5, 2};
                this.AttackType = "Magic";
                myStaff.StaffEquipped = true;
                myshield.ShieldEquipped = true;
            }
            this.ATK_DEF_SPD = new double[]{BaseATK_DEF_SPD[0], BaseATK_DEF_SPD[1], BaseATK_DEF_SPD[2], BaseATK_DEF_SPD[3]};
            myRing.Equip(this);
            myCape.Equip(this);
            EquipReStat();
        }
        else
        {
            CreateEnemy(level);
        }
    }

    private void CreateEnemy(int level) {
        Random rand = new Random();
        int levelRandom = rand.nextInt(1,5);
        int nameRandom = rand.nextInt(1,4);
        this.name = All_Enemy[nameRandom-1];
        this.PlayerClass = "Enemy";
        this.AttackType = Attack_Type[rand.nextInt(Attack_Type.length)];
        this.Level_EXP_CAP = new int[]{level - 1 + levelRandom, 0, 0};
        switch(All_Enemy[nameRandom-1])
        {
            case "Slime":{
                this.BaseHP_MP = new double[]{20 + (level * 5),50};
                this.HP_MP = BaseHP_MP;
                this.ATK_DEF_SPD = new double[]{1 + level, 1 + level, 1 + level * 0.05,1 + level};
                break;
            }
            case "Goblin":{
                this.BaseHP_MP = new double[]{30 + (level * 5),50};
                this.HP_MP = BaseHP_MP;
                if(AttackType.equals(Attack_Type[0]))
                    this.ATK_DEF_SPD = new double[]{3 + level, 5 + level, 3 + level * 0.1,0};
                else if(AttackType.equals(Attack_Type[1]))
                    this.ATK_DEF_SPD = new double[]{1 + level * 0.5, 5 + level, 3 + level * 0.1,3+level};
                break;
            }
            case "Skeleton":{
                this.BaseHP_MP = new double[]{25 + (level * 5),50};
                this.HP_MP = BaseHP_MP;
                if(AttackType.equals(Attack_Type[0]))
                    this.ATK_DEF_SPD = new double[]{5 + level, 4 + level, 2 + level * 0.1,0};
                else if(AttackType.equals(Attack_Type[1]))
                    this.ATK_DEF_SPD = new double[]{1 + level*0.5, 4 + level, 2 + level * 0.1,5+level};
                break;
            }
            case "Orc":{
                this.BaseHP_MP = new double[]{50 + (level * 5),50};
                this.HP_MP = BaseHP_MP;
                if(AttackType.equals(Attack_Type[0]))
                    this.ATK_DEF_SPD = new double[]{7 + level, 7 + level, 1 + level * 0.05,0};
                else if(AttackType.equals(Attack_Type[1]))
                    this.ATK_DEF_SPD = new double[]{3 + level*0.5, 7 + level, 1 + level * 0.05,7+level};
                break;
            }
        }
    }

    public double[] GetBaseStat(){
        return BaseHP_MP;
    }

    @Override
    public void useSwordDash() {
        System.out.println(name + " use Sword Dash!!");
    }

    @Override
    public void useShieldGuard() {
        System.out.println(name + " use Shield Guard!!");
    }

    @Override
    public void useFireBall() {
        System.out.println(name + " use Fire Ball!!");
    }

    @Override
    public void useMagicMirror() {
        System.out.println(name + " use Magic Mirror!!");
    }

    @Override
    public void Status() {
        System.out.println("------------------------------------");
        System.out.println("Hero Info");
        System.out.println("------------------------------------");
        System.out.println("Name : " + name);
        System.out.println("Class : " + PlayerClass);
        System.out.println("Level : " + Level_EXP_CAP[0]);
        System.out.println("Exp : " + Level_EXP_CAP[1]+ " / " + Level_EXP_CAP[2]);
        System.out.println("------------------------------------");
        System.out.println("Status");
        System.out.println("------------------------------------");
        System.out.println("Hp : " + HP_MP[0] + " / " + BaseHP_MP[0]);
        System.out.println("Mp : " + HP_MP[1] + " / " + BaseHP_MP[1]);
        System.out.println("Atk : " + ATK_DEF_SPD[0] + "(+" + (myRing.RingStat[0] + myCape.CapeStat[0] + (mysword.SwordEquipped ?  mysword.SwordDmg : 0)) +")");
        System.out.println("Def : " + ATK_DEF_SPD[1] + "(+" + (myRing.RingStat[1] + myCape.CapeStat[1]+ (myshield.ShieldEquipped ? myshield.ShieldDef : 0)) +")");
        System.out.println("Spd : " + decfor.format(ATK_DEF_SPD[2]) +
                "(" + (ATK_DEF_SPD[2] == BaseATK_DEF_SPD[2] ? "-0" : (ATK_DEF_SPD[2] > BaseATK_DEF_SPD[2] ? "+" : "-")
                + decfor.format(Math.abs(BaseATK_DEF_SPD[2] - ATK_DEF_SPD[2] ))) +")");
        System.out.println("Int : " + ATK_DEF_SPD[3] + "(+" + (myRing.RingStat[3] + myCape.CapeStat[3] + (myStaff.StaffEquipped ? myStaff.StaffDmg : 0)) +")");
        Equipment();
    }

    @Override
    public void Equipment() {
        System.out.println("------------------------------------");
        System.out.println("Equipment");
        System.out.println("------------------------------------");
        if(PlayerClass.equals(All_charClass[0])) {
            System.out.println("Weapon : " + (mysword.SwordEquipped ? "Sword Level " + mysword.Level_EXP_CAP[0]
                    + "(" + mysword.Level_EXP_CAP[1] + " / " + mysword.Level_EXP_CAP[2] + ")" : "None"));
        }
        else if(PlayerClass.equals(All_charClass[1])) {

                System.out.println("Weapon : " + (myStaff.StaffEquipped ? "Staff Level " + myStaff.Level_EXP_CAP[0]
                        +"("+myStaff.Level_EXP_CAP[1]+ " / " + myStaff.Level_EXP_CAP[2]+")": "None"));
        }
        System.out.println("Shield : " + (myshield.ShieldEquipped ? "Shield Level " + myshield.Level_EXP_CAP[0]
                + "("+myshield.Level_EXP_CAP[1]+" / "+myshield.Level_EXP_CAP[2]+")" : "None"));
        System.out.println("------------------------------------");
        System.out.println("Accessories");
        System.out.println("------------------------------------");
        myRing.Stat();
        myCape.Stat();

    }

    @Override
    public void LevelUp() {
        if(Level_EXP_CAP[1] >= Level_EXP_CAP[2]) {
            Level_EXP_CAP[1] -= Level_EXP_CAP[2];
            Level_EXP_CAP[0]++;
            Level_EXP_CAP[2] = 100 + ((Level_EXP_CAP[0] - 1) * 10);
            BaseHP_MP = new double[]{(BaseHP_MP[0] + ((Level_EXP_CAP[0] - 1) * 10)),(BaseHP_MP[1] + ((Level_EXP_CAP[0] - 1) * 2))};
            BaseATK_DEF_SPD = new double[]{BaseATK_DEF_SPD[0]+ ((Level_EXP_CAP[0] - 1) * 2)
                    ,BaseATK_DEF_SPD[1]+ ((Level_EXP_CAP[0] - 1)),BaseATK_DEF_SPD[2]+ ((Level_EXP_CAP[0] - 1)),BaseATK_DEF_SPD[3]+ ((Level_EXP_CAP[0]-1))};
            ATK_DEF_SPD = new double[]{BaseATK_DEF_SPD[0], BaseATK_DEF_SPD[1], BaseATK_DEF_SPD[2],BaseATK_DEF_SPD[3]};
            System.out.println("------------------------------------");
            System.out.println("You have level up!!! Now you're level " + Level_EXP_CAP[0]);
        }
        if(mysword.SwordEquipped)
        {
            mysword.LevelUP();
        }
        if(myStaff.StaffEquipped)
        {
            myStaff.LevelUP();
        }
        if(myshield.ShieldEquipped)
        {
            myshield.LevelUP();
        }
        EquipReStat();
    }

    @Override
    public double DMGtType(double ATk, double INT, String Type) {
        if(Type.equals("Strength")){
            return ATk;
        }
        else if(Type.equals("Magic")){
            return INT;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public double DEFType(double DEF, double INT, String Type) {
        if(Type.equals("Strength")){
            return DEF;
        }
        else if(Type.equals("Magic")){
            return (DEF/2) + (INT/2);
        }
        else
            return 0;
    }

    public void Rest()
    {
        System.out.println("Taking some rest..");
        System.out.println("---------------------------------");

        HP_MP[0] += 10 * Level_EXP_CAP[0];
        HP_MP[1] += 5 * Level_EXP_CAP[0];
        if(HP_MP[0] > BaseHP_MP[0]) {
            HP_MP[0] = BaseHP_MP[0];
        }
        if(HP_MP[1] > BaseHP_MP[1]) {
            HP_MP[1] = BaseHP_MP[1];
        }

        System.out.println("Your HP is now " + HP_MP[0]+" / " + BaseHP_MP[0]);
        System.out.println("Your MP is now " + HP_MP[1] + " / " + BaseHP_MP[1]);
        System.out.println("---------------------------------");
    }

    public void Equip()
    {
        Equipment();
        if(PlayerClass.equals("Knight"))
            System.out.println("[1] " + (mysword.SwordEquipped ? "Unequipped Sword" : "Equip Sword"));
        else if(PlayerClass.equals("Mage"))
            System.out.println("[1] " + (myStaff.StaffEquipped ? "Unequipped MagicStaff" : "Equip MagicStaff"));
        System.out.println("[2] " + (myshield.ShieldEquipped ? "Unequipped Shield" : "Equip Shield"));
        System.out.println("[3] Exit" );
        switch(Main.FalseInputChecked()) {
            case 1:{
                if(PlayerClass.equals(All_charClass[0])) {
                    mysword.SwordEquipped = !mysword.SwordEquipped;

                }
                else if(PlayerClass.equals(All_charClass[1])) {
                    myStaff.StaffEquipped = !myStaff.StaffEquipped;
                }
                EquipReStat();
                break;
            }
            case 2:{
                myshield.ShieldEquipped = !myshield.ShieldEquipped;
                EquipReStat();
                break;
            }
            case 3:{
                break;
            }
            case 4:{
                System.out.println(" ");
                System.out.println("------------------------------------");
                System.out.println("Wrong Input!! Please try again!");
                System.out.println("---------------------------------");
                System.out.println(" ");
                break;
            }
        }

    }

    void EquipReStat()
    {
        double amount = 0;
        if(mysword.SwordEquipped)
        {
            ATK_DEF_SPD[0] = BaseATK_DEF_SPD[0] + mysword.SwordDmg;
            amount += mysword.SwordSpd;
        }
        else
        {
            ATK_DEF_SPD[0] = BaseATK_DEF_SPD[0];
        }

        if(myStaff.StaffEquipped){
            ATK_DEF_SPD[3] = BaseATK_DEF_SPD[3] + myStaff.StaffDmg;
            amount += myStaff.StaffSpeed;
        }
        else
        {
            ATK_DEF_SPD[3] = BaseATK_DEF_SPD[3];
        }

        if(myshield.ShieldEquipped)
        {
            ATK_DEF_SPD[1] = BaseATK_DEF_SPD[1] + myshield.ShieldDef;
            amount += myshield.ShieldSpd;

        }
        else
        {
            ATK_DEF_SPD[1] = BaseATK_DEF_SPD[1];
        }

        myRing.Equip(this);
        myCape.Equip(this);

        ATK_DEF_SPD[2] = BaseATK_DEF_SPD[2] + myRing.RingStat[2] + myCape.CapeStat[2] - amount;
    }


}
