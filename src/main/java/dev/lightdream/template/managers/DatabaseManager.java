package dev.lightdream.template.managers;

import dev.lightdream.api.IAPI;
import dev.lightdream.api.databases.User;
import dev.lightdream.api.dto.LambdaExecutor;
import dev.lightdream.api.managers.database.HikariDatabaseManager;
import dev.lightdream.api.managers.database.HikariDatabaseManagerImpl;
import dev.lightdream.api.managers.database.IDatabaseManagerImpl;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class DatabaseManager extends HikariDatabaseManager implements IDatabaseManagerImpl {
    public DatabaseManager(IAPI api) {
        super(api);
    }

    @Override
    public void setup() {
        setup(User.class);
    }

    @Override
    public @NotNull User getUser(@NotNull UUID uuid) {
        Optional<User> optionalUser = getAll(User.class).stream().filter(user -> user.uuid.equals(uuid)).findFirst();

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        User user = new User(api, uuid, Bukkit.getOfflinePlayer(uuid).getName(), api.getSettings().baseLang);
        save(user, false);
        return user;
    }

    @SuppressWarnings("unused")
    @Override
    public @Nullable User getUser(@NotNull String name) {
        Optional<User> optionalUser = getAll(User.class).stream().filter(user -> user.name.equals(name)).findFirst();

        return optionalUser.orElse(null);
    }

    @SuppressWarnings("unused")
    @Override
    public @NotNull User getUser(@NotNull OfflinePlayer player) {
        return getUser(player.getUniqueId());
    }

    @Override
    public @NotNull User getUser(@NotNull Player player) {
        return getUser(player.getUniqueId());
    }

    @SuppressWarnings("unused")
    @Override
    public @Nullable User getUser(int id) {
        Optional<User> optionalUser = getAll(User.class).stream().filter(user -> user.id == id).findFirst();

        return optionalUser.orElse(null);
    }

    @SuppressWarnings("unused")
    public @Nullable User getUser(@NotNull CommandSender sender) {
        if (sender instanceof Player) {
            return getUser((Player) sender);
        }
        return api.getConsoleUser();
    }

    @Override
    public HashMap<Class<?>, String> getDataTypes() {
        return null;
    }

    @Override
    public HashMap<Class<?>, LambdaExecutor> getSerializeMap() {
        return null;
    }

    @Override
    public HashMap<Class<?>, LambdaExecutor> getDeserializeMap() {
        return null;
    }



}
