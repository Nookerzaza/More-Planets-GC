/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.schematic;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.diona.gui.DionaGuiSchematicRocketT3;
import stevekung.mods.moreplanets.diona.inventory.DionaContainerRocketBenchT3;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaSchematicRocketT3 implements ISchematicPage
{
	@Override
	public int getPageID()
	{
		return DionaConfigManager.idSchematicRocketT3;
	}

	@Override
	public int getGuiID()
	{
		return DionaConfigManager.idGuiRocketT3;
	}

	@Override
	public ItemStack getRequiredItem()
	{
		return new ItemStack(DionaItems.schematicT3.itemID, 1, 0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiScreen getResultScreen(EntityPlayer player, int x, int y, int z)
	{
		return new DionaGuiSchematicRocketT3(player.inventory, x, y, z);
	}

	@Override
	public Container getResultContainer(EntityPlayer player, int x, int y, int z)
	{
		return new DionaContainerRocketBenchT3(player.inventory, x, y, z);
	}

	@Override
	public int compareTo(ISchematicPage o)
	{
		if (this.getPageID() > o.getPageID())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
}