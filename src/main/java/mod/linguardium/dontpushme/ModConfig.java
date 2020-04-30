package mod.linguardium.dontpushme;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config.Gui.Background("minecraft:textures/block/orange_shulker_box.png")
@Config(name="dontpushme")
public class ModConfig implements ConfigData {
    public boolean player_collision = true;
    public boolean animal_can_push = true;
    public boolean animal_is_pushed = true;
    public boolean hostile_can_push = true;
    public boolean hostile_is_pushed = true;
}
