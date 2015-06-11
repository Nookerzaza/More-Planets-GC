/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.GCCoreEntityCreeper;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntitySkeleton;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntitySpider;
import micdoodle8.mods.galacticraft.core.entities.GCCoreEntityZombie;
import micdoodle8.mods.galacticraft.core.perlin.NoiseModule;
import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreCraterSize;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreMapGenBaseMeta;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.GCCoreMapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.diona.entities.DionaEntitySpaceEnderman;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseCow;
import stevekung.mods.moreplanets.polongnius.entities.PolongniusEntityCheeseSlime;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;
import stevekung.mods.moreplanets.polongnius.worldgen.dungeon.PolongniusRoomBoss;
import stevekung.mods.moreplanets.polongnius.worldgen.dungeon.PolongniusRoomChests;
import stevekung.mods.moreplanets.polongnius.worldgen.dungeon.PolongniusRoomEmpty;
import stevekung.mods.moreplanets.polongnius.worldgen.dungeon.PolongniusRoomSpawner;
import stevekung.mods.moreplanets.polongnius.worldgen.dungeon.PolongniusRoomTreasure;

public class PolongniusChunkProvider extends ChunkProviderGenerate
{
	final short topBlockID = (short) PolongniusBlocks.polongniusBasicBlock.blockID;
	final byte topBlockMeta = 7;
	final short fillBlockID = (short) PolongniusBlocks.polongniusBasicBlock.blockID;
	final byte fillBlockMeta = 8;
	final short lowerBlockID = (short) PolongniusBlocks.polongniusBasicBlock.blockID;
	final byte lowerBlockMeta = 9;

	private final Random rand;

	private final NoiseModule noiseGen1;
	private final NoiseModule noiseGen2;
	private final NoiseModule noiseGen3;
	private final NoiseModule noiseGen4;

	public PolongniusBiomeDecorator biomedecoratorplanet = new PolongniusBiomeDecorator(PolongniusBiomeGenBase.polongnius);

	private final World worldObj;

	private final GCCoreMapGenDungeon dungeonGenerator = new GCCoreMapGenDungeon(PolongniusBlocks.polongniusBasicBlock.blockID, 14, 8, 16, 3);

