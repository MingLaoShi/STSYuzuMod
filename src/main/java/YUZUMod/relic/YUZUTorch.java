package YUZUMod.relic;

import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

public class YUZUTorch extends CustomRelic {
    public static final String ID= ModHelper.makePath("Torch");
    private static final Texture texture= TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture outline= TextureLoader.getTexture(ModHelper.makeRelicOutLinePath(ID));
    private static final RelicTier type=RelicTier.BOSS;
    private int actNum=0;
    public YUZUTorch() {
        super(ID, texture,outline,type, LandingSound.MAGICAL);
        this.grayscale=true;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if(AbstractDungeon.actNum!=this.actNum){
            this.actNum=AbstractDungeon.actNum;
            this.grayscale=true;
        }
        if(room instanceof RestRoom){
            this.grayscale=false;
        }
    }

    @Override
    public void atTurnStart() {
        if(!this.grayscale){
            addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void onEquip() {
        this.actNum= AbstractDungeon.actNum;
    }
}
