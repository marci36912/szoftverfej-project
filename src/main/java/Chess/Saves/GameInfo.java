package Chess.Saves;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.time.LocalDateTime;

/***
 * A class containing the game informations.
 */
@JsonDeserialize(as = GameInfo.class)
public class GameInfo
{
    private String userName;
    private String duration;
    private String date;
    private int steps;

    /***
     * Default constructor
     */
    public GameInfo()
    {
        super();
    }

    /***
     * A constructor, that sets the data.
     * @param userName The name of the user.
     * @param steps The number of steps the user took.
     * @param duration The duration of the game in seconds.
     */
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

    /***
     *
     * {@return the name of the user}
     */
    public String getUserName()
    {
        return userName;
    }

    /***
     *
     * {@return the duration of the game}
     */
    public String getDuration()
    {
        return duration;
    }

    /***
     *
     * {@return the date of the play}
     */
    public String getDate()
    {
        return date;
    }

    /***
     *
     * {@return the number of steps taken}
     */
    public int getSteps()
    {
        return steps;
    }

    /***
     * Sets the name of the user
     * @param userName the name of the user
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /***
     * Sets the duration of the game
     * @param duration the duration of the game
     */
    public void setDuration(String duration)
    {
        this.duration = duration;
    }

    /***
     * Sets the actual date of the game
     * @param date the actual date of the game
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /***
     * Sets the number of steps
     * @param steps the number of steps taken
     */
    public void setSteps(int steps)
    {
        this.steps = steps;
    }

    /***
     * {@return the string of the games informations}
     */
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
