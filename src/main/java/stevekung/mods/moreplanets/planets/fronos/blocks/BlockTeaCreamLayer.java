/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.item.Item;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class BlockTeaCreamLayer extends BlockCreamLayer
{
	public BlockTeaCreamLayer(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public Item getCreamBallDropped()
	{
		return FronosItems.cream_ball;
	}

	@Override
	public int getCreamBallMetaDropped()
	{
		return 4;
	}
}