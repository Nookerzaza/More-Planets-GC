/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MPEntityBossFlying extends EntityMob
{
	public MPEntityBossFlying(World par1World)
	{
		super(par1World);
	}

	@Override
	protected void fall(float par1) {}

	@Override
	protected void updateFallState(double par1, boolean par3) {}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.motionY *= 0.6000000238418579D;
	}

	@Override
	public void moveEntityWithHeading(float par1, float par2)
	{
		if (this.isInWater())
		{
			this.moveFlying(par1, par2, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.800000011920929D;
			this.motionY *= 0.800000011920929D;
			this.motionZ *= 0.800000011920929D;
		}
		else if (this.handleLavaMovement())
		{
			this.moveFlying(par1, par2, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
		}
		else
		{
			float f2 = 0.91F;

			if (this.onGround)
			{
				f2 = 0.54600006F;
				int i = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

				if (i > 0)
				{
					f2 = Block.blocksList[i].slipperiness * 0.91F;
				}
			}

			float f3 = 0.16277136F / (f2 * f2 * f2);
			this.moveFlying(par1, par2, this.onGround ? 0.1F * f3 : 0.02F);
			f2 = 0.91F;

			if (this.onGround)
			{
				f2 = 0.54600006F;
				int j = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

				if (j > 0)
				{
					f2 = Block.blocksList[j].slipperiness * 0.91F;
				}
			}
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= f2;
			this.motionY *= f2;
			this.motionZ *= f2;
		}

		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d0 = this.posX - this.prevPosX;
		double d1 = this.posZ - this.prevPosZ;
		float f4 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

		if (f4 > 1.0F)
		{
			f4 = 1.0F;
		}
		this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

	@Override
	public boolean isOnLadder()
	{
		return false;
	}
}