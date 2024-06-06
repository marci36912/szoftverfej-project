package Chess.Saves;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter
{
    public static void writeSaves(File outputFile, List<GameInfo> games) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(outputFile, games);
    }
}
