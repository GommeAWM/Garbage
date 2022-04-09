package get.Command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Config;
import get.Garbage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GarbageCMD extends Command {

    public Config garbage_list_config;
    int value;

    static int GARBAGE_ID_FORM = 0xAAA0001;


    public GarbageCMD(String cmd, String descr, String usg){
        super(cmd, descr, usg);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;

            if (!player.hasPermission("reyd.garbage")){
                player.sendMessage(Garbage.getGarbageCFG().perms());
                return true;
            }

            if (args.length == 1){
                String text = args[0].toLowerCase();
                if (text.equals("help")){

                    Config garbage_list_config = new Config(new File(Garbage.getInstance().getDataFolder(), "/garbage_list.yml"), Config.YAML);
                    garbage_list_config.reload();

                    List<String> list_id = garbage_list_config.getStringList(player.getName());

                    FormWindowCustom formWindowCustom = new FormWindowCustom(Garbage.getGarbageCFG().titel());
                    formWindowCustom.addElement(new ElementLabel(Garbage.getGarbageCFG().desc()));
                    formWindowCustom.addElement(new ElementLabel(Garbage.getGarbageCFG().list() + list_id));
                    formWindowCustom.addElement(new ElementInput(Garbage.getGarbageCFG().infos()));
                    player.showFormWindow(formWindowCustom, GARBAGE_ID_FORM);
                }

                else if (text.equals("info")){
                    player.sendMessage("§b[§aGetGarbage§b] §fThe owner of this plugin is §aDaniel Reydovich §b(§eGommeAWM§b) §7// §cxxtdaniel");
                    return true;
                }

                else {
                    player.sendMessage(Garbage.getGarbageCFG().usg());
                    return true;
                }


            }

            if (args.length < 1){

                Config garbage_list_config = new Config(new File(Garbage.getInstance().getDataFolder(), "/garbage_list.yml"), Config.YAML);
                garbage_list_config.reload();

                List<String> list_id = garbage_list_config.getStringList(player.getName());

                if (garbage_list_config.get(player.getName()) == null){
                    player.sendMessage(Garbage.getGarbageCFG().empty());
                    return true;
                }


                List<String> itemsname = new ArrayList<>();

                for (int d = 0; d < list_id.size(); d++){
                    String sid = (list_id.get(d));
                    int meta = Integer.parseInt(sid.substring(sid.lastIndexOf(":") + 1));
                    int id = Integer.parseInt(sid.substring(sid.lastIndexOf("/") + 1, sid.indexOf(":")));
                    itemsname.add(Item.get(id, meta).getName());
                }

                player.sendMessage(Garbage.getGarbageCFG().success() + list_id);

                player.sendMessage(Garbage.getGarbageCFG().success() + itemsname);


                try {
                    value = 0;

//                    for (int a = 0; a < itemsname.size(); a++){
//                        itemsname.remove(a);
//                    }

                    player.sendTip(Garbage.getGarbageCFG().usg());
                    for (int d = 0; d <= list_id.size(); d++){

                        String sid = list_id.get(d);
                        int meta = Integer.parseInt(sid.substring(sid.lastIndexOf(":") + 1));
                        int id = Integer.parseInt(sid.substring(sid.lastIndexOf("/") + 1, sid.indexOf(":")));

                        Map<Integer, Item> items = player.getInventory().getContents();
                        for (int i = 0; i < 37; i++){
                            try {
                                Item it = items.get(i);

                                if (it.getId() == id){

                                    if (it.isTool() || it.isArmor()){
                                        player.getInventory().setItem(i, Item.get(Item.AIR));
                                    }

                                    if (it.getDamage() == meta){
                                        player.getInventory().setItem(i, Item.get(Item.AIR));
                                    }

                                }

                            } catch (NullPointerException exception){
                            }
                        }

                    }

                } catch (IndexOutOfBoundsException e){
                }

            }


        }

        return true;
    }

}
