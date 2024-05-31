package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.action.YUZUClearCriticalRateAction;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.power.YUZUCriticalHitPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUBriefSilence extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("BriefSilence");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeImgPath("card","default");
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUBriefSilence() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        upgradeBaseCost(2);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster != null && abstractMonster.getIntentBaseDmg() >= 0) {
            addToBot(new StunMonsterAction(abstractMonster,abstractPlayer));
        }
        addToBot(new YUZUClearCriticalRateAction());
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        addToBot(new RemoveSpecificPowerAction(abstractPlayer,abstractPlayer, YUZUCriticalHitPower.POWER_ID));
    }
}
