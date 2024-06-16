package idkhi.tradecraft.blockentity;

import idkhi.tradecraft.TradeCraft;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class CustomEnderChestBlockEntity extends BlockEntity {

    public CustomEnderChestBlockEntity(BlockPos pos, BlockState state) {
        super(TradeCraft.CUSTOM_ENDER_CHEST_BLOCK_ENTITY, pos, state);
    }

    public static void register() {
        Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("tradecraft", "custom_ender_chest"),
                BlockEntityType.Builder.create(CustomEnderChestBlockEntity::new, TradeCraft.CUSTOM_ENDER_CHEST_BLOCK).build(null)
        );
    }
}
