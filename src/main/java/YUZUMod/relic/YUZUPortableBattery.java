package YUZUMod.relic;

import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

public class YUZUPortableBattery extends CustomRelic {
    public static final String ID= ModHelper.makePath("PortableBattery");
    private static final Texture texture=TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture outline= TextureLoader.getTexture(ModHelper.makeRelicOutLinePath(ID));
    private static final RelicTier type=RelicTier.UNCOMMON;
    public YUZUPortableBattery() {
        super(ID, texture,outline,type, LandingSound.MAGICAL);
        this.counter=0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.counter++;
        if(this.counter>6){
            this.grayscale=true;
        }
    }

    @Override
    public void atTurnStart() {
        if(!this.grayscale){
            addToBot(new GainEnergyAction(1));
        }
    }
}
