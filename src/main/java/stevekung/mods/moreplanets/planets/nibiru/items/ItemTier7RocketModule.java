/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.items.ItemBaseMP;

public class ItemTier7RocketModule extends ItemBaseMP
{
	public ItemTier7RocketModule(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < this.getItemVariantsName().length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (player.worldObj.isRemote)
		{
			if (itemStack.getItemDamage() == 2)
			{
				list.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("desc.tier7.name"));
			}
		}
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "tier_7_rocket_engine", "tier_7_booster", "tier_7_heavy_duty_plate", "tier_7_rocket_fin", "tier_7_nose_cone" };
	}
}