package get.Command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import get.Garbage;

public class gid extends Command {

    public gid(String cmd, String descr){
        super(cmd, descr);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;

            Item item = player.getInventory().getItemInHand();
            String id = String.valueOf(item.getId());
            String meta = String.valueOf(item.getDamage());
            String msg = Garbage.getGarbageCFG().wid().replace("{id}", id).replace("{meta}", meta);
            player.sendMessage(msg);

        }

        return true;
    }
}
