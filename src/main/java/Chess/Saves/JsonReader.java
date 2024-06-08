package Chess.Saves;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * A class for reading a json save file
 */
public class JsonReader
{
    /***
     * @param file the path of the save file
     * {@return a list of the saves}
     * @throws IOException when a not valid save.json is provided
     */
    public static List<GameInfo> readSaves(File file) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        GameInfo[] infos = mapper.readValue(file, GameInfo[].class);
        return Arrays.stream(infos).toList();
    }

}
