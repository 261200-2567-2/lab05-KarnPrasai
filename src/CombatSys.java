import java.util.Random;
import java.util.Scanner;

public class CombatSys {

    RPGCharacter player;
    private boolean end = false;
    private static final Random rand = new Random();

    private static final String[] CombatAction = {"Attack","Defense","Dodge"};

    CombatSys(RPGCharacter P1) {
        this.player = P1;
    }

    void Attack(RPGCharacter enemy)
    {
        while(player.HP_MP[0] > 0 || enemy.HP_MP[0] > 0 || !end){
            if(player.HP_MP[0] <= 0)
            {
                System.out.println(" ");
                System.out.println("------------------------------------");
                System.out.println("You Died!");
                player.end = true;
                break;
            }
            if(enemy.HP_MP[0] <= 0)
            {
                int EXPGain = enemy.Level_EXP_CAP[0] * rand.nextInt(20,50);
                player.Level_EXP_CAP[1] += EXPGain;
                if(player.mysword.SwordEquipped)
                    player.mysword.Level_EXP_CAP[1] += EXPGain / 2;
                if(player.myshield.ShieldEquipped)
                    player.myshield.Level_EXP_CAP[1] += EXPGain / 2;


                System.out.println(" ");
                System.out.println("-----------------------------------");
                System.out.println("You Defeated a " +enemy.name + " !");
                System.out.println("Receive " + EXPGain + " exp !!!");
                EnemyDrop(player);
                break;
            }
            if(end)
            {
                break;
            }
            System.out.println(" ");
            System.out.println("Fighting "+enemy.name+"!!");
            System.out.println(" ");
            System.out.println("------------------------------------");
            System.out.println(" ");
            System.out.println("Enemy : "+ enemy.name);
            System.out.println("Level : "+ enemy.Level_EXP_CAP[0]);
            System.out.println("HP : "+ enemy.HP_MP[0]);
            System.out.println("Atk : "+enemy.ATK_DEF_SPD[0]);
            System.out.println("Def : "+enemy.ATK_DEF_SPD[1]);
            System.out.println("Spd : "+enemy.ATK_DEF_SPD[2]);
            System.out.println(" ");
            System.out.println("------------------------------------");
            System.out.println(" ");
            System.out.println("Player");
            System.out.println("Level : "+ player.Level_EXP_CAP[0]);
            System.out.println("HP : "+ player.HP_MP[0]);
            System.out.println("MP : "+ player.HP_MP[1]);
            System.out.println("Atk : "+player.ATK_DEF_SPD[0]);
            System.out.println("Def : "+player.ATK_DEF_SPD[1]);
            System.out.println(" ");
            System.out.println("-----------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("------------------------------------");
            System.out.println("[1] Attack (Cost 5 MP)");
            System.out.println("[2] Defend (Regen 10 MP)");
            System.out.println("[3] Dodge (Regen 10 MP)");
            System.out.println("[4] Run");
            switch(Main.FalseInputChecked())
            {
                case 1:{
                    if(player.HP_MP[1] >= 5)
                    {
                        if(player.ATK_DEF_SPD[2] < enemy.ATK_DEF_SPD[2])
                        {
                            ActionTurn(enemy,player,CombatAction[0]);
                            if(player.HP_MP[0] <= 0)
                            {
                                break;
                            }
                            ActionTurn(player,enemy,CombatAction[0]);
                        }
                        else
                        {
                            ActionTurn(player,enemy,CombatAction[0]);
                            if(enemy.HP_MP[0] <= 0)
                            {
                                break;
                            }
                            ActionTurn(enemy,player,CombatAction[0]);
                        }
                        player.HP_MP[1] -= 5;
                        break;
                    }

                    else{
                        System.out.println("You don't have enough MP!!");
                    }


                }
                case 2:{
                    ActionTurn(player,enemy,CombatAction[1]);
                    player.HP_MP[1] += 10;
                    double[] BaseHPMP = player.GetBaseStat();
                    if(player.HP_MP[1] > BaseHPMP[1])
                    {
                        player.HP_MP[1] = BaseHPMP[1];
                    }
                    break;
                }
                case 3:{
                    ActionTurn(player,enemy,CombatAction[2]);
                    player.HP_MP[1] += 10;
                    double[] BaseHPMP = player.GetBaseStat();
                    if(player.HP_MP[1] > BaseHPMP[1])
                    {
                        player.HP_MP[1] = BaseHPMP[1];
                    }
                    break;
                }
                case 4:{
                    System.out.println(" ");
                    System.out.println("------------------------------------");
                    System.out.println("Run Away!!!!");
                    end = true;
                    break;
                }
                case 5:{
                    System.out.println(" ");
                    System.out.println("------------------------------------");
                    System.out.println("Wrong Input!! Please try again!");
                    System.out.println("------------------------------------");
                    System.out.print("Please enter your Choice : ");
                    break;
                }
            }

        }


    }

    private static void ActionTurn(RPGCharacter First,RPGCharacter Second,String Type)
    {
        double FirstDMG = First.DMGtType(First.ATK_DEF_SPD[0],First.ATK_DEF_SPD[3],First.AttackType);
        double SecDMG = Second.DMGtType(Second.ATK_DEF_SPD[0],Second.ATK_DEF_SPD[3],Second.AttackType);
        double FirstDef = First.DEFType(First.ATK_DEF_SPD[1],First.ATK_DEF_SPD[3],Second.AttackType);
        double SecDef = Second.DEFType(Second.ATK_DEF_SPD[1],Second.ATK_DEF_SPD[3],First.AttackType);
        if(Type.equals(CombatAction[0]))
        {
            double DMG = (FirstDMG
                    - SecDef*0.5) <= 0 ? 0 : FirstDMG - (SecDef*0.5);
            Second.HP_MP[0] -= DMG;
            System.out.println(" ");
            System.out.println("------------------------------------");
            if(!First.isEnemy)
            {
                if(First.PlayerClass.equals("Knight"))
                {
                    First.useSwordDash();
                }
                else if(First.PlayerClass.equals("Mage"))
                {
                    First.useFireBall();
                }
            }
            System.out.println(" ");
            System.out.println(First.name + " attacked " + Second.name + "!");
            System.out.println("Deal " + DMG + " Damage.");

        }
        if(Type.equals(CombatAction[1])){
            double DMG = (SecDMG - FirstDef <= 0 ? 0 : SecDMG - FirstDef);
            First.HP_MP[0] -= DMG;
            System.out.println(" ");
            System.out.println("------------------------------------");
            if(!First.isEnemy)
            {
                if(First.PlayerClass.equals("Knight"))
                {
                    First.useShieldGuard();
                }
                else if(First.PlayerClass.equals("Mage"))
                {
                    First.useMagicMirror();
                }
            }
            System.out.println(" ");
            System.out.println(Second.name + " attacked " + First.name + "!");
            System.out.println("Deal " + DMG + " Damage.");
        }
        if(Type.equals(CombatAction[2])){
            int evadeChance = rand.nextInt(1,100);
            if(First.ATK_DEF_SPD[2] / Second.ATK_DEF_SPD[2] * 100 >= evadeChance)
            {
                System.out.println(" ");
                System.out.println("------------------------------------");
                System.out.println("Dodged!!");
            }
            else
            {
                System.out.println(" ");
                System.out.println("------------------------------------");
                System.out.println("Failed to Evade");
                ActionTurn(Second,First,"Attack");
            }
        }

    }

    private void EnemyDrop(RPGCharacter player)
    {
        int DropItem = rand.nextInt(10,100);
        int DropChance = rand.nextInt(1,100);
        if(DropChance <= 80) {
            System.out.println("The enemy Drop some item!!");
            if (DropItem <= 10) {
                Ring DroppedRing = new Ring(true, player.Level_EXP_CAP[0]);
                DroppedRing.Stat();
                int EquipChoice = EquippedChoice();
                if(EquipChoice == 1){
                    player.myRing = DroppedRing;
                    player.myRing.Equip(player);
                    player.EquipReStat();
                    System.out.println("You have equipped a new Ring ");
                }
                else
                {
                    System.out.println("You have thrown it away");
                }
            }
            else{
                Cape DroppedCape = new Cape(true,player.Level_EXP_CAP[0]);
                DroppedCape.Stat();
                int EquipChoice = EquippedChoice();
                if(EquipChoice == 1){
                    player.myCape = DroppedCape;
                    player.myCape.Equip(player);
                    player.EquipReStat();
                    System.out.println("You have equipped a new Cape ");
                }
                else
                {
                    System.out.println("You have thrown it away");
                }
            }
        }
    }
    private int EquippedChoice()
    {
        System.out.println("------------------------------------");
        System.out.println("Would you like to take it?");
        System.out.println("------------------------------------");
        System.out.println("[1] Take it");
        System.out.println("[2] Throw it away");
        return Main.FalseInputChecked();
    }


}
