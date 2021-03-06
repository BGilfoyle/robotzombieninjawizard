package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class HealShield extends Shield
{
    public String getDisplayName()
    {
        return "Heal Shield";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "Heals you when you dodge an enemy attack",
            "Heal amount: 5 HP",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDodgePercent()
    {
        return 6;
    }

    public int getPaddingPercent()
    {
        return 3;
    }

    public int getValue()
    {
        return 200;
    }

    public void dodgesAttack(MainCharacter mainCharacter)
    {
        System.out.println("Dodged attack with heal shield - healing 5 HP");

        mainCharacter.heal(5);
    }
}
