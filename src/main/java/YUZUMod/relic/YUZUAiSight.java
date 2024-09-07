package YUZUMod.relic;

import YUZUMod.action.YUZUApplyCriticalRateAction;
import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUAiSight extends CustomRelic {
    public static final String ID= ModHelper.makePath("AiSight");
    private static final Texture texture=TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture outline= TextureLoader.getTexture(ModHelper.makeRelicOutLinePath(ID));
    private static final RelicTier type=RelicTier.BOSS;
    public YUZUAiSight() {
        super(ID, texture,outline,type, LandingSound.MAGICAL);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(YUZUSight.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(YUZUSight.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    return;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(YUZUSight.ID);
    }

    @Override
    public void atTurnStart() {
        addToBot(new YUZUApplyCriticalRateAction(6));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
