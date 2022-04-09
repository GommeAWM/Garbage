package get.Listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.Config;
import get.Garbage;

import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class FormResponsiveListener implements Listener {

    static int GARBAGE_ID_FORM = 0xAAA0001;

    static int TPAFORMWINDOW = 0xAAA0001;

    @EventHandler
    public void onPlayerForm(PlayerFormRespondedEvent event){

        Player player = event.getPlayer();

        if (event.getWindow() instanceof FormWindowCustom){
            FormWindowCustom formWindowCustom = (FormWindowCustom) event.getWindow();
            FormResponseCustom formResponseCustom = formWindowCustom.getResponse();
            if (event.getFormID() == GARBAGE_ID_FORM){

                Config garbage_list_config = new Config(new File(Garbage.getInstance().getDataFolder(), "/garbage_list.yml"), Config.YAML);
                garbage_list_config.reload();

                List<String> list_id = garbage_list_config.getStringList(player.getName());

                if (event.wasClosed()){
                    return;
                }

                String idtoadd = formResponseCustom.getInputResponse(2).toLowerCase();

                if (!(idtoadd.contains("/")) || !idtoadd.contains(":")){
                    player.sendMessage(Garbage.getGarbageCFG().fal());
                    return;
                }

                if (!list_id.contains(idtoadd)){
                    if (idtoadd.isEmpty()){
                        player.sendMessage(Garbage.getGarbageCFG().emp());
                        return;
                    } else {
                        list_id.add(idtoadd);
                        garbage_list_config.set(player.getName(), list_id);
                        garbage_list_config.save();
                        player.sendMessage(Garbage.getGarbageCFG().add().replace("{id}", idtoadd));
                    }
                } else {
                    list_id.remove(idtoadd);
                    garbage_list_config.set(player.getName(), list_id);
                    garbage_list_config.save();
                    player.sendMessage(Garbage.getGarbageCFG().remove().replace("{id}", idtoadd));

                }

            }
        }

    }

}
