package OSMiner;

import OSMiner.tasks.*;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "BIG MINE", description = "MINE ROCK GOOD", properties = "author=Anthony; topic=999, client=4")
public class Miner extends PollingScript<ClientContext> implements PaintListener {
    private List<Task> taskList = new ArrayList<Task>();
    private int Count = 0;
    private final int startingXP = ctx.skills.experience(Constants.SKILLS_MINING);
    int Gold = 444;


    @Override
    public void start() {
        String userOptions[] = {"Bank", "Powermine"};
        String userChoice = "" + (String) JOptionPane.showInputDialog(null, "Bank or Powermine?", "OSMiner", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[0]);
        if (userChoice.equals("Bank")) {
            taskList.add(new Bank(ctx));
            taskList.add(new Walk(ctx));
            taskList.add(new Mine(ctx));

        }
        if (userChoice.equals("Powermine")) {
            taskList.add(new Drop(ctx));
            taskList.add(new PowerMine(ctx));
        }
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
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
        int expGain = (ctx.skills.experience(Constants.SKILLS_MINING) - startingXP);
        int rocksMined = (expGain / 65);
        int GP = rocksMined*370;

        Graphics2D g = (Graphics2D) graphics;
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, 200, 110);

        g.setColor(new Color(255, 255, 255));
        g.drawRect(0, 0, 200, 110);

        g.drawString("OSMiner", 20, 20);
        g.drawString("Running: " + String.format("%02d:%02d:%02d", Hours, Minutes, Seconds), 20, 40);
        g.drawString("Rocks Mined: " + rocksMined, 20, 60);
        g.drawString("Experience Gained: " + (expGain), 20, 80);
        g.drawString("GP Made: $"+ (GP), 20,100);

    }


}
