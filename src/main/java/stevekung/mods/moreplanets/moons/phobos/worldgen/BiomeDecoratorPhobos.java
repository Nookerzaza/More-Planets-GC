/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.phobos.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.moons.deimos.blocks.DeimosBlocks;

public class BiomeDecoratorPhobos extends BiomeDecoratorSpace
{
	private World world;

	private WorldGenerator dirtGen;
	private WorldGenerator deshGen;
	private WorldGenerator ironGen;
	private WorldGenerator chondriteGen;
	private WorldGenerator tinGen;
	private WorldGenerator copperGen;

	public BiomeDecoratorPhobos()
	{
		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.dirtGen = new WorldGenMinableMeta(DeimosBlocks.deimos_block, 32, 1, true, DeimosBlocks.deimos_block, 2);
		this.deshGen = new WorldGenMinableMeta(DeimosBlocks.deimos_block, 8, 7, true, DeimosBlocks.deimos_block, 2);
		this.ironGen = new WorldGenMinableMeta(DeimosBlocks.deimos_block, 8, 6, true, DeimosBlocks.deimos_block, 2);
		this.tinGen = new WorldGenMinableMeta(DeimosBlocks.deimos_block, 8, 4, true, DeimosBlocks.deimos_block, 2);
		this.copperGen = new WorldGenMinableMeta(DeimosBlocks.deimos_block, 8, 5, true, DeimosBlocks.deimos_block, 2);
		this.chondriteGen = new WorldGenMinableMeta(MPBlocks.chondrite_block, 32, 0, true, DeimosBlocks.deimos_block, 2);
	}

	@Override
	protected void decorate()
	{
		this.generateOre(16, this.ironGen, 0, 64);
		this.generateOre(16, this.chondriteGen, 0, 128);
		this.generateOre(16, this.tinGen, 0, 64);
		this.generateOre(16, this.copperGen, 0, 64);
		this.generateOre(16, this.deshGen, 20, 48);
		this.generateOre(32, this.dirtGen, 0, 255);
	}

	@Override
	protected void setCurrentWorld(World world)
	{
		this.world = world;
	}

	@Override
	protected World getCurrentWorld()
	{
		return this.world;
	}
}