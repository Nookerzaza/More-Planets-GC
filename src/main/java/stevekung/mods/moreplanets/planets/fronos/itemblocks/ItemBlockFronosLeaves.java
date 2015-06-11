/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.core.itemblocks.ItemBlockMorePlanet;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockFronosLeaves;

public class ItemBlockFronosLeaves extends ItemBlockMorePlanet
{
	public ItemBlockFronosLeaves(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta | 4;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		BlockFronosLeaves block = (BlockFronosLeaves)this.field_150939_a;
		return super.getUnlocalizedName() + "." + block.getLeafType(itemStack.getItemDamage());
	}
}