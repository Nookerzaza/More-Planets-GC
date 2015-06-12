/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.planets.kapteynb.blocks.KapteynBBlocks;

public class ItemFrozenWaterBucket extends ItemBucket
{
	public ItemFrozenWaterBucket(String name)
	{
		super(KapteynBBlocks.frozen_water);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
		this.setContainerItem(Items.bucket);
	}

	//	@Override
	//	@SideOnly(Side.CLIENT)
	//	public EnumRarity getRarity(ItemStack itemStack)
	//	{
	//		return ClientProxyCore.galacticraftItem;
	//	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return MorePlanetsCore.mpItemsTab;
	}
}