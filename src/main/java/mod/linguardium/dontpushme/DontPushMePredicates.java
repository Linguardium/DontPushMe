package mod.linguardium.dontpushme;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class DontPushMePredicates {
    public static Predicate<Entity> canPlayersCollide(Entity entity) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        Predicate<Entity> pred = entity2 -> true;
        if (entity instanceof PlayerEntity) {
            pred= (entity2) -> (config.player_collision || !(entity2 instanceof PlayerEntity));
        }
        return pred;
    }
    public static Predicate<Entity> canAnimalsCollide(Entity entity) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        Predicate<Entity> pred = entity2 -> true;
        if (entity instanceof PlayerEntity) {
            pred= (entity2) -> (config.animal_is_pushed || !(entity2 instanceof AnimalEntity));
        }else if(entity instanceof AnimalEntity) {
            pred = (entity2) -> (config.animal_can_push || !(entity2 instanceof PlayerEntity));
        }
        return pred;
    }
    public static Predicate<Entity> canHostilesCollide(Entity entity) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        Predicate<Entity> pred = entity2 -> true;
        if (entity instanceof PlayerEntity) {
            pred = (entity2) -> (config.hostile_is_pushed || !(entity2 instanceof HostileEntity));
        }
        if (entity instanceof HostileEntity) {
            pred = (entity2) -> (config.hostile_can_push || !(entity2 instanceof PlayerEntity));
        }
        return pred;
    }
    public static boolean canPush(Entity self, Entity target) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        if (self instanceof PlayerEntity) {
            return (config.player_collision || !(target instanceof PlayerEntity && target.isSneaking())) &&
                   (config.animal_is_pushed || !(target instanceof AnimalEntity)) &&
                   (config.hostile_is_pushed || !(target instanceof HostileEntity));
        }else if (self instanceof AnimalEntity) {
            return (config.animal_can_push || !(target instanceof PlayerEntity));
        }else if (self instanceof HostileEntity) {
            return (config.hostile_can_push || !(target instanceof PlayerEntity));
        }
        return true;
    }
}
