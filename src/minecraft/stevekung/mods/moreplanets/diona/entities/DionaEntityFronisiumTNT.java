/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DionaEntityFronisiumTNT extends Entity
{
	public int fuse;
	private EntityLivingBase tntPlacedBy;

	public DionaEntityFronisiumTNT(World par1World)
	{
		super(par1World);
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F;
	}

	public DionaEntityFronisiumTNT(World par1World, double par2, double par4, double par6, EntityLivingBase par8EntityLivingBase)
	{
		this(par1World);
		this.setPosition(par2, par4, par6);
		float f = (float)(Math.random() * Math.PI * 2.0D);
		this.motionX = -((float)Math.sin(f)) * 0.02F;
		this.motionY = 0.20000000298023224D;
		this.motionZ = -((float)Math.cos(f)) * 0.02F;
		this.fuse = 80;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
		this.tntPlacedBy = par8EntityLivingBase;
	}

	@Override
	protected void entityInit() {}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.fuse-- <= 0)
		{
			this.setDead();

			if (!this.worldObj.isRemote)
			{
				this.explode();
			}
		}
		else
		{
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode()
	{
		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 8.0F, true);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setByte("Fuse", (byte)this.fuse);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		this.fuse = par1NBTTagCompound.getByte("Fuse");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	public EntityLivingBase getTntPlacedBy()
	{
		return this.tntPlacedBy;
	}
}