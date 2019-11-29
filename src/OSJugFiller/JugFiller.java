package OSJugFiller;

import OSJugFiller.tasks.Bank;
import OSJugFiller.tasks.Fill;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name = "BIG FILL", description = "FILL JUG GOOD", properties = "Author=Anthony; Topic=999, client=4")

public class JugFiller extends PollingScript<ClientContext>
{
    private List<OSJugFiller.Task> taskList = new ArrayList<Task>();

    @Override
    public void start()
    {
        taskList.addAll((Arrays.asList(new Bank(ctx), new Fill(ctx))));

    }


    @Override
    public void poll()
    {
        for (OSJugFiller.Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }


    }
}
