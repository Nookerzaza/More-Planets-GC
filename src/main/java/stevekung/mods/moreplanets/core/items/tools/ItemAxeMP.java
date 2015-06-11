/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.items.tools;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAxeMP extends ItemAxe
{
	public Item repairItems;
	public int repairItemsMeta;
	public String texture;

	public ItemAxeMP(String name, ToolMaterial material, Item item, int meta, String texture)
	{
		super(material);
		this.repairItems = item;
		this.repairItemsMeta = meta;
		this.texture = texture;
		this.setUnlocalizedName(name);
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpToolsTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(this.texture);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemStack, ItemStack itemStack2)
	{
		if (itemStack2.getItem() == this.repairItems && itemStack2.getItemDamage() == this.repairItemsMeta)
		{
			return true;
		}
		return false;
	}
}