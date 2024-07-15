package YUZUMod.relic;

import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZURainbowCat extends CustomRelic {
    public static final String ID= ModHelper.makePath("RainbowCat");
    private static final Texture texture=TextureLoader.getTexture(ModHelper.makeRelicImagePath(ID));
    private static final Texture outline= TextureLoader.getTexture(ModHelper.makeRelicOutLinePath(ID));
    private static final RelicTier type=RelicTier.SHOP;
    public YUZURainbowCat() {
        super(ID, texture,outline,type, LandingSound.MAGICAL);
        this.counter=0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        this.counter=0;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        this.counter++;
    }

    @Override
    public void onVictory() {
        this.counter=0;
        this.grayscale=false;
    }

    @Override
    public void onPlayerEndTurn() {
        if(!this.grayscale&&this.counter>=15){
            addToBot(new SkipEnemiesTurnAction());
            this.grayscale=true;
        }
    }
}
