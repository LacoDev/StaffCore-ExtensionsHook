package de.lacodev.staffcore.extensionshook.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import de.lacodev.staffcore.extensionshook.enums.StaffCoreChannels;
import de.lacodev.staffcore.extensionshook.enums.StaffCoreInventory;

public class ChannelListener implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] data) {
		
        if(!channel.equals("BungeeCord")) return;
        
        ByteArrayDataInput in = ByteStreams.newDataInput(data);
        String subchannel = in.readUTF();
        
        if (subchannel.equals(StaffCoreChannels.OPEN_INVENTORY.toString())) {
        	
        	Bukkit.getConsoleSender().sendMessage("§cSC-Extensions §8» §8Received Message from §aStaffCore-Bungee§8!");
          
        	String targetname = in.readUTF();
        	String inv = in.readUTF();
        	
        	Player target = Bukkit.getPlayer(targetname);
        	
        	if(target != null) {
        		
        		try {
        			StaffCoreInventory gui = StaffCoreInventory.valueOf(inv);
        			
        			target.openInventory(gui.getDedicatedInventory());
        		} catch (IllegalArgumentException e) {
        			target.sendMessage("§cSC-Extensions §8» §cCouldn't find the requested inventory!");
        		}
        		
        	}
      
        }
	      
	}

}
