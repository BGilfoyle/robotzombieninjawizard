package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;

public class Potion extends InventoryItem
{
    public String getDisplayName()
    {
        return "Potion";
    }

    public void useOnCharacter(MainCharacter character)
    {
        System.out.println("TODO: Use potion on character");
    }
}
