package YUZUMod.cards;

import YUZUMod.cards.options.YUZUClimaxOption;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class YUZUClimax extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Climax");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=0;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUClimax() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=0;
    }

    @Override
    protected void upgradeMethod() {
//        this.upgradeBaseCost(0);
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
        this.isInnate=true;
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> options=new ArrayList<>();
        for(int j=0;j<2;j++){
            options.add(new YUZUClimaxOption(j,this.magicNumber));
        }
        addToBot(new ChooseOneAction(options));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        this.baseMagicNumber=this.magicNumber=0;
    }

    @Override
    public void triggerWhenDrawn() {
        this.baseMagicNumber++;
        this.magicNumber=this.baseMagicNumber;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if(this.upgraded){
            this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION+CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }else{
            this.rawDescription=CARD_STRINGS.DESCRIPTION+CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(this.upgraded){
            this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION+CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }else{
            this.rawDescription=CARD_STRINGS.DESCRIPTION+CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        if(this.upgraded){
            this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION+CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }else{
            this.rawDescription=CARD_STRINGS.DESCRIPTION+CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }
}
