import org.bukkit.plugin.java.JavaPlugin;

public class CustomExplosion extends JavaPlugin {
    @Override
    public void onLoad() {
        super.onLoad();
        System.out.println("Custom Explosion loaded.");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("Custom Explosion disabled.");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("Custom Explosion enabled.");
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);

        this.getCommand("genplanet").setExecutor(new CommandGeneratePlanet());
    }
}
