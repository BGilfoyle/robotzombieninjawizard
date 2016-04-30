package rznw.game.enemy;

import rznw.utility.RandomMatrix;
import rznw.utility.RandomNumberGenerator;

public class EnemyCharacterGenerator
{
    public EnemyCharacter generateCharacter(int dungeonLevel)
    {
        int[][] enemyProbabilities = new int[][]
        {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
        };

        RandomMatrix matrix = new RandomMatrix(enemyProbabilities);
        int index = matrix.getRandomSelection(dungeonLevel - 1);

        int enemyLevel = RandomNumberGenerator.randomInteger(0, 2 * dungeonLevel - 1);
        System.out.println("Generating enemy with level: " + enemyLevel);

        switch (index)
        {
            case 0:
                return new Mummy(enemyLevel);
            case 1:
                return new Assassin(enemyLevel);
            case 2:
                return new Werewolf(enemyLevel);
            case 3:
                return new Thief(enemyLevel);
            case 4:
                return new BeastSummoner(enemyLevel);
            case 5:
                return new Leech(enemyLevel);
            case 6:
                return new HealthNinja(enemyLevel);
            case 7:
                return new RockMan(enemyLevel);
            case 8:
                return new Yeti(enemyLevel);
            case 9:
                return new InvisibleWizard(enemyLevel);
            case 10:
                return new Viper(enemyLevel);
            case 11:
                return new Javelineer(enemyLevel);
            case 12:
                return new GravityWizard(enemyLevel);
            case 13:
                return new QuicksandDweller(enemyLevel);
            case 14:
                return new Sphinx(enemyLevel);
            case 15:
                return new XRayCat(enemyLevel);
            case 16:
                return new Enchanter(enemyLevel);
            case 17:
                return new Crusher(enemyLevel);
            case 18:
                return new Dragon(enemyLevel);
            case 19:
                return new Nosferatu(enemyLevel);
            case 20:
                return new KingLizardWizard(enemyLevel);
            case 21:
                return new Undertaker(enemyLevel);
            case 22:
                return new Phantasm(enemyLevel);
            case 23:
                return new FumeBeast(enemyLevel);
            case 24:
                return new Oni(enemyLevel);
        }

        return null;
    }
}
