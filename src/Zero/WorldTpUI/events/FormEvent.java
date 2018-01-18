package Zero.WorldTpUI.events;

import Zero.WorldTpUI.Main;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.level.Level;
import cn.nukkit.utils.TextFormat;

public class FormEvent implements Listener {

  private Main plugin;

  public FormEvent(Main main){
    plugin = main;
  }

  public Main getPlugin(){
	return plugin;
  }
  
    @EventHandler
    public void formRespond(cn.nukkit.event.player.PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        FormWindow window = event.getWindow();
        if (event.getResponse() == null) return;
        if (window instanceof FormWindowSimple) {
            String title = ((FormWindowSimple) event.getWindow()).getTitle();
            String button = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
            if (!event.wasClosed()) {
                if(title == "World Teleport UI"){
			
		}
	    }
	}
    }
}
