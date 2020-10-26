package space.debian.WrapAPI.tools;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class SimpleItem {

    private Material itemType;
    private String name;
    private List<String> lore;
    private short dataId = -1;
    private HashMap<Enchantment, Integer> enchantments = null;

    public ItemStack asItemstack(int amount) {
        ItemStack result = (dataId == -1) ? new ItemStack(itemType, amount) : new ItemStack(itemType, amount, dataId);
        ItemMeta resultMeta = result.getItemMeta();
        resultMeta.setLore(lore);
        resultMeta.setDisplayName(name);
        if (enchantments != null)
            enchantments.forEach((k, v) -> resultMeta.addEnchant(k, v, true));
        result.setItemMeta(resultMeta);
        return (result);
    }

    public ItemStack asItemstack() {
        return asItemstack(1);
    }

    public Material getItemType() {
        return itemType;
    }

    public SimpleItem setItemType(Material itemType) {
        this.itemType = itemType;
        return this;
    }

    public String getName() {
        return name;
    }

    public SimpleItem setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getLore() {
        return lore;
    }

    public SimpleItem addLoreLine(String loreLine) {
        lore.add(loreLine);
        return this;
    }

    public SimpleItem setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public short getDataId() {
        return dataId;
    }

    public SimpleItem setDataId(short dataId) {
        this.dataId = dataId;
        return this;
    }

    public void addEnchantment(Enchantment enchantment, Integer level) {
        if (enchantments == null)
            enchantments = new HashMap<>();
        enchantments.put(enchantment, level);
    }

    public SimpleItem addEnchantment(Enchantment enchantment) {
        addEnchantment(enchantment, 1);
        return this;
    }
}
