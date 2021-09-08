package de.lacodev.staffcore.extensionshook.enums;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import de.lacodev.staffcore.extensionshook.ExtensionHook;
import de.lacodev.staffcore.extensionshook.utils.Data;
import de.lacodev.staffcore.extensionshook.utils.XMaterial;

public enum Extension {
	
	HOOK("SC-EX-Hook"),PLUGIN_MANAGER("SC-EX-PluginManager"),TWOFA_AUTHENTICATION("SC-EX-2FA-Authenticator"),ANTICHEAT_HOOK("SC-EX-AntiCheatHook"),GUIS("SC-EX-GUI"),REWARDS("SC-EX-Rewards"),TASKS("SC-EX-Tasks");
	
	String name;
	
	Extension(String name) {
		this.name = name;
	}

	public boolean isActive() {
		ResultSet rs = ExtensionHook.getInstance().getMySQL().query("SELECT STATUS FROM StaffCore_extensionsdb WHERE TYPE = '"+ this.toString() +"'");
		
		try {
			if(rs.next()) {
				return rs.getBoolean("STATUS");
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public boolean exists() {
		ResultSet rs = ExtensionHook.getInstance().getMySQL().query("SELECT TYPE FROM StaffCore_extensionsdb WHERE TYPE = '"+ this.toString() +"'");
		
		try {
			if(rs.next()) {
				return rs.getString("TYPE") != null;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public void activate() {
		if(this.equals(HOOK)) {
			ExtensionHook.getInstance().getMySQL().update("UPDATE StaffCore_extensionsdb SET STATUS = '1' WHERE TYPE = '"+ this.toString() +"'");
		} else {
			ExtensionHook.getInstance().getMySQL().update("UPDATE StaffCore_extensionsdb SET STATUS = '1' WHERE TYPE = '"+ this.toString() +"'");
			ExtensionHook.getInstance().getPluginManager().enablePlugin(ExtensionHook.getInstance().getPluginManager().getPlugin(name));
		}
		Bukkit.getConsoleSender().sendMessage("§cSC-Extensions §8» §8" + this.name + " is now §aactive§8!");
	}
	
	public void deactivate() {
		if(this.equals(HOOK)) {
			ExtensionHook.getInstance().getMySQL().update("UPDATE StaffCore_extensionsdb SET STATUS = '0' WHERE TYPE = '"+ this.toString() +"'");
		} else {
			ExtensionHook.getInstance().getPluginManager().disablePlugin(ExtensionHook.getInstance().getPluginManager().getPlugin(name));
			ExtensionHook.getInstance().getMySQL().update("UPDATE StaffCore_extensionsdb SET STATUS = '0' WHERE TYPE = '"+ this.toString() +"'");
		}
		Bukkit.getConsoleSender().sendMessage("§cSC-Extensions §8» §8" + this.name + " is now §cinactive§8!");
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getState() {
		if(isActive()) {
			return "§aActive";
		} else {
			return "§cInactive";
		}
	}
	
	public String getVersion() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
		
		if(plugin != null) {
			return "§a" + plugin.getDescription().getVersion();
		} else {
			return "§cPlugin not installed";
		}
	}
	
	public ItemStack getStateIndicatorItem() {
		if(isActive()) {
			return Data.buildItemStack(XMaterial.LIME_DYE, "§8» §7Click to deactivate Extension","§7Name §8» §c" + name,null);
		} else {
			return Data.buildItemStack(XMaterial.GRAY_DYE, "§8» §aClick to activate Extension","§7Name §8» §a" + name,null);
		}
	}
}
