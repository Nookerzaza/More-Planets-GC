/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.worldgen;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.world.biome.BiomeGenBase;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.handler.IPlanetFog;
import stevekung.mods.moreplanets.core.world.biome.BiomeGenBaseMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseCow;
import stevekung.mods.moreplanets.planets.polongnius.entities.EntityCheeseSlime;

public class BiomeGenBasePolongnius extends BiomeGenBaseMP implements IPlanetFog
{
	public static BiomeGenBase polongnius = new BiomeGenBasePolongnius().setBiomeName("Polongnius");

	public BiomeGenBasePolongnius()
	{
		super(ConfigManagerMP.idPolongniusBiome);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCheeseCow.class, 6, 2, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedZombie.class, 8, 2, 3));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSpider.class, 8, 2, 3));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 8, 2, 3));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedCreeper.class, 8, 2, 3));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedEnderman.class, 8, 1, 2));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCheeseSlime.class, 2, 1, 2));
	}

	@Override
	public float getFogDensity(int x, int y, int z)
	{
		return 0.15F;
	}

	@Override
	public int getFogColor(int x, int y, int z)
	{
		return -1854138;
	}
}