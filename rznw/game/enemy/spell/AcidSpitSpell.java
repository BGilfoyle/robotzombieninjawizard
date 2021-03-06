package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class AcidSpitSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting acid spit with spell points of: " + spellPoints);

        int damage = 40 + 10 * spellPoints;

        MainCharacter mainCharacter = gameWorld.getMainCharacter();
        mainCharacter.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);

        mainCharacter.getStatusEffects().poison();
        mainCharacter.getStatusEffects().confuse();
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
