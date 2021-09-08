package de.lacodev.staffcore.extensionshook;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.lacodev.staffcore.extensionshook.enums.Extension;
import de.lacodev.staffcore.extensionshook.listeners.ChannelListener;
import de.lacodev.staffcore.extensionshook.listeners.ListenerExtensionsInv;
import de.lacodev.staffcore.extensionshook.mysql.MySQL;

public class ExtensionHook extends JavaPlugin{
	
	public static ExtensionHook instance;
	public MySQL mysql;
	
	public String compatibleVersion = "StaffCore-Bungee v2.1.0-Release";
	
	public void onEnable() {
		
		instance = this;
		
		setupMySQL();
		
		if(mysql.isConnected()) {
			registerChannels();
			registerEvents();
			setActive();
			Bukkit.getConsoleSender().sendMessage("§cSC-Extensions §8» §8Environment: §7"+ Bukkit.getVersion());
			Bukkit.getConsoleSender().sendMessage("§cSC-Extensions §8» §cHook-v" + this.getDescription().getVersion() + " §8is compatible with §c" + compatibleVersion);
			Bukkit.getConsoleSender().sendMessage("§cSC-Extensions §8» §8Hook is §anow listening§8!");
		}
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new ListenerExtensionsInv(), this);
	}

	public void onDisable() {
	    this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
	    this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
	    
	    if(Extension.HOOK.isActive()) {
	    	Extension.HOOK.deactivate();
	    }
	}
	
	private void registerChannels() {
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new ChannelListener());
	}

	private void setupMySQL() {
		
		File mysqlf = new File(getDataFolder().getPath(), "mysql.yml");
		
		if(!mysqlf.exists()) {
			try {	
				YamlConfiguration mysqlcfg = YamlConfiguration.loadConfiguration(mysqlf);
				
				mysqlcfg.set("MySQL.HOST", "host");
				mysqlcfg.set("MySQL.PORT", "3306");
				mysqlcfg.set("MySQL.DATABASE", "database");
				mysqlcfg.set("MySQL.USERNAME", "username");
				mysqlcfg.set("MySQL.PASSWORD", "password");
				
				mysqlcfg.save(mysqlf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		YamlConfiguration mysqlcfg = YamlConfiguration.loadConfiguration(mysqlf);
		
	    MySQL.HOST = mysqlcfg.getString("MySQL.HOST");
	    MySQL.PORT = mysqlcfg.getString("MySQL.PORT");
	    MySQL.DATABASE = mysqlcfg.getString("MySQL.DATABASE");
	    MySQL.USER = mysqlcfg.getString("MySQL.USERNAME");
	    MySQL.PASSWORD = mysqlcfg.getString("MySQL.PASSWORD");
		
		mysql = new MySQL(MySQL.HOST,MySQL.DATABASE,MySQL.USER,MySQL.PASSWORD);
		
		mysql.update("CREATE TABLE IF NOT EXISTS StaffCore_extensionsdb(id INT(6) AUTO_INCREMENT UNIQUE, TYPE VARCHAR(255), STATUS BOOLEAN)");
		mysql.update("CREATE TABLE IF NOT EXISTS StaffCore_extensions_pm_plugindata(id INT(6) AUTO_INCREMENT UNIQUE, PLUGIN VARCHAR(255), "
				+ "RESOURCE_ID INT(6), AUTHOR INT(12), DOWNLOAD_TYPE VARCHAR(255), URL VARCHAR(500), NAME VARCHAR(100), TAG VARCHAR(100), VERSION VARCHAR(255))");
	}
	
	public MySQL getMySQL() {
		return mysql;
	}
	
	public void setActive() {
		Extension hook = Extension.HOOK;
		if(hook.exists()) {
			if(!hook.isActive()) {
				hook.activate();
			}
		} else {
			mysql.update("INSERT INTO StaffCore_extensionsdb(TYPE,STATUS) VALUES ('"+ hook.toString() +"','1')");
		}
	}
	
	public PluginManager getPluginManager() {
		return Bukkit.getPluginManager();
	}

	public static ExtensionHook getInstance() {
		return instance;
	}

}
