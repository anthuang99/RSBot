package OSCrafter;

import OSCrafter.tasks.*;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "BIG CRAFT", description = "CRAFT STUFF GOOD", properties = "Author=Anthony; Topic=999, client=4")

public class Crafter extends PollingScript<ClientContext> {
    private List<OSCrafter.Task> taskList = new ArrayList<Task>();


    @Override
    public void start() {
        String userOptions[] = {"Gold Rings", "Sapphire Rings"};
        String userChoice = "" + (String) JOptionPane.showInputDialog(null, "What to Sapphire?", "OSCrafter", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[1]);
        if (userChoice.equals("Gold Rings")) {
            taskList.add(new Gold(ctx));
            taskList.add(new GoldWalk(ctx));
            taskList.add(new GoldBank(ctx));
        }
        if (userChoice.equals("Sapphire Rings")) {
            taskList.add(new Sapphire(ctx));
            taskList.add(new SapphireBank(ctx));
            taskList.add(new SapphireWalk(ctx));
        }

    }

    @Override
    public void poll() {
        for (OSCrafter.Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }

    }
}