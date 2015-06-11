/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.dimension;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import stevekung.mods.moreplanets.core.MorePlanetsCore;
import stevekung.mods.moreplanets.core.world.IUltraVioletLevel;
import stevekung.mods.moreplanets.planets.fronos.worldgen.ChunkProviderFronos;
import stevekung.mods.moreplanets.planets.fronos.worldgen.WorldChunkManagerFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderFronos extends WorldProviderSpace implements IGalacticraftWorldProvider, ISolarLevel, IUltraVioletLevel
{
	@Override
	public Vector3 getFogColor()
	{
		float f = 1.1F - this.getStarBrightness(1.0F);
		return new Vector3(2F / 255F * f, 128F / 255F * f, 253F / 255F * f);
	}

	@Override
	public Vector3 getSkyColor()
	{
		if (this.worldObj.isRaining())
		{
			float f = 1.15F - this.getStarBrightness(1.0F);
			return new Vector3(0 / 255F * f, 100 / 255F * f, 190 / 255F * f);
		}
		float f = 1.15F - this.getStarBrightness(1.0F);
		return new Vector3(120 / 255F * f, 191 / 255F * f, 255 / 255F * f);
	}

	@Override
	public boolean canRainOrSnow()
	{
		return true;
	}

	@Override
	public boolean canBlockFreeze(int x, int y, int z, boolean byWater)
	{
		return false;
	}

	@Override
	public void updateWeather()
	{
		super.updateWeather();
		this.worldObj.getWorldInfo().setRaining(true);
		this.worldObj.getWorldInfo().setThundering(true);
	}

	@Override
	public boolean canDoLightning(Chunk chunk)
	{
		return true;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return true;
	}

	@Override
	public boolean hasSunset()
	{
		return false;
	}

	@Override
	public long getDayLength()
	{
		return 180000L;
	}

	@Override
	public boolean shouldForceRespawn()
	{
		return !ConfigManagerCore.forceOverworldRespawn;
	}

	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass()
	{
		return ChunkProviderFronos.class;
	}

	@Override
	public Class<? extends WorldChunkManager> getWorldChunkManagerClass()
	{
		return WorldChunkManagerFronos.class;
	}

	@Override
	public float getCloudHeight()
	{
		return 128F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1)
	{
		float f1 = this.worldObj.getCelestialAngle(par1);
		float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}
		return f2 * f2 * 0.8F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1)
	{
		float f1 = this.worldObj.getCelestialAngle(1.0F);
		float f2 = 0.9F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

		if (f2 < 0.0F)
		{
			f2 = 0.0F;
		}
		if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}
		f2 = 1.0F - f2;
		return f2 * 1.0F;
	}

	@Override
	public double getHorizon()
	{
		return 44.0D;
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 44;
	}

	@Override
	public boolean canCoordinateBeSpawn(int var1, int var2)
	{
		return true;
	}

	@Override
	public double getSolarEnergyMultiplier()
	{
		return 5.0D;
	}

	@Override
	public float getGravity()
	{
		return 0.0F;
	}

	@Override
	public double getMeteorFrequency()
	{
		return 20.0D;
	}

	@Override
	public double getFuelUsageMultiplier()
	{
		return 0.9D;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier >= 7;
	}

	@Override
	public float getFallDamageModifier()
	{
		return 1.0F;
	}

	@Override
	public float getSoundVolReductionAmount()
	{
		return 1.0F;
	}

	@Override
	public CelestialBody getCelestialBody()
	{
		return MorePlanetsCore.fronos;
	}

	@Override
	public boolean hasBreathableAtmosphere()
	{
		return true;
	}

	@Override
	public float getThermalLevelModifier()
	{
		return 0.0F;
	}

	@Override
	public float getWindLevel()
	{
		return 1.0F;
	}

	@Override
	public double getUltraVioletEnergyMultiplie()
	{
		return 2.4D;
	}
}