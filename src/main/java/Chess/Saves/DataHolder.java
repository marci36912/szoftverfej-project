package Chess.Saves;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * A class for holding information about a game.
 */
public class DataHolder
{
    /***
     * Every game that's have been played and saved.
     */
    public static ArrayList<GameInfo> GameInfos;

    /***
     * Default constructor for DataHolder.
     */
    public DataHolder()
    {
        if(GameInfos == null)
        {
            GameInfos = new ArrayList<GameInfo>();
        }
    }

    /***
     * writes a save to save.json
     * @param file the directory of the save
     * @throws IOException error during the writing
     */
    public void WriteSaves(File file) throws IOException
    {
        if (GameInfos.size() <= 0)
        {
            throw new IllegalCallerException("Nincs jatek eltarolva");
        }

        JsonWriter.writeSaves(file, GameInfos);
    }

    /***
     * Loads a save file
     * @param file save.json
     * @throws IOException not valid file given
     */
    public void LoadSaves(File file) throws IOException
    {
        GameInfos.clear();
        GameInfos.addAll(JsonReader.readSaves(file));
    }

    /***
     * Ads a new game to the list.
     * \
     * @param name the name of the player
     * @param steps the count of the steps the player took
     * @param duration the time in seconds that elapsed during the game
     */
    public void AddGame(String name, int steps, long duration)
    {
        GameInfos.add(new GameInfo(name, steps, duration));
    }
}