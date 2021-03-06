package rznw.game.spell.robot;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class RobotSpellFactory extends SpellFactory
{
    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case 0:
                return new PowerDownSpell();
            case 1:
                return new ElectricFieldSpell();
            case 2:
                return new OverloadSpell();
            case 3:
                return new SuckPowerSpell();
            case 4:
                return new RocketPackSpell();
            case 5:
                return new RocketJumpSpell();
            case 6:
                return new RocketShotSpell();
            case 7:
                return new ParalyzingBlastSpell();
            case 8:
                return new GeneticTargetingSpell();
            case 9:
                return new LevelDownSpell();
            case 10:
                return new BoostGeneticsSpell();
            case 11:
                return new LevelUpSpell();
            case 12:
                return new ExtractWeaponSpell();
            case 13:
                return new MeatShieldSpell();
            case 14:
                return new SignalWeaponSpell();
            default:
                return null;
        }
    }
}
