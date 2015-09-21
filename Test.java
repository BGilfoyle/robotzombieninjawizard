import rznw.game.CharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.generator.MapGenerator;
import rznw.turn.MainCharacterTurnHandler;
import rznw.ui.CharacterScreenKeyListener;
import rznw.ui.CharacterScreenRenderer;
import rznw.ui.CharacterSummaryRenderer;
import rznw.ui.DispatchKeyListener;
import rznw.ui.MainGameFrame;
import rznw.ui.MainMenuKeyListener;
import rznw.ui.MainMenuRenderer;
import rznw.ui.MapRenderer;
import rznw.ui.MovementKeyListener;
import rznw.ui.SkillsScreenKeyListener;
import rznw.ui.SkillsScreenRenderer;

public class Test
{
    public static void main(String[] args)
    {
        CharacterGenerator characterGenerator = new CharacterGenerator();
        MainCharacter character = characterGenerator.generateMainCharacter();

        MainGameFrame frame = new MainGameFrame("Robot Zombie Ninja Wizard");

        MapGenerator mapGenerator = new MapGenerator();
        GameWorld gameWorld = new GameWorld(character, characterGenerator, mapGenerator);
        gameWorld.generateMap();

        MapRenderer renderer = new MapRenderer(frame);
        renderer.render(gameWorld.getMap());

        CharacterSummaryRenderer characterSummaryRenderer = new CharacterSummaryRenderer(frame);
        characterSummaryRenderer.render(character);

        MainCharacterTurnHandler turnHandler = new MainCharacterTurnHandler(gameWorld, character, characterSummaryRenderer);

        MovementKeyListener movementListener = new MovementKeyListener(turnHandler, renderer, gameWorld);
        MainMenuKeyListener mainMenuKeyListener = new MainMenuKeyListener(new MainMenuRenderer(frame));
        CharacterScreenKeyListener characterScreenKeyListener = new CharacterScreenKeyListener(new CharacterScreenRenderer(frame));
        SkillsScreenKeyListener skillsScreenKeyListener = new SkillsScreenKeyListener(new SkillsScreenRenderer(frame));
        DispatchKeyListener dispatchListener = new DispatchKeyListener(movementListener, mainMenuKeyListener, characterScreenKeyListener, skillsScreenKeyListener);

        frame.display(dispatchListener);
    }
}
