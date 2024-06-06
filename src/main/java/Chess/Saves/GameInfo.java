package Chess.Saves;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonDeserialize(as = GameInfo.class)
public class GameInfo
{
    private String userName;
    private String duration;
    private String date;
    private int steps;

    public GameInfo()
    {
        super();
    }

    public GameInfo(String userName, int steps, long duration)
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
        this.date = LocalDate.now().toString();
        this.duration = timeFormatter(duration);
    }

    private String timeFormatter(long duration)
    {
        return String.format("%02d:%02d", (int)duration / 60, (int)duration % 60);
    }

    public String getUserName()
    {
        return userName;
    }

    public String getDuration()
    {
        return duration;
    }

    public String getDate()
    {
        return date;
    }

    public int getSteps()
    {
        return steps;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setSteps(int steps)
    {
        this.steps = steps;
    }

    @Override
    public String toString()
    {
        return "GameInfo{" +
                "userName='" + userName + '\'' +
                ", duration='" + duration + '\'' +
                ", date='" + date + '\'' +
                ", steps=" + steps +
                '}';
    }
}
