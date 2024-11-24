package net.hatedero.warlocksmod.dataattachments.doublejump;

import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import org.jetbrains.annotations.Nullable;
import net.minecraft.nbt.Tag;

public class DoubleJumpProvider implements IAttachmentSerializer<Tag, DoubleJump> {

    public DoubleJumpProvider(){}
    @Override
    public DoubleJump read(IAttachmentHolder iAttachmentHolder, Tag tag, HolderLookup.Provider provider) {
        return null;
    }

    @Nullable
    @Override
    public Tag write(DoubleJump doubleJump, HolderLookup.Provider provider) {
        return null;
    }
}
