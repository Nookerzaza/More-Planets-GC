/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.items.IPowerCrystal;
import stevekung.mods.moreplanets.common.items.ItemBaseMP;

public class ItemKapteynB extends ItemBaseMP implements IPowerCrystal
{
	public ItemKapteynB(String name)
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
	public String[] getItemVariantsName()
	{
		return new String[] { "frozen_iron_ingot", "uranium_gem", "compressed_frozen_iron", "uranium_stick", "frozen_iron_stick", "ice_crystal_shard" };
	}

	@Override
	public boolean isPowerCrystal(int meta)
	{
		return meta == 1 || meta == 5;
	}

	@Override
	public int getPowerCrystalBurnTime(int meta)
	{
		return meta == 1 ? 16000 : meta == 5 ? 6400 : 0;
	}
}