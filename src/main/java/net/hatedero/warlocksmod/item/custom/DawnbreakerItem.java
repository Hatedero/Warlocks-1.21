package net.hatedero.warlocksmod.item.custom;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class DawnbreakerItem extends AbstractLegendaryWeapon {

    public DawnbreakerItem(Tier tier, Properties properties) {
        super(tier, properties, "light_greatsword");
    }

}
