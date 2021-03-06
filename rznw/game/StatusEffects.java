package rznw.game;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class StatusEffects
{
    private static final int POISON_DAMAGE = 10;

    private Character character;

    private int frozenTurns = 0;
    private boolean poisoned = false;
    private boolean isReversingPain = false;
    private boolean deathStriking = false;
    private boolean smokeBombEnabled = false;
    private boolean counterstriking = false;
    private int confuseTurns = 0;
    private int signalWeaponTurns = 0;
    private int thornSkinTurns = 0;
    private int poisonSkinTurns = 0;
    private int barbedSkinTurns = 0;
    private int resistDamageTurns = 0;
    private int infectiousRageTurns = 0;
    private int feedBrainTurns = 0;
    private boolean inferZombie = false;
    private int turnsToSkip = 0;
    private int armorBreakPercent = 0;
    private int rageTurns = 0;
    private int detectVitalityRadius = 0;
    private int itemTradeNumber = 0;
    private int priceReductionPercent = 0;
    private int bonusDropProbability = 0;
    private int bonusGoldPercent = 0;
    private int magicSeedsTurns = 0;
    private int manaSuckTurns = 0;
    private int meatShieldTurns = 0;
    private int meatShieldPaddingPercent = 0;
    private int meatShieldDodgePercent = 0;
    private int invisibleTurns = 0;

    public StatusEffects(Character character)
    {
        this.character = character;
    }

    public void freeze()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.frozenTurns = 1;
        }
    }

    public void freeze(int numTurns)
    {
        this.frozenTurns = numTurns;
    }

    public void poison()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.poisoned = true;
        }
    }

    public boolean isPoisoned()
    {
        return this.poisoned;
    }

    public void healPoison()
    {
        this.poisoned = false;
    }

    public void reversePain()
    {
        this.isReversingPain = true;
    }

    public boolean isFrozen()
    {
        return this.frozenTurns > 0;
    }

    public boolean isReversingPain()
    {
        return this.isReversingPain;
    }

    public void enableDeathStrike()
    {
        this.deathStriking = true;
    }

    public boolean isDeathStriking()
    {
        return this.deathStriking;
    }

    public void disableDeathStrike()
    {
        this.deathStriking = false;
    }

    public void enableSmokeBomb()
    {
        this.smokeBombEnabled = true;
    }

    public boolean smokeBombEnabled()
    {
        return this.smokeBombEnabled;
    }

    public void disableSmokeBomb()
    {
        this.smokeBombEnabled = false;
    }

    public void enableCounterstrike()
    {
        this.counterstriking = true;
    }

    public boolean isCounterstriking()
    {
        return this.counterstriking;
    }

    public void disableCounterstrike()
    {
        this.counterstriking = false;
    }

    public void confuse()
    {
        if (!this.thickSkinDodgesEffect())
        {
            this.confuseTurns = 3;
        }
    }

    public void healConfusion()
    {
        this.confuseTurns = 0;
    }

    public boolean isConfused()
    {
        return this.confuseTurns > 0;
    }

    public void enableSignalWeapon(int numTurns)
    {
        this.signalWeaponTurns = numTurns;
    }

    public boolean signalWeaponEnabled()
    {
        return this.signalWeaponTurns > 0;
    }

    public void enableThornSkin(int numTurns)
    {
        this.thornSkinTurns = numTurns;
    }

    public boolean thornSkinEnabled()
    {
        return this.thornSkinTurns > 0;
    }

    public void enablePoisonSkin(int numTurns)
    {
        this.poisonSkinTurns = numTurns;
    }

    public boolean poisonSkinEnabled()
    {
        return this.poisonSkinTurns > 0;
    }

    public void enableBarbedSkin(int numTurns)
    {
        this.barbedSkinTurns = numTurns;
    }

    public boolean barbedSkinEnabled()
    {
        return this.barbedSkinTurns > 0;
    }

    public void enableResistDamage(int numTurns)
    {
        this.resistDamageTurns = numTurns;
    }

    public boolean isResistingDamage()
    {
        return this.resistDamageTurns > 0;
    }

    public void enableInfectiousRage(int numTurns)
    {
        this.infectiousRageTurns = numTurns;
    }

    public boolean infectiousRageEnabled()
    {
        return this.infectiousRageTurns > 0;
    }

    public void enableFeedBrain(int numTurns)
    {
        this.feedBrainTurns = numTurns;
    }

    public boolean feedBrainEnabled()
    {
        return this.feedBrainTurns > 0;
    }

    public void enableInferZombie()
    {
        this.inferZombie = true;
    }

    public boolean isInferringZombie()
    {
        return this.inferZombie;
    }

    public void disableInferZombie()
    {
        this.inferZombie = false;
    }

    public void skipTurns(int turnsToSkip)
    {
        this.turnsToSkip = turnsToSkip;
    }

    public boolean isSkippingTurn()
    {
        return this.turnsToSkip > 0;
    }

    public void breakArmor(int armorBreakPercent)
    {
        this.armorBreakPercent = armorBreakPercent;
    }

    public int getArmorBreakPercent()
    {
        return this.armorBreakPercent;
    }

    public void enableRage(int numTurns)
    {
        this.rageTurns = numTurns;
    }

    public boolean rageEnabled()
    {
        return this.rageTurns > 0;
    }

    public void enableDetectVitality(int detectVitalityRadius)
    {
        this.detectVitalityRadius = detectVitalityRadius;
    }

    public boolean detectVitalityEnabled()
    {
        return this.detectVitalityRadius > 0;
    }

    public void disableDetectVitality()
    {
        this.detectVitalityRadius = 0;
    }

    public void enableItemTrade(int itemTradeNumber)
    {
        this.itemTradeNumber = itemTradeNumber;
    }

    public boolean itemTradeEnabled()
    {
        return this.itemTradeNumber > 0;
    }

    public boolean disableItemTrade()
    {
        return this.itemTradeNumber > 0;
    }

    public void enableSummonShopkeeper(int priceReductionPercent)
    {
        this.priceReductionPercent = priceReductionPercent;
    }

    public void disableSummonShopkeeper()
    {
        this.priceReductionPercent = 0;
    }

    public boolean summonShopkeeperEnabled()
    {
        return this.priceReductionPercent > 0;
    }

    public void enableBoostGenetics(int bonusDropProbability, int bonusGoldPercent)
    {
        this.bonusDropProbability = bonusDropProbability;
        this.bonusGoldPercent = bonusGoldPercent;
    }

    public int getBonusDropProbability()
    {
        return this.bonusDropProbability;
    }

    public int getBonusGoldPercent()
    {
        return this.bonusGoldPercent;
    }

    public boolean magicSeedsEnabled()
    {
        return this.magicSeedsTurns > 0;
    }

    public void enableMagicSeeds(int numTurns)
    {
        this.magicSeedsTurns = numTurns;
    }

    public boolean manaSuckEnabled()
    {
        return this.manaSuckTurns > 0;
    }

    public void enableManaSuck(int numTurns)
    {
        this.manaSuckTurns = numTurns;
    }

    public int getMeatShieldPaddingPercent()
    {
        return this.meatShieldPaddingPercent;
    }

    public int getMeatShieldDodgePercent()
    {
        return this.meatShieldDodgePercent;
    }

    public boolean meatShieldEnabled()
    {
        return this.meatShieldTurns > 0;
    }

    public void enableMeatShield(int numTurns, int bonusPadding, int bonusDodge)
    {
        this.meatShieldTurns = numTurns;
        this.meatShieldPaddingPercent = bonusPadding;
        this.meatShieldDodgePercent = bonusDodge;
    }

    private boolean thickSkinDodgesEffect()
    {
        if (!(this.character instanceof MainCharacter))
        {
            return false;
        }

        int statPoints = ((MainCharacter)this.character).getStatPoints(11);
        int probability = 5 * statPoints;

        Armor armor = ((MainCharacter)character).getEquipment().getEquippedArmor();
        if (armor != null)
        {
            System.out.println("Adding additional thick skin stats from armor");
            probability += armor.getThickSkinBonus();
        }

        boolean success = RandomNumberGenerator.rollSucceeds(probability);

        if (success)
        {
            System.out.println("Dodging status effect due to thick skin");
        }

        return success;
    }

    public void makeInvisible(int numTurns)
    {
        this.invisibleTurns = numTurns;
    }

    public boolean isInvisible()
    {
        return this.invisibleTurns > 0;
    }

    public void processTurn(Character character, GameWorld gameWorld)
    {
        if (this.frozenTurns > 0)
        {
            this.frozenTurns--;
        }

        if (this.poisoned)
        {
            System.out.println("Damaging character due to poison");
            character.damage(StatusEffects.POISON_DAMAGE, null, gameWorld, Character.DAMAGE_SOURCE_OTHER);
        }

        this.isReversingPain = false;

        if (this.confuseTurns > 0)
        {
            this.confuseTurns--;
        }

        if (this.signalWeaponTurns > 0)
        {
            System.out.println("Used up one turn of signal weapon");
            this.signalWeaponTurns--;
        }

        if (this.thornSkinTurns > 0)
        {
            this.thornSkinTurns--;
        }

        if (this.poisonSkinTurns > 0)
        {
            this.poisonSkinTurns--;
        }

        if (this.barbedSkinTurns > 0)
        {
            this.barbedSkinTurns--;
        }

        if (this.resistDamageTurns > 0)
        {
            this.resistDamageTurns--;
        }

        if (this.infectiousRageTurns > 0)
        {
            this.infectiousRageTurns--;
        }

        if (this.feedBrainTurns > 0)
        {
            this.feedBrainTurns--;
        }

        if (this.turnsToSkip > 0)
        {
            this.turnsToSkip--;
        }

        if (this.rageTurns > 0)
        {
            this.rageTurns--;
        }

        if (this.magicSeedsTurns > 0)
        {
            this.magicSeedsTurns--;
        }

        if (this.manaSuckTurns > 0)
        {
            this.manaSuckTurns--;
        }

        if (this.meatShieldTurns > 0)
        {
            this.meatShieldTurns--;
        }

        if (this.invisibleTurns > 0)
        {
            this.invisibleTurns--;
        }
    }
}
