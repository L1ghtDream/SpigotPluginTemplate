package dev.lightdream.template;

import dev.lightdream.api.API;
import dev.lightdream.api.LightDreamPlugin;
import dev.lightdream.api.configs.SQLConfig;
import dev.lightdream.api.databases.User;
import dev.lightdream.api.managers.MessageManager;
import dev.lightdream.api.managers.database.IDatabaseManagerImpl;
import dev.lightdream.template.configs.Config;
import dev.lightdream.template.configs.Lang;
import dev.lightdream.template.managers.DatabaseManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class Main extends LightDreamPlugin {

    public static Main instance;

    //Settings
    public Config config;
    public Lang lang;

    //Managers
    public IDatabaseManagerImpl databaseManager;

    @Override
    public void onEnable() {
        instance = this;

        init("SpigotTemplate", "st", "1.0");

        databaseManager = new DatabaseManager(this);
    }


    @Override
    public @NotNull String parsePapi(OfflinePlayer offlinePlayer, String s) {
        return "";
    }

    @Override
    public void loadConfigs() {
        sqlConfig = fileManager.load(SQLConfig.class);
        config = fileManager.load(Config.class);
        baseConfig = config;
        lang = fileManager.load(Lang.class, fileManager.getFile(baseConfig.baseLang));
        baseLang = lang;
    }

    @Override
    public void disable() {

    }

    @Override
    public void registerFileManagerModules() {

    }

    @Override
    public void registerUser(Player player) {

    }

    @Override
    public void loadBaseCommands() {
    }

    @Override
    public MessageManager instantiateMessageManager() {
        return new MessageManager(this, Main.class);
    }

    @Override
    public void registerLangManager() {
        API.instance.langManager.register(Main.class, getLangs());
    }

    @Override
    public HashMap<String, Object> getLangs() {
        HashMap<String, Object> langs = new HashMap<>();

        baseConfig.langs.forEach(lang -> {
            Lang l = fileManager.load(Lang.class, fileManager.getFile(lang));
            langs.put(lang, l);
        });

        return langs;
    }

    @Override
    public IDatabaseManagerImpl getDatabaseManager() {
        return databaseManager;
    }

    @Override
    public void setLang(Player player, String s) {
        User user = databaseManager.getUser(player);
        user.setLang(s);
        user.save();
    }


}
