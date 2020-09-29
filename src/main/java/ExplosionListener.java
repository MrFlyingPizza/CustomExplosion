import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.util.Vector;

public class ExplosionListener implements Listener {

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        System.out.println("Explosion event listened.");
        System.out.println(event.getEntityType());

        Location eventLocation = event.getLocation();
        World eventWorld = eventLocation.getWorld();
        eventWorld.playSound(eventLocation, Sound.ENTITY_GENERIC_EXPLODE, 5, 5);
        eventWorld.playEffect(eventLocation, Effect.SMOKE, 5);

        event.setCancelled(true);

        double explosionPower = 1;
        double xOffset, yOffSet, zOffset;
        for (Block block : event.blockList()) {

            xOffset = eventLocation.getX() - block.getX();
            yOffSet = eventLocation.getY() - block.getY();
            zOffset = eventLocation.getZ() - block.getZ();

            FallingBlock fallingBlock = eventWorld.spawnFallingBlock(block.getLocation(),block.getBlockData());
            block.setType(Material.AIR);
            fallingBlock.setVelocity(new Vector(explosionPower/xOffset,explosionPower/yOffSet,explosionPower/zOffset));
        }
    }
}
