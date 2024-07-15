package YUZUMod.relic;

import YUZUMod.action.YUZUChangeCriticalHitRateMaxAction;
import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class YUZUSightModifiedVersion extends CustomRelic {
    public static final String ID= ModHelper.makePath("SightModifiedVersion");
    private static final Texture texture=TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture outline= TextureLoader.getTexture(ModHelper.makeRelicOutLinePath(ID));
    private static final RelicTier type=RelicTier.COMMON;
    public YUZUSightModifiedVersion() {
        super(ID, texture,outline,type, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        addToBot(new YUZUChangeCriticalHitRateMaxAction(-1));
        this.grayscale=true;
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);
        this.grayscale=false;
    }
}
