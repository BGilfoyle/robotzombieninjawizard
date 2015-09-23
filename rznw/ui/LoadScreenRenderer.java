package rznw.ui;

public class LoadScreenRenderer
{
    public static final int NUM_ROWS = 32;
    public static final int NUM_COLUMNS = 40;

    private MainGameFrame frame;

    public LoadScreenRenderer(MainGameFrame frame)
    {
        this.frame = frame;
    }

    public void render()
    {
        for (int i = 0; i < CharacterScreenRenderer.NUM_ROWS; i++)
        {
            for (int j = 0; j < CharacterScreenRenderer.NUM_COLUMNS; j++)
            {
                this.frame.renderDisplayCharacter(i, j, ' ');
            }
        }

        this.renderCenteredString(1, "Load");
    }

    private void renderCenteredString(int row, String string)
    {
        int column = (MainMenuRenderer.NUM_COLUMNS - string.length()) / 2;
        this.frame.renderDisplayString(row, column, string);
    }
}