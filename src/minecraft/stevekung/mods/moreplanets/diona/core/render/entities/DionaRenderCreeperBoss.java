/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core.render.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.diona.core.models.entities.DionaModelCreeperBoss;
import stevekung.mods.moreplanets.diona.entities.DionaEntityCreeperBoss;

public class DionaRenderCreeperBoss extends RenderLiving
{
	private static final ResourceLocation invulnerableCreeperTexture = new ResourceLocation("diona:textures/model/creeperBossArmor.png");
	private static final ResourceLocation creeperTexture = new ResourceLocation("diona:textures/model/creeper.png");
	private static final ResourceLocation powerTexture = new ResourceLocation("galacticraftcore:textures/model/power.png");

	public DionaRenderCreeperBoss()
	{
		super(new DionaModelCreeperBoss(), 1.0F);
	}

	protected void scaleMob(float f)
	{
		GL11.glScalef(f, f, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return creeperTexture;
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		BossStatus.setBossStatus((IBossDisplayData) par1EntityLiving, false);

		super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
	}

	protected int func_27006_a(DionaEntityCreeperBoss par1EntityCreeper, int par2, float par3)
	{
		if (par1EntityCreeper.isArmored())
		{
			if (par2 == 1)
			{
				final float f1 = par1EntityCreeper.ticksExisted + par3;
				this.bindTexture(invulnerableCreeperTexture);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				final float f2 = f1 * 0.01F;
				final float f3 = f1 * 0.01F;
				GL11.glTranslatef(f2, f3, 0.0F);
				this.setRenderPassModel(this.mainModel);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				return 1;
			}
			if (par2 == 2)
			{
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}
		else if (!par1EntityCreeper.isArmored())
		{
			if (par2 == 1)
			{
				final float f1 = par1EntityCreeper.ticksExisted + par3;
				this.bindTexture(powerTexture);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				final float f2 = f1 * 0.01F;
				final float f3 = f1 * 0.01F;
				GL11.glTranslatef(f2, f3, 0.0F);
				this.setRenderPassModel(this.mainModel);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				return 1;
			}
			if (par2 == 2)
			{
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}
		return -1;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
	{
		this.scaleMob(3.8F);
	}

	@Override
	protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
	{
		return super.getColorMultiplier(par1EntityLivingBase, par2, par3);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return this.func_27006_a((DionaEntityCreeperBoss) par1EntityLivingBase, par2, par3);
	}

	@Override
	protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
	{
		return -1;
	}
}