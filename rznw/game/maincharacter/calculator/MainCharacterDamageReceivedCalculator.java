package rznw.game.maincharacter.calculator;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Shield;
import rznw.utility.RandomNumberGenerator;

public class MainCharacterDamageReceivedCalculator
{
    public int getDamage(MainCharacter mainCharacter, int damage, Character damageSource, int damageSourceType)
    {
        if (damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            System.out.println("Hit by a magical source");
            int dodgePercent = 5 * mainCharacter.getSkillPoints(14);
            System.out.println("Magic dodge change: " + dodgePercent);

            Shield shield = mainCharacter.getEquipment().getEquippedShield();
            if (shield != null)
            {
                int shieldDodgePercent = shield.getMagicDodgePercent();
                System.out.println("Shield magic dodge percent: " + shieldDodgePercent);
                dodgePercent += shieldDodgePercent;
            }

            if (RandomNumberGenerator.rollSucceeds(dodgePercent))
            {
                System.out.println("Successfully dodged magic");
                return 0;
            }
        }

        int paddingPercent = 2 * mainCharacter.getStatPoints(9);
        if (mainCharacter.getStatusEffects().isResistingDamage())
        {
            paddingPercent += 2 * mainCharacter.getSpellPoints(1);
        }

        if (damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int magicPaddingPercent = 5 * mainCharacter.getStatPoints(15);

            Shield shield = mainCharacter.getEquipment().getEquippedShield();
            if (shield != null)
            {
                int shieldPaddingPercent = shield.getMagicPaddingPercent();
                System.out.println("Shield magic padding percent: " + shieldPaddingPercent);
                magicPaddingPercent += shieldPaddingPercent;
            }

            System.out.println("Preventing " + magicPaddingPercent + "% of damage");
            paddingPercent += magicPaddingPercent;
        }

        if (mainCharacter.getStatusEffects().rageEnabled())
        {
            int paddingPenalty = Math.max(21 - mainCharacter.getSkillPoints(9), 1);
            System.out.println("Padding penalty: " + paddingPenalty);
            paddingPercent -= paddingPenalty;
        }

        Shield shield = mainCharacter.getEquipment().getEquippedShield();
        if (shield != null)
        {
            int shieldPaddingPercent = shield.getPaddingPercent();
            System.out.println("Additional shield padding percent: " + shieldPaddingPercent);
            paddingPercent += shieldPaddingPercent;
        }

        Armor armor = mainCharacter.getEquipment().getEquippedArmor();
        if (armor != null)
        {
            int armorPaddingPercent = armor.getPaddingPercent();
            System.out.println("Additional armor padding percent: " + armorPaddingPercent);
            paddingPercent += armorPaddingPercent;
        }

        if (mainCharacter.getStatusEffects().meatShieldEnabled())
        {
            int meatShieldPaddingPercent = mainCharacter.getStatusEffects().getMeatShieldPaddingPercent();
            System.out.println("Additional meat shield pardding percent: " + meatShieldPaddingPercent);
            paddingPercent += meatShieldPaddingPercent;
        }

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        if (padding > 0)
        {
            System.out.println("Padding percent: " + paddingPercent);
            System.out.println("Padding damage: " + padding);
        }

        if (damageSource instanceof EnemyCharacter && damageSourceType == Character.DAMAGE_SOURCE_MAGICAL)
        {
            int bonusDamagePercent = 5 * ((EnemyCharacter)damageSource).getStatPoints(EnemyCharacter.STAT_MANA_BURN);

            if (bonusDamagePercent > 0)
            {
                 int bonusDamage = (int)Math.floor(bonusDamagePercent / 100.0 * (damage - padding));
                 damage += bonusDamage;

                 System.out.println("Enemy mana burn bonus damage: " + bonusDamage);
            }
        }

        if (mainCharacter.getStatusEffects().isReversingPain())
        {
            System.out.println("Reversing pain!");

            return -(damage - padding);
        }

        return damage - padding;
    }
}
