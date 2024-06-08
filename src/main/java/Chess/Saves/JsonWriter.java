package Chess.Saves;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/***
 * A class for writing a json save file
 */
public class JsonWriter
{
    /***
     * Writes a save to a selected location
     * @param outputFile the path of the file
     * @param games a list of the games
     * @throws IOException when a not valid save or list of games provided
     */
    public static void writeSaves(File outputFile, List<GameInfo> games) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(outputFile, games);
    }
}
