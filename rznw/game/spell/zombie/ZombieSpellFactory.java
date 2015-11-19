package rznw.game.spell.zombie;

import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;

public class ZombieSpellFactory extends SpellFactory
{
    public Spell getSpell(int spellIndex)
    {
        switch (spellIndex)
        {
            case 4:
                return new FeedFleshSpell();
            case 6:
                return new FeedPastSpell();
            default:
                return null;
        }
    }
}
