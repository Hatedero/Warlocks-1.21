package net.hatedero.warlocksmod.item.custom.weapons.heavy.light;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class OldDragonSlayerSpearItem extends SwordItem {
    Holder<MobEffect>[] associated;

    public OldDragonSlayerSpearItem(Tier tier, Properties properties, Holder<MobEffect>[] asso) {
        super(tier, properties);
        this.associated = asso;
    }

    public void applyAssociated(LivingEntity e){
        for(Holder<MobEffect> me : associated) {
            e.addEffect(new MobEffectInstance((Holder<MobEffect>) me, 200, 0, false, false));
        }
    }
}
