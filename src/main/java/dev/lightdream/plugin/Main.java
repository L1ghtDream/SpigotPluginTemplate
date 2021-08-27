package dev.lightdream.plugin;

import dev.lightdream.api.LightDreamPlugin;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public final class Main extends LightDreamPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        init("SpigotTemplate", "st", "1.0");
        instance = this;
    }

    @Override
    public @NotNull String parsePapi(OfflinePlayer offlinePlayer, String s) {
        return "";
    }

    @Override
    public void loadConfigs() {

    }

    @Override
    public void loadBaseCommands() {

    }


}
