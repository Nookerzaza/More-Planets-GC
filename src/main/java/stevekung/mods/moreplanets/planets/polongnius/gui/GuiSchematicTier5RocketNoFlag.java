/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.gui;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicResultPage;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.planets.polongnius.inventory.container.ContainerSchematicTier5RocketNoFlag;

public class GuiSchematicTier5RocketNoFlag extends GuiContainer implements ISchematicResultPage
{
	private static ResourceLocation tier5NoFlagSchematicTexture = new ResourceLocation("polongnius:textures/gui/schematic_tier5_rocket.png");

	private int pageIndex;

	public GuiSchematicTier5RocketNoFlag(InventoryPlayer par1InventoryPlayer, int x, int y, int z)
	{
		super(new ContainerSchematicTier5RocketNoFlag(par1InventoryPlayer, x, y, z));
		this.ySize = 238;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 130, this.height / 2 - 30 + 27 - 12, 40, 20, GCCoreUtil.translate("gui.button.back.name")));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 130, this.height / 2 - 30 + 27 + 12, 40, 20, GCCoreUtil.translate("gui.button.next.name")));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if (par1GuiButton.enabled)
		{
			switch (par1GuiButton.id)
			{
			case 0:
				SchematicRegistry.flipToLastPage(this.pageIndex);
				break;
			case 1:
				SchematicRegistry.flipToNextPage(this.pageIndex);
				break;
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(StatCollector.translateToLocal(ConfigManagerMP.enableRocketWithThaiFlag ? "schematics.container.rocket.noflag.tier5" : "schematics.container.rocket.tier5"), 7, -20 + 27, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, 220 - 104 + 2 + 27, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(GuiSchematicTier5RocketNoFlag.tier5NoFlagSchematicTexture);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void setPageIndex(int index)
	{
		this.pageIndex = index;
	}
}