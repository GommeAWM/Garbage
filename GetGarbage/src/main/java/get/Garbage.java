package get;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import get.Command.GarbageCMD;
import get.Command.gid;
import get.Listener.FormResponsiveListener;
import get.Utils.GarbageCFG;

public class Garbage extends PluginBase {

    private static Garbage instance;
    public static GarbageCFG garbageCFG;

    public void onEnable() {
        instance = this;
        garbageCFG = new GarbageCFG(this);
        garbageCFG.createDefaultConfig();
        register();
        this.getLogger().info("§fEnable: §a§lGetGarbage");
    }

    public void onDisable() {
        this.getLogger().info("§fDisable: §c§lGetGarbage");
    }

    private void register(){
        SimpleCommandMap simpleCommandMap = getServer().getCommandMap();
        simpleCommandMap.register("help", new GarbageCMD("garbage", Garbage.getGarbageCFG().description(), Garbage.getGarbageCFG().usg()));
        simpleCommandMap.register("help", new gid("gid", Garbage.getGarbageCFG().wdescr()));

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new FormResponsiveListener(), this);
    }

    public static Garbage getInstance(){
        return instance;
    }

    public static GarbageCFG getGarbageCFG() {
        return Garbage.garbageCFG;
    }

}
