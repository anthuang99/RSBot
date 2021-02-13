package OSKiller;

import OSKiller.tasks.*;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "SLAUGHTER GANG", description = "KILL STUFF GOOD", properties = "Author=Anthony; Topic=999, client=4")

public class Killer extends PollingScript<ClientContext> {

    private List<OSKiller.Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        String [] userOptions = {"PowerKill", "Kill and Loot"};
        String userChoice = "" + (String) JOptionPane.showInputDialog(null, "PowerKill or Kill and Loot", "OSKiller", JOptionPane.PLAIN_MESSAGE, null, userOptions, userOptions[0]);
        if(userChoice.equals("PowerKill"))
        {
            taskList.add(new PowerKill(ctx));
        }
        if(userChoice.equals("Kill and Loot"))
        {
            taskList.add(new Bank(ctx));
            taskList.add(new Walk(ctx));
            taskList.add(new Kill(ctx));
        }
    }

    @Override
    public void poll() {
        for (Task task :taskList)
        {
            if(task.activate())
            {
                task.execute();
            }
        }
        }
    }