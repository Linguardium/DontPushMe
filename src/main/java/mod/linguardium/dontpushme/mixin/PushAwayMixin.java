package mod.linguardium.dontpushme.mixin;

import mod.linguardium.dontpushme.DontPushMePredicates;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public class PushAwayMixin {
    @Inject(at=@At(value="INVOKE",target="net/minecraft/entity/Entity.addVelocity(DDD)V", ordinal = 1), method="pushAwayFrom", locals = LocalCapture.CAPTURE_FAILHARD)
    private void DontPushMe$PushingMe(Entity entity, CallbackInfo info, double d, double e) {
        if (!DontPushMePredicates.canPush((Entity)(Object)this,entity)) {
            entity.setVelocity(entity.getVelocity().add(-d, 0, -e));
            entity.velocityDirty = true;
        }
    }
    @Inject(at=@At(value="INVOKE",target="net/minecraft/entity/Entity.addVelocity(DDD)V", ordinal=0), method="pushAwayFrom", locals = LocalCapture.CAPTURE_FAILHARD)
    private void DontPushMe$PushingYou(Entity entity, CallbackInfo info, double d, double e, double f, double g) {
        if (!DontPushMePredicates.canPush(entity,(Entity)(Object)this)) {
            ((Entity)(Object)this).setVelocity(((Entity)(Object)this).getVelocity().add(d, 0, e));
            ((Entity)(Object)this).velocityDirty = true;
        }
    }
}
