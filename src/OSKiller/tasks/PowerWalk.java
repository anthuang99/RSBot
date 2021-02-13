package OSKiller.tasks;

import OSKiller.Task;
import OSKiller.Walker;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PowerWalk extends Task<ClientContext> {
    //public static final Tile[] path = {new Tile(3209, 3220, 2), new Tile(3206, 3217, 2), new Tile(3206, 3213, 2), new Tile(3206, 3208, 1), new Tile(3206, 3208, 0), new Tile(3210, 3210, 0), new Tile(3214, 3210, 0), new Tile(3215, 3214, 0), new Tile(3215, 3218, 0)};
    public static final Tile[] path = {new Tile(3255, 3287, 0), new Tile(3255, 3283, 0), new Tile(3255, 3279, 0), new Tile(3255, 3275, 0), new Tile(3255, 3271, 0), new Tile(3253, 3267, 0), new Tile(3249, 3267, 0), new Tile(3245, 3266, 0), new Tile(3242, 3263, 0), new Tile(3238, 3262, 0), new Tile(3234, 3262, 0), new Tile(3230, 3262, 0), new Tile(3226, 3262, 0), new Tile(3222, 3262, 0), new Tile(3218, 3262, 0), new Tile(3218, 3258, 0), new Tile(3218, 3254, 0), new Tile(3218, 3250, 0), new Tile(3218, 3246, 0), new Tile(3219, 3242, 0), new Tile(3222, 3238, 0), new Tile(3226, 3236, 0), new Tile(3229, 3233, 0), new Tile(3230, 3229, 0), new Tile(3231, 3225, 0), new Tile(3232, 3221, 0), new Tile(3228, 3219, 0), new Tile(3224, 3219, 0), new Tile(3220, 3219, 0), new Tile(3216, 3219, 0), new Tile(3215, 3215, 0), new Tile(3215, 3211, 0), new Tile(3211, 3211, 0), new Tile(3207, 3210, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3213, 2), new Tile(3206, 3217, 2), new Tile(3209, 3220, 2)};
    private final Walker walker = new Walker(ctx);

    public PowerWalk(ClientContext ctx) {
        super(ctx);
    }
    private int FoodIDs = 315; //Shrimps
    private int FoodID = 333; //Trout
    private int LootIDc = 1739; //Cowhide


    @Override
    public boolean activate() {
        return ctx.inventory.select().id(FoodID).count() < 1 ||
                (ctx.inventory.select().id(FoodID).count() > 0 && path[0].distanceTo(ctx.players.local()) >= 10);
    }

    @Override
    public void execute() {


        if (!ctx.movement.running() && ctx.movement.energyLevel() >= 25) {
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.select().id(FoodID).count() < 1) {
                walker.walkPath(path);
            } else {
                walker.walkPathReverse(path);
            }


        }
    }
}
