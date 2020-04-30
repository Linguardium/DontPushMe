package mod.linguardium.dontpushme.mixin;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import mod.linguardium.dontpushme.DontPushMe;
import mod.linguardium.dontpushme.DontPushMePredicates;
import mod.linguardium.dontpushme.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class PlayerCollisionMixin extends Entity {
    public PlayerCollisionMixin() { super(null,null);  }
    @ModifyVariable(at=@At(value="STORE", ordinal=0),method="tickCramming")
    private List<Entity> dontpush$cramming(List<Entity> list) {
            return ((LivingEntity)(Object)this).world.getEntities(this, this.getBoundingBox(),
                    EntityPredicates.canBePushedBy((LivingEntity)(Object)this)
                    .and(DontPushMePredicates.canAnimalsCollide(this))
                    .and(DontPushMePredicates.canHostilesCollide(this))
                    .and(DontPushMePredicates.canPlayersCollide(this))
            );

    }
}
