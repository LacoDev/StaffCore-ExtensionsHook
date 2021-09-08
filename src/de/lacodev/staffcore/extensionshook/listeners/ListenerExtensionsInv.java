package de.lacodev.staffcore.extensionshook.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.lacodev.staffcore.extensionshook.ExtensionHook;
import de.lacodev.staffcore.extensionshook.enums.Extension;
import de.lacodev.staffcore.extensionshook.enums.StaffCoreInventory;
import de.lacodev.staffcore.extensionshook.utils.Data;
import de.lacodev.staffcore.extensionshook.utils.XMaterial;

public class ListenerExtensionsInv implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		
		if(e.getView().getTitle().matches(StaffCoreInventory.EXTENSION_HANDLER_MAINGUI.getTitle())) {
			e.setCancelled(true);
			
			if(e.getCurrentItem() != null) {
				if(e.getCurrentItem().hasItemMeta()) {
					if(e.getCurrentItem().getItemMeta().getDisplayName().matches("§8» §7Manage Extensions")) {
						
						player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
						
					}
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().matches("§8» §cClose Menu")) {
						
						player.closeInventory();
						
					}
				}
			}
			
		}
		
		if(e.getView().getTitle().matches(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getTitle())) {
			e.setCancelled(true);
			
			if(e.getCurrentItem() != null) {
				if(e.getCurrentItem().hasItemMeta()) {
					if(e.getCurrentItem().getItemMeta().hasLore()) {
						
						if(e.getCurrentItem().getItemMeta().getLore().get(1).startsWith("§7Name §8» §c")) {
							
							String name = e.getCurrentItem().getItemMeta().getLore().get(1).replace("§7Name §8» §c", "");
							
							if(name.matches(Extension.PLUGIN_MANAGER.getName())) {
								Extension.PLUGIN_MANAGER.deactivate();
								player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
							}
							if(name.matches(Extension.TWOFA_AUTHENTICATION.getName())) {
								Extension.TWOFA_AUTHENTICATION.deactivate();
								player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
							}
							if(name.matches(Extension.ANTICHEAT_HOOK.getName())) {
								Extension.ANTICHEAT_HOOK.deactivate();
								player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
							}
							if(name.matches(Extension.GUIS.getName())) {
								Extension.GUIS.deactivate();
								player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
							}
							if(name.matches(Extension.REWARDS.getName())) {
								Extension.REWARDS.deactivate();
								player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
							}
							if(name.matches(Extension.TASKS.getName())) {
								Extension.TASKS.deactivate();
								player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
							}
							
						}
						
						if(e.getCurrentItem().getItemMeta().getLore().get(1).startsWith("§7Name §8» §a")) {
							
							String name = e.getCurrentItem().getItemMeta().getLore().get(1).replace("§7Name §8» §a", "");
							
							if(name.matches(Extension.PLUGIN_MANAGER.getName())) {
								if(Extension.PLUGIN_MANAGER.getVersion().matches("§cPlugin not installed")) {
									int slot = e.getSlot();
									e.getClickedInventory().setItem(slot, Data.buildItem(XMaterial.BARRIER, "§8» §cPlugin not installed..."));
									Bukkit.getScheduler().runTaskLaterAsynchronously(ExtensionHook.getInstance(), new Runnable() {

										@Override
										public void run() {
											e.getClickedInventory().setItem(slot, Extension.PLUGIN_MANAGER.getStateIndicatorItem());
										}
										
									}, 60);
								} else {
									Extension.PLUGIN_MANAGER.activate();
									player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
								}
							}
							if(name.matches(Extension.TWOFA_AUTHENTICATION.getName())) {
								if(Extension.TWOFA_AUTHENTICATION.getVersion().matches("§cPlugin not installed")) {
									int slot = e.getSlot();
									e.getClickedInventory().setItem(slot, Data.buildItem(XMaterial.BARRIER, "§8» §cPlugin not installed..."));
									Bukkit.getScheduler().runTaskLaterAsynchronously(ExtensionHook.getInstance(), new Runnable() {

										@Override
										public void run() {
											e.getClickedInventory().setItem(slot, Extension.TWOFA_AUTHENTICATION.getStateIndicatorItem());
										}
										
									}, 60);
								} else {
									Extension.TWOFA_AUTHENTICATION.activate();
									player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
								}
							}
							if(name.matches(Extension.ANTICHEAT_HOOK.getName())) {
								if(Extension.ANTICHEAT_HOOK.getVersion().matches("§cPlugin not installed")) {
									int slot = e.getSlot();
									e.getClickedInventory().setItem(slot, Data.buildItem(XMaterial.BARRIER, "§8» §cPlugin not installed..."));
									Bukkit.getScheduler().runTaskLaterAsynchronously(ExtensionHook.getInstance(), new Runnable() {

										@Override
										public void run() {
											e.getClickedInventory().setItem(slot, Extension.ANTICHEAT_HOOK.getStateIndicatorItem());
										}
										
									}, 60);
								} else {
									Extension.ANTICHEAT_HOOK.activate();
									player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
								}
							}
							if(name.matches(Extension.GUIS.getName())) {
								if(Extension.GUIS.getVersion().matches("§cPlugin not installed")) {
									int slot = e.getSlot();
									e.getClickedInventory().setItem(slot, Data.buildItem(XMaterial.BARRIER, "§8» §cPlugin not installed..."));
									Bukkit.getScheduler().runTaskLaterAsynchronously(ExtensionHook.getInstance(), new Runnable() {

										@Override
										public void run() {
											e.getClickedInventory().setItem(slot, Extension.GUIS.getStateIndicatorItem());
										}
										
									}, 60);
								} else {
									Extension.GUIS.activate();
									player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
								}
							}
							if(name.matches(Extension.REWARDS.getName())) {
								if(Extension.REWARDS.getVersion().matches("§cPlugin not installed")) {
									int slot = e.getSlot();
									e.getClickedInventory().setItem(slot, Data.buildItem(XMaterial.BARRIER, "§8» §cPlugin not installed..."));
									Bukkit.getScheduler().runTaskLaterAsynchronously(ExtensionHook.getInstance(), new Runnable() {

										@Override
										public void run() {
											e.getClickedInventory().setItem(slot, Extension.REWARDS.getStateIndicatorItem());
										}
										
									}, 60);
								} else {
									Extension.REWARDS.activate();
									player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
								}
							}
							if(name.matches(Extension.TASKS.getName())) {
								if(Extension.TASKS.getVersion().matches("§cPlugin not installed")) {
									int slot = e.getSlot();
									e.getClickedInventory().setItem(slot, Data.buildItem(XMaterial.BARRIER, "§8» §cPlugin not installed..."));
									Bukkit.getScheduler().runTaskLaterAsynchronously(ExtensionHook.getInstance(), new Runnable() {

										@Override
										public void run() {
											e.getClickedInventory().setItem(slot, Extension.TASKS.getStateIndicatorItem());
										}
										
									}, 60);
								} else {
									Extension.TASKS.activate();
									player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MANAGER_GUI.getDedicatedInventory());
								}
							}
						}
					}
					if(e.getCurrentItem().getItemMeta().getDisplayName().matches("§c< Go back")) {
						player.openInventory(StaffCoreInventory.EXTENSION_HANDLER_MAINGUI.getDedicatedInventory());
					}
				}
			}
		}
	}

}