	{
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomEmpty(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomSpawner(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomChests(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new PolongniusRoomChests(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.bossRooms.add(new PolongniusRoomBoss(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.treasureRooms.add(new PolongniusRoomTreasure(null, 0, 0, 0, ForgeDirection.UNKNOWN));
	}

	private BiomeGenBase[] biomesForGeneration = { PolongniusBiomeGenBase.polongnius };

	private final GCCoreMapGenBaseMeta caveGenerator = new PolongniusGenCaves();

	private static final int CRATER_PROB = 600;

	// DO NOT CHANGE
	private static final int MID_HEIGHT = 40;
	private static final int CHUNK_SIZE_X = 16;
	private static final int CHUNK_SIZE_Y = 128;
	private static final int CHUNK_SIZE_Z = 16;

	public PolongniusChunkProvider(World par1World, long par2, boolean par4)
	{
		super(par1World, par2, par4);
		this.worldObj = par1World;
		this.rand = new Random(par2);
		this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25);
		this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25);
		this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, 0.25);
		this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, 0.25);
	}

	public void generateTerrain(int chunkX, int chunkZ, short[] idArray, byte[] metaArray)
	{
		this.noiseGen1.frequency = 0.0125;
		this.noiseGen2.frequency = 0.015;
		this.noiseGen3.frequency = 0.01;
		this.noiseGen4.frequency = 0.02;

		for (int x = 0; x < PolongniusChunkProvider.CHUNK_SIZE_X; x++)
		{
			for (int z = 0; z < PolongniusChunkProvider.CHUNK_SIZE_Z; z++)
			{
				final double d = this.noiseGen1.getNoise(x + chunkX * 16, z + chunkZ * 16) * 8;
				final double d2 = this.noiseGen2.getNoise(x + chunkX * 16, z + chunkZ * 16) * 24;
				double d3 = this.noiseGen3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1;
				d3 *= 4;

				double yDev = 0;

				if (d3 < 0.0D)
				{
					yDev = d;
				}
				else if (d3 > 1.0D)
				{
					yDev = d2;
				}
				else
				{
					yDev = d + (d2 - d) * d3;
				}

				for (int y = 0; y < PolongniusChunkProvider.CHUNK_SIZE_Y; y++)
				{
					if (y < PolongniusChunkProvider.MID_HEIGHT + yDev)
					{
						idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
						metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
					}
				}
			}
		}
	}

	public void replaceBlocksForBiome(int par1, int par2, short[] arrayOfIDs, byte[] arrayOfMeta, BiomeGenBase[] par4ArrayOfBiomeGenBase)
	{
		final int var5 = 20;
		for (int var8 = 0; var8 < 16; ++var8)
		{
			for (int var9 = 0; var9 < 16; ++var9)
			{
				final int var12 = (int) (this.noiseGen4.getNoise(var8 + par1 * 16, var9 * par2 * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int var13 = -1;
				short var14 = this.topBlockID;
				byte var14m = this.topBlockMeta;
				short var15 = this.fillBlockID;
				byte var15m = this.fillBlockMeta;

				for (int var16 = 127; var16 >= 0; --var16)
				{
					final int index = this.getIndex(var8, var16, var9);
					arrayOfMeta[index] = 0;

					if (var16 <= 0 + this.rand.nextInt(5))
					{
						arrayOfIDs[index] = (short) Block.bedrock.blockID;
					}
					else
					{
						final int var18 = arrayOfIDs[index];
						if (var18 == 0)
						{
							var13 = -1;
						}
						else if (var18 == this.lowerBlockID)
						{
							arrayOfMeta[index] = this.lowerBlockMeta;

							if (var13 == -1)
							{
								if (var12 <= 0)
								{
									var14 = 0;
									var14m = 0;
									var15 = this.lowerBlockID;
									var15m = this.lowerBlockMeta;
								}
								else if (var16 >= var5 - -16 && var16 <= var5 + 1)
								{
									var14 = this.topBlockID;
									var14m = this.topBlockMeta;
									var14 = this.fillBlockID;
									var14m = this.fillBlockMeta;
								}

								var13 = var12;

								if (var16 >= var5 - 1)
								{
									arrayOfIDs[index] = var14;
									arrayOfMeta[index] = var14m;
								}
								else if (var16 < var5 - 1 && var16 >= var5 - 2)
								{
									arrayOfIDs[index] = var15;
									arrayOfMeta[index] = var15m;
								}
							}
							else if (var13 > 0)
							{
								--var13;
								arrayOfIDs[index] = var15;
								arrayOfMeta[index] = var15m;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public Chunk provideChunk(int par1, int par2)
	{
		this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		final short[] ids = new short[32768 * 2];
		final byte[] meta = new byte[32768 * 2];
		this.generateTerrain(par1, par2, ids, meta);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		this.createCraters(par1, par2, ids, meta);
		this.replaceBlocksForBiome(par1, par2, ids, meta, this.biomesForGeneration);
		this.caveGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
		this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), par1 * 16, 25, par2 * 16, par1, par2, ids, meta);
		final Chunk var4 = new Chunk(this.worldObj, ids, meta, par1, par2);
		var4.generateSkylightMap();
		return var4;
	}

	public void createCraters(int chunkX, int chunkZ, short[] chunkArray, byte[] metaArray)
	{
		for (int cx = chunkX - 2; cx <= chunkX + 2; cx++)
		{
			for (int cz = chunkZ - 2; cz <= chunkZ + 2; cz++)
			{
				for (int x = 0; x < PolongniusChunkProvider.CHUNK_SIZE_X; x++)
				{
					for (int z = 0; z < PolongniusChunkProvider.CHUNK_SIZE_Z; z++)
					{
						if (Math.abs(this.randFromPoint(cx * 16 + x, (cz * 16 + z) * 1000)) < this.noiseGen4.getNoise(x * PolongniusChunkProvider.CHUNK_SIZE_X + x, cz * PolongniusChunkProvider.CHUNK_SIZE_Z + z) / PolongniusChunkProvider.CRATER_PROB)
						{
							final Random random = new Random(cx * 16 + x + (cz * 16 + z) * 5000);
							final GCCoreCraterSize cSize = GCCoreCraterSize.sizeArray[random.nextInt(GCCoreCraterSize.sizeArray.length)];
							final int size = random.nextInt(cSize.MAX_SIZE - cSize.MIN_SIZE) + cSize.MIN_SIZE;
							this.makeCrater(cx * 16 + x, cz * 16 + z, chunkX * 16, chunkZ * 16, size, chunkArray, metaArray);
						}
					}
				}
			}
		}
	}

	public void makeCrater(int craterX, int craterZ, int chunkX, int chunkZ, int size, short[] chunkArray, byte[] metaArray)
	{
		for (int x = 0; x < PolongniusChunkProvider.CHUNK_SIZE_X; x++)
		{
			for (int z = 0; z < PolongniusChunkProvider.CHUNK_SIZE_Z; z++)
			{
				double xDev = craterX - (chunkX + x);
				double zDev = craterZ - (chunkZ + z);
				if (xDev * xDev + zDev * zDev < size * size)
				{
					xDev /= size;
					zDev /= size;
					final double sqrtY = xDev * xDev + zDev * zDev;
					double yDev = sqrtY * sqrtY * 6;
					yDev = 5 - yDev;
					int helper = 0;
					for (int y = 127; y > 0; y--)
					{
						if (chunkArray[this.getIndex(x, y, z)] != 0 && helper <= yDev)
						{
							chunkArray[this.getIndex(x, y, z)] = 0;
							metaArray[this.getIndex(x, y, z)] = 0;
							helper++;
						}
						if (helper > yDev)
						{
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public boolean chunkExists(int par1, int par2)
	{
		return true;
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	private int getIndex(int x, int y, int z)
	{
		return y << 8 | z << 4 | x;
	}

	private double randFromPoint(int x, int z)
	{
		int n;
		n = x + z * 57;
		n = n << 13 ^ n;
		return 1.0 - (n * (n * n * 15731 + 789221) + 1376312589 & 0x7fffffff) / 1073741824.0;
	}

	public void decoratePlanet(World par1World, Random par2Random, int par3, int par4)
	{
		this.biomedecoratorplanet.decorate(par1World, par2Random, par3, par4);
	}

	@Override
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	{
		BlockSand.fallInstantly = true;
		final int var4 = par2 * 16;
		final int var5 = par3 * 16;
		this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		final long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		final long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(par2 * var7 + par3 * var9 ^ this.worldObj.getSeed());

		this.dungeonGenerator.handleTileEntities(this.rand);

		this.decoratePlanet(this.worldObj, this.rand, var4, var5);
		BlockSand.fallInstantly = false;
	}

	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	{
		return true;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return PolongniusConfigManager.generateOtherMods ? "RandomLevelSource" : "PolongniusLevelSource";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int i, int j, int k)
	{
		if (par1EnumCreatureType == EnumCreatureType.monster)
		{
			final List monsters = new ArrayList();
			monsters.add(new SpawnListEntry(GCCoreEntityZombie.class, 8, 2, 3));
			monsters.add(new SpawnListEntry(GCCoreEntitySpider.class, 8, 2, 3));
			monsters.add(new SpawnListEntry(GCCoreEntitySkeleton.class, 8, 2, 3));
			monsters.add(new SpawnListEntry(GCCoreEntityCreeper.class, 8, 2, 3));
			monsters.add(new SpawnListEntry(DionaEntitySpaceEnderman.class, 8, 1, 2));
			monsters.add(new SpawnListEntry(PolongniusEntityCheeseSlime.class, 8, 1, 2));
			return monsters;
		}
		else if (par1EnumCreatureType == EnumCreatureType.creature)
		{
			final List creatures = new ArrayList();
			creatures.add(new SpawnListEntry(PolongniusEntityCheeseCow.class, 10, 2, 4));
			return creatures;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void recreateStructures(int par1, int par2)
	{
	}
}