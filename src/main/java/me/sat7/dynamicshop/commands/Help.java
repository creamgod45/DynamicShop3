package me.sat7.dynamicshop.commands;

import me.sat7.dynamicshop.commands.shop.Command;
import me.sat7.dynamicshop.utilities.UserUtil;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.sat7.dynamicshop.DynamicShop;
import me.sat7.dynamicshop.utilities.ItemsUtil;
import me.sat7.dynamicshop.utilities.ShopUtil;

import java.util.UUID;

import static me.sat7.dynamicshop.constants.Constants.*;
import static me.sat7.dynamicshop.utilities.LangUtil.t;

public final class Help
{
    private Help()
    {

    }

    // 명령어 도움말 표시
    public static void showHelp(String helpcode, Player player, String[] args)
    {
        if (!UserUtil.ccUser.get().getBoolean(player.getUniqueId() + ".cmdHelp"))
            return;

        UUID uuid = player.getUniqueId();
        if (UserUtil.userTempData.get(uuid).equals(helpcode))
            return;

        UserUtil.userTempData.put(uuid, helpcode);

        if (helpcode.equals("main"))
        {
            player.sendMessage(DynamicShop.dsPrefix(player) + t(player, "HELP.TITLE").replace("{command}", "main"));
            player.sendMessage(" - shop: " + t(player, "HELP.SHOP"));
            player.sendMessage(" - qsell: " + t(player, "HELP.QSELL"));
            player.sendMessage(" - cmdHelp: " + t(player, "HELP.CMD"));
            if (player.hasPermission(P_ADMIN_CREATE_SHOP))
                player.sendMessage("§e - createshop: " + t(player, "HELP.CREATE_SHOP"));
            if (player.hasPermission(P_ADMIN_DELETE_SHOP))
                player.sendMessage("§e - deleteshop: " + t(player, "HELP.DELETE_SHOP"));
            if (player.hasPermission(P_ADMIN_RELOAD))
                player.sendMessage("§e - reload: " + t(player, "HELP.RELOAD"));
            player.sendMessage("");
        } else if (helpcode.equals("shop"))
        {
            player.sendMessage(DynamicShop.dsPrefix(player) + t(player, "HELP.TITLE").replace("{command}", "shop"));

            player.sendMessage(" - " + t(player, "HELP.USAGE") + ": /ds shop [<shopname>]");
            if (player.hasPermission(P_ADMIN_SHOP_EDIT) || player.hasPermission(P_ADMIN_EDIT_ALL))
            {
                player.sendMessage(" - " + t(player, "HELP.USAGE")
                        + ": /ds shop <shopname> <enable | addhand | add | edit | editall | setToRecAll | sellbuy | permission | maxpage | flag | currency | position | shophours | fluctuation | stockStabilizing | command | account | log | resetTradingVolume | background>");
            }

            if (player.hasPermission(P_ADMIN_SHOP_EDIT))
            {
                player.sendMessage("§e - enable: " + t(player, "HELP.SHOP_ENABLE"));
                player.sendMessage("§e - addhand: " + t(player, "HELP.SHOP_ADD_HAND"));
                player.sendMessage("§e - add: " + t(player, "HELP.SHOP_ADD_ITEM"));
                player.sendMessage("§e - edit: " + t(player, "HELP.SHOP_EDIT"));
            }

            if (player.hasPermission(P_ADMIN_EDIT_ALL))
                player.sendMessage("§e - editall: " + t(player, "HELP.EDIT_ALL"));
            if (player.hasPermission(P_ADMIN_SHOP_EDIT))
                player.sendMessage("§e - setToRecAll: " + t(player, "HELP.SET_TO_REC_ALL"));
            player.sendMessage("");
        } else if (helpcode.equals("open_shop"))
        {
            CMDManager.openShop.SendHelpMessage(player);
        } else if (helpcode.equals("enable"))
        {
            CMDManager.enable.SendHelpMessage(player);
        } else if (helpcode.equals("add_hand") && player.hasPermission(P_ADMIN_SHOP_EDIT))
        {
            CMDManager.addHand.SendHelpMessage(player);

            ItemStack tempItem = player.getInventory().getItemInMainHand();

            if (tempItem.getType() != Material.AIR)
            {
                int idx = ShopUtil.findItemFromShop(args[1], tempItem);
                if (idx != -1)
                {
                    player.sendMessage("");
                    ItemsUtil.sendItemInfo(player, args[1], idx, "HELP.ITEM_ALREADY_EXIST");
                }
            } else
            {
                player.sendMessage(" - " + t(player, "ERR.HAND_EMPTY2"));
            }

            player.sendMessage("");
        } else if (helpcode.startsWith("add") && player.hasPermission(P_ADMIN_SHOP_EDIT))
        {
            if (helpcode.length() > "add".length())
            {
                try
                {
                    Material material = Material.getMaterial(args[3]);
                    if(material == null) return;
                    ItemStack tempItem = new ItemStack(material);
                    int idx = ShopUtil.findItemFromShop(args[1], tempItem);

                    if (idx != -1)
                    {
                        ItemsUtil.sendItemInfo(player, args[1], idx, "HELP.ITEM_ALREADY_EXIST");
                        player.sendMessage("");
                    }
                } catch (Exception ignored)
                {
                }
            } else
            {
                CMDManager.add.SendHelpMessage(player);
            }
        } else if (helpcode.contains("edit") && !helpcode.equals("edit_all") && player.hasPermission(P_ADMIN_SHOP_EDIT))
        {
            if (helpcode.length() > "edit".length())
            {
                try
                {
                    Material material = Material.getMaterial(args[3].substring(args[3].indexOf("/") + 1));
                    if(material == null) return;
                    ItemStack tempItem = new ItemStack(material);
                    int idx = ShopUtil.findItemFromShop(args[1], tempItem);

                    if (idx != -1)
                    {
                        ItemsUtil.sendItemInfo(player, args[1], idx, "HELP.ITEM_INFO");
                        player.sendMessage(" - " + t(player, "HELP.REMOVE_ITEM"));
                        player.sendMessage("");
                    }
                } catch (Exception ignored)
                {
                }
            } else
            {
                CMDManager.edit.SendHelpMessage(player);
            }
        } else if (helpcode.equals("edit_all"))
        {
            CMDManager.editAll.SendHelpMessage(player);
        } else if (helpcode.equals("set_to_rec_all"))
        {
            CMDManager.setToRecAll.SendHelpMessage(player);
        } else if (helpcode.equals("cmd_help"))
        {
            CMDManager.commandHelp.SendHelpMessage(player);
        }else if (helpcode.equals("iteminfo"))
        {
            CMDManager.itemInfo.SendHelpMessage(player);
        }else if (helpcode.equals("create_shop"))
        {
            CMDManager.createShop.SendHelpMessage(player);
        } else if (helpcode.equals("delete_shop"))
        {
            CMDManager.deleteShop.SendHelpMessage(player);
        } else if (helpcode.equals("merge_shop"))
        {
            CMDManager.mergeShop.SendHelpMessage(player);
        } else if (helpcode.equals("rename_shop"))
        {
            CMDManager.renameShop.SendHelpMessage(player);
        } else if (helpcode.equals("copy_shop"))
        {
            CMDManager.copyShop.SendHelpMessage(player);
        } else if (helpcode.equals("permission"))
        {
            CMDManager.permission.SendHelpMessage(player);
        } else if (helpcode.equals("max_page"))
        {
            CMDManager.maxPage.SendHelpMessage(player);
        } else if (helpcode.equals("currency"))
        {
            CMDManager.currency.SendHelpMessage(player);
        } else if (helpcode.equals("flag"))
        {
            CMDManager.flag.SendHelpMessage(player);
        } else if (helpcode.equals("position"))
        {
            CMDManager.position.SendHelpMessage(player);
        } else if (helpcode.equals("shophours"))
        {
            CMDManager.shopHours.SendHelpMessage(player);
        } else if (helpcode.equals("fluctuation"))
        {
            CMDManager.fluctuation.SendHelpMessage(player);
        } else if (helpcode.equals("stock_stabilizing"))
        {
            CMDManager.stockStabilizing.SendHelpMessage(player);
        } else if (helpcode.equals("command"))
        {
            CMDManager.command.SendHelpMessage(player);
            Command.PrintCurrentState(player, Shop.GetShopName(args), true, true);
        } else if (helpcode.equals("account"))
        {
            CMDManager.account.SendHelpMessage(player);
        } else if (helpcode.equals("set_tax"))
        {
            CMDManager.setTax.SendHelpMessage(player);
        } else if (helpcode.equals("set_default_shop"))
        {
            CMDManager.setDefaultShop.SendHelpMessage(player);
        } else if (helpcode.equals("delete_old_user"))
        {
            CMDManager.deleteUser.SendHelpMessage(player);
        } else if (helpcode.equals("sellbuy"))
        {
            CMDManager.sellBuy.SendHelpMessage(player);
        } else if (helpcode.equals("log"))
        {
            CMDManager.log.SendHelpMessage(player);
        }
        else if (helpcode.equals("resetTradingVolume"))
        {
            CMDManager.resetTradingVolume.SendHelpMessage(player);
        }
        else if (helpcode.equals("background"))
        {
            CMDManager.background.SendHelpMessage(player);
        }
        else
        {
            CMDManager.commandHelp.SendHelpMessage(player);
        }
    }

