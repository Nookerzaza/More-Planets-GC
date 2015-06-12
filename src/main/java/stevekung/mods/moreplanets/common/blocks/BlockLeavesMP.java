/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockLeavesMP extends BlockBaseMP implements IShearable
{
	public static PropertyBool DECAYABLE = PropertyBool.create("decayable");
	public static PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
	int[] surroundings;

	public BlockLeavesMP()
	{
		super(Material.leaves);
		this.setTickRandomly(true);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		byte b0 = 1;
		int i = b0 + 1;
		int j = pos.getX();
		int k = pos.getY();
		int l = pos.getZ();

		if (world.isAreaLoaded(new BlockPos(j - i, k - i, l - i), new BlockPos(j + i, k + i, l + i)))
		{
			for (int i1 = -b0; i1 <= b0; ++i1)
			{
				for (int j1 = -b0; j1 <= b0; ++j1)
				{
					for (int k1 = -b0; k1 <= b0; ++k1)
					{
						BlockPos blockpos1 = pos.add(i1, j1, k1);
						IBlockState iblockstate1 = world.getBlockState(blockpos1);

						if (iblockstate1.getBlock().isLeaves(world, blockpos1))
						{
							iblockstate1.getBlock().beginLeavesDecay(world, blockpos1);
						}
					}
				}
			}
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (!world.isRemote)
		{
			if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean)state.getValue(DECAYABLE)).booleanValue())
			{
				byte b0 = 4;
				int i = b0 + 1;
				int j = pos.getX();
				int k = pos.getY();
				int l = pos.getZ();
				byte b1 = 32;
				int i1 = b1 * b1;
				int j1 = b1 / 2;

				if (this.surroundings == null)
				{
					this.surroundings = new int[b1 * b1 * b1];
				}

				int k1;

				if (world.isAreaLoaded(new BlockPos(j - i, k - i, l - i), new BlockPos(j + i, k + i, l + i)))
				{
					int l1;
					int i2;

					for (k1 = -b0; k1 <= b0; ++k1)
					{
						for (l1 = -b0; l1 <= b0; ++l1)
						{
							for (i2 = -b0; i2 <= b0; ++i2)
							{
								BlockPos tmp = new BlockPos(j + k1, k + l1, l + i2);
								Block block = world.getBlockState(tmp).getBlock();

								if (!block.canSustainLeaves(world, tmp))
								{
									if (block.isLeaves(world, tmp))
									{
										this.surroundings[(k1 + j1) * i1 + (l1 + j1) * b1 + i2 + j1] = -2;
									}
									else
									{
										this.surroundings[(k1 + j1) * i1 + (l1 + j1) * b1 + i2 + j1] = -1;
									}
								}
								else
								{
									this.surroundings[(k1 + j1) * i1 + (l1 + j1) * b1 + i2 + j1] = 0;
								}
							}
						}
					}

					for (k1 = 1; k1 <= 4; ++k1)
					{
						for (l1 = -b0; l1 <= b0; ++l1)
						{
							for (i2 = -b0; i2 <= b0; ++i2)
							{
								for (int j2 = -b0; j2 <= b0; ++j2)
								{
									if (this.surroundings[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1] == k1 - 1)
									{
										if (this.surroundings[(l1 + j1 - 1) * i1 + (i2 + j1) * b1 + j2 + j1] == -2)
										{
											this.surroundings[(l1 + j1 - 1) * i1 + (i2 + j1) * b1 + j2 + j1] = k1;
										}

										if (this.surroundings[(l1 + j1 + 1) * i1 + (i2 + j1) * b1 + j2 + j1] == -2)
										{
											this.surroundings[(l1 + j1 + 1) * i1 + (i2 + j1) * b1 + j2 + j1] = k1;
										}

										if (this.surroundings[(l1 + j1) * i1 + (i2 + j1 - 1) * b1 + j2 + j1] == -2)
										{
											this.surroundings[(l1 + j1) * i1 + (i2 + j1 - 1) * b1 + j2 + j1] = k1;
										}

										if (this.surroundings[(l1 + j1) * i1 + (i2 + j1 + 1) * b1 + j2 + j1] == -2)
										{
											this.surroundings[(l1 + j1) * i1 + (i2 + j1 + 1) * b1 + j2 + j1] = k1;
										}

										if (this.surroundings[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1 - 1] == -2)
										{
											this.surroundings[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1 - 1] = k1;
										}

										if (this.surroundings[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1 + 1] == -2)
										{
											this.surroundings[(l1 + j1) * i1 + (i2 + j1) * b1 + j2 + j1 + 1] = k1;
										}
									}
								}
							}
						}
					}
				}

				k1 = this.surroundings[j1 * i1 + j1 * b1 + j1];

				if (k1 >= 0)
				{
					world.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
				}
				else
				{
					this.destroy(world, pos);
				}
			}
		}
	}

	private void destroy(World world, BlockPos pos)
	{
		this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
		world.setBlockToAir(pos);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return Blocks.leaves.isOpaqueCube();
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return Blocks.leaves.getBlockLayer();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return Blocks.leaves.shouldSideBeRendered(world, pos, side);
	}

	@Override
	public boolean isVisuallyOpaque()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.canLightningStrike(pos.up()) && !World.doesBlockHaveSolidTopSurface(world, pos.down()) && rand.nextInt(15) == 1)
		{
			double d0 = pos.getX() + rand.nextFloat();
			double d1 = pos.getY() - 0.05D;
			double d2 = pos.getZ() + rand.nextFloat();
			world.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> ret = new ArrayList<ItemStack>();
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		int chance = 20;

		if (fortune > 0)
		{
			chance -= 2 << fortune;

			if (chance < 10)
			{
				chance = 10;
			}
		}

		if (rand.nextInt(chance) == 0)
		{
			ret.add(new ItemStack(this.getItemDropped(state, rand, fortune), 1, this.damageDropped(state)));
		}

		chance = 200;

		if (fortune > 0)
		{
			chance -= 10 << fortune;

			if (chance < 40)
			{
				chance = 40;
			}
		}
		this.captureDrops(true);
		ret.addAll(this.captureDrops(false));
		return ret;
	}

	@Override
	public boolean isShearable(ItemStack itemStack, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public void beginLeavesDecay(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (!(Boolean)state.getValue(CHECK_DECAY))
		{
			world.setBlockState(pos, state.withProperty(CHECK_DECAY, true), 4);
		}
	}
}