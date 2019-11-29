package OSDuster;

import OSDuster.tasks.Bank;
import OSDuster.tasks.Dust;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Script.Manifest(name = "BIG DUSTER", description = "DUST STUFF GOOD", properties = "Author=Anthony; Topic=999, client=4")

public class Duster extends PollingScript<ClientContext>
{
    private List<OSDuster.Task> taskList = new ArrayList<Task>();

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
}
