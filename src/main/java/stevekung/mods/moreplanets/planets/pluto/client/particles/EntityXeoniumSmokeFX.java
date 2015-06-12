/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.pluto.client.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityXeoniumSmokeFX extends EntityFX
{
	float smokeParticleScale;

	public EntityXeoniumSmokeFX(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.particleRed *= 0.1F;
		this.particleGreen *= 0.95F;
		this.particleBlue *= 0.7F;
		this.particleScale *= 0.75F;
		this.smokeParticleScale = this.particleScale;
		this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
		this.particleMaxAge = (int)(this.particleMaxAge * this.particleScale);
		this.noClip = false;
	}

	@Override
	public void func_180434_a(WorldRenderer worldRender, Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		float f6 = (this.particleAge + par2) / this.particleMaxAge * 32.0F;

		if (f6 < 0.0F)
		{
			f6 = 0.0F;
		}
		if (f6 > 1.0F)
		{
			f6 = 1.0F;
		}
		this.particleScale = this.smokeParticleScale * f6;
		super.func_180434_a(worldRender, entity, par2, par3, par4, par5, par6, par7);
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}

		this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
		this.motionY += 0.004D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.posY == this.prevPosY)
		{
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}