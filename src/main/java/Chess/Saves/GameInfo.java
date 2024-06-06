package Chess.Saves;

import java.time.LocalDate;

public class GameInfo
{
    private String userName;
    private String duration;
    private LocalDate date;
    private int steps;

    public GameInfo(String userName, int steps, float duration)
    {
        if(userName == null || userName.isEmpty())
        {
            this.userName = "Anonymus";
        }
        else
        {
            this.userName = userName;
        }

        this.steps = steps;
        this.date = LocalDate.now();
        this.duration = timeFormatter(duration);
    }

    private String timeFormatter(float duration)
    {
        return String.format("%02d:%02d", duration / 60, duration % 60);
    }
}
