package com.opryshok.block.food;

import com.opryshok.BorukvaFood;
import com.opryshok.item.KnifeTool;
import com.opryshok.item.ModItems;
import com.opryshok.utils.BorukvaFoodUtil;
import com.opryshok.utils.ModProperties;
import com.opryshok.utils.TransparentBlocks.TransparentFlatTripWire;
import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.BlockBoundAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;

public class VeganPizza extends Block implements TransparentFlatTripWire, FactoryBlock {

    public VeganPizza(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(getDefaultState().with(ModProperties.SLICES, 0));
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return new Model(initialBlockState);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ModProperties.SLICES);
        super.appendProperties(builder);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack stack = player.getMainHandStack();
        boolean canCut = false;

        if (stack.getItem() instanceof KnifeTool) {
            canCut = true;
        }

        if (!canCut && FabricLoader.getInstance().isModLoaded("farmersdelight")) {
            TagKey<Item> fdKnives = TagKey.of(net.minecraft.registry.RegistryKeys.ITEM,
                    Identifier.of("farmersdelight", "tools/knives"));
            if (stack.isIn(fdKnives)) canCut = true;
        }

        if (canCut) {
            int i = state.get(ModProperties.SLICES);

            ItemScatterer.spawn(world, player.getX(), player.getY(), player.getZ(),
                    new ItemStack(this.getSlice(), 1));

            if (i == 7) {
                BorukvaFoodUtil.ledgerMixinInvoke();
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            } else {
                world.setBlockState(pos, state.with(ModProperties.SLICES, i + 1), 3);
                BorukvaFoodUtil.ledgerMixinInvoke();
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolidBlock(world, pos.down());
    }

    public Item getSlice() {
        return ModItems.VEGAN_PIZZA_SLICE;
    }

    public static class Model extends BlockModel {
        public static final ArrayList<ItemStack> MODEL = new ArrayList<>();

        static {
            for (int i = 0; i <= 7; i++) {
                MODEL.add(ItemDisplayElementUtil.getModel(Identifier.of(BorukvaFood.MOD_ID, "block/vegan_pizza")
                        .withSuffixedPath("_slice" + i)));
            }
        }
        protected ItemDisplayElement pizza;
        protected Model(BlockState state) {
            init(state);
        }

        protected void init(BlockState state) {
            this.pizza = ItemDisplayElementUtil.createSimple(MODEL.get(state.get(ModProperties.SLICES)));
            this.pizza.setScale(new Vector3f(1f));
            this.addElement(this.pizza);
        }

        protected void updateItem(BlockState state) {
            this.pizza.setItem(MODEL.get(state.get(ModProperties.SLICES)));
        }

        @Override
        public void notifyUpdate(HolderAttachment.UpdateType updateType) {
            if (updateType == BlockBoundAttachment.BLOCK_STATE_UPDATE) {
                updateItem(this.blockState());
                this.tick();
            }
            super.notifyUpdate(updateType);
        }
    }
}
