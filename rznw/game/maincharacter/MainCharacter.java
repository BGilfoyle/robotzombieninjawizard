package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItem;

public abstract class MainCharacter extends Character
{
    public static final int STAT_POINTS_PER_LEVEL = 4;
    public static final int SKILL_POINTS_PER_LEVEL = 4;
    public static final int SPELL_POINTS_PER_LEVEL = 4;

    public static final int STAT_HEALTH = 0;
    public static final int STAT_MANA = 12;

    private static String[] statCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private static String[] statName = {
        "Health",
        "Physical Regeneration",
        "Last Breath",
        "Life Bond",
        "Accuracy",
        "Dodge",
        "Sight",
        "Find Traps",
        "Damage",
        "Padding",
        "Unencumberance",
        "Thick Skin",
        "Mana",
        "Mental Regeneration",
        "Mana Burn",
        "Magic Resistance"
    };

    private static String[] skillCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private static String[] skillName = {
        "Detect Vitality",
        "Detect Enemies",
        "Blood Rage",
        "Potion Find",
        "Summon Shopkeeper",
        "Waypoint",
        "Fast Hands",
        "Find Stairs",
        "Item Trade",
        "Rage",
        "Abundance",
        "Disarm Traps",
        "Magic Seeds",
        "Mana Suck",
        "Protective Field",
        "Mana River"
    };

    private int level = 0;
    private int experience = 0;
    private int pendingLevels = 0;

    private int[] stats;
    private int[] skills;
    private int[] spells;
    private Inventory inventory;

    public MainCharacter()
    {
        super(20, 20);

        this.stats = new int[16];
        this.skills = new int[16];
        this.spells = new int[16];

        for (int i = 0; i < this.stats.length; i++)
        {
            this.stats[i] = 0;
            this.skills[i] = 0;
            this.spells[i] = 0;
        }

        this.inventory = new Inventory();

        this.HP = this.getMaxHP();
        this.MP = this.getMaxMP();
    }

    public static String getStatCategory(int categoryNumber)
    {
        return MainCharacter.statCategory[categoryNumber];
    }

    public static String getStatName(int statNumber)
    {
        return MainCharacter.statName[statNumber];
    }

    public static String getSkillCategory(int categoryNumber)
    {
        return MainCharacter.skillCategory[categoryNumber];
    }

    public static String getSkillName(int skillNumber)
    {
        return MainCharacter.skillName[skillNumber];
    }

    public abstract String getSpellCategory(int categoryNumber);

    public abstract String getSpellName(int spellNumber);

    public abstract String getSpellDescription(int spellNumber);

    public int getLevel()
    {
        return this.level;
    }

    public int getExperience()
    {
        return this.experience;
    }

    public int getMaxHP()
    {
        return 20 + 2 * this.stats[MainCharacter.STAT_HEALTH];
    }

    public int getMaxMP()
    {
        return 20 + 2 * this.stats[MainCharacter.STAT_MANA];
    }

    public int getDamage()
    {
        return 5;
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setPendingLevels(int pendingLevels)
    {
        this.pendingLevels = pendingLevels;
    }

    public int getPendingLevels()
    {
        return this.pendingLevels;
    }

    public void grantExperience(int experience)
    {
        this.experience += experience;
    }

    public void addStatPoint(int statNumber)
    {
        this.stats[statNumber]++;
    }

    public int getStatPoints(int statNumber)
    {
        return this.stats[statNumber];
    }

    public void addSkillPoint(int skillNumber)
    {
        this.skills[skillNumber]++;
    }

    public int getSkillPoints(int skillNumber)
    {
        return this.skills[skillNumber];
    }

    public void addSpellPoint(int spellNumber)
    {
        this.spells[spellNumber]++;
    }

    public int getSpellPoints(int spellNumber)
    {
        return this.spells[spellNumber];
    }

    public void resetStateAfterLevelUp()
    {
        this.fillHP();
        this.fillMP();
    }

    public abstract String getCharacterClass();

    public void useItem(int itemIndex)
    {
        InventoryItem item = this.inventory.getItemGroup(itemIndex).getItem();
        item.useOnCharacter(this);
    }
}
