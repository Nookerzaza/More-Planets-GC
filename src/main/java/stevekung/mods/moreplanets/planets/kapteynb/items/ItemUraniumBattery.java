/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items;

import micdoodle8.mods.galacticraft.api.item.IItemElectric;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemUraniumBattery extends ItemElectricBase implements IItemElectric
{
	public ItemUraniumBattery(String name)
	{
		super();
		this.setUnlocalizedName(name);
		this.setTextureName("kapteynb:uranium_battery");
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpItemsTab;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}

	@Override
	public float getMaxElectricityStored(ItemStack itemStack)
	{
		return 500000;
	}

	@Override
	public int getTierGC(ItemStack itemStack)
	{
		return 8;
	}
}