package YUZUMod.relic;

import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import YUZUMod.inter.YUZUTriggerOnCriticalHitInterface;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUEnergyGatheringBomb extends CustomRelic implements YUZUTriggerOnCriticalHitInterface {
    public static final String ID= ModHelper.makePath("EnergyGatheringBomb");
    private static final Texture texture=TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture outline= TextureLoader.getTexture(ModHelper.makeRelicOutLinePath(ID));
    private static final RelicTier type=RelicTier.RARE;
    public YUZUEnergyGatheringBomb() {
        super(ID, texture,outline,type, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void triggerOnCriticalHit() {
        addToBot(new GainBlockAction(AbstractDungeon.player,2));
    }
}
