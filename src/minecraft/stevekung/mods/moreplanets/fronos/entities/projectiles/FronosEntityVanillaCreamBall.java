/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.entities.projectiles;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;

public class FronosEntityVanillaCreamBall extends EntityThrowable
{
	public FronosEntityVanillaCreamBall(World par1World)
	{
		super(par1World);
	}

	public FronosEntityVanillaCreamBall(World par1World, EntityLivingBase par2EntityLivingBase)
	{
		super(par1World, par2EntityLivingBase);
	}

	public FronosEntityVanillaCreamBall(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{
		if (par1MovingObjectPosition.entityHit != null)
		{
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);

			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
			{
				((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.jump.id, 100, 2));
				((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.regeneration.id, 250, 2));
			}
		}

		for (int i = 0; i < 8; ++i)
		{
			MorePlanetCore.proxy.spawnParticle("vanillaBall", this.posX, this.posY, this.posZ);
		}

		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}