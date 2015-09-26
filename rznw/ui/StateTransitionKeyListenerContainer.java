package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;
import rznw.ui.MapRenderer;

import java.util.HashMap;

public class StateTransitionKeyListenerContainer
{
    private MainCharacterTurnHandler turnHandler;
    private MapRenderer renderer;
    private GameWorld gameWorld;

    private HashMap<Integer, StateTransitionKeyListener> listeners;

    public StateTransitionKeyListenerContainer(MainGameFrame frame, MainCharacterTurnHandler turnHandler, MapRenderer renderer, GameWorld gameWorld, MainCharacter character)
    {
        this.listeners = new HashMap<Integer, StateTransitionKeyListener>();

        this.listeners.put(DispatchKeyListener.STATE_GAME_MOTION, new MovementKeyListener(turnHandler, renderer, gameWorld, character));
        this.listeners.put(DispatchKeyListener.STATE_GAME_ESCAPE_MENU, new MainMenuKeyListener(new MainMenuRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_CHARACTER_SCREEN, new CharacterScreenKeyListener(new CharacterScreenRenderer(frame, character)));
        this.listeners.put(DispatchKeyListener.STATE_SKILLS_SCREEN, new SkillsScreenKeyListener(new SkillsScreenRenderer(frame), character));
        this.listeners.put(DispatchKeyListener.STATE_SPELLS_SCREEN, new SpellsScreenKeyListener(new SpellsScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_INVENTORY_SCREEN, new InventoryScreenKeyListener(new InventoryScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_SAVE_SCREEN, new SaveScreenKeyListener(new SaveScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_LOAD_SCREEN, new LoadScreenKeyListener(new LoadScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_NEW_GAME_SCREEN, new NewGameScreenKeyListener(new NewGameScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_EXIT_SCREEN, new ExitScreenKeyListener(new ExitScreenRenderer(frame)));
        this.listeners.put(DispatchKeyListener.STATE_LEVEL_UP_STATS_MENU, new LevelUpStatsMenuKeyListener(new LevelUpStatsMenuRenderer(frame), character));
        this.listeners.put(DispatchKeyListener.STATE_LEVEL_UP_SKILLS_MENU, new LevelUpSkillsMenuKeyListener(new LevelUpSkillsMenuRenderer(frame), character));
        this.listeners.put(DispatchKeyListener.STATE_LEVEL_UP_SPELLS_MENU, new LevelUpSpellsMenuKeyListener(new LevelUpSpellsMenuRenderer(frame), character));
    }

    public StateTransitionKeyListener getListener(int state)
    {
        return this.listeners.get(state);
    }
}