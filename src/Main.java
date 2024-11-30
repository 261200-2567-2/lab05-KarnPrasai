// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;
//import java.util.Random;

//FalseInputChecker
//https://stackoverflow.com/questions/67824658/how-to-handle-users-inputting-invalid-types-into-a-scanner

public class Main {
    public static void main(String[] args) {
        String[] YourCharacter = CreateCharacter();
        RPGCharacter Player = new RPGCharacter(YourCharacter[0],1,YourCharacter[1],false);
        while(Player.HP_MP[0] > 0 || !Player.end) {
            if(Player.HP_MP[0] <= 0 || Player.end) {
                break;
            }
            MainMenu(Player);
        }
        System.out.println(" ");
        System.out.println("---------------------------------");
        System.out.println("Game Over");
        System.out.println("---------------------------------");

    }

    public static int FalseInputChecked(){
        System.out.println("------------------------------------");
        System.out.print("Please enter your Choice : ");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean userIntReceived = false;

        while (!userIntReceived) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                userIntReceived = true;
            } catch (NumberFormatException e) {
                System.out.println("That wasn't a valid input. Please try again.");
                System.out.println("---------------------------------");
                System.out.print("Please enter your Choice : ");
            }
        }
        return choice;
    }

    public static String[] CreateCharacter()
    {
        System.out.println(" ");
        System.out.println("------------------------------------");
        System.out.println("Welcome to RPG GAME.!!");
        System.out.println("Let's Start by create your character!");
        System.out.print("Character name : ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("------------------------------------");
        System.out.println("Select your character Class");
        System.out.println("[1] Knight");
        System.out.println("[2] Mage");
        String playerclass = "Knight";
        switch (FalseInputChecked()) {
            case 1: {
                playerclass = "Knight";
                break;
            }
            case 2: {
                playerclass = "Mage";
                break;
            }
        }
        return new String[]{name,playerclass};

    }

    public static void MainMenu(RPGCharacter Player)
    {
        Player.LevelUp();
        System.out.println(" ");
        System.out.println("Adventure time!!");
        System.out.println("---------------------------------");
        System.out.println("What would you like to do?");
        System.out.println("[1] Fight");
        System.out.println("[2] Rest");
        System.out.println("[3] Status");
        System.out.println("[4] Equipment");
        System.out.println("[5] Exit");
        switch(FalseInputChecked())
        {
            case 1:{
                RPGCharacter enemy = new RPGCharacter("enemy",Player.Level_EXP_CAP[0],"Enemy",true);
                CombatSys combat = new CombatSys(Player);
                combat.Attack(enemy);
                break;
            }
            case 2:{
                Player.Rest();
                break;
            }
            case 3:{
                Player.Status();
                break;
            }
            case 4:{
                Player.Equip();
                break;
            }
            case 5:{
                Player.end = true;
                break;
            }
            case 6:{
                System.out.println(" ");
                System.out.println("------------------------------------");
                System.out.println("Wrong Input!! Please try again!");
                System.out.println("---------------------------------");
                System.out.println(" ");
                break;
            }
        }
    }

}
