//package net.hatedero.warlocksmod.dash;
//
//import net.hatedero.warlocksmod.WarlocksMod;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.resources.ResourceLocation;
//import net.neoforged.neoforge.capabilities.*;
//import net.neoforged.neoforge.common.util.INBTSerializable;
//import net.neoforged.neoforge.common.util.Lazy;
//import net.neoforged.neoforge.energy.EnergyStorage;
//import org.jetbrains.annotations.Nullable;
//import org.jetbrains.annotations.UnknownNullability;
//
//import java.util.List;
//
//public class PlayerDashProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
//    public static EntityCapability<EnergyStorage,PlayerDash> PLAYER_COOLDOWN = EntityCapability.create(ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "player_cooldown"), EnergyStorage.class, PlayerDash.class);
//
//    private PlayerDash nbDash = null;
//    private final Lazy<PlayerDash> optional = Lazy.of(this::createPlayerDash);
//
//    private PlayerDash createPlayerDash() {
//        if(this.nbDash==null){
//            this.nbDash = new PlayerDash();
//        }
//
//        return this.nbDash;
//    }
//
//    @Nullable
//    @Override
//    public Object getCapability(Object o, Object o2) {
//
//        return null;
//    }
//
////    @Override
////    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
////        CompoundTag nbt = new CompoundTag();
////        createPlayerDash().saveNBTData(nbt);
////        return nbt;
////    }
////
////    @Override
////    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
////        createPlayerDash().loadNBTData(compoundTag);
////    }
//}
