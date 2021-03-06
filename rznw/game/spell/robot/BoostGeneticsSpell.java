package rznw.game.spell.robot;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class BoostGeneticsSpell extends UndirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Boost Genetics");

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        System.out.println("Radius is: " + radius);

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();
        Map map = gameWorld.getMap();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);

        int bonusDropProbability = 2 * spellPoints;
        int bonusGoldPercent = 5 * spellPoints;

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        { 
            System.out.println("Boosting genetics on an enemy: " + bonusDropProbability + ", " + bonusGoldPercent);
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            enemy.getStatusEffects().enableBoostGenetics(bonusDropProbability, bonusGoldPercent);
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Drop rate increase: " + (2 * spellPoints) + "%",
            "Bonus gold: " + (5 * spellPoints) + "%",
            "Radius: " + radius
        };
    }
}
