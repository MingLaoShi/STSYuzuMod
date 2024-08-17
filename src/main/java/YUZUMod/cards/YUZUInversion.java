//package YUZUMod.cards;
//
//import YUZUMod.helper.ModHelper;
//import YUZUMod.action.YUZUInversionAction;
//import YUZUMod.character.YuzuCharacter;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//public class YUZUInversion extends YUZUCustomCard{
//    public static final String ID= ModHelper.makePath("Inversion");
//    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME=CARD_STRINGS.NAME;
//    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
//    private static final int COST=1;
//    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE=CardType.SKILL;
//    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
//    private static final CardTarget TARGET=CardTarget.NONE;
//    private static final CardRarity RARITY=CardRarity.UNCOMMON;
//
//    public YUZUInversion() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.baseMagicNumber=this.magicNumber=1;
//    }
//
//    @Override
//    protected void upgradeMethod() {
//        this.upgradeBaseCost(0);
//    }
//
//    @Override
//    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        addToBot(new DrawCardAction(this.magicNumber));
//        addToBot(new YUZUInversionAction());
//    }
//
//}
