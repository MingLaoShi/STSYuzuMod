package YUZUMod.ui;

import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

public class YUZUCriticalIcon extends AbstractCustomIcon {
    public static String NAME = ModHelper.makePath("CriticalIcon");
    private static Texture TEXTRUE = TextureLoader.getTexture(ModHelper.makeImgPath("UI", "criticalicon"));
    public YUZUCriticalIcon() {
        super(NAME, TEXTRUE);
    }


}
