/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.kapteynb.render.tileentities;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import stevekung.mods.moreplanets.planets.kapteynb.blocks.BlockKapteynBAncientChest;
import stevekung.mods.moreplanets.planets.kapteynb.models.ModelKapteynBAncientChest;
import stevekung.mods.moreplanets.planets.kapteynb.models.ModelLargeKapteynBAncientChest;
import stevekung.mods.moreplanets.planets.kapteynb.tileentities.TileEntityKapteynBAncientChest;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityKapteynBAncientChestRenderer extends TileEntitySpecialRenderer
{
	private static ResourceLocation christmasChest = new ResourceLocation("textures/entity/chest/christmas.png");
	private static ResourceLocation largeChristmasChest = new ResourceLocation("textures/entity/chest/christmas_double.png");
	public static ResourceLocation chestNormal = new ResourceLocation("kapteynb:textures/model/kapteyn_b_ancient_chest.png");
	private static ResourceLocation largeChestNormal = new ResourceLocation("kapteynb:textures/model/kapteyn_b_ancient_chest_double.png");;
	private static ResourceLocation steveChestNormal = new ResourceLocation("mpcore:textures/model/steve_kung_chest.png");
	private static ResourceLocation steveLargeChestNormal = new ResourceLocation("mpcore:textures/model/steve_kung_chest_double.png");;

	public static ModelKapteynBAncientChest chestModel = new ModelKapteynBAncientChest();
	private ModelKapteynBAncientChest largeChestModel = new ModelLargeKapteynBAncientChest();

	private Calendar calendar = Calendar.getInstance();

	private boolean isChristmas;
	private boolean isSteveBirthDay;

	public TileEntityKapteynBAncientChestRenderer()
	{
		//                        Month					//Day Before			//Day After
		if (this.calendar.get(2) + 1 == 12 && this.calendar.get(5) >= 24 && this.calendar.get(5) <= 26)
		{
			this.isChristmas = true;
		}
		else if (this.calendar.get(2) + 1 == 2 && this.calendar.get(5) >= 1 && this.calendar.get(5) <= 3)
		{
			this.isSteveBirthDay = true;
		}
	}

	public void renderTileEntityChestAt(TileEntityKapteynBAncientChest par1TileEntityChest, double par2, double par4, double par6, float par8)
	{
		int i;

		if (!par1TileEntityChest.hasWorldObj())
		{
			i = 0;
		}
		else
		{
			Block block = par1TileEntityChest.getBlockType();
			i = par1TileEntityChest.getBlockMetadata();

			if (block instanceof BlockKapteynBAncientChest && i == 0)
			{
				try
				{
					((BlockKapteynBAncientChest)block).unifyAdjacentChests(par1TileEntityChest.getWorldObj(), par1TileEntityChest.xCoord, par1TileEntityChest.yCoord, par1TileEntityChest.zCoord);
				}
				catch (ClassCastException e)
				{
					FMLLog.severe("Attempted to render a chest at %d, %d, %d that was not a chest", par1TileEntityChest.xCoord, par1TileEntityChest.yCoord, par1TileEntityChest.zCoord);
				}
			}
			par1TileEntityChest.checkForAdjacentChests();
		}

		if (par1TileEntityChest.adjacentChestZNeg == null && par1TileEntityChest.adjacentChestXNeg == null)
		{
			ModelKapteynBAncientChest modelchest;

			if (par1TileEntityChest.adjacentChestXPos == null && par1TileEntityChest.adjacentChestZPosition == null)
			{
				modelchest = TileEntityKapteynBAncientChestRenderer.chestModel;

				if (this.isChristmas)
				{
					this.bindTexture(TileEntityKapteynBAncientChestRenderer.christmasChest);
				}
				else if (this.isSteveBirthDay)
				{
					this.bindTexture(TileEntityKapteynBAncientChestRenderer.steveChestNormal);
				}
				else
				{
					this.bindTexture(TileEntityKapteynBAncientChestRenderer.chestNormal);
				}
			}
			else
			{
				modelchest = this.largeChestModel;

				if (this.isChristmas)
				{
					this.bindTexture(TileEntityKapteynBAncientChestRenderer.largeChristmasChest);
				}
				else if (this.isSteveBirthDay)
				{
					this.bindTexture(TileEntityKapteynBAncientChestRenderer.steveLargeChestNormal);
				}
				else
				{
					this.bindTexture(TileEntityKapteynBAncientChestRenderer.largeChestNormal);
				}
			}

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
			short short1 = 0;

			if (i == 2)
			{
				short1 = 180;
			}

			if (i == 3)
			{
				short1 = 0;
			}

			if (i == 4)
			{
				short1 = 90;
			}

			if (i == 5)
			{
				short1 = -90;
			}

			if (i == 2 && par1TileEntityChest.adjacentChestXPos != null)
			{
				GL11.glTranslatef(1.0F, 0.0F, 0.0F);
			}

			if (i == 5 && par1TileEntityChest.adjacentChestZPosition != null)
			{
				GL11.glTranslatef(0.0F, 0.0F, -1.0F);
			}

			GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			float f1 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
			float f2;

			if (par1TileEntityChest.adjacentChestZNeg != null)
			{
				f2 = par1TileEntityChest.adjacentChestZNeg.prevLidAngle + (par1TileEntityChest.adjacentChestZNeg.lidAngle - par1TileEntityChest.adjacentChestZNeg.prevLidAngle) * par8;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

			if (par1TileEntityChest.adjacentChestXNeg != null)
			{
				f2 = par1TileEntityChest.adjacentChestXNeg.prevLidAngle + (par1TileEntityChest.adjacentChestXNeg.lidAngle - par1TileEntityChest.adjacentChestXNeg.prevLidAngle) * par8;

				if (f2 > f1)
				{
					f1 = f2;
				}
			}

			f1 = 1.0F - f1;
			f1 = 1.0F - f1 * f1 * f1;
			modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
			modelchest.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderTileEntityChestAt((TileEntityKapteynBAncientChest)par1TileEntity, par2, par4, par6, par8);
	}
}