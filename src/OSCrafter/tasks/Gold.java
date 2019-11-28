package OSCrafter.tasks;

import OSCrafter.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GameObject;

public class Gold extends Task<ClientContext> {

    public static final Tile[] furnace = {new Tile(3109, 3499, 0)};
    private int GoldID = 2357;
    private int RingID = 1635;//Gold
    private int FurnaceID = 24009;

    public Gold(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        return ctx.inventory.select().id(GoldID).count() > 0 && furnace[0].distanceTo(ctx.players.local()) < 2;
    }

    @Override
    public void execute() {
        boolean craft = true;
        final int CraftingLevel = ctx.skills.level(Constants.SKILLS_CRAFTING);
        final GameObject Furnace = ctx.objects.select().id(FurnaceID).nearest().poll();
        final boolean smelt = Furnace.interact("Smelt");
        {
            Furnace.interact("Smelt");
        }
        Condition.sleep(1000);
        while (smelt) {
            while (craft) {
                ctx.input.click(95, 140, true); //Gold Rings
                Condition.sleep(2000);
                craft = false;
            }
            if (CraftingLevel != ctx.skills.level(Constants.SKILLS_CRAFTING)) {
                break;
            } else if (ctx.inventory.select().id(RingID).count() > 0 && ctx.inventory.select().id(RingID).count() < 27 && CraftingLevel == ctx.skills.level(Constants.SKILLS_CRAFTING)) {
                ctx.input.move(575, 230);
            } else {
                break;
            }

        }


    }
}
