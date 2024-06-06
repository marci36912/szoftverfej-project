package Chess.Saves;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHolder
{
    public static List<GameInfo> GameInfos = new ArrayList<GameInfo>();

    public static void WriteSaves(File file) throws IOException
    {
        if (GameInfos.size() <= 0)
        {
            throw new IllegalCallerException("Nincs jatek eltarolva");
        }

        JsonWriter.writeSaves(file, GameInfos);
    }

    public static void LoadSaves(File file) throws IOException
    {
        GameInfos = JsonReader.readSaves(file);
    }
}