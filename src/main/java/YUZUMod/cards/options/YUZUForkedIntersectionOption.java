package YUZUMod.cards.options;

import YUZUMod.cards.YUZUCustomCard;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class YUZUForkedIntersectionOption extends YUZUCustomCard {
    public static final String ID= ModHelper.makePath("ForkedIntersectionOption");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=-2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.POWER;
    private static final CardColor COLOR= CardColor.COLORLESS;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.SPECIAL;
    private int position;

    public YUZUForkedIntersectionOption(int position) {
        super(ID, NAME, IMG_PATH, COST, CARD_STRINGS.EXTENDED_DESCRIPTION[position], TYPE, COLOR, RARITY, TARGET);
        this.position=position;
        switch (position){
            case 0:
                this.baseMagicNumber=this.magicNumber=1;
                break;
            case 1:
                this.baseMagicNumber=this.magicNumber=1;
                break;
            case 2:
                this.baseBlock=this.block=7;
                break;
            case 3:
                this.baseMagicNumber=this.magicNumber=1;
                break;
        }
        this.textureImg=ModHelper.makeImgPath("cards","ForkedIntersectionOption"+position);
//        this.setPortraitTextures(ModHelper.makeImgPath("cards","ForkedIntersectionOption"+position),
//               ModHelper.makeImgPath("cards","ForkedIntersectionOption"+position+"_p"));
        this.loadCardImage(this.textureImg);
    }

    @Override
    protected void upgradeMethod() {

    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }


    @Override
    public void onChoseThisOption() {
        super.onChoseThisOption();
        AbstractPlayer p=AbstractDungeon.player;
        switch (this.position){
            case 0:
                addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber)));
                break;
            case 1:
                addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,this.magicNumber)));
                break;
            case 2:
                addToBot(new GainBlockAction(p,this.block));
                break;
            case 3:
                addToBot(new DrawCardAction(this.magicNumber));
                break;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new YUZUForkedIntersectionOption(this.position);
    }
}
