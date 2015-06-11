/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.worldgen.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class StructureKoentusVillagePieces
{
	public static ArrayList<StructureKoentusVillagePieceWeight> getStructureVillageWeightedPieceList(Random par0Random, int par1)
	{
		ArrayList<StructureKoentusVillagePieceWeight> var2 = new ArrayList<StructureKoentusVillagePieceWeight>();
		var2.add(new StructureKoentusVillagePieceWeight(ComponentKoentusVillageHut.class, 5, MathHelper.getRandomIntegerInRange(par0Random, 2 + par1, 5 + par1 * 3)));
		var2.add(new StructureKoentusVillagePieceWeight(ComponentKoentusVillageField.class, 5, MathHelper.getRandomIntegerInRange(par0Random, 3 + par1, 5 + par1)));
		var2.add(new StructureKoentusVillagePieceWeight(ComponentKoentusVillageHouse.class, 5, MathHelper.getRandomIntegerInRange(par0Random, 3 + par1, 4 + par1 * 2)));

		Iterator<StructureKoentusVillagePieceWeight> var3 = var2.iterator();

		while (var3.hasNext())
		{
			if (var3.next().villagePiecesLimit == 0)
			{
				var3.remove();
			}
		}
		return var2;
	}

	private static int func_75079_a(List<StructureKoentusVillagePieceWeight> par0List)
	{
		boolean var1 = false;
		int var2 = 0;
		StructureKoentusVillagePieceWeight var4;

		for (Iterator<StructureKoentusVillagePieceWeight> var3 = par0List.iterator(); var3.hasNext(); var2 += var4.villagePieceWeight)
		{
			var4 = var3.next();

			if (var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit)
			{
				var1 = true;
			}
		}
		return var1 ? var2 : -1;
	}

	private static ComponentKoentusVillage func_75083_a(ComponentKoentusVillageStartPiece par0ComponentVillageStartPiece, StructureKoentusVillagePieceWeight par1StructureVillagePieceWeight, List<StructureComponent> par2List, Random par3Random, int par4, int par5, int par6, int par7, int par8)
	{
		Class<?> var9 = par1StructureVillagePieceWeight.villagePieceClass;
		Object var10 = null;

		if (var9 == ComponentKoentusVillageHut.class)
		{
			var10 = ComponentKoentusVillageHut.func_74908_a(par0ComponentVillageStartPiece, par2List, par3Random, par4, par5, par6, par7, par8);
		}
		else if (var9 == ComponentKoentusVillageField.class)
		{
			var10 = ComponentKoentusVillageField.func_74900_a(par0ComponentVillageStartPiece, par2List, par3Random, par4, par5, par6, par7, par8);
		}
		else if (var9 == ComponentKoentusVillageHouse.class)
		{
			var10 = ComponentKoentusVillageHouse.func_74921_a(par0ComponentVillageStartPiece, par2List, par3Random, par4, par5, par6, par7, par8);
		}
		return (ComponentKoentusVillage) var10;
	}

	private static ComponentKoentusVillage getNextVillageComponent(ComponentKoentusVillageStartPiece par0ComponentVillageStartPiece, List<StructureComponent> par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		int var8 = StructureKoentusVillagePieces.func_75079_a(par0ComponentVillageStartPiece.structureVillageWeightedPieceList);

		if (var8 <= 0)
		{
			return null;
		}
		else
		{
			int var9 = 0;

			while (var9 < 5)
			{
				++var9;
				int var10 = par2Random.nextInt(var8);
				Iterator<StructureKoentusVillagePieceWeight> var11 = par0ComponentVillageStartPiece.structureVillageWeightedPieceList.iterator();

				while (var11.hasNext())
				{
					StructureKoentusVillagePieceWeight var12 = var11.next();
					var10 -= var12.villagePieceWeight;

					if (var10 < 0)
					{
						if (!var12.canSpawnMoreVillagePiecesOfType(par7) || var12 == par0ComponentVillageStartPiece.structVillagePieceWeight && par0ComponentVillageStartPiece.structureVillageWeightedPieceList.size() > 1)
						{
							break;
						}

						ComponentKoentusVillage var13 = StructureKoentusVillagePieces.func_75083_a(par0ComponentVillageStartPiece, var12, par1List, par2Random, par3, par4, par5, par6, par7);

						if (var13 != null)
						{
							++var12.villagePiecesSpawned;
							par0ComponentVillageStartPiece.structVillagePieceWeight = var12;

							if (!var12.canSpawnMoreVillagePieces())
							{
								par0ComponentVillageStartPiece.structureVillageWeightedPieceList.remove(var12);
							}
							return var13;
						}
					}
				}
			}

			StructureBoundingBox var14 = ComponentKoentusVillageTorch.func_74904_a(par0ComponentVillageStartPiece, par1List, par2Random, par3, par4, par5, par6);

			if (var14 != null)
			{
				return new ComponentKoentusVillageTorch(par0ComponentVillageStartPiece, par7, par2Random, var14, par6);
			}
			else
			{
				return null;
			}
		}
	}

	private static StructureComponent getNextVillageStructureComponent(ComponentKoentusVillageStartPiece par0ComponentVillageStartPiece, List<StructureComponent> par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		if (par7 > 50)
		{
			return null;
		}
		else if (Math.abs(par3 - par0ComponentVillageStartPiece.getBoundingBox().minX) <= 112 && Math.abs(par5 - par0ComponentVillageStartPiece.getBoundingBox().minZ) <= 112)
		{
			ComponentKoentusVillage var8 = StructureKoentusVillagePieces.getNextVillageComponent(par0ComponentVillageStartPiece, par1List, par2Random, par3, par4, par5, par6, par7 + 1);

			if (var8 != null)
			{
				par1List.add(var8);
				par0ComponentVillageStartPiece.field_74932_i.add(var8);
				return var8;
			}
			return null;
		}
		else
		{
			return null;
		}
	}

	private static StructureComponent getNextComponentVillagePath(ComponentKoentusVillageStartPiece par0ComponentVillageStartPiece, List<StructureComponent> par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		if (par7 > 3 + par0ComponentVillageStartPiece.terrainType)
		{
			return null;
		}
		else if (Math.abs(par3 - par0ComponentVillageStartPiece.getBoundingBox().minX) <= 112 && Math.abs(par5 - par0ComponentVillageStartPiece.getBoundingBox().minZ) <= 112)
		{
			StructureBoundingBox var8 = ComponentKoentusVillagePathGen.func_74933_a(par0ComponentVillageStartPiece, par1List, par2Random, par3, par4, par5, par6);

			if (var8 != null && var8.minY > 10)
			{
				ComponentKoentusVillagePathGen var9 = new ComponentKoentusVillagePathGen(par0ComponentVillageStartPiece, par7, par2Random, var8, par6);

				par1List.add(var9);
				par0ComponentVillageStartPiece.field_74930_j.add(var9);
				return var9;
			}
			return null;
		}
		else
		{
			return null;
		}
	}

	static StructureComponent getNextStructureComponent(ComponentKoentusVillageStartPiece par0ComponentVillageStartPiece, List<StructureComponent> par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		return StructureKoentusVillagePieces.getNextVillageStructureComponent(par0ComponentVillageStartPiece, par1List, par2Random, par3, par4, par5, par6, par7);
	}

	static StructureComponent getNextStructureComponentVillagePath(ComponentKoentusVillageStartPiece par0ComponentVillageStartPiece, List<StructureComponent> par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		return StructureKoentusVillagePieces.getNextComponentVillagePath(par0ComponentVillageStartPiece, par1List, par2Random, par3, par4, par5, par6, par7);
	}
}