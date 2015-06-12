/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockBaseMP;

public class ItemBlockCream extends ItemBlockBaseMP
{
	public ItemBlockCream(Block block)
	{
		super(block);
	}

	@Override
	public String[] getBlockVariantsName()
	{
		return new String[] { "vanilla", "chocolate", "strawberry", "orange", "tea", "lemon" };
	}

	@Override
	public boolean reverseName()
	{
		return true;
	}
}