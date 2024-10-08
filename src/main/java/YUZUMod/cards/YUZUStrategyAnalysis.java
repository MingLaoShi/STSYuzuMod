//package YUZUMod.cards;
//
//import YUZUMod.helper.ModHelper;
//import YUZUMod.character.YuzuCharacter;
//import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.powers.StrengthPower;
//import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
//
//public class YUZUStrategyAnalysis extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("StrategyAnalysis");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
//    private static final int COST=1;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.SKILL;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.SELF_AND_ENEMY;
//    private static final CardRarity RARITY=CardRarity.UNCOMMON;
//
//    public YUZUStrategyAnalysis() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.exhaust=true;
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.upgradeBaseCost(0);
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new RemoveAllBlockAction(abstractMonster,abstractPlayer));
//        for(AbstractPower p:abstractPlayer.powers){
//            if(p.type== AbstractPower.PowerType.DEBUFF){
//                addToBot(new RemoveSpecificPowerAction(abstractPlayer,abstractPlayer,p));
//            }
//        }
//    }
//
//    @Override
//    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        commonUse(abstractPlayer,abstractMonster);
//        addToBot(new RemoveSpecificPowerAction(abstractMonster,abstractPlayer, StrengthPower.POWER_ID));
//        addToBot(new LoseEnergyAction(EnergyPanel.getCurrentEnergy()));
//    }
//}
