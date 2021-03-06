package rznw.game.maincharacter.inventory;

public class WoodenSword extends Weapon
{
    public String getDisplayName()
    {
        return "Wooden Sword";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 10;
    }

    public int getValue()
    {
        return 200;
    }
}
