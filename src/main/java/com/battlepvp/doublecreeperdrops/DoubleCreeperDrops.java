/*
 * Copyright (C) 2014 Harry Devane
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.battlepvp.doublecreeperdrops;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * https://www.github.com/Harry5573OP
 *
 * @author Harry5573OP
 */
public class DoubleCreeperDrops extends JavaPlugin implements Listener {
      
      @Getter
      private static DoubleCreeperDrops plugin_instance;
      @Getter
      private PluginDescriptionFile pdf_file;
      
      @Override
      public void onEnable() {
            plugin_instance = this;
            pdf_file = getDescription();
            
            logMessage("=[ Plugin version " + pdf_file.getVersion() + " starting ]=");
            
            getServer().getPluginManager().registerEvents(this, this);
            
            logMessage("=[ Plugin version " + pdf_file.getVersion() + " started ]=");
      }
      
      @Override
      public void onDisable() {
            logMessage("=[ Plugin version " + pdf_file.getVersion() + " shutting down ]=");
            
            logMessage("=[ Plugin version " + pdf_file.getVersion() + " shutdown ]=");
      }
      
      private void logMessage(String message) {
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "[" + getName() + "] " + ChatColor.WHITE + message);
      }
      
      @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
      public void onDeath(EntityDeathEvent event) {
            if (event.getEntity() == null || event.getEntity().getType() != EntityType.CREEPER) {
                  return;
            }

            //We will drop an extra drop for every drop.
            for (ItemStack creeper_drop : event.getDrops()) {
                  event.getEntity().getLocation().getWorld().dropItemNaturally(event.getEntity().getLocation(), creeper_drop.clone());
            }
      }
}
