/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.render.entities;

import micdoodle8.mods.galacticraft.planets.mars.client.model.ModelSludgeling;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import stevekung.mods.moreplanets.planets.nibiru.entities.EntityInfectedWorm;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderInfectedWorm extends RenderLiving
{
	private static final ResourceLocation delriumWormTexture = new ResourceLocation("nibiru:textures/model/infected_worm.png");

	public RenderInfectedWorm()
	{
		super(new ModelSludgeling(), 0.2F);
	}

	protected ResourceLocation delriumWormTexture(EntityInfectedWorm entityDelriumWorm)
	{
		return RenderInfectedWorm.delriumWormTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return this.delriumWormTexture((EntityInfectedWorm) par1Entity);
	}

	public void renderDelriumWorm(EntityInfectedWorm entityDelriumWorm, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entityDelriumWorm, par2, par4, par6, par8, par9);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
	{
		return 180.0F;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		return -1;
	}

	@Override
	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderDelriumWorm((EntityInfectedWorm) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderDelriumWorm((EntityInfectedWorm) par1Entity, par2, par4, par6, par8, par9);
	}
}