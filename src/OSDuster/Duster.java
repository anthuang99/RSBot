package OSDuster;

import OSDuster.tasks.Bank;
import OSDuster.tasks.Dust;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GeItem;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Script.Manifest(name = "BIG DUSTER", description = "DUST CHOCOLATE GOOD", properties = "Author=Anthony; Topic=999, client=4")

public class Duster extends PollingScript<ClientContext> implements PaintListener
{
    private List<OSDuster.Task> taskList = new ArrayList<Task>();
    int counter = -1;
    //int chocolateDusted = 0;
    private float MilliPerHour = 3600000;
    public static int ActionCounter;
    public static int ChocolateCounter = 0;


    @Override
    public void start()
    {
        taskList.addAll((Arrays.asList(new Bank(ctx), new Dust(ctx))));

    }

    @Override
    public void poll() {
        for (OSDuster.Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }

    }
    @Override
    public void repaint(Graphics graphics) {
        long Milliseconds = this.getTotalRuntime();
        long Seconds = Milliseconds / 1000 % 60;
        long Minutes = Milliseconds / (1000 * 60) % 60;
        long Hours = Milliseconds / (1000 * 60 * 60) % 60;
        int price = GeItem.getPrice(1975)-GeItem.getPrice(1973);

        int GP = ChocolateCounter *price;
        float perHour = MilliPerHour/Milliseconds;
        float gpPerHour = GP*perHour;
        float chocolatePerHour = ChocolateCounter*perHour;


        Graphics2D g = (Graphics2D) graphics;
        g.setColor(new Color(0, 0, 0, 255));
        g.fillRect(0, 340, 520, 140); //Main Rectangle
        g.fillRect(0,260,190,80); //Price Rectangle

        g.setColor(new Color(255, 255, 255));
        g.drawRect(0, 340, 520, 140); //Main Rectangle
        g.drawRect(0,260,190,80); //Price Rectangle
        g.drawLine(0,310,190,310); //Price line

        g.drawString("Chocolate Bar Price: $" + GeItem.getPrice(1973), 20, 280);
        g.drawString("Chocolate Dust Price: $" + GeItem.getPrice(1975), 20,300);
        g.drawString("Profit per Bar: $" + price,20,330);

        g.drawString("BIG DUSTER", 230, 360);
        g.drawLine(0,370,520,370); //Title line
        g.drawString("Running: " + String.format("%02d:%02d:%02d", Hours, Minutes, Seconds), 20, 400);
        g.drawString("Chocolate Dusted: " + NumberFormat.getNumberInstance(Locale.US).format(ChocolateCounter), 20,440);
        if(ActionCounter == 1)
        {
            g.drawString("Current action: Dusting Chocolate", 230,400);
        }
        else if (ActionCounter == 2)
        {
            g.drawString("Current action: Banking Chocolate", 230,400);
        }
        g.drawString("GP Made: $"+ NumberFormat.getNumberInstance(Locale.US).format(GP), 20,420);
        g.drawString("GP per Hour: $" + NumberFormat.getNumberInstance(Locale.US).format((int)gpPerHour),230,420);
        g.drawString("Chocolate per Hour: " + NumberFormat.getNumberInstance(Locale.US).format((int)chocolatePerHour),230,440);

        g.drawString("Antbot",451,360);
        g.drawLine(444,360,470,365);
        g.drawLine(494,360,470,365);
        g.drawLine(444,350,470,345);
        g.drawLine(494,350,470,345);

    }


}
