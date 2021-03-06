package rznw.game.spell.ninja;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;

public class ReversePainSpell extends UndirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Reverse Pain");

        MainCharacter character = gameWorld.getMainCharacter();
        character.getStatusEffects().reversePain();
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage taken: 0",
            "HP healed: 100% of enemy damage",
            "Number of turns: 1"
        };
    }
}
