/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.items.ItemBaseMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBasicNibiru extends ItemBaseMP
{
	public ItemBasicNibiru(String name)
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
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String[] getItemVariantsName()
	{
		return new String[] { "red_gem", "norium_ingot", "compressed_red_gem", "compressed_norium", "red_gem_stick", "norium_stick" };
	}

	@Override
	public String getTexturesFolder()
	{
		return "nibiru";
	}
}