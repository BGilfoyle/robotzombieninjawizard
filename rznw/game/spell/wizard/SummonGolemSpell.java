package rznw.game.spell.wizard;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.SummonedGolem;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedGolemMapElement;
import rznw.map.element.Void;

public class SummonGolemSpell extends UndirectedSpell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Summon Golem");

        MapElement element = this.getPositionElement(gameWorld);
        System.out.println("Creating golem at: " + element.getRow() + ", " + element.getColumn());

        int maxHP = 100 + 5 * spellPoints;
        System.out.println("Max HP is: " + maxHP);

        SummonedGolem golem = new SummonedGolem(maxHP);
        SummonedGolemMapElement golemElement = new SummonedGolemMapElement(element.getRow(), element.getColumn(), golem);
        golem.setMapElement(golemElement);
        gameWorld.getMap().setElement(golemElement.getRow(), golemElement.getColumn(), golemElement);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private MapElement getPositionElement(GameWorld gameWorld)
    {
        HashMap<MapElement, Double> distanceMap = new HashMap<MapElement, Double>();
        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (gameWorld.getMap().getElement(row, column) == null)
                {
                    double distance = Math.sqrt(Math.pow(characterElement.getRow() - row, 2) + Math.pow(characterElement.getColumn() - column, 2));
                    distanceMap.put(new Void(row, column), distance);
                }
            }
        }

        List<java.util.Map.Entry<MapElement, Double>> distanceList = new LinkedList<java.util.Map.Entry<MapElement, Double>>(distanceMap.entrySet());
        Collections.sort(distanceList, new Comparator<java.util.Map.Entry<MapElement, Double>>()
        {
            public int compare(java.util.Map.Entry<MapElement, Double> o1, java.util.Map.Entry<MapElement, Double> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return distanceList.get(0).getKey();
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Golem HP: " + (100 + 5 * spellPoints),
            "Golem damage: 10"
        };
    }
}
