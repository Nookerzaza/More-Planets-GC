/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockLeaves extends BlockLeavesBase implements IShearable
{
	public static enum FronosLeafCategory
	{
		CAT1, CAT2, CAT3, CAT4;
	}

	private static final String[] leaves = new String[] {"redMaple"/*, "bamboo", "magic", "dark", "dead", "fir", "holy", "orangeautumn", "origin", "pinkcherry", "maple", "whitecherry", "hellbark", "jacaranda"*/};

	private Icon[][] textures;
	private final FronosLeafCategory category;
	int[] adjacentTreeBlocks;

	public FronosBlockLeaves(int blockID, FronosLeafCategory cat)
	{
		super(blockID, Material.leaves, false);
		this.category = cat;
		this.setTickRandomly(true);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(Block.soundGrassFootstep);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[3][leaves.length];

		for (int i = 0; i < leaves.length; ++i)
		{
			this.textures[0][i] = iconRegister.registerIcon("fronos:leaves_" + leaves[i] + "_fancy");
			this.textures[1][i] = iconRegister.registerIcon("fronos:leaves_" + leaves[i] + "_fast");
		}
	}

	@Override
	public Icon getIcon(int side, int metadata)
	{
		int type = getTypeFromMeta(metadata) + this.category.ordinal() * 4;
		return this.textures[!this.isOpaqueCube() ? 0 : 1][type >= leaves.length ? 0 : type];
	}

	@Override
	public boolean isOpaqueCube()
	{
		return Block.leaves.isOpaqueCube();
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 1; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (world.canLightningStrikeAt(x, y + 1, z) && !world.doesBlockHaveSolidTopSurface(x, y - 1, z) && random.nextInt(15) == 1)
		{
			double d0 = x + random.nextFloat();
			double d1 = y - 0.05D;
			double d2 = z + random.nextFloat();
			world.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		super.randomDisplayTick(world, x, y, z, random);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		byte radius = 1;
		int bounds = radius + 1;

		if (world.checkChunksExist(x - bounds, y - bounds, z - bounds, x + bounds, y + bounds, z + bounds))
		{
			for (int i = -radius; i <= radius; ++i)
			{
				for (int j = -radius; j <= radius; ++j)
				{
					for (int k = -radius; k <= radius; ++k)
					{
						int blockID = world.getBlockId(x + i, y + j, z + k);

						if (Block.blocksList[blockID] != null)
						{
							Block.blocksList[blockID].beginLeavesDecay(world, x + i, y + j, z + k);
						}
					}
				}
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.isRemote)
		{
			return;
		}

		int meta = world.getBlockMetadata(x, y, z);

		if ((meta & 8) != 0 && (meta & 4) == 0)
		{
			byte b0 = 4;
			int i1 = b0 + 1;
			byte b1 = 32;
			int j1 = b1 * b1;
			int k1 = b1 / 2;

			if (this.adjacentTreeBlocks == null)
			{
				this.adjacentTreeBlocks = new int[b1 * b1 * b1];
			}

			int l1;

			if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1))
			{
				int i2;
				int j2;
				int k2;

				for (l1 = -b0; l1 <= b0; ++l1)
				{
					for (i2 = -b0; i2 <= b0; ++i2)
					{
						for (j2 = -b0; j2 <= b0; ++j2)
						{
							k2 = world.getBlockId(x + l1, y + i2, z + j2);

							Block block = Block.blocksList[k2];

							if (block != null && block.canSustainLeaves(world, x + l1, y + i2, z + j2))
							{
								this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
							}
							else if (block != null && block.isLeaves(world, x + l1, y + i2, z + j2))
							{
								this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
							}
							else
							{
								this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
							}
						}
					}
				}

				for (l1 = 1; l1 <= 4; ++l1)
				{
					for (i2 = -b0; i2 <= b0; ++i2)
					{
						for (j2 = -b0; j2 <= b0; ++j2)
						{
							for (k2 = -b0; k2 <= b0; ++k2)
							{
								if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1)
								{
									if (this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
									{
										this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
									}

									if (this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2)
									{
										this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
									}

									if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2)
									{
										this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
									}

									if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2)
									{
										this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
									}

									if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 - 1] == -2)
									{
										this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 - 1] = l1;
									}

									if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2)
									{
										this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
									}
								}
							}
						}
					}
				}
			}

			l1 = this.adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

			if (l1 >= 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, meta & -9, 4);
			}
			else
			{
				this.removeLeaves(world, x, y, z);
			}
		}
	}

	private void removeLeaves(World world, int x, int y, int z)
	{
		this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return FronosBlocks.fronosBlockSapling.blockID;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int par7)
	{
		if (world.isRemote)
		{
			return;
		}

		if (world.rand.nextInt(20) == 0)
		{
			int var9 = this.idDropped(meta, world.rand, par7);
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(var9, 1, this.damageDropped(meta)));
		}

		/*if (((meta & 3) == 0 || (meta & 3) == 4 || (meta & 3) == 7) && (world.rand.nextInt(50) == 0))
		{
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(Items.food.get(), 1, 8));
		}*/
	}

	@Override
	public int damageDropped(int meta)
	{
		return getTypeFromMeta(meta) + this.category.ordinal() * 4 + 1;
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4)
	{
		return getTypeFromMeta(par1World.getBlockMetadata(par2, par3, par4));
	}

	@Override
	public int quantityDropped(Random random)
	{
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, getTypeFromMeta(world.getBlockMetadata(x, y, z))));
		return ret;
	}

	public String getLeafType(int metadata)
	{
		int type = getTypeFromMeta(metadata) + this.category.ordinal() * 4;
		return leaves[type >= leaves.length ? 0 : type];
	}

	private static int getTypeFromMeta(int meta)
	{
		meta = meta & 3;
		if (meta < 0 || meta >= leaves.length)
		{
			meta = 0;
		}
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean par1)
	{
		this.graphicsLevel = par1;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(World world, int x, int y, int z)
	{
		return true;
	}
}