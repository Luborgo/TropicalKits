package me.tropical.kits;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
  implements Listener
{
  public String Prefix;
  public final Logger logger = Logger.getLogger("Minecraft");
  public static Main plugin;

  public void onDisable() {
    PluginDescriptionFile pdfFile = getDescription();
    this.logger.info(ChatColor.DARK_GREEN + pdfFile.getName() + " Foi Desativado Com Sucesso!");
  }

  public void onEnable() {
    PluginDescriptionFile pdfFile = getDescription();
    this.logger.info(ChatColor.DARK_GREEN + pdfFile.getName() + " Versao " + pdfFile.getVersion() + " Foi Ativado Com Sucesso!");
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    Player player = (Player)sender;

    if(cmd.getName().equalsIgnoreCase("kit") || commandLabel.equalsIgnoreCase("receber")) {
      if (args.length == 0) {
        player.sendMessage("§cKits Disponiveis:");
        player.sendMessage("§a/kit noob");
        player.sendMessage("§a/kit pvp");
        player.sendMessage("§a/kit vip");
        player.sendMessage("§a/kit vipplus");
        return true;
      }
      
      if(args[0].equalsIgnoreCase("noob")){
       player.sendMessage("§aRecebendo kit noob!");
       PlayerInventory PI = player.getInventory();
       PI.addItem(new ItemStack(Material.IRON_PICKAXE, 1)); /* Picareta De Ferro Basica */
       PI.addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1)); /* Maça Dourada Encantada */
      }
      
      if(args[0].equalsIgnoreCase("pvp")){
       player.sendMessage("§aRecebendo kit noob!");
       //TODO: Kit PvP
      }
      
    } else if (commandLabel.equalsIgnoreCase("tropicalkits-reload")) {
      player.sendMessage(this.Prefix + ChatColor.GREEN + "Plugin Recarregado Com Sucesso!");
      //TODO: Reload De Verdade
    }
    return false;
  }
}