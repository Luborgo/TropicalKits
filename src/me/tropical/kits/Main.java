package me.tropical.kits;

import java.util.HashMap;
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
import org.bukkit.scheduler.BukkitRunnable;

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
	  cooldownTime = new HashMap<Player, Integer>();
      cooldownTask = new HashMap<Player, BukkitRunnable>();
    PluginDescriptionFile pdfFile = getDescription();
    this.logger.info(ChatColor.DARK_GREEN + pdfFile.getName() + " Versao " + pdfFile.getVersion() + " Foi Ativado Com Sucesso!");
  }
  private HashMap<Player, Integer> cooldownTime;
  private HashMap<Player, BukkitRunnable> cooldownTask;
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    final Player player = (Player)sender;

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
       PI.addItem(new ItemStack(Material.IRON_AXE, 1 )); /* Machado de Ferro */
      }
      
      if(args[0].equalsIgnoreCase("pvp")){
       if(!cooldownTime.containsKey(player)){
    	   player.sendMessage("§aRecebendo kit pvp!");
           PlayerInventory PI = player.getInventory();
           PI.addItem(new ItemStack(Material.STONE_SWORD, 1)); /* Picareta De Ferro Basica */
           PI.addItem(new ItemStack(Material.LEATHER_HELMET, 1)); /* Picareta De Ferro Basica */
           PI.addItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1)); /* Picareta De Ferro Basica */
           PI.addItem(new ItemStack(Material.LEATHER_LEGGINGS, 1)); /* Picareta De Ferro Basica */
           PI.addItem(new ItemStack(Material.LEATHER_BOOTS, 1)); /* Picareta De Ferro Basica */
           PI.addItem(new ItemStack(Material.GOLDEN_APPLE, 1)); /* Picareta De Ferro Basica */
           cooldownTime.put(player, 120);
           cooldownTask.put(player, new BukkitRunnable() {
                   public void run() {
                           cooldownTime.put(player, cooldownTime.get(player) - 1);
                           if (cooldownTime.get(player) == 0) {
                                   cooldownTime.remove(player);
                                   cooldownTask.remove(player);
                                   cancel();
                           }
                   }
           });
          
           cooldownTask.get(player).runTaskTimer(this, 20, 20);
       } else {
    	  player.sendMessage("§cVoce nao pode pegar o kit por mais " + cooldownTime.get(player) + " segundos!");
       }
      }
      
    } else if (commandLabel.equalsIgnoreCase("tropicalkits-reload")) {
      player.sendMessage(this.Prefix + ChatColor.GREEN + "Plugin Recarregado Com Sucesso!");
      //TODO: Reload De Verdade
    }
    return false;
  }
}