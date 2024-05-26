package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.power.YUZUCriticalHitPower;
import baModDeveloper.power.YUZUVulturesEyePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUVulturesEye extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("VulturesEye");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeImgPath("card","default");
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.BASIC;

    public YUZUVulturesEye() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=1;

    }

    @Override
    protected void upgradeMethod() {
        upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(this.upgraded){
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUVulturesEyePower(abstractPlayer,1)));
        }else{
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUVulturesEyePower(abstractPlayer,2)));
        }
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster, int masterNum) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUCriticalHitPower(abstractPlayer,this.magicNumber)));
    }
}
