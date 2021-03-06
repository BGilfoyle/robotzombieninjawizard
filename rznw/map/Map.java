package rznw.map;

import java.util.Collection;
import java.util.Vector;

import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.map.element.SummonedMinionMapElement;
import rznw.game.maincharacter.MainCharacter;

public class Map
{
    public static final int NUM_ROWS = 30;
    public static final int NUM_COLUMNS = 40;
    public static final int MAX_LEVEL = 12;

    private int level;
    private MapElement[][] elements;
    private MapElement[][] backgroundElements;
    private boolean[][] visible;
    private boolean[][] visited;

    public Map(int level)
    {
        this.level = level;

        this.elements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.backgroundElements = new MapElement[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.visible = new boolean[Map.NUM_ROWS][Map.NUM_COLUMNS];
        this.visited = new boolean[Map.NUM_ROWS][Map.NUM_COLUMNS];

        for (int i = 0; i < Map.NUM_ROWS; i++)
        {
            for (int j = 0; j < Map.NUM_COLUMNS; j++)
            {
                this.visible[i][j] = false;
                this.visited[i][j] = false;
            }
        }
    }

    public int getLevel()
    {
        return this.level;
    }

    public MapElement getElement(int i, int j)
    {
        return this.elements[i][j];
    }

    public MapElement getBackgroundElement(int i, int j)
    {
        return this.backgroundElements[i][j];
    }

    public void setElement(int i, int j, MapElement element)
    {
        this.elements[i][j] = element;
    }

    public void setBackgroundElement(int i, int j, MapElement element)
    {
        this.backgroundElements[i][j] = element;
    }

    public void setVisible(MainCharacter character, int i, int j)
    {
        if (this.visible[i][j] == false)
        {
            MapElement backgroundElement = this.getBackgroundElement(i, j);
            if (backgroundElement != null)
            {
                backgroundElement.reveal(character);
            }
        }

        this.visible[i][j] = true;
    }

    public boolean isVisible(int i, int j)
    {
        return this.visible[i][j];
    }

    public void setElementVisited(MainCharacter character, int i, int j)
    {
        int radius = character.getViewRadius();

        int minRow = Math.max(i - radius, 0);
        int maxRow = Math.min(i + radius, Map.NUM_ROWS - 1);
        int minColumn = Math.max(j - radius, 0);
        int maxColumn = Math.min(j + radius, Map.NUM_COLUMNS - 1);

        for (int row = minRow; row <= maxRow; row++)
        {
            for (int column = minColumn; column <= maxColumn; column++)
            {
                this.setVisible(character, row, column);
            }
        }

        radius = 3 * character.getSkillPoints(7);
        System.out.println("Looking for stairs with radius: " + radius);
        minRow = Math.max(i - radius, 0);
        maxRow = Math.min(i + radius, Map.NUM_ROWS - 1);
        minColumn = Math.max(j - radius, 0);
        maxColumn = Math.min(j + radius, Map.NUM_COLUMNS - 1);

        for (int row = minRow; row <= maxRow; row++)
        {
            for (int column = minColumn; column <= maxColumn; column++)
            {
                if (this.getBackgroundElement(row, column) instanceof Stairs)
                {
                    if (!this.isVisible(row, column))
                    {
                        System.out.println("Found stairs using skill");
                    }
                    this.setVisible(character, row, column);
                }
            }
        }
    }

    public Collection<EnemyCharacter> getEnemies()
    {
        return new MapScraper().getEnemies(this);
    }

    public Collection<SummonedCharacter> getSummons()
    {
        return new MapScraper().getSummons(this);
    }

    public Collection<EnemyCharacter> getEnemiesInRectangle(int minRow, int minColumn, int maxRow, int maxColumn)
    {
        return new MapScraper().getEnemiesInRectangle(this, minRow, minColumn, maxRow, maxColumn);
    }

    public boolean elementVisited(int i, int j)
    {
        return this.visited[i][j];
    }

    public void visit(int i, int j)
    {
        System.out.println("Setting a map square as visited");
        this.visited[i][j] = true;
    }

    public boolean isLastLevel()
    {
        return this.level == Map.MAX_LEVEL;
    }
}
