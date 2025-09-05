package com.opryshok.datagen;

import com.google.common.collect.ImmutableMap;
import com.opryshok.BorukvaFood;
import com.opryshok.block.ModBlocks;
import com.opryshok.block.bushes.BlackcurrantsBush;
import com.opryshok.block.bushes.GooseberryBush;
import com.opryshok.block.crops.TomatoCrop;
import com.opryshok.block.leaves.LemonFruitLeaves;
import com.opryshok.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.client.ModelIds;
import net.minecraft.util.math.Direction;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;

import java.util.Map;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    private final Map<Block, TexturedModel> uniqueModels = ImmutableMap.<Block, TexturedModel>builder()
            .build();

    public static BlockFamily LEMON_BLOCK_FAMILY = BlockFamilies.register(ModBlocks.LEMON_PLANKS)
            .button(ModBlocks.LEMON_BUTTON)
            .fence(ModBlocks.LEMON_FENCE)
            .fenceGate(ModBlocks.LEMON_FENCE_GATE)
            .pressurePlate(ModBlocks.LEMON_PRESSURE_PLATE)
            .slab(ModBlocks.LEMON_SLAB)
            .stairs(ModBlocks.LEMON_STAIRS)
            .trapdoor(ModBlocks.LEMON_TRAPDOOR)
            .build();

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        this.registerFamily(blockStateModelGenerator, LEMON_BLOCK_FAMILY);
        generateUvLockedStairs(blockStateModelGenerator, ModBlocks.LEMON_STAIRS);

        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.TOMATO, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.CABBAGE, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.CORN, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.CHILLI_PEPPER, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.BLACKCURRANTS_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED, BlackcurrantsBush.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.GOOSEBERRY_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED, GooseberryBush.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.CUCUMBER, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.LETTUCE, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.RICE, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.NETHER_WHEAT, BlockStateModelGenerator.CrossType.NOT_TINTED, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerCrop(ModBlocks.ONION, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerCrop(ModBlocks.ENDER_INFECTED_ONION, TomatoCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        generateCrate(blockStateModelGenerator, ModBlocks.BEETROOT_CRATE, "beetroot_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.CABBAGE_CRATE, "cabbage_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.CARROT_CRATE, "carrot_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.CHILLI_PEPPER_CRATE, "chilli_pepper_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.CORN_CRATE, "corn_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.CUCUMBER_CRATE, "cucumber_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.LETTUCE_CRATE, "lettuce_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.POTATO_CRATE, "potato_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.TOMATO_CRATE, "tomato_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.ONION_CRATE, "onion_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.RICE_CRATE, "rice_crate");
        generateCrate(blockStateModelGenerator, ModBlocks.CHORUS_CRATE, "chorus_crate");
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SALT);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LEMON_LEAVES);
        blockStateModelGenerator.createLogTexturePool(ModBlocks.LEMON_LOG).log(ModBlocks.LEMON_LOG).wood(ModBlocks.LEMON_WOOD);
        generateSapling(blockStateModelGenerator, ModBlocks.LEMON_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        blockStateModelGenerator.createLogTexturePool(ModBlocks.STRIPPED_LEMON_LOG).log(ModBlocks.STRIPPED_LEMON_LOG).wood(ModBlocks.STRIPPED_LEMON_WOOD);

        blockStateModelGenerator.createLogTexturePool(ModBlocks.AVOCADO_LOG).log(ModBlocks.AVOCADO_LOG).wood(ModBlocks.AVOCADO_WOOD);
        blockStateModelGenerator.createLogTexturePool(ModBlocks.STRIPPED_AVOCADO_LOG).log(ModBlocks.STRIPPED_AVOCADO_LOG).wood(ModBlocks.STRIPPED_AVOCADO_WOOD);
        blockStateModelGenerator.createLogTexturePool(ModBlocks.NETHER_HAY).log(ModBlocks.NETHER_HAY);

        BlockStateModelGenerator.BlockTexturePool avocadoPlanksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.AVOCADO_PLANKS);
        avocadoPlanksPool.slab(ModBlocks.AVOCADO_SLAB);
        avocadoPlanksPool.fenceGate(ModBlocks.AVOCADO_FENCE_GATE);
        avocadoPlanksPool.button(ModBlocks.AVOCADO_BUTTON);
        avocadoPlanksPool.pressurePlate(ModBlocks.AVOCADO_PRESSURE_PLATE);
        avocadoPlanksPool.fence(ModBlocks.AVOCADO_FENCE);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.AVOCADO_TRAPDOOR);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AVOCADO_LEAVES);
        generateSapling(blockStateModelGenerator, ModBlocks.AVOCADO_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);

        generateFruitLeaves(blockStateModelGenerator, ModBlocks.LEMON_FRUIT_LEAVES);
        generateFruitLeaves(blockStateModelGenerator, ModBlocks.AVOCADO_FRUIT_LEAVES);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModBlocks.WORMWOOD, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.AVOCADO_DOOR_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.LEMON_DOOR_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.MEAT_PIZZA_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.MEAT_PIZZA_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGAN_PIZZA_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.VEGAN_PIZZA_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNGUS_PIZZA_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.FUNGUS_PIZZA_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.FERTILIZER, Models.GENERATED);
        itemModelGenerator.register(ModItems.COMPOST, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.CABBAGE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHILLI_PEPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUCUMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.LETTUCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HARVEST_SICKLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CHOCOLATE_ICE_CREAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICE_CREAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRY_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACKCURRANT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.BREAD_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BORSCH, Models.GENERATED);
        itemModelGenerator.register(ModItems.BROTH, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_SOUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGAN_BARBECUE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_VEGAN_BARBECUE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_BARBECUE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_BEEF_BARBECUE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALT, Models.GENERATED);
        itemModelGenerator.register(ModItems.OIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAYONNAISE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KETCHUP, Models.GENERATED);
        itemModelGenerator.register(ModItems.GUACAMOLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WAFFLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_BAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ONION, Models.GENERATED);
        itemModelGenerator.register(ModItems.PICKLE_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON_PIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_PIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_PIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOT_SPICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PICKLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.JACK_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON_CANDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALT_POPCORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_POPCORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE_POPCORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.POPCORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHEESE, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVASH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHAWARMA, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.VEGETABLE_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_BEEF_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.AVOCADO, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOMATO_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUCUMBER_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.CHOCOLATE_CAKE_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.HONEY_CAKE_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.RICE_PANICLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RICE_BOWL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALMON_FILLET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALMON_MAKI, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALMON_NIGIRI, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALMON_URAMAKI, Models.GENERATED);
        itemModelGenerator.register(ModItems.ONIGIRI, Models.GENERATED);
        itemModelGenerator.register(ModItems.NORI, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAUERKRAUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALO, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUNFLOWER_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROASTED_SUNFLOWER_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BOILED_CORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHER_WHEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHER_BUN, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOGLIN_MEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_HOGLIN_MEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.FUNGUS_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_CHICKEN_LEG, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHICKEN_LEG, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_MUTTON_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUTTON_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_SQUID_RING, Models.GENERATED);
        itemModelGenerator.register(ModItems.SQUID_RING, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEELED_SQUID_TENTACLES, Models.GENERATED);
        itemModelGenerator.register(ModItems.SQUID_TENTAClES, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHORUS_FRUIT_IN_CHOCOLATE_ON_A_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHORUS_FRUITS_WITH_ENDER_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_INFECTED_ONION, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_JAM_STEW_WITH_CHORUS_FRUIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_PIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_CHORUS_FRUIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BREAD_SLICE_WITH_HONEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUTTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CROISSANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_BREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAPE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOGLIN_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHER_BUN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRAPE_SAPLING, Models.GENERATED);
    }
    private void generateCrate(BlockStateModelGenerator generator, Block generatedBlock, String path){
        generator.registerCubeWithCustomTextures(
                generatedBlock,
                generatedBlock,
                (block, otherTextureSource) -> new TextureMap()
                        .put(TextureKey.DOWN, Identifier.of(BorukvaFood.MOD_ID, "block/crate_bottom"))
                        .put(TextureKey.UP, Identifier.of(BorukvaFood.MOD_ID, "block/" + path + "_top"))
                        .put(TextureKey.SIDE, Identifier.of(BorukvaFood.MOD_ID, "block/" + path + "_side"))
                        .put(TextureKey.PARTICLE, Identifier.of(BorukvaFood.MOD_ID, "block/" + path + "_side"))
        );
    }
    private void generateFruitLeaves(BlockStateModelGenerator generator, Block leavesBlock){
        Identifier FruitLeavesFalse = TexturedModel.CUBE_ALL.upload(leavesBlock, generator.modelCollector);
        Identifier FruitLeavesTrue = generator.createSubModel(leavesBlock, "_has_fruit", Models.CUBE_ALL, TextureMap::all);
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(leavesBlock)
                .with(BlockStateModelGenerator.createBooleanModelMap(LemonFruitLeaves.HAS_FRUIT, new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(FruitLeavesTrue)).build()), new WeightedVariant(Pool.<ModelVariant>builder().add(new ModelVariant(FruitLeavesFalse)).build()))));
    }
    private void generateSapling(BlockStateModelGenerator generator, Block block, BlockStateModelGenerator.CrossType crossType) {
        generator.registerItemModel(block.asItem(), crossType.registerItemModel(generator, block));
        generator.registerTintableCrossBlockState(block, crossType);
    }

    private void generateUvLockedStairs(BlockStateModelGenerator generator, Block stairs) {
        Identifier straight = ModelIds.getBlockModelId(stairs);
        Identifier inner = ModelIds.getBlockSubModelId(stairs, "_inner");
        Identifier outer = ModelIds.getBlockSubModelId(stairs, "_outer");

        VariantsBlockStateSupplier supplier = VariantsBlockStateSupplier.create(stairs);
        BlockStateVariantMap map = BlockStateVariantMap.create(StairsBlock.HALF, StairsBlock.FACING, StairsBlock.SHAPE);

        Direction[] directions = new Direction[]{Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH};

        for (BlockHalf half : BlockHalf.values()) {
            for (Direction facing : directions) {
                int base = switch (facing) {
                    case EAST -> 0;
                    case SOUTH -> 90;
                    case WEST -> 180;
                    case NORTH -> 270;
                    default -> 0;
                };
                for (StairShape shape : StairShape.values()) {
                    Identifier model = switch (shape) {
                        case STRAIGHT -> straight;
                        case INNER_LEFT, INNER_RIGHT -> inner;
                        case OUTER_LEFT, OUTER_RIGHT -> outer;
                    };
                    int x = half == BlockHalf.TOP ? 180 : 0;
                    int y = base;
                    if (half == BlockHalf.BOTTOM && (shape == StairShape.INNER_LEFT || shape == StairShape.OUTER_LEFT)) {
                        y = (base + 270) % 360;
                    } else if (half == BlockHalf.TOP && (shape == StairShape.INNER_RIGHT || shape == StairShape.OUTER_RIGHT)) {
                        y = (base + 90) % 360;
                    }

                    BlockStateVariant variant = BlockStateVariant.create().put(VariantSettings.MODEL, model).uvlock();
                    if (x != 0) {
                        variant.put(VariantSettings.X, rotation(x));
                    }
                    if (y != 0) {
                        variant.put(VariantSettings.Y, rotation(y));
                    }
                    map.register(half, facing, shape, variant);
                }
            }
        }

        generator.blockStateCollector.accept(supplier.coordinate(map));
    }

    private VariantSettings.Rotation rotation(int degrees) {
        return switch (degrees) {
            case 0 -> VariantSettings.Rotation.R0;
            case 90 -> VariantSettings.Rotation.R90;
            case 180 -> VariantSettings.Rotation.R180;
            case 270 -> VariantSettings.Rotation.R270;
            default -> throw new IllegalArgumentException("Invalid rotation: " + degrees);
        };
    }

    private void registerFamily(BlockStateModelGenerator generator, BlockFamily family) {
        TexturedModel texturedModel = this.uniqueModels.getOrDefault(family.getBaseBlock(), TexturedModel.CUBE_ALL.get(family.getBaseBlock()));
        generator.new BlockTexturePool(texturedModel.getTextures()).base(family.getBaseBlock(), texturedModel.getModel()).family(family);
    }
}
