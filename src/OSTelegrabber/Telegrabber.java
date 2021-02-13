package OSTelegrabber;

import OSTelegrabber.tasks.Bank;
import OSTelegrabber.tasks.BunnyGrabber;
import OSTelegrabber.tasks.Telegrab;
import OSTelegrabber.tasks.Walk;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GeItem;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Script.Manifest(name = "BIG TELEGRAB", description = "TELEGRAB STUFF GOOD", properties = "Author=Anthony; Topic=999, client=4")


public class Telegrabber extends PollingScript<ClientContext> implements PaintListener
{
    public static int ActionCounter;
    private final int startingMagic = ctx.skills.experience(Constants.SKILLS_MAGIC);
    private List<OSTelegrabber.Task> taskList = new ArrayList<Task>();
    @Override
    public void start()
    {
        String[] userOptions = {"Standard Telegrab", "Hopping Telegrab"};
        String userChoice = "" + (String) JOptionPane.showInputDialog(null, "Standard or Hopping Telegrabber", "OSTelegrabber", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[0]);

        if (userChoice.equals("Standard Telegrab")) { //131k gp/hour
            taskList.add(new Bank(ctx));
            taskList.add(new Walk(ctx));
            taskList.add(new Telegrab(ctx));

        }
        if (userChoice.equals("Hopping Telegrab")) { //105k gp/hour
            taskList.add(new Bank(ctx));
            taskList.add(new Walk(ctx));
            taskList.add(new BunnyGrabber(ctx));
        }
    }


    @Override
    public void poll() {
        for(OSTelegrabber.Task task: taskList)
        {
            if(task.activate())
            {
                task.execute();
            }
        }

    }

    @Override
    public void repaint(Graphics graphics)
    {
        float milliPerHour = 3600000;
        long Milliseconds = this.getTotalRuntime();
        long Seconds = Milliseconds / 1000 % 60;
        long Minutes = Milliseconds / (1000 * 60) % 60;
        long Hours = Milliseconds / (1000 * 60 * 60) % 60;
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(new Color(0, 0, 0, 255));
        g.fillRect(0, 340, 520, 140);

        g.setColor(new Color(255, 255, 255));
        g.drawRect(0, 340, 520, 140);
        int MagicXP =(ctx.skills.experience(Constants.SKILLS_MAGIC)-startingMagic);
        int Wines = MagicXP/43;
        int GPMade = Wines* GeItem.getPrice (245);
        float perHour = milliPerHour /Milliseconds;
        float gpPerHour = GPMade*perHour;
        float xpPerHour = MagicXP*perHour;






        g.drawString("BIG TELEGRAB", 230, 360);
        g.drawLine(0,370,520,370);
        g.drawString("Running: " + String.format("%02d:%02d:%02d", Hours, Minutes, Seconds), 20, 400);
        g.drawString("GP: $" + NumberFormat.getNumberInstance(Locale.US).format(GPMade), 20,420);
        g.drawString("XP: " + NumberFormat.getNumberInstance(Locale.US).format(MagicXP),20,440);
        int gpHour = (int)gpPerHour;
        int xpHour = (int)xpPerHour;
        g.drawString("GP per Hour: $" + NumberFormat.getNumberInstance(Locale.US).format(gpHour),230,420);
        g.drawString("XP per Hour: " + NumberFormat.getNumberInstance(Locale.US).format(xpHour),230,440);
        if(ActionCounter == 1)
        {
            g.drawString("Current Action: Banking",230,400);

        }
        else if(ActionCounter ==2)
        {
            g.drawString("Current Action: Walking to Temple",230,400);
        }
        else if(ActionCounter ==3)
        {
            g.drawString("Current Action: Walking to Bank",230,400);
        }
        else if(ActionCounter ==4)
        {
            g.drawString("Current Action: Telegrabbing Wines",230,400);
        }


        g.drawString("Antbot",451,360);
        g.drawLine(444,360,470,365);
        g.drawLine(494,360,470,365);
        g.drawLine(444,350,470,345);
        g.drawLine(494,350,470,345);



    }



}
