/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.fronos.entities.FronosEntityJellyStrawberrySlime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FronosRenderJellyStrawberrySlime extends RenderLiving
{
	private static final ResourceLocation strawberrySlimeTextures = new ResourceLocation("fronos:textures/model/jellySlime/strawberry.png");
	private ModelBase scaleAmount;

	public FronosRenderJellyStrawberrySlime(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
	{
		super(par1ModelBase, par3);
		this.scaleAmount = par2ModelBase;
	}

	protected int shouldSlimeRenderPass(FronosEntityJellyStrawberrySlime par1EntitySlime, int par2, float par3)
	{
		if (par1EntitySlime.isInvisible())
		{
			return 0;
		}
		else if (par2 == 0)
		{
			this.setRenderPassModel(this.scaleAmount);
			GL11.glEnable(GL11.GL_NORMALIZE);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			return 1;
		}
		else
		{
			if (par2 == 1)
			{
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			return -1;
		}
	}

	protected void scaleSlime(FronosEntityJellyStrawberrySlime par1EntitySlime, float par2)
	{
		float f1 = par1EntitySlime.getSlimeSize();
		float f2 = (par1EntitySlime.prevSquishFactor + (par1EntitySlime.squishFactor - par1EntitySlime.prevSquishFactor) * par2) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GL11.glScalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	protected ResourceLocation getSlimeTextures(FronosEntityJellyStrawberrySlime par1EntitySlime)
	{
		return strawberrySlimeTextures;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
	{
		this.scaleSlime((FronosEntityJellyStrawberrySlime)par1EntityLivingBase, par2);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.shouldSlimeRenderPass((FronosEntityJellyStrawberrySlime)par1EntityLivingBase, par2, par3);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.getSlimeTextures((FronosEntityJellyStrawberrySlime)par1Entity);
	}
}