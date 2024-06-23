package baModDeveloper.power;

import baModDeveloper.action.YUZUBurningDamageAction;
import baModDeveloper.helper.ModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class YUZUBurningPower extends AbstractPower {
    public static final String POWER_ID= ModHelper.makePath("BurningPower");
    private static final AbstractPower.PowerType TYPE= AbstractPower.PowerType.DEBUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","default84");
    private static final String IMG_32=ModHelper.makeImgPath("power","default32");
    private int damage;
    private AbstractCreature source;
    public static int BASEDAMAGE=4;
    public YUZUBurningPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name=NAME;
        this.ID=POWER_ID;
        this.type=TYPE;
        this.owner=owner;
        this.source=source;
        this.region128=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84),0,0,84,84);
        this.region48=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32),0,0,32,32);
        this.amount=amount;
        this.damage=4;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.damage= (int) (BASEDAMAGE*Math.pow(2, (this.amount / 10) ));
        this.description=String.format(DESCRIPTIONS[0],this.damage);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new YUZUBurningDamageAction(this.owner, this.owner, this.damage, AbstractGameAction.AttackEffect.FIRE));
        }
    }
}
