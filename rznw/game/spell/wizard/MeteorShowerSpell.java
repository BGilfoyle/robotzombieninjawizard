package rznw.game.spell.wizard;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class MeteorShowerSpell extends UndirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Meteor Shower");

        MainCharacter character = gameWorld.getMainCharacter();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        int hitProbability = 5 * spellPoints;

        Map map = gameWorld.getMap();
        MapElement characterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();

            if (RandomNumberGenerator.rollSucceeds(hitProbability))
            {
                System.out.println("Meteor hit!");

                System.out.println("Before: " + enemy.getHP());
                int damage = 50 + 50 * spellPoints;
                enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("After: " + enemy.getHP());
            }
            else
            {
                System.out.println("Meteor miss!");
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);
        int damage = 50 + 50 * spellPoints;

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Radius: " + radius,
            "Hit probability: " + 5 * spellPoints + "%",
            "Damage: " + damage
        };
    }
}
