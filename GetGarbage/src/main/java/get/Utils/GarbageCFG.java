package get.Utils;

import cn.nukkit.utils.Config;
import get.Garbage;

import java.io.File;

public class GarbageCFG {

    public Garbage garbage;
    public File file;
    public Config config;

    public GarbageCFG(final Garbage garbage) {
        this.garbage = garbage;
        this.file = new File(garbage.getDataFolder() + "/Garbage.yml");
        this.config = new Config(this.file, 2);
    }

    public void createDefaultConfig() {
        this.addDefault("options.titel0", "§0Garbage");
        this.addDefault("options.ui.text.description", "§7Here you can §6edit §7what would be §6clear §7for §6items §7from Inventory using §c/garbage\n\n§7Write the §6IDs §7of the §6items §7you want to §aadd §7or §cremove §7from your list\n\n§c|| §7To get the ID of the item, write §b/gid");
        this.addDefault("options.ui.text.list", "§eThe Items List: §b");
        this.addDefault("options.ui.text.infos", "§a/id:meta - §eExample: /2:4");
        this.addDefault("options.ui.messages.success", "§6§lYou successfully cleared your Inventory ");
        this.addDefault("options.ui.messages.empty", "§6§lYour list is Empty -> write §f/garbage help");
        this.addDefault("options.ui.messages.add", "§aYou successfully add Item §c{id} §7to your List");
        this.addDefault("options.ui.messages.remove", "§cYou successfully delete Item §c{id} from your List");
        this.addDefault("options.ui.messages.emptyfield", "§cThis area can't be empty");
        this.addDefault("options.ui.messages.false", "§cwrite /id:meta - §eExample: §b/2:3");
        this.addDefault("options.command.messages.wid.success", "§7The §c/id:meta §7of the item is: §b/{id}:{meta}");
        this.addDefault("options.permission", "§cYou need Permission");
        this.addDefault("options.usagemsg", "§c/garbage help");
        this.addDefault("options.description", "To clear Items what you don't need");
        this.addDefault("options.command.wid.description", "to get id and meta");
    }

    public String titel() {
        return this.config.getString("options.titel0");
    }

    public String desc() {
        return this.config.getString("options.ui.text.description");
    }

    public String list() {
        return this.config.getString("options.ui.text.list");
    }

    public String infos() {
        return this.config.getString("options.ui.text.infos");
    }

    public String success() {
        return this.config.getString("options.ui.messages.success");
    }

    public String empty() {
        return this.config.getString("options.ui.messages.empty");
    }

    public String add() {
        return this.config.getString("options.ui.messages.add");
    }

    public String remove() {
        return this.config.getString("options.ui.messages.remove");
    }

    public String emp() {
        return this.config.getString("options.ui.messages.emptyfield");
    }

    public String fal() {
        return this.config.getString("options.ui.messages.false");
    }

    public String wid() {
        return this.config.getString("options.command.messages.wid.success");
    }

    public String wdescr() {
        return this.config.getString("options.command.wid.description");
    }

    public String perms() {
        return this.config.getString("options.permission");
    }

    public String usg() {
        return this.config.getString("options.usagemsg");
    }

    public String description() {
        return this.config.getString("options.description");
    }

    public void addDefault(final String path, final Object object) {
        if (!this.config.exists(path)) {
            this.config.set(path, object);
            this.config.save(this.file);
        }
    }

}
