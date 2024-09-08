package YUZUMod.cards.options;

import YUZUMod.cards.YUZUClimax;
import YUZUMod.cards.YUZUCustomCard;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUClimaxOption extends YUZUCustomCard {
    public static final String ID= ModHelper.makePath("ClimaxOption");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(YUZUClimax.ID);
    private static final int COST=-2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.SPECIAL;
    private int position;

    public YUZUClimaxOption(int position,int magicNum) {
        super(ID, NAME, IMG_PATH, COST, CARD_STRINGS.EXTENDED_DESCRIPTION[position], TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=magicNum;
        this.position=position;

    }

    @Override
    protected void upgradeMethod() {

    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void onChoseThisOption() {
        switch (this.position){
            case 0:
                addToTop(new DrawCardAction(this.magicNumber));
                break;
            case 1:
                addToTop(new GainEnergyAction(this.magicNumber));
                break;
            default:
                break;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new YUZUClimaxOption(this.position,this.baseMagicNumber);
    }
}
