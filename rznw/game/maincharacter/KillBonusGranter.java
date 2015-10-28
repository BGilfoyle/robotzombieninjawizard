package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.utility.RandomNumberGenerator;

public class KillBonusGranter
{
    public void grantKillBonuses(Character character, Character otherCharacter)
    {
        if (!(character instanceof MainCharacter) || !(otherCharacter instanceof EnemyCharacter))
        {
            return;
        }

        MainCharacter mainCharacter = (MainCharacter)character;
        EnemyCharacter enemyCharacter = (EnemyCharacter)otherCharacter;

        this.grantExperience(mainCharacter, enemyCharacter);
        this.grantGold(mainCharacter, enemyCharacter);
        this.grantItems(mainCharacter, enemyCharacter);
    }

    private void grantExperience(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int oldLevel = mainCharacter.getLevel();

        int experience = enemyCharacter.getExperienceReward();
        mainCharacter.grantExperience(experience);
        int newLevel = ExperienceCalculator.getLevel(mainCharacter.getExperience());

        if (newLevel > oldLevel)
        {
            System.out.println("Leveling up " + (newLevel - oldLevel) + " time(s) to level " + newLevel);
            mainCharacter.setLevel(newLevel);
            mainCharacter.setPendingLevels(newLevel - oldLevel);
        }
    }

    private void grantGold(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int baseGold = enemyCharacter.getNumGold();
        int bonusGoldPercent = RandomNumberGenerator.randomInteger(0, mainCharacter.getSkillPoints(10));
        int bonusGold = (int)(baseGold * bonusGoldPercent / 100);

        mainCharacter.getInventory().addGold(baseGold + bonusGold);
    }

    private void grantItems(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        InventoryItemGroup itemGroup = enemyCharacter.getItemDrops();
        if (itemGroup != null)
        {
            mainCharacter.getInventory().addItems(itemGroup);
        }
    }
}
