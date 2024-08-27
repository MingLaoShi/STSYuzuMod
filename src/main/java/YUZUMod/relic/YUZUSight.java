package YUZUMod.relic;

import YUZUMod.action.YUZUApplyCriticalRateAction;
import YUZUMod.helper.ModHelper;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class YUZUSight extends CustomRelic {
    public static final String ID= ModHelper.makePath("Sight");
    private static final Texture texture= ImageMaster.loadImage(ModHelper.makeImgPath("relic","Sight"));
    private static final Texture outline=ImageMaster.loadImage(ModHelper.makeImgPath("relic","Sight_p"));
    private static final RelicTier type=RelicTier.STARTER;
    public YUZUSight() {
        super(ID, texture,outline,type, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        addToBot(new YUZUApplyCriticalRateAction(6));
        this.grayscale=true;

    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale=false;
    }
}
