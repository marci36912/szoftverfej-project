package Chess.Saves;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHolder
{
    public static ArrayList<GameInfo> GameInfos;

    public DataHolder()
    {
        if(GameInfos == null)
        {
            GameInfos = new ArrayList<GameInfo>();
        }
    }

    public void WriteSaves(File file) throws IOException
    {
        if (GameInfos.size() <= 0)
        {
            throw new IllegalCallerException("Nincs jatek eltarolva");
        }

        JsonWriter.writeSaves(file, GameInfos);
    }

    public void LoadSaves(File file) throws IOException
    {
        GameInfos.clear();
        GameInfos.addAll(JsonReader.readSaves(file));
    }

    public void AddGame(String name, int steps, long duration)
    {
        GameInfos.add(new GameInfo(name, steps, duration));
    }
}