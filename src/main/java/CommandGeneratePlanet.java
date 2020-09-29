import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.Math;
import java.util.Random;

public class CommandGeneratePlanet implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {

        if (commandSender instanceof Player) {

            Location senderLocation = ((Player) commandSender).getLocation();
            int planetRadius = Integer.parseInt(strings[0]);
            int planetHeight = Integer.parseInt(strings[1]);
            int planetWaves = Integer.parseInt(strings[2]);
            int planetWaveHeight = Integer.parseInt(strings[3]);
            System.out.println(strings.length);

            buildPlanet(senderLocation.getWorld(), senderLocation.getBlockX(), senderLocation.getBlockY(),
                    senderLocation.getBlockZ(), planetRadius, planetHeight, planetWaves, planetWaveHeight);

            return true;
        }
        return false;
    }

    public void buildPlanet(World world, int x, int y, int z, double r, double h, int waves, int waveHeight) {

        double waveConst = waves*2*Math.PI;

        for (int yOffset = 0; yOffset < h; yOffset++) {

            double rVariation = waveHeight * Math.sin((yOffset/h) * waveConst);

            buildDisk(world, x, y + yOffset, z, (int) (r * Math.sqrt(yOffset / h) + rVariation));
        }
    }

    public void buildDisk(World world, int x, int y, int z, int r) {

        int noiseRadius = 3;

        for (int xOffset = -r ; xOffset <= r ; xOffset++) {

            int zD = (int) Math.sqrt((r*r)-(xOffset*xOffset));

            for (int zOffset = -zD; zOffset <= zD ; zOffset++) {

                if (xOffset < (-r+noiseRadius) || xOffset > (r-noiseRadius) || zOffset < (-zD+noiseRadius)
                        || zOffset > (zD-noiseRadius)) {

                    Random random = new Random();
                    if (random.nextBoolean()) {
                        world.getBlockAt(x+xOffset, y, z+zOffset).setType(Material.STONE);
                    }

                } else {

                    world.getBlockAt(x+xOffset, y, z+zOffset).setType(Material.STONE);
                }
            }
        }
    }
}
