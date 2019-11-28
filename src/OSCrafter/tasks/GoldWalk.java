package OSCrafter.tasks;

import OSCrafter.Task;
import OSCrafter.Walker;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class GoldWalk extends Task<ClientContext> {
    //private static final Tile[] path = {new Tile(3274, 3186, 0), new Tile(3277, 3186, 0), new Tile(3280, 3185, 0), new Tile(3280, 3182, 0), new Tile(3280, 3179, 0), new Tile(3280, 3176, 0), new Tile(3277, 3173, 0), new Tile(3275, 3170, 0), new Tile(3273, 3167, 0), new Tile(3270, 3167, 0)};
    public static final Tile[] path = {new Tile(3109, 3499, 0), new Tile(3105, 3499, 0), new Tile(3101, 3499, 0), new Tile(3097, 3496, 0)};
    private final Walker walker = new Walker(ctx);
    private int GoldID = 2357;
    private int RingID = 1635;

    public GoldWalk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (ctx.inventory.select().id(GoldID).count() < 28 && path[0].distanceTo(ctx.players.local()) > 3) || ctx.inventory.select().id(RingID).count() == 27;

    }

    @Override
    public void execute() {
        if (!ctx.movement.running() && ctx.movement.energyLevel() >= 25) {
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.select().id(RingID).count() == 27) {
                walker.walkPath(path);
            } else {
                ctx.camera.angle(88);
                ctx.camera.pitch(76);
                walker.walkPathReverse(path);
            }


        }
    }
}
