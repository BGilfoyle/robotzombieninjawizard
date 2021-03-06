package rznw.game.maincharacter.inventory;

public class EtherealShield extends Shield
{
    public String getDisplayName()
    {
        return "Ethereal Shield";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDodgePercent()
    {
        return 8;
    }

    public int getPaddingPercent()
    {
        return 0;
    }

    public int getValue()
    {
        return 400;
    }
}
