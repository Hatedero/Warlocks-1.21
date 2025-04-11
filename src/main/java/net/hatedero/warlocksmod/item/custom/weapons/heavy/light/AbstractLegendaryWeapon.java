package net.hatedero.warlocksmod.item.custom.weapons.heavy.light;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

abstract class AbstractLegendaryWeapon extends SwordItem {
    public AbstractLegendaryWeapon(Tier tier, Properties properties, String t) {
        super(tier, properties);
        type = t;
    }

    private String type;
}