    public static void showHelp(String helpcode, CommandSender sender, String[] args) {
        // 控制台不需要权限和用户数据检查

        switch (helpcode) {
            case "main":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "main"));
                sender.sendMessage(" - shop: " + t(sender, "HELP.SHOP"));
                sender.sendMessage(" - qsell: " + t(sender, "HELP.QSELL"));
                sender.sendMessage(" - cmdHelp: " + t(sender, "HELP.CMD"));
                sender.sendMessage("§e - createshop: " + t(sender, "HELP.CREATE_SHOP"));
                sender.sendMessage("§e - deleteshop: " + t(sender, "HELP.DELETE_SHOP"));
                sender.sendMessage("§e - reload: " + t(sender, "HELP.RELOAD"));
                sender.sendMessage("");
                break;
            case "shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "shop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop [<shopname>]");
                sender.sendMessage(" - " + t(sender, "HELP.USAGE")
                        + ": /ds shop <shopname> <enable | addhand | add | edit | editall | setToRecAll | sellbuy | permission | maxpage | flag | currency | position | shophours | fluctuation | stockStabilizing | command | account | log | resetTradingVolume | background>");

                sender.sendMessage("§e - enable: " + t(sender, "HELP.SHOP_ENABLE"));
                sender.sendMessage("§e - add: " + t(sender, "HELP.SHOP_ADD_ITEM"));
                sender.sendMessage("§e - edit: " + t(sender, "HELP.SHOP_EDIT"));
                sender.sendMessage("§e - editall: " + t(sender, "HELP.EDIT_ALL"));
                sender.sendMessage("§e - setToRecAll: " + t(sender, "HELP.SET_TO_REC_ALL"));
                sender.sendMessage("");
                break;
            case "open_shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "open_shop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds openshop <shopname> [player]");
                sender.sendMessage("");
                break;
            case "enable":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "enable"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> enable <true | false>");
                sender.sendMessage("");
                break;
            case "add":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "add"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> add <material> <buy_price> <sell_price> <median_stock> <stock> [display_name]");
                sender.sendMessage("");
                break;
            case "edit":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "edit"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> edit <index/material> <value> <buy_price | sell_price | median_stock | stock | display_name | enchant | flags | potiontype | potionlevel | potionextended | potion_duration | hide_flags | leather_color | spawn_egg | custom_data>");
                sender.sendMessage("");
                break;
            case "edit_all":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "edit_all"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> editall <value> <buy_price | sell_price | median_stock | stock>");
                sender.sendMessage("");
                break;
            case "set_to_rec_all":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "set_to_rec_all"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> setToRecAll");
                sender.sendMessage("");
                break;
            case "cmd_help":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "cmdHelp"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds cmdHelp <on | off>");
                sender.sendMessage(" - " + t(sender, "HELP.CMD"));
                sender.sendMessage("");
                break;
            case "iteminfo":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "iteminfo"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds iteminfo");
                sender.sendMessage("");
                break;
            case "create_shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "createshop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds createshop <shopname>");
                sender.sendMessage("");
                break;
            case "delete_shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "deleteshop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds deleteshop <shopname>");
                sender.sendMessage("");
                break;
            case "merge_shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "mergeshop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds mergeshop <shop1> <shop2>");
                sender.sendMessage("");
                break;
            case "rename_shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "renameshop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds renameshop <old_name> <new_name>");
                sender.sendMessage("");
                break;
            case "copy_shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "copyshop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds copyshop <source_shop> <target_shop>");
                sender.sendMessage("");
                break;
            case "permission":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "permission"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> permission <permission>");
                sender.sendMessage("");
                break;
            case "max_page":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "maxpage"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> maxpage <page_number>");
                sender.sendMessage("");
                break;
            case "currency":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "currency"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> currency <Vault | Exp | PlayerPoint | JobPoint>");
                sender.sendMessage("");
                break;
            case "flag":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "flag"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> flag <flag_name> <true | false>");
                sender.sendMessage("");
                break;
            case "position":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "position"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> position");
                sender.sendMessage("");
                break;
            case "shophours":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "shophours"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> shophours <open_hour> <close_hour>");
                sender.sendMessage("");
                break;
            case "fluctuation":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "fluctuation"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> fluctuation <value>");
                sender.sendMessage("");
                break;
            case "stock_stabilizing":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "stockStabilizing"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> stockStabilizing <interval_ticks> <recover_percentage>");
                sender.sendMessage("");
                break;
            case "command":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "command"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> command <sell | buy> add <index> <command>");
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> command <sell | buy> delete <index>");
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> command active <true | false>");
                sender.sendMessage("");
                Command.PrintCurrentState(sender, Shop.GetShopName(args), true, true);
                break;
            case "account":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "account"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> account <info | deposit | withdraw | give | set> [value]");
                sender.sendMessage("");
                break;
            case "set_tax":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "settax"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds settax <tax_percentage>");
                sender.sendMessage("");
                break;
            case "set_default_shop":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "setdefaultshop"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds setdefaultshop <shopname>");
                sender.sendMessage("");
                break;
            case "delete_old_user":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "deleteolduser"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds deleteolduser <days>");
                sender.sendMessage("");
                break;
            case "sellbuy":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "sellbuy"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> sellbuy <true | false>");
                sender.sendMessage("");
                break;
            case "log":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "log"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> log <player_name>");
                sender.sendMessage("");
                break;
            case "resetTradingVolume":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "resetTradingVolume"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> resetTradingVolume");
                sender.sendMessage("");
                break;
            case "background":
                sender.sendMessage(DynamicShop.dsPrefix(sender) + t(sender, "HELP.TITLE").replace("{command}", "background"));
                sender.sendMessage(" - " + t(sender, "HELP.USAGE") + ": /ds shop <shopname> background <material>");
                sender.sendMessage("");
                break;
        }
    }
}
