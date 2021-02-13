package OSKiller;


import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;


public class Walker {
    private ClientContext ctx;

    public Walker(ClientContext ctx) {
        this.ctx = ctx;
    }

    public Tile getNextTile(Tile[] t) {
        Tile nextTile = this.ctx.movement.newTilePath(t).next();
        int index = 0;
        Player p = this.ctx.players.local();

        for(int i = t.length - 1; i >= 0; --i) {
            if (t[i].equals(nextTile)) {
                if (i + 1 <= t.length - 1 && nextTile.distanceTo(p) < 3.0D) {
                    index = i + 1;
                } else {
                    index = i;
                }
                break;
            }

            if (t[i].distanceTo(p) < 8.0D) {
                index = i;
                break;
            }
        }

        return t[index];
    }

    public boolean handleObstacle(Tile[] t) {
        Tile nextTile = this.getNextTile(t);
        if (nextTile.matrix(this.ctx).reachable()) {
            return false;
        } else {
            final Player p = this.ctx.players.local();
            double distance = 1.0D / 0.0;
            GameObject obstacle = null;
            Iterator var7 = this.ctx.objects.select(5).name(new Pattern[]{Pattern.compile("(staircase)|(ladder)|((.*)?door)|(gate(.*)?)", 2)}).iterator();

            while(true) {
                while(true) {
                    GameObject go;
                    double calcDist;
                    String[] var11;
                    int var12;
                    int var13;
                    String s;
                    do {
                        do {
                            while(true) {
                                do {
                                    label114:
                                    do {
                                        while(var7.hasNext()) {
                                            go = (GameObject)var7.next();
                                            calcDist = go.tile().distanceTo(new Tile(nextTile.x(), nextTile.y(), p.tile().floor()));
                                            if (nextTile.floor() != p.tile().floor()) {
                                                continue label114;
                                            }

                                            if (nextTile.distanceTo(this.ctx.players.local()) <= 50.0D) {
                                                if (go.type() == org.powerbot.script.rt4.GameObject.Type.BOUNDARY && calcDist < distance && this.reachable(go)) {
                                                    obstacle = go;
                                                    distance = calcDist;
                                                }
                                            } else if (go.type() != org.powerbot.script.rt4.GameObject.Type.BOUNDARY) {
                                                var11 = go.actions();
                                                var12 = var11.length;

                                                for(var13 = 0; var13 < var12; ++var13) {
                                                    s = var11[var13];
                                                    if (go.tile().distanceTo(nextTile) + go.tile().distanceTo(p) < distance && this.reachable(go) && s != null && !s.equals("null") && (s.contains("Climb-up") || s.contains("Climb-down") || s.contains("Enter"))) {
                                                        obstacle = go;
                                                        distance = go.tile().distanceTo(nextTile) + go.tile().distanceTo(p);
                                                        break;
                                                    }
                                                }
                                            }
                                        }

                                        if (obstacle == null) {
                                            return false;
                                        }

                                        if (obstacle.inViewport()) {
                                            obstacle.bounds(this.getBounds(obstacle));
                                            if (nextTile.floor() > p.tile().floor()) {
                                                if (obstacle.interact("Climb-up")) {
                                                    return this.handlePostInteraction();
                                                }
                                            } else if (nextTile.floor() < p.tile().floor()) {
                                                if (obstacle.interact("Climb-down")) {
                                                    return this.handlePostInteraction();
                                                }
                                            } else if (nextTile.distanceTo(this.ctx.players.local()) > 50.0D) {
                                                if (obstacle.interact("Climb-")) {
                                                    return this.handlePostInteraction();
                                                }
                                            } else if (obstacle.interact("Open")) {
                                                return this.handlePostInteraction();
                                            }
                                        } else if (this.ctx.movement.step(obstacle)) {
                                            this.ctx.camera.turnTo(obstacle);
                                            Condition.wait(new Callable<Boolean>() {
                                                public Boolean call() throws Exception {
                                                    return p.animation() == -1 && !p.inMotion();
                                                }
                                            }, 1000, 3);
                                        }

                                        return false;
                                    } while(go.type() != org.powerbot.script.rt4.GameObject.Type.INTERACTIVE);
                                } while(go.actions().length <= 0);

                                if (nextTile.floor() > p.tile().floor()) {
                                    break;
                                }

                                if (calcDist < distance && this.reachable(go)) {
                                    var11 = go.actions();
                                    var12 = var11.length;

                                    for(var13 = 0; var13 < var12; ++var13) {
                                        s = var11[var13];
                                        if (s != null && !s.equals("null") && s.contains("Climb-down")) {
                                            obstacle = go;
                                            distance = calcDist;
                                            break;
                                        }
                                    }
                                }
                            }
                        } while(calcDist >= distance);
                    } while(!this.reachable(go));

                    var11 = go.actions();
                    var12 = var11.length;

                    for(var13 = 0; var13 < var12; ++var13) {
                        s = var11[var13];
                        if (s != null && !s.equals("null") && s.contains("Climb-up")) {
                            obstacle = go;
                            distance = calcDist;
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean reachable(GameObject go) {
        int a = go.width();
        Tile t1 = new Tile(go.tile().x() + a, go.tile().y(), go.tile().floor());
        Tile t2 = new Tile(go.tile().x() - a, go.tile().y(), go.tile().floor());
        Tile t3 = new Tile(go.tile().x(), go.tile().y() + a, go.tile().floor());
        Tile t4 = new Tile(go.tile().x(), go.tile().y() - a, go.tile().floor());
        return t1.matrix(this.ctx).reachable() || t2.matrix(this.ctx).reachable() || t3.matrix(this.ctx).reachable() || t4.matrix(this.ctx).reachable();
    }

    private boolean handlePostInteraction() {
        final Player p = this.ctx.players.local();
        if (this.ctx.game.crosshair() == Game.Crosshair.ACTION) {
            Condition.wait(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return p.animation() == -1 && !p.inMotion();
                }
            }, 1000, 3);
            return true;
        } else {
            return false;
        }
    }

    public boolean walkPath(Tile[] t) {
        Tile ti = this.getNextTile(t);
        Tile nt = ti.derive(Random.nextInt(-1, 1), Random.nextInt(-1, 1));
        if (nt.matrix(this.ctx).reachable()) {
            return this.ctx.movement.step(nt);
        } else {
            return ti.matrix(this.ctx).reachable() ? this.ctx.movement.step(ti) : this.handleObstacle(t);
        }
    }

    private int[] getBounds(GameObject go) {
        switch(this.getType(go.name())) {
            case DOOR:
                switch(go.orientation()) {
                    case 0:
                    case 4:
                    case 6:
                        return new int[]{-5, 15, 0, -220, 0, 90};
                    case 1:
                        return new int[]{0, 90, 0, -220, 110, 130};
                    case 2:
                        return new int[]{100, 120, 0, -220, 0, 90};
                    case 3:
                        return new int[]{0, 100, 0, -200, 10, 20};
                    case 5:
                    default:
                        return new int[]{-32, 32, -64, 0, -32, 32};
                }
            case GATE:
                switch(go.orientation()) {
                    case 0:
                    case 4:
                    case 6:
                        return new int[]{5, 10, 0, -80, 20, 80};
                    case 1:
                        return new int[]{0, 80, 0, -80, 118, 123};
                    case 2:
                        return new int[]{118, 123, 0, -80, 0, 80};
                    case 3:
                        return new int[]{10, 80, 0, -80, 15, 0};
                    case 5:
                    default:
                        return new int[]{-32, 32, -64, 0, -32, 32};
                }
            case LADDER:
                switch(go.orientation()) {
                    case 0:
                    case 2:
                    case 4:
                        return new int[]{-20, 40, 20, -40, 0, -60};
                    case 1:
                    case 5:
                        return new int[]{0, -40, -64, 0, -32, 32};
                    case 3:
                    case 7:
                        return new int[]{10, 40, -64, 0, -32, 32};
                    case 6:
                        return new int[]{-20, 40, 20, -40, 0, 60};
                }
        }

        return new int[]{-32, 32, -64, 0, -32, 32};
    }

    private OSMiner.Walker.Type getType(String s) {
        if (s.matches("(?i)((.*)?door)")) {
            return OSMiner.Walker.Type.DOOR;
        } else if (s.matches("(?i)(gate(.*)?)")) {
            return OSMiner.Walker.Type.GATE;
        } else {
            return s.equalsIgnoreCase("ladder") ? OSMiner.Walker.Type.LADDER : OSMiner.Walker.Type.TEAPOT;
        }
    }

    public boolean walkPathReverse(Tile[] t) {
        t = this.ctx.movement.newTilePath(t).reverse().toArray();
        return this.walkPath(t);
    }

    public static enum Type {
        DOOR,
        GATE,
        LADDER,
        TEAPOT;

        private Type() {
        }
    }
}
