package de.lacodev.staffcore.extensionshook.enums;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import de.lacodev.staffcore.extensionshook.ExtensionHook;
import de.lacodev.staffcore.extensionshook.utils.Data;
import de.lacodev.staffcore.extensionshook.utils.XMaterial;

public enum StaffCoreInventory {
	
	EXTENSION_HANDLER_MAINGUI("§cStaffCore §8- §7Extensions"),EXTENSION_HANDLER_MANAGER_GUI("§8» §7Manage §cSC §7Extensions");
	
	String title;
	
	StaffCoreInventory(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

	public Inventory getDedicatedInventory() {
		switch(this.toString()) {
		case "EXTENSION_HANDLER_MAINGUI":
			Inventory inv = Bukkit.createInventory(null, 9, getTitle());
			
			for(int i = 0; i < inv.getSize(); i++) {
				inv.setItem(i, Data.buildPlace());
			}
			
			inv.setItem(0, Data.buildHead("action", "§8» §7Manage Extensions"));
			inv.setItem(1, Data.buildHead("shop", "§8» §aBuy Extensions"));
			inv.setItem(8, Data.buildItem(XMaterial.BARRIER, "§8» §cClose Menu"));
			
			return inv;
		case "EXTENSION_HANDLER_MANAGER_GUI":
			Inventory inv1 = Bukkit.createInventory(null, 36, getTitle());
			
			for(int i = 0; i < inv1.getSize(); i++) {
				inv1.setItem(i, Data.buildPlace());
			}
			
			Bukkit.getScheduler().runTaskAsynchronously(ExtensionHook.getInstance(), new Runnable() {

				@Override
				public void run() {
					inv1.setItem(10, Data.buildHead("plugin", "§8» §c" + Extension.PLUGIN_MANAGER.getName(), "§7Extension State §8» " + Extension.PLUGIN_MANAGER.getState(), "§7Version §8» " + Extension.PLUGIN_MANAGER.getVersion()));
					inv1.setItem(11, Data.buildHead("plugin", "§8» §c" + Extension.TWOFA_AUTHENTICATION.getName(), "§7Extension State §8» " + Extension.TWOFA_AUTHENTICATION.getState(), "§7Version §8» " + Extension.TWOFA_AUTHENTICATION.getVersion()));
					inv1.setItem(12, Data.buildHead("plugin", "§8» §c" + Extension.ANTICHEAT_HOOK.getName(), "§7Extension State §8» " + Extension.ANTICHEAT_HOOK.getState(), "§7Version §8» " + Extension.ANTICHEAT_HOOK.getVersion()));
					inv1.setItem(13, Data.buildHead("plugin", "§8» §c" + Extension.GUIS.getName(), "§7Extension State §8» " + Extension.GUIS.getState(), "§7Version §8» " + Extension.GUIS.getVersion()));
					inv1.setItem(14, Data.buildHead("plugin", "§8» §c" + Extension.REWARDS.getName(), "§7Extension State §8» " + Extension.REWARDS.getState(), "§7Version §8» " + Extension.REWARDS.getVersion()));
					inv1.setItem(15, Data.buildHead("plugin", "§8» §c" + Extension.TASKS.getName(), "§7Extension State §8» " + Extension.TASKS.getState(), "§7Version §8» " + Extension.TASKS.getVersion()));
					
					inv1.setItem(19, Extension.PLUGIN_MANAGER.getStateIndicatorItem());
					inv1.setItem(20, Extension.TWOFA_AUTHENTICATION.getStateIndicatorItem());
					inv1.setItem(21, Extension.ANTICHEAT_HOOK.getStateIndicatorItem());
					inv1.setItem(22, Extension.GUIS.getStateIndicatorItem());
					inv1.setItem(23, Extension.REWARDS.getStateIndicatorItem());
					inv1.setItem(24, Extension.TASKS.getStateIndicatorItem());
					
					inv1.setItem(35, Data.buildHead("oak_arrow_left", "§c< Go back"));
				}
				
			});
			
			return inv1;
		}
		return null;
	}

}
