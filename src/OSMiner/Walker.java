package OSMiner;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Player;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;


public class Walker {

    private ClientContext ctx;

    public Walker(ClientContext ctx) {
        this.ctx = ctx;
    }

    /**
     * Returns the next tile to walk to, disregarding whether it is reachable.
     *
     * @param t The tile path being traversed.
     * @return The next tile to traverse to.
     */
    public Tile getNextTile(Tile[] t) {
        Tile nextTile = ctx.movement.newTilePath(t).next();                 //The next tile, as suggested by the RSBot api, this will be the next reachable tile.
        int index = 0;                                                      //The index at which the next tile (by our definition) is at. Default to 0 (start tile).
        final Player p = ctx.players.local();

        /*
         * Loop through the path, backwards.
         * Find the intended next tiles index
         * then check if there is a better option.
         */
        for (int i = t.length - 1; i >= 0; i--) {
            if (t[i].equals(nextTile)) {                                    //This is the index at which the suggested next tile resides
                if (i + 1 <= t.length - 1 && nextTile.distanceTo(p) < 3) {  //If we're not at the end of the path and we're very close to the suggested next tile
                    index = i + 1;                                          //then it's too close to bother with. We will try to go to the tile after instead.
                    break;
                }
                index = i;                                                  //Suggested next tile was the best option as it is not very close, and reachable.
                break;
            } else if(t[i].distanceTo(p)<8){
                index = i;                                                  //if next closest tile is <8 and it's not the next tile then we can assume the next tile is probably not correct, perhaps no reachable tile available...
                break;
            }
        }
        return t[index];
    }

    /**
     * Will detect which obstacle to tackle to give Walker the ability to
     * traverse to the next tile.     *
     *
     * @param t The tile path being traversed.
     * @return True if obstacle was clicked. False otherwise.
     */
    public boolean handleObstacle(Tile[] t) {
        Tile nextTile = getNextTile(t);                                       //The calculated next tile.
        /*
         * Return false as there is no obstacle to handle.
         * Perhaps this was called whilst we were still walking
         * and the tile became reachable?
         */
        if (nextTile.matrix(ctx).reachable()) {
            return false;
        }

        final Player p = ctx.players.local();
        double distance = Double.POSITIVE_INFINITY;
        GameObject obstacle = null;
        for (GameObject go 