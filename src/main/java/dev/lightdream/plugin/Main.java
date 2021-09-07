package dev.lightdream.plugin;

import dev.lightdream.api.LightDreamPlugin;
import dev.lightdream.api.files.config.Config;
import dev.lightdream.api.files.config.Lang;
import dev.lightdream.api.files.config.SQLConfig;
import dev.lightdream.api.utils.LangUtils;
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
        sqlConfig = fileManager.load(SQLConfig.class);
        baseConfig = fileManager.load(Config.class);
        baseLang = (Lang) fileManager.load(LangUtils.getLang(Main.class, baseConfig.lang));
    }

    @Override
    public void loadBaseCommands() {
    }


}
